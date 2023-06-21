import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IProductTaxmonomy, ProductTaxmonomy } from '@/shared/model/product-taxmonomy.model';
import ProductTaxmonomyService from './product-taxmonomy.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProductTaxmonomyUpdate',
  setup() {
    const productTaxmonomyService = inject('productTaxmonomyService', () => new ProductTaxmonomyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const productTaxmonomy: Ref<IProductTaxmonomy> = ref(new ProductTaxmonomy());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemId: {},
      kingdeeId: {},
      itemName: {},
      groupName: {},
      parentGroupName: {},
      subGroupName: {},
      description: {},
    };
    const v$ = useVuelidate(validationRules, productTaxmonomy as any);
    v$.value.$validate();

    return {
      productTaxmonomyService,
      alertService,
      productTaxmonomy,
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
      if (this.productTaxmonomy.id) {
        this.productTaxmonomyService()
          .update(this.productTaxmonomy)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.productTaxmonomy.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.productTaxmonomyService()
          .create(this.productTaxmonomy)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.productTaxmonomy.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
