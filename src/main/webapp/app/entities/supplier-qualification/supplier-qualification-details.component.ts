import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { ISupplierQualification } from '@/shared/model/supplier-qualification.model';
import SupplierQualificationService from './supplier-qualification.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SupplierQualificationDetails',
  setup() {
    const supplierQualificationService = inject('supplierQualificationService', () => new SupplierQualificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const supplierQualification: Ref<ISupplierQualification> = ref({});

    const retrieveSupplierQualification = async supplierQualificationId => {
      try {
        const res = await supplierQualificationService().find(supplierQualificationId);
        supplierQualification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.supplierQualificationId) {
      retrieveSupplierQualification(route.params.supplierQualificationId);
    }

    return {
      alertService,
      supplierQualification,

      previousState,
      t$: useI18n().t,
    };
  },
});
