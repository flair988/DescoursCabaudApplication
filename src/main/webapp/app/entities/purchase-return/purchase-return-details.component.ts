import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IPurchaseReturn } from '@/shared/model/purchase-return.model';
import PurchaseReturnService from './purchase-return.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PurchaseReturnDetails',
  setup() {
    const purchaseReturnService = inject('purchaseReturnService', () => new PurchaseReturnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const purchaseReturn: Ref<IPurchaseReturn> = ref({});

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

    return {
      alertService,
      purchaseReturn,

      previousState,
      t$: useI18n().t,
    };
  },
});
