import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IOPSiteQualification, OPSiteQualification } from '@/shared/model/op-site-qualification.model';
import OPSiteQualificationService from './op-site-qualification.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OPSiteQualificationUpdate',
  setup() {
    const oPSiteQualificationService = inject('oPSiteQualificationService', () => new OPSiteQualificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const oPSiteQualification: Ref<IOPSiteQualification> = ref(new OPSiteQualification());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOPSiteQualification = async oPSiteQualificationId => {
      try {
        const res = await oPSiteQualificationService().find(oPSiteQualificationId);
        oPSiteQualification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.oPSiteQualificationId) {
      retrieveOPSiteQualification(route.params.oPSiteQualificationId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      cateGory: {},
      supplier: {},
      date: {},
      operationSite: {},
      attributedLoRForThisSite: {},
      siteQualificationStatus: {},
      csrResult: {},
      qualityProductionResult: {},
      businessLiabilityResult: {},
      comments: {},
    };
    const v$ = useVuelidate(validationRules, oPSiteQualification as any);
    v$.value.$validate();

    return {
      oPSiteQualificationService,
      alertService,
      oPSiteQualification,
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
      if (this.oPSiteQualification.id) {
        this.oPSiteQualificationService()
          .update(this.oPSiteQualification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.oPSiteQualification.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.oPSiteQualificationService()
          .create(this.oPSiteQualification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('descoursCabaudApplicationApp.oPSiteQualification.created', { param: param.id }).toString()
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
