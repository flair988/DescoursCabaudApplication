import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IProductFinished } from '@/shared/model/product-finished.model';
import ProductFinishedService from './product-finished.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProductFinishedDetails',
  setup() {
    const productFinishedService = inject('productFinishedService', () => new ProductFinishedService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const productFinished: Ref<IProductFinished> = ref({});

    const retrieveProductFinished = async productFinishedId => {
      try {
        const res = await productFinishedService().find(productFinishedId);
        productFinished.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.productFinishedId) {
      retrieveProductFinished(route.params.productFinishedId);
    }

    return {
      alertService,
      productFinished,

      previousState,
      t$: useI18n().t,
    };
  },
});
