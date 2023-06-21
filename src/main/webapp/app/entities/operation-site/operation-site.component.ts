import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IOperationSite } from '@/shared/model/operation-site.model';
import OperationSiteService from './operation-site.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OperationSite',
  setup() {
    const { t: t$ } = useI18n();
    const operationSiteService = inject('operationSiteService', () => new OperationSiteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const operationSites: Ref<IOperationSite[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOperationSites = async () => {
      isFetching.value = true;
      try {
        const res = await operationSiteService().retrieve();
        operationSites.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOperationSites();
    };

    onMounted(async () => {
      await retrieveOperationSites();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOperationSite) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOperationSite = async () => {
      try {
        await operationSiteService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.operationSite.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOperationSites();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      operationSites,
      handleSyncList,
      isFetching,
      retrieveOperationSites,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOperationSite,
      t$,
    };
  },
});
