import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IPI } from '@/shared/model/pi.model';
import PIService from './pi.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PI',
  setup() {
    const { t: t$ } = useI18n();
    const pIService = inject('pIService', () => new PIService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pIS: Ref<IPI[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrievePIs = async () => {
      isFetching.value = true;
      try {
        const res = await pIService().retrieve();
        pIS.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrievePIs();
    };

    onMounted(async () => {
      await retrievePIs();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IPI) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removePI = async () => {
      try {
        await pIService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.pI.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrievePIs();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      pIS,
      handleSyncList,
      isFetching,
      retrievePIs,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removePI,
      t$,
    };
  },
});
