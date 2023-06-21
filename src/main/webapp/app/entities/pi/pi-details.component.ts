import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IPI } from '@/shared/model/pi.model';
import PIService from './pi.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PIDetails',
  setup() {
    const pIService = inject('pIService', () => new PIService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const pI: Ref<IPI> = ref({});

    const retrievePI = async pIId => {
      try {
        const res = await pIService().find(pIId);
        pI.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pIId) {
      retrievePI(route.params.pIId);
    }

    return {
      alertService,
      pI,

      previousState,
      t$: useI18n().t,
    };
  },
});
