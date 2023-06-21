import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IOPSiteQualification } from '@/shared/model/op-site-qualification.model';
import OPSiteQualificationService from './op-site-qualification.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OPSiteQualification',
  setup() {
    const { t: t$ } = useI18n();
    const oPSiteQualificationService = inject('oPSiteQualificationService', () => new OPSiteQualificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const oPSiteQualifications: Ref<IOPSiteQualification[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveOPSiteQualifications = async () => {
      isFetching.value = true;
      try {
        const res = await oPSiteQualificationService().retrieve();
        oPSiteQualifications.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveOPSiteQualifications();
    };

    onMounted(async () => {
      await retrieveOPSiteQualifications();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IOPSiteQualification) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeOPSiteQualification = async () => {
      try {
        await oPSiteQualificationService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.oPSiteQualification.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveOPSiteQualifications();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      oPSiteQualifications,
      handleSyncList,
      isFetching,
      retrieveOPSiteQualifications,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeOPSiteQualification,
      t$,
    };
  },
});
