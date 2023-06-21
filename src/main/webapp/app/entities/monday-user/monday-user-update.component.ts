import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IMondayUser, MondayUser } from '@/shared/model/monday-user.model';
import MondayUserService from './monday-user.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MondayUserUpdate',
  setup() {
    const mondayUserService = inject('mondayUserService', () => new MondayUserService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const mondayUser: Ref<IMondayUser> = ref(new MondayUser());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      mondayId: {},
      name: {},
      email: {},
      url: {},
    };
    const v$ = useVuelidate(validationRules, mondayUser as any);
    v$.value.$validate();

    return {
      mondayUserService,
      alertService,
      mondayUser,
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
      if (this.mondayUser.id) {
        this.mondayUserService()
          .update(this.mondayUser)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.mondayUser.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.mondayUserService()
          .create(this.mondayUser)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.mondayUser.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
