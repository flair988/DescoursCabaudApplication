/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import SalesDeliveryUpdate from '../../../../../../main/webapp/app/entities/sales-delivery/sales-delivery-update.vue';
import SalesDeliveryService from '../../../../../../main/webapp/app/entities/sales-delivery/sales-delivery.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type SalesDeliveryUpdateComponentType = InstanceType<typeof SalesDeliveryUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const salesDeliverySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SalesDeliveryUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SalesDelivery Management Update Component', () => {
    let comp: SalesDeliveryUpdateComponentType;
    let salesDeliveryServiceStub: SinonStubbedInstance<SalesDeliveryService>;

    beforeEach(() => {
      route = {};
      salesDeliveryServiceStub = sinon.createStubInstance<SalesDeliveryService>(SalesDeliveryService);

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
          salesDeliveryService: () => salesDeliveryServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SalesDeliveryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.salesDelivery = salesDeliverySample;
        salesDeliveryServiceStub.update.resolves(salesDeliverySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(salesDeliveryServiceStub.update.calledWith(salesDeliverySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        salesDeliveryServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SalesDeliveryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.salesDelivery = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(salesDeliveryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        salesDeliveryServiceStub.find.resolves(salesDeliverySample);
        salesDeliveryServiceStub.retrieve.resolves([salesDeliverySample]);

        // WHEN
        route = {
          params: {
            salesDeliveryId: '' + salesDeliverySample.id,
          },
        };
        const wrapper = shallowMount(SalesDeliveryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.salesDelivery).toMatchObject(salesDeliverySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        salesDeliveryServiceStub.find.resolves(salesDeliverySample);
        const wrapper = shallowMount(SalesDeliveryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
