import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IPurchaseReturn, PurchaseReturn } from '@/shared/model/purchase-return.model';
import PurchaseReturnService from './purchase-return.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PurchaseReturnUpdate',
  setup() {
    const purchaseReturnService = inject('purchaseReturnService', () => new PurchaseReturnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const purchaseReturn: Ref<IPurchaseReturn> = ref(new PurchaseReturn());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePurchaseReturn = async purchaseReturnId => {
      try {
        const res = await purchaseReturnService().find(purchaseReturnId);
        purchaseReturn.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.purchaseReturnId) {
      retrievePurchaseReturn(route.params.purchaseReturnId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      kingdeeId: {},
      docNumber: {},
      supplier: {},
      supplierEmail: {},
      materialReturnDate: {},
      reasonForReturn: {},
      purchaseDept: {},
      remarks: {},
      docStatus: {},
      itemCode: {},
      supplierCode: {},
    };
    const v$ = useVuelidate(validationRules, purchaseReturn as any);
    v$.value.$validate();

    return {
      purchaseReturnService,
      alertService,
      purchaseReturn,
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
      if (this.purchaseReturn.id) {
        this.purchaseReturnService()
          .update(this.purchaseReturn)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.purchaseReturn.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.purchaseReturnService()
          .create(this.purchaseReturn)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.purchaseReturn.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
