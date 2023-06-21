import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IOperationSiteAuditing, OperationSiteAuditing } from '@/shared/model/operation-site-auditing.model';
import OperationSiteAuditingService from './operation-site-auditing.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OperationSiteAuditingUpdate',
  setup() {
    const operationSiteAuditingService = inject('operationSiteAuditingService', () => new OperationSiteAuditingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const operationSiteAuditing: Ref<IOperationSiteAuditing> = ref(new OperationSiteAuditing());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOperationSiteAuditing = async operationSiteAuditingId => {
      try {
        const res = await operationSiteAuditingService().find(operationSiteAuditingId);
        operationSiteAuditing.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.operationSiteAuditingId) {
      retrieveOperationSiteAuditing(route.params.operationSiteAuditingId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      cateGory: {},
      supplier: {},
      operationSite: {},
      linkSupplierFactory: {},
      typeOfSite: {},
      auditingTool: {},
      auditingDate: {},
      csrResult: {},
      qualityProductionResult: {},
      businessLiabilityResult: {},
      comments: {},
      issueDate: {},
      dueDate: {},
      closedDate: {},
      closed: {},
      status: {},
      capComments: {},
    };
    const v$ = useVuelidate(validationRules, operationSiteAuditing as any);
    v$.value.$validate();

    return {
      operationSiteAuditingService,
      alertService,
      operationSiteAuditing,
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
      if (this.operationSiteAuditing.id) {
        this.operationSiteAuditingService()
          .update(this.operationSiteAuditing)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.operationSiteAuditing.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.operationSiteAuditingService()
          .create(this.operationSiteAuditing)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('descoursCabaudApplicationApp.operationSiteAuditing.created', { param: param.id }).toString()
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
