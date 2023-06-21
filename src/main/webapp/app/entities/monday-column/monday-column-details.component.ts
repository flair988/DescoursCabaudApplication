import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IMondayColumn } from '@/shared/model/monday-column.model';
import MondayColumnService from './monday-column.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MondayColumnDetails',
  setup() {
    const mondayColumnService = inject('mondayColumnService', () => new MondayColumnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const mondayColumn: Ref<IMondayColumn> = ref({});

    const retrieveMondayColumn = async mondayColumnId => {
      try {
        const res = await mondayColumnService().find(mondayColumnId);
        mondayColumn.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.mondayColumnId) {
      retrieveMondayColumn(route.params.mondayColumnId);
    }

    return {
      alertService,
      mondayColumn,

      previousState,
      t$: useI18n().t,
    };
  },
});
