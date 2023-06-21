import { computed, defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { IItem, Item } from '@/shared/model/item.model';
import ItemService from './item.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ItemUpdate',
  setup() {
    const itemService = inject('itemService', () => new ItemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const item: Ref<IItem> = ref(new Item());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveItem = async itemId => {
      try {
        const res = await itemService().find(itemId);
        item.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.itemId) {
      retrieveItem(route.params.itemId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      itemStatus: {},
      itemFranceName: {},
      kingdeeId: {},
      itemName: {},
      codeag: {},
      technicalDocuments: {},
      certification: {},
      opportunitySheet: {},
      packingType: {},
      salePackageImage: {},
      cartonLengthMilimeter: {},
      cartonHeightMilimeter: {},
      barcode: {},
      cartonWeightKg: {},
      cartonWeightGr: {},
      cartonWidthMilimeter: {},
      productDescriptionAndFonctionalities: {},
      drawing: {},
      userManual: {},
      palletSize: {},
      typeOfMarketing: {},
      productPic: {},
      label: {},
      comment: {},
      productTaxonomy: {},
      netWeight: {},
      grossWeight: {},
      unitOfWeight: {},
      cartonVolumeMilimeter: {},
    };
    const v$ = useVuelidate(validationRules, item as any);
    v$.value.$validate();

    return {
      itemService,
      alertService,
      item,
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
      if (this.item.id) {
        this.itemService()
          .update(this.item)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('descoursCabaudApplicationApp.item.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.itemService()
          .create(this.item)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('descoursCabaudApplicationApp.item.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
