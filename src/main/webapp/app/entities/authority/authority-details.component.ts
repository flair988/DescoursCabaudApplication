import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IAuthority } from '@/shared/model/authority.model';
import AuthorityService from './authority.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AuthorityDetails',
  setup() {
    const authorityService = inject('authorityService', () => new AuthorityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const authority: Ref<IAuthority> = ref({});

    const retrieveAuthority = async authorityId => {
      try {
        const res = await authorityService().find(authorityId);
        authority.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.authorityId) {
      retrieveAuthority(route.params.authorityId);
    }

    return {
      alertService,
      authority,

      previousState,
      t$: useI18n().t,
    };
  },
});
