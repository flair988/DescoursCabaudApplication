/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import CommercialInvoiceDetails from '../../../../../../main/webapp/app/entities/commercial-invoice/commercial-invoice-details.vue';
import CommercialInvoiceService from '../../../../../../main/webapp/app/entities/commercial-invoice/commercial-invoice.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type CommercialInvoiceDetailsComponentType = InstanceType<typeof CommercialInvoiceDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const commercialInvoiceSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CommercialInvoice Management Detail Component', () => {
    let commercialInvoiceServiceStub: SinonStubbedInstance<CommercialInvoiceService>;
    let mountOptions: MountingOptions<CommercialInvoiceDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      commercialInvoiceServiceStub = sinon.createStubInstance<CommercialInvoiceService>(CommercialInvoiceService);

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
          commercialInvoiceService: () => commercialInvoiceServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        commercialInvoiceServiceStub.find.resolves(commercialInvoiceSample);
        route = {
          params: {
            commercialInvoiceId: '' + 123,
          },
        };
        const wrapper = shallowMount(CommercialInvoiceDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.commercialInvoice).toMatchObject(commercialInvoiceSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        commercialInvoiceServiceStub.find.resolves(commercialInvoiceSample);
        const wrapper = shallowMount(CommercialInvoiceDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
