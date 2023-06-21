import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IOperationSite, OperationSite } from '@/shared/model/operation-site.model';
import OperationSiteService from './operation-site.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OperationSiteUpdate',
  setup() {
    const operationSiteService = inject('operationSiteService', () => new OperationSiteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const operationSite: Ref<IOperationSite> = ref(new OperationSite());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOperationSite = async operationSiteId => {
      try {
        const res = await operationSiteService().find(operationSiteId);
        operationSite.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.operationSiteId) {
      retrieveOperationSite(route.params.operationSiteId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      linkSupplierFactory: {},
      typeOfSite: {},
      contact: {},
      siteAddress: {},
      cateGory: {},
      country: {},
      kingdeeId: {},
      businessLicense: {},
      sasDate: {},
      iso900ValidUtil: {},
      iso14001ValidUtil: {},
      attributeLor: {},
      siteQualification: {},
      qualificationScore: {},
      pqvScore: {},
      pqvDate: {},
      pqvDecision: {},
      technicalAuditDate: {},
      technicalAuditScore: {},
      thirdRdPartyDate: {},
      thirdRdPartyScore: {},
      bopeDate: {},
      bopeScore: {},
      capRequired: {},
    };
    const v$ = useVuelidate(validationRules, operationSite as any);
    v$.value.$validate();

    return {
      operationSiteService,
      alertService,
      operationSite,
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
      if (this.operationSite.id) {
        this.operationSiteService()
          .update(this.operationSite)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.operationSite.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.operationSiteService()
          .create(this.operationSite)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.operationSite.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
