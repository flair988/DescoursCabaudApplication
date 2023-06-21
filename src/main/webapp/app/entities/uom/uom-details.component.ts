import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IUom } from '@/shared/model/uom.model';
import UomService from './uom.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UomDetails',
  setup() {
    const uomService = inject('uomService', () => new UomService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const uom: Ref<IUom> = ref({});

    const retrieveUom = async uomId => {
      try {
        const res = await uomService().find(uomId);
        uom.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.uomId) {
      retrieveUom(route.params.uomId);
    }

    return {
      alertService,
      uom,

      previousState,
      t$: useI18n().t,
    };
  },
});
