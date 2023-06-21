import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IOPSiteQualification } from '@/shared/model/op-site-qualification.model';
import OPSiteQualificationService from './op-site-qualification.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OPSiteQualificationDetails',
  setup() {
    const oPSiteQualificationService = inject('oPSiteQualificationService', () => new OPSiteQualificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const oPSiteQualification: Ref<IOPSiteQualification> = ref({});

    const retrieveOPSiteQualification = async oPSiteQualificationId => {
      try {
        const res = await oPSiteQualificationService().find(oPSiteQualificationId);
        oPSiteQualification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.oPSiteQualificationId) {
      retrieveOPSiteQualification(route.params.oPSiteQualificationId);
    }

    return {
      alertService,
      oPSiteQualification,

      previousState,
      t$: useI18n().t,
    };
  },
});
