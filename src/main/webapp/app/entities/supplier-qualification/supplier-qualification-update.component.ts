import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { ISupplierQualification, SupplierQualification } from '@/shared/model/supplier-qualification.model';
import SupplierQualificationService from './supplier-qualification.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SupplierQualificationUpdate',
  setup() {
    const supplierQualificationService = inject('supplierQualificationService', () => new SupplierQualificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const supplierQualification: Ref<ISupplierQualification> = ref(new SupplierQualification());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSupplierQualification = async supplierQualificationId => {
      try {
        const res = await supplierQualificationService().find(supplierQualificationId);
        supplierQualification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.supplierQualificationId) {
      retrieveSupplierQualification(route.params.supplierQualificationId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      cateGory: {},
      supplier: {},
      date: {},
      supplierStatus: {},
      evaluationStatus: {},
      businessLiabilityBopeScore: {},
      comments: {},
    };
    const v$ = useVuelidate(validationRules, supplierQualification as any);
    v$.value.$validate();

    return {
      supplierQualificationService,
      alertService,
      supplierQualification,
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
      if (this.supplierQualification.id) {
        this.supplierQualificationService()
          .update(this.supplierQualification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.supplierQualification.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.supplierQualificationService()
          .create(this.supplierQualification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('descoursCabaudApplicationApp.supplierQualification.created', { param: param.id }).toString()
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
