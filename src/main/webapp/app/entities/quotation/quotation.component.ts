import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IQuotation } from '@/shared/model/quotation.model';
import QuotationService from './quotation.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Quotation',
  setup() {
    const { t: t$ } = useI18n();
    const quotationService = inject('quotationService', () => new QuotationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const quotations: Ref<IQuotation[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQuotations = async () => {
      isFetching.value = true;
      try {
        const res = await quotationService().retrieve();
        quotations.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveQuotations();
    };

    onMounted(async () => {
      await retrieveQuotations();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQuotation) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQuotation = async () => {
      try {
        await quotationService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.quotation.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQuotations();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      quotations,
      handleSyncList,
      isFetching,
      retrieveQuotations,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQuotation,
      t$,
    };
  },
});
