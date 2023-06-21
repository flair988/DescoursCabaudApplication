import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IForwarder } from '@/shared/model/forwarder.model';
import ForwarderService from './forwarder.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Forwarder',
  setup() {
    const { t: t$ } = useI18n();
    const forwarderService = inject('forwarderService', () => new ForwarderService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const forwarders: Ref<IForwarder[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveForwarders = async () => {
      isFetching.value = true;
      try {
        const res = await forwarderService().retrieve();
        forwarders.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveForwarders();
    };

    onMounted(async () => {
      await retrieveForwarders();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IForwarder) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeForwarder = async () => {
      try {
        await forwarderService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.forwarder.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveForwarders();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      forwarders,
      handleSyncList,
      isFetching,
      retrieveForwarders,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeForwarder,
      t$,
    };
  },
});
