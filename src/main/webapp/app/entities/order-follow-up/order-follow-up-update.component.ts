import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IOrderFollowUp, OrderFollowUp } from '@/shared/model/order-follow-up.model';
import OrderFollowUpService from './order-follow-up.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OrderFollowUpUpdate',
  setup() {
    const orderFollowUpService = inject('orderFollowUpService', () => new OrderFollowUpService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const orderFollowUp: Ref<IOrderFollowUp> = ref(new OrderFollowUp());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOrderFollowUp = async orderFollowUpId => {
      try {
        const res = await orderFollowUpService().find(orderFollowUpId);
        orderFollowUp.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.orderFollowUpId) {
      retrieveOrderFollowUp(route.params.orderFollowUpId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      poNumber: {},
      supplier: {},
      orderDate: {},
      cateGory: {},
      inspectionDate: {},
      requestEndOfProdDate: {},
      totalAmount: {},
      totalDiscount: {},
      disCountRate: {},
      regularCheck: {},
      etd: {},
      atd: {},
      eta: {},
      updatedEta: {},
      documentStatus: {},
      customInstruction: {},
      customInspection: {},
      depositPaymentDate: {},
      balanceOfTotalPaymentDate: {},
      amountDepositPayment: {},
      amountPayment: {},
      dcMember: {},
      comment: {},
      itemName: {},
      kingdeeId: {},
      parentKingdeeId: {},
      qty: {},
      itemCode: {},
      contractEndOfProdDate: {},
      supplierId: {},
    };
    const v$ = useVuelidate(validationRules, orderFollowUp as any);
    v$.value.$validate();

    return {
      orderFollowUpService,
      alertService,
      orderFollowUp,
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
      if (this.orderFollowUp.id) {
        this.orderFollowUpService()
          .update(this.orderFollowUp)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.orderFollowUp.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.orderFollowUpService()
          .create(this.orderFollowUp)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.orderFollowUp.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
