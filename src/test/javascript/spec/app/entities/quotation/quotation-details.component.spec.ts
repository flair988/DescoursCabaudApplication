/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import QuotationDetails from '../../../../../../main/webapp/app/entities/quotation/quotation-details.vue';
import QuotationService from '../../../../../../main/webapp/app/entities/quotation/quotation.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type QuotationDetailsComponentType = InstanceType<typeof QuotationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const quotationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Quotation Management Detail Component', () => {
    let quotationServiceStub: SinonStubbedInstance<QuotationService>;
    let mountOptions: MountingOptions<QuotationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      quotationServiceStub = sinon.createStubInstance<QuotationService>(QuotationService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          quotationService: () => quotationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        quotationServiceStub.find.resolves(quotationSample);
        route = {
          params: {
            quotationId: '' + 123,
          },
        };
        const wrapper = shallowMount(QuotationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.quotation).toMatchObject(quotationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        quotationServiceStub.find.resolves(quotationSample);
        const wrapper = shallowMount(QuotationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
