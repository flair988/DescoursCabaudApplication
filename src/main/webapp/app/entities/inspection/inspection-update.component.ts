import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IInspection, Inspection } from '@/shared/model/inspection.model';
import InspectionService from './inspection.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'InspectionUpdate',
  setup() {
    const inspectionService = inject('inspectionService', () => new InspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const inspection: Ref<IInspection> = ref(new Inspection());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveInspection = async inspectionId => {
      try {
        const res = await inspectionService().find(inspectionId);
        inspection.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.inspectionId) {
      retrieveInspection(route.params.inspectionId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      kingdeeId: {},
      supplierName: {},
      email: {},
      inspectionDate: {},
      cateGory: {},
      qCResult: {},
      docStatus: {},
      inspectionType: {},
      inspectionBookingStatus: {},
      inspectionEndDate: {},
      supplierId: {},
      reportNumber: {},
    };
    const v$ = useVuelidate(validationRules, inspection as any);
    v$.value.$validate();

    return {
      inspectionService,
      alertService,
      inspection,
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
      if (this.inspection.id) {
        this.inspectionService()
          .update(this.inspection)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.inspection.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.inspectionService()
          .create(this.inspection)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.inspection.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
