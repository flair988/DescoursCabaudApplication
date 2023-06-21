/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import CommercialInvoiceUpdate from '../../../../../../main/webapp/app/entities/commercial-invoice/commercial-invoice-update.vue';
import CommercialInvoiceService from '../../../../../../main/webapp/app/entities/commercial-invoice/commercial-invoice.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type CommercialInvoiceUpdateComponentType = InstanceType<typeof CommercialInvoiceUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const commercialInvoiceSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CommercialInvoiceUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CommercialInvoice Management Update Component', () => {
    let comp: CommercialInvoiceUpdateComponentType;
    let commercialInvoiceServiceStub: SinonStubbedInstance<CommercialInvoiceService>;

    beforeEach(() => {
      route = {};
      commercialInvoiceServiceStub = sinon.createStubInstance<CommercialInvoiceService>(CommercialInvoiceService);

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
          commercialInvoiceService: () => commercialInvoiceServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(CommercialInvoiceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.commercialInvoice = commercialInvoiceSample;
        commercialInvoiceServiceStub.update.resolves(commercialInvoiceSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(commercialInvoiceServiceStub.update.calledWith(commercialInvoiceSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        commercialInvoiceServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CommercialInvoiceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.commercialInvoice = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(commercialInvoiceServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        commercialInvoiceServiceStub.find.resolves(commercialInvoiceSample);
        commercialInvoiceServiceStub.retrieve.resolves([commercialInvoiceSample]);

        // WHEN
        route = {
          params: {
            commercialInvoiceId: '' + commercialInvoiceSample.id,
          },
        };
        const wrapper = shallowMount(CommercialInvoiceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.commercialInvoice).toMatchObject(commercialInvoiceSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        commercialInvoiceServiceStub.find.resolves(commercialInvoiceSample);
        const wrapper = shallowMount(CommercialInvoiceUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
