/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import CommercialInvoice from '../../../../../../main/webapp/app/entities/commercial-invoice/commercial-invoice.vue';
import CommercialInvoiceService from '../../../../../../main/webapp/app/entities/commercial-invoice/commercial-invoice.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type CommercialInvoiceComponentType = InstanceType<typeof CommercialInvoice>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CommercialInvoice Management Component', () => {
    let commercialInvoiceServiceStub: SinonStubbedInstance<CommercialInvoiceService>;
    let mountOptions: MountingOptions<CommercialInvoiceComponentType>['global'];

    beforeEach(() => {
      commercialInvoiceServiceStub = sinon.createStubInstance<CommercialInvoiceService>(CommercialInvoiceService);
      commercialInvoiceServiceStub.retrieve.resolves({ headers: {} });

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
          commercialInvoiceService: () => commercialInvoiceServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        commercialInvoiceServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CommercialInvoice, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(commercialInvoiceServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.commercialInvoices[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CommercialInvoiceComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CommercialInvoice, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        commercialInvoiceServiceStub.retrieve.reset();
        commercialInvoiceServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        commercialInvoiceServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCommercialInvoice();
        await comp.$nextTick(); // clear components

        // THEN
        expect(commercialInvoiceServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(commercialInvoiceServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
