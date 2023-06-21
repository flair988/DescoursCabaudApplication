import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IOperationSiteAuditing } from '@/shared/model/operation-site-auditing.model';
import OperationSiteAuditingService from './operation-site-auditing.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OperationSiteAuditing',
  setup() {
    const { t: t$ } = useI18n();
    const operationSiteAuditingService = inject('operationSiteAuditingService', () => new OperationSiteAuditingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const operationSiteAuditings: Ref<IOperationSiteAuditing[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOperationSiteAuditings = async () => {
      isFetching.value = true;
      try {
        const res = await operationSiteAuditingService().retrieve();
        operationSiteAuditings.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOperationSiteAuditings();
    };

    onMounted(async () => {
      await retrieveOperationSiteAuditings();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOperationSiteAuditing) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOperationSiteAuditing = async () => {
      try {
        await operationSiteAuditingService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.operationSiteAuditing.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOperationSiteAuditings();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      operationSiteAuditings,
      handleSyncList,
      isFetching,
      retrieveOperationSiteAuditings,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOperationSiteAuditing,
      t$,
    };
  },
});
