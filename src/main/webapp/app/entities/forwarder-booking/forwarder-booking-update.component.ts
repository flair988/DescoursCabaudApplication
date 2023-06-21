import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IForwarderBooking, ForwarderBooking } from '@/shared/model/forwarder-booking.model';
import ForwarderBookingService from './forwarder-booking.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ForwarderBookingUpdate',
  setup() {
    const forwarderBookingService = inject('forwarderBookingService', () => new ForwarderBookingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const forwarderBooking: Ref<IForwarderBooking> = ref(new ForwarderBooking());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      kingdeeId: {},
      customer: {},
      orderDate: {},
      forwarder: {},
      totalQty: {},
      loadingPort: {},
      dischargePort: {},
      containerType: {},
      containerSize: {},
      containerNumber: {},
      supplier: {},
      supplierEmail: {},
      eta: {},
      etd: {},
      transportMode: {},
      numberOfCartons: {},
      numberOfRef: {},
      totalVolume: {},
      totalWeight: {},
      remark: {},
      client: {},
      kingdeeUniqueId: {},
    };
    const v$ = useVuelidate(validationRules, forwarderBooking as any);
    v$.value.$validate();

    return {
      forwarderBookingService,
      alertService,
      forwarderBooking,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.forwarderBooking.id) {
        this.forwarderBookingService()
          .update(this.forwarderBooking)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.forwarderBooking.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.forwarderBookingService()
          .create(this.forwarderBooking)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.forwarderBooking.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
