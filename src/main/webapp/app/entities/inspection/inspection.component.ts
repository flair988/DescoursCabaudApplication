import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IInspection } from '@/shared/model/inspection.model';
import InspectionService from './inspection.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Inspection',
  setup() {
    const { t: t$ } = useI18n();
    const inspectionService = inject('inspectionService', () => new InspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const inspections: Ref<IInspection[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveInspections = async () => {
      isFetching.value = true;
      try {
        const res = await inspectionService().retrieve();
        inspections.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveInspections();
    };

    onMounted(async () => {
      await retrieveInspections();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IInspection) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeInspection = async () => {
      try {
        await inspectionService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.inspection.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveInspections();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      inspections,
      handleSyncList,
      isFetching,
      retrieveInspections,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeInspection,
      t$,
    };
  },
});
