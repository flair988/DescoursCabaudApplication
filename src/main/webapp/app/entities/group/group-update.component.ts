import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IGroup, Group } from '@/shared/model/group.model';
import GroupService from './group.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'GroupUpdate',
  setup() {
    const groupService = inject('groupService', () => new GroupService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const group: Ref<IGroup> = ref(new Group());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveGroup = async groupId => {
      try {
        const res = await groupService().find(groupId);
        group.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.groupId) {
      retrieveGroup(route.params.groupId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      groupId: {},
      groupNumber: {},
      parentId: {},
      groupName: {},
      description: {},
    };
    const v$ = useVuelidate(validationRules, group as any);
    v$.value.$validate();

    return {
      groupService,
      alertService,
      group,
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
      if (this.group.id) {
        this.groupService()
          .update(this.group)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.group.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.groupService()
          .create(this.group)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.group.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
