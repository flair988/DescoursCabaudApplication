import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IForwarderBooking } from '@/shared/model/forwarder-booking.model';
import ForwarderBookingService from './forwarder-booking.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ForwarderBooking',
  setup() {
    const { t: t$ } = useI18n();
    const forwarderBookingService = inject('forwarderBookingService', () => new ForwarderBookingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const forwarderBookings: Ref<IForwarderBooking[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveForwarderBookings = async () => {
      isFetching.value = true;
      try {
        const res = await forwarderBookingService().retrieve();
        forwarderBookings.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveForwarderBookings();
    };

    onMounted(async () => {
      await retrieveForwarderBookings();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IForwarderBooking) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeForwarderBooking = async () => {
      try {
        await forwarderBookingService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.forwarderBooking.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveForwarderBookings();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      forwarderBookings,
      handleSyncList,
      isFetching,
      retrieveForwarderBookings,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeForwarderBooking,
      t$,
    };
  },
});
