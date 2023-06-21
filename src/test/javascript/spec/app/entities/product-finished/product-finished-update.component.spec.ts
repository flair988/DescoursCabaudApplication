/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ProductFinishedUpdate from '../../../../../../main/webapp/app/entities/product-finished/product-finished-update.vue';
import ProductFinishedService from '../../../../../../main/webapp/app/entities/product-finished/product-finished.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProductFinishedUpdateComponentType = InstanceType<typeof ProductFinishedUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const productFinishedSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProductFinishedUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProductFinished Management Update Component', () => {
    let comp: ProductFinishedUpdateComponentType;
    let productFinishedServiceStub: SinonStubbedInstance<ProductFinishedService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          productFinishedService: () => productFinishedServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ProductFinishedUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.productFinished = productFinishedSample;
        productFinishedServiceStub.update.resolves(productFinishedSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productFinishedServiceStub.update.calledWith(productFinishedSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        productFinishedServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProductFinishedUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.productFinished = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productFinishedServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        productFinishedServiceStub.find.resolves(productFinishedSample);
        productFinishedServiceStub.retrieve.resolves([productFinishedSample]);

        // WHEN
        route = {
          params: {
            productFinishedId: '' + productFinishedSample.id,
          },
        };
        const wrapper = shallowMount(ProductFinishedUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.productFinished).toMatchObject(productFinishedSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        productFinishedServiceStub.find.resolves(productFinishedSample);
        const wrapper = shallowMount(ProductFinishedUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
