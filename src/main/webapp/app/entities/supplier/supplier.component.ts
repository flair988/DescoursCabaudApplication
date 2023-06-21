import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { ISupplier } from '@/shared/model/supplier.model';
import SupplierService from './supplier.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Supplier',
  setup() {
    const { t: t$ } = useI18n();
    const supplierService = inject('supplierService', () => new SupplierService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const suppliers: Ref<ISupplier[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveSuppliers = async () => {
      isFetching.value = true;
      try {
        const res = await supplierService().retrieve();
        suppliers.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveSuppliers();
    };

    onMounted(async () => {
      await retrieveSuppliers();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: ISupplier) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeSupplier = async () => {
      try {
        await supplierService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.supplier.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveSuppliers();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      suppliers,
      handleSyncList,
      isFetching,
      retrieveSuppliers,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeSupplier,
      t$,
    };
  },
});
