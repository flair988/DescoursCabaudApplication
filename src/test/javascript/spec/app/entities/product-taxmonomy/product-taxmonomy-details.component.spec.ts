/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ProductTaxmonomyDetails from '../../../../../../main/webapp/app/entities/product-taxmonomy/product-taxmonomy-details.vue';
import ProductTaxmonomyService from '../../../../../../main/webapp/app/entities/product-taxmonomy/product-taxmonomy.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProductTaxmonomyDetailsComponentType = InstanceType<typeof ProductTaxmonomyDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const productTaxmonomySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProductTaxmonomy Management Detail Component', () => {
    let productTaxmonomyServiceStub: SinonStubbedInstance<ProductTaxmonomyService>;
    let mountOptions: MountingOptions<ProductTaxmonomyDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      productTaxmonomyServiceStub = sinon.createStubInstance<ProductTaxmonomyService>(ProductTaxmonomyService);

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
          productTaxmonomyService: () => productTaxmonomyServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        productTaxmonomyServiceStub.find.resolves(productTaxmonomySample);
        route = {
          params: {
            productTaxmonomyId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProductTaxmonomyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.productTaxmonomy).toMatchObject(productTaxmonomySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        productTaxmonomyServiceStub.find.resolves(productTaxmonomySample);
        const wrapper = shallowMount(ProductTaxmonomyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
