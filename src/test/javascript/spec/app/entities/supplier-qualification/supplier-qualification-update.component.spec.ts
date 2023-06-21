/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import SupplierQualificationUpdate from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification-update.vue';
import SupplierQualificationService from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SupplierQualificationUpdateComponentType = InstanceType<typeof SupplierQualificationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const supplierQualificationSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SupplierQualificationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SupplierQualification Management Update Component', () => {
    let comp: SupplierQualificationUpdateComponentType;
    let supplierQualificationServiceStub: SinonStubbedInstance<SupplierQualificationService>;

    beforeEach(() => {
      route = {};
      supplierQualificationServiceStub = sinon.createStubInstance<SupplierQualificationService>(SupplierQualificationService);

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
          supplierQualificationService: () => supplierQualificationServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SupplierQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.supplierQualification = supplierQualificationSample;
        supplierQualificationServiceStub.update.resolves(supplierQualificationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(supplierQualificationServiceStub.update.calledWith(supplierQualificationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        supplierQualificationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SupplierQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.supplierQualification = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(supplierQualificationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        supplierQualificationServiceStub.find.resolves(supplierQualificationSample);
        supplierQualificationServiceStub.retrieve.resolves([supplierQualificationSample]);

        // WHEN
        route = {
          params: {
            supplierQualificationId: '' + supplierQualificationSample.id,
          },
        };
        const wrapper = shallowMount(SupplierQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.supplierQualification).toMatchObject(supplierQualificationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        supplierQualificationServiceStub.find.resolves(supplierQualificationSample);
        const wrapper = shallowMount(SupplierQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
