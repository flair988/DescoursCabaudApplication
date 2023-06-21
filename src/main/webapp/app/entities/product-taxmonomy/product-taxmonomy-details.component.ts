import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IProductTaxmonomy } from '@/shared/model/product-taxmonomy.model';
import ProductTaxmonomyService from './product-taxmonomy.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProductTaxmonomyDetails',
  setup() {
    const productTaxmonomyService = inject('productTaxmonomyService', () => new ProductTaxmonomyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const productTaxmonomy: Ref<IProductTaxmonomy> = ref({});

    const retrieveProductTaxmonomy = async productTaxmonomyId => {
      try {
        const res = await productTaxmonomyService().find(productTaxmonomyId);
        productTaxmonomy.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.productTaxmonomyId) {
      retrieveProductTaxmonomy(route.params.productTaxmonomyId);
    }

    return {
      alertService,
      productTaxmonomy,

      previousState,
      t$: useI18n().t,
    };
  },
});
