import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IOperationSite } from '@/shared/model/operation-site.model';
import OperationSiteService from './operation-site.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OperationSiteDetails',
  setup() {
    const operationSiteService = inject('operationSiteService', () => new OperationSiteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const operationSite: Ref<IOperationSite> = ref({});

    const retrieveOperationSite = async operationSiteId => {
      try {
        const res = await operationSiteService().find(operationSiteId);
        operationSite.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.operationSiteId) {
      retrieveOperationSite(route.params.operationSiteId);
    }

    return {
      alertService,
      operationSite,

      previousState,
      t$: useI18n().t,
    };
  },
});
