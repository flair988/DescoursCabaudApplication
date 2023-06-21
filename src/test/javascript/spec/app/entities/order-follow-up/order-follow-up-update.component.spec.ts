/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OrderFollowUpUpdate from '../../../../../../main/webapp/app/entities/order-follow-up/order-follow-up-update.vue';
import OrderFollowUpService from '../../../../../../main/webapp/app/entities/order-follow-up/order-follow-up.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OrderFollowUpUpdateComponentType = InstanceType<typeof OrderFollowUpUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const orderFollowUpSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OrderFollowUpUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OrderFollowUp Management Update Component', () => {
    let comp: OrderFollowUpUpdateComponentType;
    let orderFollowUpServiceStub: SinonStubbedInstance<OrderFollowUpService>;

    beforeEach(() => {
      route = {};
      orderFollowUpServiceStub = sinon.createStubInstance<OrderFollowUpService>(OrderFollowUpService);

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
          orderFollowUpService: () => orderFollowUpServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(OrderFollowUpUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.orderFollowUp = orderFollowUpSample;
        orderFollowUpServiceStub.update.resolves(orderFollowUpSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(orderFollowUpServiceStub.update.calledWith(orderFollowUpSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        orderFollowUpServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OrderFollowUpUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.orderFollowUp = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(orderFollowUpServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        orderFollowUpServiceStub.find.resolves(orderFollowUpSample);
        orderFollowUpServiceStub.retrieve.resolves([orderFollowUpSample]);

        // WHEN
        route = {
          params: {
            orderFollowUpId: '' + orderFollowUpSample.id,
          },
        };
        const wrapper = shallowMount(OrderFollowUpUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.orderFollowUp).toMatchObject(orderFollowUpSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        orderFollowUpServiceStub.find.resolves(orderFollowUpSample);
        const wrapper = shallowMount(OrderFollowUpUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
