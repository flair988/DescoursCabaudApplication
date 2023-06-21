import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IOperationSiteAuditing } from '@/shared/model/operation-site-auditing.model';
import OperationSiteAuditingService from './operation-site-auditing.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OperationSiteAuditingDetails',
  setup() {
    const operationSiteAuditingService = inject('operationSiteAuditingService', () => new OperationSiteAuditingService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const operationSiteAuditing: Ref<IOperationSiteAuditing> = ref({});

    const retrieveOperationSiteAuditing = async operationSiteAuditingId => {
      try {
        const res = await operationSiteAuditingService().find(operationSiteAuditingId);
        operationSiteAuditing.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.operationSiteAuditingId) {
      retrieveOperationSiteAuditing(route.params.operationSiteAuditingId);
    }

    return {
      alertService,
      operationSiteAuditing,

      previousState,
      t$: useI18n().t,
    };
  },
});
