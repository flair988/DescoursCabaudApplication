import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IPurchaseReturn } from '@/shared/model/purchase-return.model';
import PurchaseReturnService from './purchase-return.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PurchaseReturn',
  setup() {
    const { t: t$ } = useI18n();
    const purchaseReturnService = inject('purchaseReturnService', () => new PurchaseReturnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const purchaseReturns: Ref<IPurchaseReturn[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePurchaseReturns = async () => {
      isFetching.value = true;
      try {
        const res = await purchaseReturnService().retrieve();
        purchaseReturns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePurchaseReturns();
    };

    onMounted(async () => {
      await retrievePurchaseReturns();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPurchaseReturn) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePurchaseReturn = async () => {
      try {
        await purchaseReturnService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.purchaseReturn.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePurchaseReturns();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      purchaseReturns,
      handleSyncList,
      isFetching,
      retrievePurchaseReturns,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePurchaseReturn,
      t$,
    };
  },
});
