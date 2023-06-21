import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IForwarder, Forwarder } from '@/shared/model/forwarder.model';
import ForwarderService from './forwarder.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ForwarderUpdate',
  setup() {
    const forwarderService = inject('forwarderService', () => new ForwarderService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const forwarder: Ref<IForwarder> = ref(new Forwarder());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemName: {},
      itemId: {},
      boardId: {},
      kingdeeId: {},
      contact: {},
      email: {},
      telephone: {},
    };
    const v$ = useVuelidate(validationRules, forwarder as any);
    v$.value.$validate();

    return {
      forwarderService,
      alertService,
      forwarder,
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
      if (this.forwarder.id) {
        this.forwarderService()
          .update(this.forwarder)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.forwarder.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.forwarderService()
          .create(this.forwarder)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.forwarder.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
