/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ForwarderBookingUpdate from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking-update.vue';
import ForwarderBookingService from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ForwarderBookingUpdateComponentType = InstanceType<typeof ForwarderBookingUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const forwarderBookingSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ForwarderBookingUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ForwarderBooking Management Update Component', () => {
    let comp: ForwarderBookingUpdateComponentType;
    let forwarderBookingServiceStub: SinonStubbedInstance<ForwarderBookingService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          forwarderBookingService: () => forwarderBookingServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ForwarderBookingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.forwarderBooking = forwarderBookingSample;
        forwarderBookingServiceStub.update.resolves(forwarderBookingSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(forwarderBookingServiceStub.update.calledWith(forwarderBookingSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        forwarderBookingServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ForwarderBookingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.forwarderBooking = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(forwarderBookingServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        forwarderBookingServiceStub.find.resolves(forwarderBookingSample);
        forwarderBookingServiceStub.retrieve.resolves([forwarderBookingSample]);

        // WHEN
        route = {
          params: {
            forwarderBookingId: '' + forwarderBookingSample.id,
          },
        };
        const wrapper = shallowMount(ForwarderBookingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.forwarderBooking).toMatchObject(forwarderBookingSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        forwarderBookingServiceStub.find.resolves(forwarderBookingSample);
        const wrapper = shallowMount(ForwarderBookingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
