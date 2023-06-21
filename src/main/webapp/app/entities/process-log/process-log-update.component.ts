import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IProcessLog, ProcessLog } from '@/shared/model/process-log.model';
import ProcessLogService from './process-log.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProcessLogUpdate',
  setup() {
    const processLogService = inject('processLogService', () => new ProcessLogService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const processLog: Ref<IProcessLog> = ref(new ProcessLog());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProcessLog = async processLogId => {
      try {
        const res = await processLogService().find(processLogId);
        processLog.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.processLogId) {
      retrieveProcessLog(route.params.processLogId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      type: {},
      request: {},
      response: {},
      status: {},
      reason: {},
      processStartTime: {},
      processEndTime: {},
    };
    const v$ = useVuelidate(validationRules, processLog as any);
    v$.value.$validate();

    return {
      processLogService,
      alertService,
      processLog,
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
      if (this.processLog.id) {
        this.processLogService()
          .update(this.processLog)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.processLog.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.processLogService()
          .create(this.processLog)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.processLog.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
