/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import SupplierQualification from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification.vue';
import SupplierQualificationService from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SupplierQualificationComponentType = InstanceType<typeof SupplierQualification>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SupplierQualification Management Component', () => {
    let supplierQualificationServiceStub: SinonStubbedInstance<SupplierQualificationService>;
    let mountOptions: MountingOptions<SupplierQualificationComponentType>['global'];

    beforeEach(() => {
      supplierQualificationServiceStub = sinon.createStubInstance<SupplierQualificationService>(SupplierQualificationService);
      supplierQualificationServiceStub.retrieve.resolves({ headers: {} });

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
          supplierQualificationService: () => supplierQualificationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        supplierQualificationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SupplierQualification, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(supplierQualificationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.supplierQualifications[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SupplierQualificationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SupplierQualification, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        supplierQualificationServiceStub.retrieve.reset();
        supplierQualificationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        supplierQualificationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSupplierQualification();
        await comp.$nextTick(); // clear components

        // THEN
        expect(supplierQualificationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(supplierQualificationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
