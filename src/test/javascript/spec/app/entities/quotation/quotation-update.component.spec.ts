/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import QuotationUpdate from '../../../../../../main/webapp/app/entities/quotation/quotation-update.vue';
import QuotationService from '../../../../../../main/webapp/app/entities/quotation/quotation.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type QuotationUpdateComponentType = InstanceType<typeof QuotationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const quotationSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QuotationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Quotation Management Update Component', () => {
    let comp: QuotationUpdateComponentType;
    let quotationServiceStub: SinonStubbedInstance<QuotationService>;

    beforeEach(() => {
      route = {};
      quotationServiceStub = sinon.createStubInstance<QuotationService>(QuotationService);

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
          quotationService: () => quotationServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(QuotationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.quotation = quotationSample;
        quotationServiceStub.update.resolves(quotationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(quotationServiceStub.update.calledWith(quotationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        quotationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QuotationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.quotation = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(quotationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        quotationServiceStub.find.resolves(quotationSample);
        quotationServiceStub.retrieve.resolves([quotationSample]);

        // WHEN
        route = {
          params: {
            quotationId: '' + quotationSample.id,
          },
        };
        const wrapper = shallowMount(QuotationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.quotation).toMatchObject(quotationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        quotationServiceStub.find.resolves(quotationSample);
        const wrapper = shallowMount(QuotationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
