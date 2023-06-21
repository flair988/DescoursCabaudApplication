import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { ICommercialInvoice } from '@/shared/model/commercial-invoice.model';
import CommercialInvoiceService from './commercial-invoice.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommercialInvoiceDetails',
  setup() {
    const commercialInvoiceService = inject('commercialInvoiceService', () => new CommercialInvoiceService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const commercialInvoice: Ref<ICommercialInvoice> = ref({});

    const retrieveCommercialInvoice = async commercialInvoiceId => {
      try {
        const res = await commercialInvoiceService().find(commercialInvoiceId);
        commercialInvoice.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.commercialInvoiceId) {
      retrieveCommercialInvoice(route.params.commercialInvoiceId);
    }

    return {
      alertService,
      commercialInvoice,

      previousState,
      t$: useI18n().t,
    };
  },
});
