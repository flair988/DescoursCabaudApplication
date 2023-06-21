import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { ICommercialInvoice } from '@/shared/model/commercial-invoice.model';
import CommercialInvoiceService from './commercial-invoice.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommercialInvoice',
  setup() {
    const { t: t$ } = useI18n();
    const commercialInvoiceService = inject('commercialInvoiceService', () => new CommercialInvoiceService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const commercialInvoices: Ref<ICommercialInvoice[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveCommercialInvoices = async () => {
      isFetching.value = true;
      try {
        const res = await commercialInvoiceService().retrieve();
        commercialInvoices.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveCommercialInvoices();
    };

    onMounted(async () => {
      await retrieveCommercialInvoices();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ICommercialInvoice) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeCommercialInvoice = async () => {
      try {
        await commercialInvoiceService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.commercialInvoice.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveCommercialInvoices();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      commercialInvoices,
      handleSyncList,
      isFetching,
      retrieveCommercialInvoices,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeCommercialInvoice,
      t$,
    };
  },
});
