import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IMondayColumn } from '@/shared/model/monday-column.model';
import MondayColumnService from './monday-column.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MondayColumn',
  setup() {
    const { t: t$ } = useI18n();
    const mondayColumnService = inject('mondayColumnService', () => new MondayColumnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const mondayColumns: Ref<IMondayColumn[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveMondayColumns = async () => {
      isFetching.value = true;
      try {
        const res = await mondayColumnService().retrieve();
        mondayColumns.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMondayColumns();
    };

    onMounted(async () => {
      await retrieveMondayColumns();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMondayColumn) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMondayColumn = async () => {
      try {
        await mondayColumnService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.mondayColumn.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMondayColumns();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      mondayColumns,
      handleSyncList,
      isFetching,
      retrieveMondayColumns,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMondayColumn,
      t$,
    };
  },
});
