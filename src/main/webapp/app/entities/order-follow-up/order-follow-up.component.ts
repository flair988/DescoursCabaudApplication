import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IOrderFollowUp } from '@/shared/model/order-follow-up.model';
import OrderFollowUpService from './order-follow-up.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OrderFollowUp',
  setup() {
    const { t: t$ } = useI18n();
    const orderFollowUpService = inject('orderFollowUpService', () => new OrderFollowUpService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const orderFollowUps: Ref<IOrderFollowUp[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOrderFollowUps = async () => {
      isFetching.value = true;
      try {
        const res = await orderFollowUpService().retrieve();
        orderFollowUps.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOrderFollowUps();
    };

    onMounted(async () => {
      await retrieveOrderFollowUps();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOrderFollowUp) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOrderFollowUp = async () => {
      try {
        await orderFollowUpService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.orderFollowUp.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOrderFollowUps();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      orderFollowUps,
      handleSyncList,
      isFetching,
      retrieveOrderFollowUps,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOrderFollowUp,
      t$,
    };
  },
});
