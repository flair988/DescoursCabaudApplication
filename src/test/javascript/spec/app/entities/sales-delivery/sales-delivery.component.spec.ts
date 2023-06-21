/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import SalesDelivery from '../../../../../../main/webapp/app/entities/sales-delivery/sales-delivery.vue';
import SalesDeliveryService from '../../../../../../main/webapp/app/entities/sales-delivery/sales-delivery.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SalesDeliveryComponentType = InstanceType<typeof SalesDelivery>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SalesDelivery Management Component', () => {
    let salesDeliveryServiceStub: SinonStubbedInstance<SalesDeliveryService>;
    let mountOptions: MountingOptions<SalesDeliveryComponentType>['global'];

    beforeEach(() => {
      salesDeliveryServiceStub = sinon.createStubInstance<SalesDeliveryService>(SalesDeliveryService);
      salesDeliveryServiceStub.retrieve.resolves({ headers: {} });

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
          salesDeliveryService: () => salesDeliveryServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        salesDeliveryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SalesDelivery, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(salesDeliveryServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.salesDeliveries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SalesDeliveryComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SalesDelivery, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        salesDeliveryServiceStub.retrieve.reset();
        salesDeliveryServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        salesDeliveryServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSalesDelivery();
        await comp.$nextTick(); // clear components

        // THEN
        expect(salesDeliveryServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(salesDeliveryServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
