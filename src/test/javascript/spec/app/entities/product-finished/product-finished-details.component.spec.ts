/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ProductFinishedDetails from '../../../../../../main/webapp/app/entities/product-finished/product-finished-details.vue';
import ProductFinishedService from '../../../../../../main/webapp/app/entities/product-finished/product-finished.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProductFinishedDetailsComponentType = InstanceType<typeof ProductFinishedDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const productFinishedSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProductFinished Management Detail Component', () => {
    let productFinishedServiceStub: SinonStubbedInstance<ProductFinishedService>;
    let mountOptions: MountingOptions<ProductFinishedDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      productFinishedServiceStub = sinon.createStubInstance<ProductFinishedService>(ProductFinishedService);

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
          productFinishedService: () => productFinishedServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        productFinishedServiceStub.find.resolves(productFinishedSample);
        route = {
          params: {
            productFinishedId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProductFinishedDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.productFinished).toMatchObject(productFinishedSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        productFinishedServiceStub.find.resolves(productFinishedSample);
        const wrapper = shallowMount(ProductFinishedDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
