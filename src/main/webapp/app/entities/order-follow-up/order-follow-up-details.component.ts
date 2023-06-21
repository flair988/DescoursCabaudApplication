import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IOrderFollowUp } from '@/shared/model/order-follow-up.model';
import OrderFollowUpService from './order-follow-up.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OrderFollowUpDetails',
  setup() {
    const orderFollowUpService = inject('orderFollowUpService', () => new OrderFollowUpService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const orderFollowUp: Ref<IOrderFollowUp> = ref({});

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

    return {
      alertService,
      orderFollowUp,

      previousState,
      t$: useI18n().t,
    };
  },
});
