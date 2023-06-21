/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Supplier from '../../../../../../main/webapp/app/entities/supplier/supplier.vue';
import SupplierService from '../../../../../../main/webapp/app/entities/supplier/supplier.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SupplierComponentType = InstanceType<typeof Supplier>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Supplier Management Component', () => {
    let supplierServiceStub: SinonStubbedInstance<SupplierService>;
    let mountOptions: MountingOptions<SupplierComponentType>['global'];

    beforeEach(() => {
      supplierServiceStub = sinon.createStubInstance<SupplierService>(SupplierService);
      supplierServiceStub.retrieve.resolves({ headers: {} });

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
          supplierService: () => supplierServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        supplierServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Supplier, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(supplierServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.suppliers[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SupplierComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Supplier, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        supplierServiceStub.retrieve.reset();
        supplierServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        supplierServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSupplier();
        await comp.$nextTick(); // clear components

        // THEN
        expect(supplierServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(supplierServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
