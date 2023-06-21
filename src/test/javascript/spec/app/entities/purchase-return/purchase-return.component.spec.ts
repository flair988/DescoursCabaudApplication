/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import PurchaseReturn from '../../../../../../main/webapp/app/entities/purchase-return/purchase-return.vue';
import PurchaseReturnService from '../../../../../../main/webapp/app/entities/purchase-return/purchase-return.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type PurchaseReturnComponentType = InstanceType<typeof PurchaseReturn>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('PurchaseReturn Management Component', () => {
    let purchaseReturnServiceStub: SinonStubbedInstance<PurchaseReturnService>;
    let mountOptions: MountingOptions<PurchaseReturnComponentType>['global'];

    beforeEach(() => {
      purchaseReturnServiceStub = sinon.createStubInstance<PurchaseReturnService>(PurchaseReturnService);
      purchaseReturnServiceStub.retrieve.resolves({ headers: {} });

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
          purchaseReturnService: () => purchaseReturnServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        purchaseReturnServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(PurchaseReturn, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(purchaseReturnServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.purchaseReturns[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PurchaseReturnComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(PurchaseReturn, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        purchaseReturnServiceStub.retrieve.reset();
        purchaseReturnServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        purchaseReturnServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePurchaseReturn();
        await comp.$nextTick(); // clear components

        // THEN
        expect(purchaseReturnServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(purchaseReturnServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
