import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IAuthority, Authority } from '@/shared/model/authority.model';
import AuthorityService from './authority.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AuthorityUpdate',
  setup() {
    const authorityService = inject('authorityService', () => new AuthorityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const authority: Ref<IAuthority> = ref(new Authority());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, authority as any);
    v$.value.$validate();

    return {
      authorityService,
      alertService,
      authority,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.authority.id) {
        this.authorityService()
          .update(this.authority)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.authority.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.authorityService()
          .create(this.authority)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.authority.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
