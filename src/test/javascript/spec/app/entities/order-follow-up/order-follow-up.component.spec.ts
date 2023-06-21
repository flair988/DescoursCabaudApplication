/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import OrderFollowUp from '../../../../../../main/webapp/app/entities/order-follow-up/order-follow-up.vue';
import OrderFollowUpService from '../../../../../../main/webapp/app/entities/order-follow-up/order-follow-up.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OrderFollowUpComponentType = InstanceType<typeof OrderFollowUp>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OrderFollowUp Management Component', () => {
    let orderFollowUpServiceStub: SinonStubbedInstance<OrderFollowUpService>;
    let mountOptions: MountingOptions<OrderFollowUpComponentType>['global'];

    beforeEach(() => {
      orderFollowUpServiceStub = sinon.createStubInstance<OrderFollowUpService>(OrderFollowUpService);
      orderFollowUpServiceStub.retrieve.resolves({ headers: {} });

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
          orderFollowUpService: () => orderFollowUpServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        orderFollowUpServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OrderFollowUp, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(orderFollowUpServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.orderFollowUps[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OrderFollowUpComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OrderFollowUp, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        orderFollowUpServiceStub.retrieve.reset();
        orderFollowUpServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        orderFollowUpServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOrderFollowUp();
        await comp.$nextTick(); // clear components

        // THEN
        expect(orderFollowUpServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(orderFollowUpServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
