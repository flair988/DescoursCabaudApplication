import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IProcessLog } from '@/shared/model/process-log.model';
import ProcessLogService from './process-log.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProcessLogDetails',
  setup() {
    const processLogService = inject('processLogService', () => new ProcessLogService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const processLog: Ref<IProcessLog> = ref({});

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

    return {
      alertService,
      processLog,

      previousState,
      t$: useI18n().t,
    };
  },
});
