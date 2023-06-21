import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { ISalesDelivery } from '@/shared/model/sales-delivery.model';
import SalesDeliveryService from './sales-delivery.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SalesDelivery',
  setup() {
    const { t: t$ } = useI18n();
    const salesDeliveryService = inject('salesDeliveryService', () => new SalesDeliveryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const salesDeliveries: Ref<ISalesDelivery[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSalesDeliverys = async () => {
      isFetching.value = true;
      try {
        const res = await salesDeliveryService().retrieve();
        salesDeliveries.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSalesDeliverys();
    };

    onMounted(async () => {
      await retrieveSalesDeliverys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISalesDelivery) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSalesDelivery = async () => {
      try {
        await salesDeliveryService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.salesDelivery.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSalesDeliverys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      salesDeliveries,
      handleSyncList,
      isFetching,
      retrieveSalesDeliverys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSalesDelivery,
      t$,
    };
  },
});
