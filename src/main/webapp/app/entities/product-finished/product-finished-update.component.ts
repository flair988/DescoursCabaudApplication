import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IProductFinished, ProductFinished } from '@/shared/model/product-finished.model';
import ProductFinishedService from './product-finished.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProductFinishedUpdate',
  setup() {
    const productFinishedService = inject('productFinishedService', () => new ProductFinishedService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const productFinished: Ref<IProductFinished> = ref(new ProductFinished());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      kingdeeId: {},
      supplier: {},
      supplierEmail: {},
      orderDate: {},
      cateGory: {},
      remark: {},
      materialReceiptDate: {},
      docStatus: {},
      supplierName: {},
    };
    const v$ = useVuelidate(validationRules, productFinished as any);
    v$.value.$validate();

    return {
      productFinishedService,
      alertService,
      productFinished,
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
      if (this.productFinished.id) {
        this.productFinishedService()
          .update(this.productFinished)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.productFinished.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.productFinishedService()
          .create(this.productFinished)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.productFinished.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
