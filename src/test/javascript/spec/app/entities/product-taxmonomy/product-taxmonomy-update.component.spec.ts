/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ProductTaxmonomyUpdate from '../../../../../../main/webapp/app/entities/product-taxmonomy/product-taxmonomy-update.vue';
import ProductTaxmonomyService from '../../../../../../main/webapp/app/entities/product-taxmonomy/product-taxmonomy.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProductTaxmonomyUpdateComponentType = InstanceType<typeof ProductTaxmonomyUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const productTaxmonomySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProductTaxmonomyUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProductTaxmonomy Management Update Component', () => {
    let comp: ProductTaxmonomyUpdateComponentType;
    let productTaxmonomyServiceStub: SinonStubbedInstance<ProductTaxmonomyService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          productTaxmonomyService: () => productTaxmonomyServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ProductTaxmonomyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.productTaxmonomy = productTaxmonomySample;
        productTaxmonomyServiceStub.update.resolves(productTaxmonomySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productTaxmonomyServiceStub.update.calledWith(productTaxmonomySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        productTaxmonomyServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProductTaxmonomyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.productTaxmonomy = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productTaxmonomyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        productTaxmonomyServiceStub.find.resolves(productTaxmonomySample);
        productTaxmonomyServiceStub.retrieve.resolves([productTaxmonomySample]);

        // WHEN
        route = {
          params: {
            productTaxmonomyId: '' + productTaxmonomySample.id,
          },
        };
        const wrapper = shallowMount(ProductTaxmonomyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.productTaxmonomy).toMatchObject(productTaxmonomySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        productTaxmonomyServiceStub.find.resolves(productTaxmonomySample);
        const wrapper = shallowMount(ProductTaxmonomyUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
