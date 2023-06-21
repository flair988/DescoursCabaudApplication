import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { ISalesDelivery } from '@/shared/model/sales-delivery.model';
import SalesDeliveryService from './sales-delivery.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SalesDeliveryDetails',
  setup() {
    const salesDeliveryService = inject('salesDeliveryService', () => new SalesDeliveryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const salesDelivery: Ref<ISalesDelivery> = ref({});

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

    return {
      alertService,
      salesDelivery,

      previousState,
      t$: useI18n().t,
    };
  },
});
