import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IInspection } from '@/shared/model/inspection.model';
import InspectionService from './inspection.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'InspectionDetails',
  setup() {
    const inspectionService = inject('inspectionService', () => new InspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const inspection: Ref<IInspection> = ref({});

    const retrieveInspection = async inspectionId => {
      try {
        const res = await inspectionService().find(inspectionId);
        inspection.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.inspectionId) {
      retrieveInspection(route.params.inspectionId);
    }

    return {
      alertService,
      inspection,

      previousState,
      t$: useI18n().t,
    };
  },
});
