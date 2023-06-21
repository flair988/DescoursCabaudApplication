import { defineComponent, inject, onMounted, ref, Ref, watch, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';

import { IAuthority } from '@/shared/model/authority.model';
import AuthorityService from './authority.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Authority',
  setup() {
    const { t: t$ } = useI18n();
    const authorityService = inject('authorityService', () => new AuthorityService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const authorities: Ref<IAuthority[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveAuthoritys = async () => {
      isFetching.value = true;
      try {
        const res = await authorityService().retrieve();
        authorities.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveAuthoritys();
    };

    onMounted(async () => {
      await retrieveAuthoritys();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IAuthority) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeAuthority = async () => {
      try {
        await authorityService().delete(removeId.value);
        const message = t$('descoursCabaudApplicationApp.authority.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveAuthoritys();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      authorities,
      handleSyncList,
      isFetching,
      retrieveAuthoritys,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeAuthority,
      t$,
    };
  },
});
