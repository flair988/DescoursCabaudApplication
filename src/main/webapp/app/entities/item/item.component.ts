import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IItem } from '@/shared/model/item.model';
import ItemService from './item.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Item',
  setup() {
    const { t: t$ } = useI18n();
    const itemService = inject('itemService', () => new ItemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const items: Ref<IItem[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveItems = async () => {
      isFetching.value = true;
      try {
        const res = await itemService().retrieve();
        items.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveItems();
    };

    onMounted(async () => {
      await retrieveItems();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IItem) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeItem = async () => {
      try {
        await itemService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.item.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveItems();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      items,
      handleSyncList,
      isFetching,
      retrieveItems,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeItem,
      t$,
    };
  },
});
