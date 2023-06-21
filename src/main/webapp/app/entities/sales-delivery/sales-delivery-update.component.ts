import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { ISalesDelivery, SalesDelivery } from '@/shared/model/sales-delivery.model';
import SalesDeliveryService from './sales-delivery.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SalesDeliveryUpdate',
  setup() {
    const salesDeliveryService = inject('salesDeliveryService', () => new SalesDeliveryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const salesDelivery: Ref<ISalesDelivery> = ref(new SalesDelivery());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSalesDelivery = async salesDeliveryId => {
      try {
        const res = await salesDeliveryService().find(salesDeliveryId);
        salesDelivery.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.salesDeliveryId) {
      retrieveSalesDelivery(route.params.salesDeliveryId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      kingdeeId: {},
      customer: {},
      orderDate: {},
      loadingPort: {},
      dischargePort: {},
      transportMode: {},
      incoterm: {},
      forwarder: {},
      eta: {},
      etd: {},
      containerType: {},
      containerSize: {},
      remark: {},
      kingdeeUniqueId: {},
      docStatus: {},
      cateGory: {},
    };
    const v$ = useVuelidate(validationRules, salesDelivery as any);
    v$.value.$validate();

    return {
      salesDeliveryService,
      alertService,
      salesDelivery,
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
      if (this.salesDelivery.id) {
        this.salesDeliveryService()
          .update(this.salesDelivery)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.salesDelivery.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.salesDeliveryService()
          .create(this.salesDelivery)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.salesDelivery.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
