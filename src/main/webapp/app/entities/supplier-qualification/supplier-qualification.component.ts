import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { ISupplierQualification } from '@/shared/model/supplier-qualification.model';
import SupplierQualificationService from './supplier-qualification.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SupplierQualification',
  setup() {
    const { t: t$ } = useI18n();
    const supplierQualificationService = inject('supplierQualificationService', () => new SupplierQualificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const supplierQualifications: Ref<ISupplierQualification[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSupplierQualifications = async () => {
      isFetching.value = true;
      try {
        const res = await supplierQualificationService().retrieve();
        supplierQualifications.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSupplierQualifications();
    };

    onMounted(async () => {
      await retrieveSupplierQualifications();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISupplierQualification) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSupplierQualification = async () => {
      try {
        await supplierQualificationService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.supplierQualification.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSupplierQualifications();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      supplierQualifications,
      handleSyncList,
      isFetching,
      retrieveSupplierQualifications,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSupplierQualification,
      t$,
    };
  },
});
