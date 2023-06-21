import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IPI, PI } from '@/shared/model/pi.model';
import PIService from './pi.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PIUpdate',
  setup() {
    const pIService = inject('pIService', () => new PIService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pI: Ref<IPI> = ref(new PI());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePI = async pIId => {
      try {
        const res = await pIService().find(pIId);
        pI.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pIId) {
      retrievePI(route.params.pIId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      poNumber: {},
      category: {},
      client: {},
      orderDate: {},
      requestedEndOfProdDate: {},
      countryOfOrigin: {},
      countryOfFinalDestination: {},
      consignee: {},
      carriageBy: {},
      termsOfDelivery: {},
      termsOfPayment: {},
      currency: {},
      remarks: {},
      kingdeeId: {},
      portOfDischarge: {},
      portOfLoading: {},
      docStatus: {},
    };
    const v$ = useVuelidate(validationRules, pI as any);
    v$.value.$validate();

    return {
      pIService,
      alertService,
      pI,
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
      if (this.pI.id) {
        this.pIService()
          .update(this.pI)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.pI.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.pIService()
          .create(this.pI)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.pI.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
