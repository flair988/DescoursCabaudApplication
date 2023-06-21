/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Quotation from '../../../../../../main/webapp/app/entities/quotation/quotation.vue';
import QuotationService from '../../../../../../main/webapp/app/entities/quotation/quotation.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type QuotationComponentType = InstanceType<typeof Quotation>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Quotation Management Component', () => {
    let quotationServiceStub: SinonStubbedInstance<QuotationService>;
    let mountOptions: MountingOptions<QuotationComponentType>['global'];

    beforeEach(() => {
      quotationServiceStub = sinon.createStubInstance<QuotationService>(QuotationService);
      quotationServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          quotationService: () => quotationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        quotationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Quotation, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(quotationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.quotations[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QuotationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Quotation, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        quotationServiceStub.retrieve.reset();
        quotationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        quotationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQuotation();
        await comp.$nextTick(); // clear components

        // THEN
        expect(quotationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(quotationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
