import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IMondayUser } from '@/shared/model/monday-user.model';
import MondayUserService from './monday-user.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MondayUserDetails',
  setup() {
    const mondayUserService = inject('mondayUserService', () => new MondayUserService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const mondayUser: Ref<IMondayUser> = ref({});

    const retrieveMondayUser = async mondayUserId => {
      try {
        const res = await mondayUserService().find(mondayUserId);
        mondayUser.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.mondayUserId) {
      retrieveMondayUser(route.params.mondayUserId);
    }

    return {
      alertService,
      mondayUser,

      previousState,
      t$: useI18n().t,
    };
  },
});
