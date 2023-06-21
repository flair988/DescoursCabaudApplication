import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IGroup } from '@/shared/model/group.model';
import GroupService from './group.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Group',
  setup() {
    const { t: t$ } = useI18n();
    const groupService = inject('groupService', () => new GroupService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const groups: Ref<IGroup[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveGroups = async () => {
      isFetching.value = true;
      try {
        const res = await groupService().retrieve();
        groups.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveGroups();
    };

    onMounted(async () => {
      await retrieveGroups();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IGroup) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeGroup = async () => {
      try {
        await groupService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.group.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveGroups();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      groups,
      handleSyncList,
      isFetching,
      retrieveGroups,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeGroup,
      t$,
    };
  },
});
