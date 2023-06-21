import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IProcessLog } from '@/shared/model/process-log.model';
import ProcessLogService from './process-log.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProcessLog',
  setup() {
    const { t: t$ } = useI18n();
    const processLogService = inject('processLogService', () => new ProcessLogService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const processLogs: Ref<IProcessLog[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProcessLogs = async () => {
      isFetching.value = true;
      try {
        const res = await processLogService().retrieve();
        processLogs.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProcessLogs();
    };

    onMounted(async () => {
      await retrieveProcessLogs();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProcessLog) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProcessLog = async () => {
      try {
        await processLogService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.processLog.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProcessLogs();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      processLogs,
      handleSyncList,
      isFetching,
      retrieveProcessLogs,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProcessLog,
      t$,
    };
  },
});
