import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IGroup } from '@/shared/model/group.model';
import GroupService from './group.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'GroupDetails',
  setup() {
    const groupService = inject('groupService', () => new GroupService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const group: Ref<IGroup> = ref({});

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

    return {
      alertService,
      group,

      previousState,
      t$: useI18n().t,
    };
  },
});
