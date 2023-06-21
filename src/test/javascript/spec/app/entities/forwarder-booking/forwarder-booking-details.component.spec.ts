/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ForwarderBookingDetails from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking-details.vue';
import ForwarderBookingService from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ForwarderBookingDetailsComponentType = InstanceType<typeof ForwarderBookingDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const forwarderBookingSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ForwarderBooking Management Detail Component', () => {
    let forwarderBookingServiceStub: SinonStubbedInstance<ForwarderBookingService>;
    let mountOptions: MountingOptions<ForwarderBookingDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      forwarderBookingServiceStub = sinon.createStubInstance<ForwarderBookingService>(ForwarderBookingService);

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
          forwarderBookingService: () => forwarderBookingServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        forwarderBookingServiceStub.find.resolves(forwarderBookingSample);
        route = {
          params: {
            forwarderBookingId: '' + 123,
          },
        };
        const wrapper = shallowMount(ForwarderBookingDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.forwarderBooking).toMatchObject(forwarderBookingSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        forwarderBookingServiceStub.find.resolves(forwarderBookingSample);
        const wrapper = shallowMount(ForwarderBookingDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
