/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import SupplierQualificationDetails from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification-details.vue';
import SupplierQualificationService from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SupplierQualificationDetailsComponentType = InstanceType<typeof SupplierQualificationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const supplierQualificationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SupplierQualification Management Detail Component', () => {
    let supplierQualificationServiceStub: SinonStubbedInstance<SupplierQualificationService>;
    let mountOptions: MountingOptions<SupplierQualificationDetailsComponentType>['global'];

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
          'router-link': true,
        },
        provide: {
          alertService,
          supplierQualificationService: () => supplierQualificationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        supplierQualificationServiceStub.find.resolves(supplierQualificationSample);
        route = {
          params: {
            supplierQualificationId: '' + 123,
          },
        };
        const wrapper = shallowMount(SupplierQualificationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.supplierQualification).toMatchObject(supplierQualificationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        supplierQualificationServiceStub.find.resolves(supplierQualificationSample);
        const wrapper = shallowMount(SupplierQualificationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
