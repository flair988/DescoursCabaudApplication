/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import ForwarderBooking from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking.vue';
import ForwarderBookingService from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ForwarderBookingComponentType = InstanceType<typeof ForwarderBooking>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ForwarderBooking Management Component', () => {
    let forwarderBookingServiceStub: SinonStubbedInstance<ForwarderBookingService>;
    let mountOptions: MountingOptions<ForwarderBookingComponentType>['global'];

    beforeEach(() => {
      forwarderBookingServiceStub = sinon.createStubInstance<ForwarderBookingService>(ForwarderBookingService);
      forwarderBookingServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          forwarderBookingService: () => forwarderBookingServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        forwarderBookingServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ForwarderBooking, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(forwarderBookingServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.forwarderBookings[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ForwarderBookingComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ForwarderBooking, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        forwarderBookingServiceStub.retrieve.reset();
        forwarderBookingServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        forwarderBookingServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeForwarderBooking();
        await comp.$nextTick(); // clear components

        // THEN
        expect(forwarderBookingServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(forwarderBookingServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
