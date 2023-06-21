import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IProductTaxmonomy } from '@/shared/model/product-taxmonomy.model';
import ProductTaxmonomyService from './product-taxmonomy.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProductTaxmonomy',
  setup() {
    const { t: t$ } = useI18n();
    const productTaxmonomyService = inject('productTaxmonomyService', () => new ProductTaxmonomyService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const productTaxmonomies: Ref<IProductTaxmonomy[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveProductTaxmonomys = async () => {
      isFetching.value = true;
      try {
        const res = await productTaxmonomyService().retrieve();
        productTaxmonomies.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveProductTaxmonomys();
    };

    onMounted(async () => {
      await retrieveProductTaxmonomys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IProductTaxmonomy) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeProductTaxmonomy = async () => {
      try {
        await productTaxmonomyService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.productTaxmonomy.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveProductTaxmonomys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      productTaxmonomies,
      handleSyncList,
      isFetching,
      retrieveProductTaxmonomys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeProductTaxmonomy,
      t$,
    };
  },
});
