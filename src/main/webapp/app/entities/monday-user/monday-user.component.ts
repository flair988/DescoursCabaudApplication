import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IMondayUser } from '@/shared/model/monday-user.model';
import MondayUserService from './monday-user.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MondayUser',
  setup() {
    const { t: t$ } = useI18n();
    const mondayUserService = inject('mondayUserService', () => new MondayUserService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const mondayUsers: Ref<IMondayUser[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMondayUsers = async () => {
      isFetching.value = true;
      try {
        const res = await mondayUserService().retrieve();
        mondayUsers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMondayUsers();
    };

    onMounted(async () => {
      await retrieveMondayUsers();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMondayUser) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMondayUser = async () => {
      try {
        await mondayUserService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.mondayUser.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMondayUsers();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      mondayUsers,
      handleSyncList,
      isFetching,
      retrieveMondayUsers,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMondayUser,
      t$,
    };
  },
});
