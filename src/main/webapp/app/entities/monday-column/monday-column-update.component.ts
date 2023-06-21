import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IMondayColumn, MondayColumn } from '@/shared/model/monday-column.model';
import MondayColumnService from './monday-column.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MondayColumnUpdate',
  setup() {
    const mondayColumnService = inject('mondayColumnService', () => new MondayColumnService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const mondayColumn: Ref<IMondayColumn> = ref(new MondayColumn());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      boardId: {},
      columnId: {},
      title: {},
      type: {},
    };
    const v$ = useVuelidate(validationRules, mondayColumn as any);
    v$.value.$validate();

    return {
      mondayColumnService,
      alertService,
      mondayColumn,
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
      if (this.mondayColumn.id) {
        this.mondayColumnService()
          .update(this.mondayColumn)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.mondayColumn.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.mondayColumnService()
          .create(this.mondayColumn)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.mondayColumn.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
