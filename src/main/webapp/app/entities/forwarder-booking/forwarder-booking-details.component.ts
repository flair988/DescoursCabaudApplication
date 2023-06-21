import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IForwarderBooking } from '@/shared/model/forwarder-booking.model';
import ForwarderBookingService from './forwarder-booking.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ForwarderBookingDetails',
  setup() {
    const forwarderBookingService = inject('forwarderBookingService', () => new ForwarderBookingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const forwarderBooking: Ref<IForwarderBooking> = ref({});

    const retrieveForwarderBooking = async forwarderBookingId => {
      try {
        const res = await forwarderBookingService().find(forwarderBookingId);
        forwarderBooking.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.forwarderBookingId) {
      retrieveForwarderBooking(route.params.forwarderBookingId);
    }

    return {
      alertService,
      forwarderBooking,

      previousState,
      t$: useI18n().t,
    };
  },
});
