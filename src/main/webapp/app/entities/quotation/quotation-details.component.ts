import { defineComponent, inject, ref, Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import { IQuotation } from '@/shared/model/quotation.model';
import QuotationService from './quotation.service';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QuotationDetails',
  setup() {
    const quotationService = inject('quotationService', () => new QuotationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const quotation: Ref<IQuotation> = ref({});

    const retrieveQuotation = async quotationId => {
      try {
        const res = await quotationService().find(quotationId);
        quotation.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.quotationId) {
      retrieveQuotation(route.params.quotationId);
    }

    return {
      alertService,
      quotation,

      previousState,
      t$: useI18n().t,
    };
  },
});
