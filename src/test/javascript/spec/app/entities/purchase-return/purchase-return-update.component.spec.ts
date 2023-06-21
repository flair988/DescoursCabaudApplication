/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import PurchaseReturnUpdate from '../../../../../../main/webapp/app/entities/purchase-return/purchase-return-update.vue';
import PurchaseReturnService from '../../../../../../main/webapp/app/entities/purchase-return/purchase-return.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type PurchaseReturnUpdateComponentType = InstanceType<typeof PurchaseReturnUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const purchaseReturnSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PurchaseReturnUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('PurchaseReturn Management Update Component', () => {
    let comp: PurchaseReturnUpdateComponentType;
    let purchaseReturnServiceStub: SinonStubbedInstance<PurchaseReturnService>;

    beforeEach(() => {
      route = {};
      purchaseReturnServiceStub = sinon.createStubInstance<PurchaseReturnService>(PurchaseReturnService);

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
          purchaseReturnService: () => purchaseReturnServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(PurchaseReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.purchaseReturn = purchaseReturnSample;
        purchaseReturnServiceStub.update.resolves(purchaseReturnSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(purchaseReturnServiceStub.update.calledWith(purchaseReturnSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        purchaseReturnServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PurchaseReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.purchaseReturn = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(purchaseReturnServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        purchaseReturnServiceStub.find.resolves(purchaseReturnSample);
        purchaseReturnServiceStub.retrieve.resolves([purchaseReturnSample]);

        // WHEN
        route = {
          params: {
            purchaseReturnId: '' + purchaseReturnSample.id,
          },
        };
        const wrapper = shallowMount(PurchaseReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.purchaseReturn).toMatchObject(purchaseReturnSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        purchaseReturnServiceStub.find.resolves(purchaseReturnSample);
        const wrapper = shallowMount(PurchaseReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
