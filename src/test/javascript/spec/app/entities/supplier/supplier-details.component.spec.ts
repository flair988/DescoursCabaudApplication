/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import SupplierDetails from '../../../../../../main/webapp/app/entities/supplier/supplier-details.vue';
import SupplierService from '../../../../../../main/webapp/app/entities/supplier/supplier.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SupplierDetailsComponentType = InstanceType<typeof SupplierDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const supplierSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Supplier Management Detail Component', () => {
    let supplierServiceStub: SinonStubbedInstance<SupplierService>;
    let mountOptions: MountingOptions<SupplierDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      supplierServiceStub = sinon.createStubInstance<SupplierService>(SupplierService);

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
          supplierService: () => supplierServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        supplierServiceStub.find.resolves(supplierSample);
        route = {
          params: {
            supplierId: '' + 123,
          },
        };
        const wrapper = shallowMount(SupplierDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.supplier).toMatchObject(supplierSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        supplierServiceStub.find.resolves(supplierSample);
        const wrapper = shallowMount(SupplierDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
