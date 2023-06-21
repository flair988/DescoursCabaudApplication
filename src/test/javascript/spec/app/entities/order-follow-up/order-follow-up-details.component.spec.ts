/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OrderFollowUpDetails from '../../../../../../main/webapp/app/entities/order-follow-up/order-follow-up-details.vue';
import OrderFollowUpService from '../../../../../../main/webapp/app/entities/order-follow-up/order-follow-up.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OrderFollowUpDetailsComponentType = InstanceType<typeof OrderFollowUpDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const orderFollowUpSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OrderFollowUp Management Detail Component', () => {
    let orderFollowUpServiceStub: SinonStubbedInstance<OrderFollowUpService>;
    let mountOptions: MountingOptions<OrderFollowUpDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      orderFollowUpServiceStub = sinon.createStubInstance<OrderFollowUpService>(OrderFollowUpService);

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
          orderFollowUpService: () => orderFollowUpServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        orderFollowUpServiceStub.find.resolves(orderFollowUpSample);
        route = {
          params: {
            orderFollowUpId: '' + 123,
          },
        };
        const wrapper = shallowMount(OrderFollowUpDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.orderFollowUp).toMatchObject(orderFollowUpSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        orderFollowUpServiceStub.find.resolves(orderFollowUpSample);
        const wrapper = shallowMount(OrderFollowUpDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
