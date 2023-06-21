import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { ICommercialInvoice, CommercialInvoice } from '@/shared/model/commercial-invoice.model';
import CommercialInvoiceService from './commercial-invoice.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommercialInvoiceUpdate',
  setup() {
    const commercialInvoiceService = inject('commercialInvoiceService', () => new CommercialInvoiceService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const commercialInvoice: Ref<ICommercialInvoice> = ref(new CommercialInvoice());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCommercialInvoice = async commercialInvoiceId => {
      try {
        const res = await commercialInvoiceService().find(commercialInvoiceId);
        commercialInvoice.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.commercialInvoiceId) {
      retrieveCommercialInvoice(route.params.commercialInvoiceId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      kingdeeId: {},
      date: {},
      client: {},
      cateGory: {},
      totalPrice: {},
      currency: {},
      remarks: {},
      clientId: {},
      docStatus: {},
    };
    const v$ = useVuelidate(validationRules, commercialInvoice as any);
    v$.value.$validate();

    return {
      commercialInvoiceService,
      alertService,
      commercialInvoice,
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
      if (this.commercialInvoice.id) {
        this.commercialInvoiceService()
          .update(this.commercialInvoice)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.commercialInvoice.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.commercialInvoiceService()
          .create(this.commercialInvoice)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('descoursCabaudApplicationApp.commercialInvoice.created', { param: param.id }).toString()
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
