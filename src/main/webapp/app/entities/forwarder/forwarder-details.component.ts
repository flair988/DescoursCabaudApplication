import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IForwarder } from '@/shared/model/forwarder.model';
import ForwarderService from './forwarder.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ForwarderDetails',
  setup() {
    const forwarderService = inject('forwarderService', () => new ForwarderService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const forwarder: Ref<IForwarder> = ref({});

    const retrieveForwarder = async forwarderId => {
      try {
        const res = await forwarderService().find(forwarderId);
        forwarder.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.forwarderId) {
      retrieveForwarder(route.params.forwarderId);
    }

    return {
      alertService,
      forwarder,

      previousState,
      t$: useI18n().t,
    };
  },
});
