import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { ISupplier, Supplier } from '@/shared/model/supplier.model';
import SupplierService from './supplier.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SupplierUpdate',
  setup() {
    const supplierService = inject('supplierService', () => new SupplierService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const supplier: Ref<ISupplier> = ref(new Supplier());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSupplier = async supplierId => {
      try {
        const res = await supplierService().find(supplierId);
        supplier.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.supplierId) {
      retrieveSupplier(route.params.supplierId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      client: {},
      category: {},
      subCategory: {},
      capStatus: {},
      supplierStatus: {},
      qualificationScore: {},
      bopeScore: {},
      internalSupplierId: {},
      contact: {},
      contactPhoneNumber: {},
      contactEmailAddress: {},
      operationSite: {},
      address: {},
      website: {},
      relationStartingYear: {},
      businessLicense: {},
      rexOriginStatus: {},
      createDate: {},
      updateDate: {},
      item: {},
      subItems: {},
      date: {},
      kingdeeId: {},
      region: {},
      supplierType: {},
      remark: {},
      frenchName: {},
    };
    const v$ = useVuelidate(validationRules, supplier as any);
    v$.value.$validate();

    return {
      supplierService,
      alertService,
      supplier,
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
      if (this.supplier.id) {
        this.supplierService()
          .update(this.supplier)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.supplier.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.supplierService()
          .create(this.supplier)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.supplier.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
