import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IProductFinished } from '@/shared/model/product-finished.model';
import ProductFinishedService from './product-finished.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProductFinished',
  setup() {
    const { t: t$ } = useI18n();
    const productFinishedService = inject('productFinishedService', () => new ProductFinishedService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const productFinisheds: Ref<IProductFinished[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProductFinisheds = async () => {
      isFetching.value = true;
      try {
        const res = await productFinishedService().retrieve();
        productFinisheds.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProductFinisheds();
    };

    onMounted(async () => {
      await retrieveProductFinisheds();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProductFinished) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProductFinished = async () => {
      try {
        await productFinishedService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.productFinished.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProductFinisheds();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      productFinisheds,
      handleSyncList,
      isFetching,
      retrieveProductFinisheds,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProductFinished,
      t$,
    };
  },
});
