import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IUom, Uom } from '@/shared/model/uom.model';
import UomService from './uom.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UomUpdate',
  setup() {
    const uomService = inject('uomService', () => new UomService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const uom: Ref<IUom> = ref(new Uom());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemId: {},
      uom: {},
      uomGroup: {},
      description: {},
      subItems: {},
      parentItem: {},
      boardId: {},
      kingdeeId: {},
    };
    const v$ = useVuelidate(validationRules, uom as any);
    v$.value.$validate();

    return {
      uomService,
      alertService,
      uom,
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
      if (this.uom.id) {
        this.uomService()
          .update(this.uom)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.uom.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.uomService()
          .create(this.uom)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.uom.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
