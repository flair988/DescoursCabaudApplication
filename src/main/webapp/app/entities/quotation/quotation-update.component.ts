import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IQuotation, Quotation } from '@/shared/model/quotation.model';
import QuotationService from './quotation.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QuotationUpdate',
  setup() {
    const quotationService = inject('quotationService', () => new QuotationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const quotation: Ref<IQuotation> = ref(new Quotation());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQuotation = async quotationId => {
      try {
        const res = await quotationService().find(quotationId);
        quotation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.quotationId) {
      retrieveQuotation(route.params.quotationId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      quotationDate: {},
      inquiryDocNumber: {},
      docNo: {},
      docStatus: {},
      supplier: {},
      kingdeeId: {},
    };
    const v$ = useVuelidate(validationRules, quotation as any);
    v$.value.$validate();

    return {
      quotationService,
      alertService,
      quotation,
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
      if (this.quotation.id) {
        this.quotationService()
          .update(this.quotation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.quotation.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.quotationService()
          .create(this.quotation)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.quotation.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
