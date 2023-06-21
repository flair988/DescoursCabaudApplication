/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import SalesDeliveryDetails from '../../../../../../main/webapp/app/entities/sales-delivery/sales-delivery-details.vue';
import SalesDeliveryService from '../../../../../../main/webapp/app/entities/sales-delivery/sales-delivery.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SalesDeliveryDetailsComponentType = InstanceType<typeof SalesDeliveryDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const salesDeliverySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SalesDelivery Management Detail Component', () => {
    let salesDeliveryServiceStub: SinonStubbedInstance<SalesDeliveryService>;
    let mountOptions: MountingOptions<SalesDeliveryDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      salesDeliveryServiceStub = sinon.createStubInstance<SalesDeliveryService>(SalesDeliveryService);

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
          salesDeliveryService: () => salesDeliveryServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        salesDeliveryServiceStub.find.resolves(salesDeliverySample);
        route = {
          params: {
            salesDeliveryId: '' + 123,
          },
        };
        const wrapper = shallowMount(SalesDeliveryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.salesDelivery).toMatchObject(salesDeliverySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        salesDeliveryServiceStub.find.resolves(salesDeliverySample);
        const wrapper = shallowMount(SalesDeliveryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
