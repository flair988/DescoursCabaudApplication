import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IUom } from '@/shared/model/uom.model';
import UomService from './uom.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Uom',
  setup() {
    const { t: t$ } = useI18n();
    const uomService = inject('uomService', () => new UomService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uoms: Ref<IUom[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveUoms = async () => {
      isFetching.value = true;
      try {
        const res = await uomService().retrieve();
        uoms.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveUoms();
    };

    onMounted(async () => {
      await retrieveUoms();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IUom) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeUom = async () => {
      try {
        await uomService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.uom.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveUoms();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      uoms,
      handleSyncList,
      isFetching,
      retrieveUoms,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeUom,
      t$,
    };
  },
});
