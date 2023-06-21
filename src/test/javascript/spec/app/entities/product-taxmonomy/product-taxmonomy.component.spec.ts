/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import ProductTaxmonomy from '../../../../../../main/webapp/app/entities/product-taxmonomy/product-taxmonomy.vue';
import ProductTaxmonomyService from '../../../../../../main/webapp/app/entities/product-taxmonomy/product-taxmonomy.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProductTaxmonomyComponentType = InstanceType<typeof ProductTaxmonomy>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProductTaxmonomy Management Component', () => {
    let productTaxmonomyServiceStub: SinonStubbedInstance<ProductTaxmonomyService>;
    let mountOptions: MountingOptions<ProductTaxmonomyComponentType>['global'];

    beforeEach(() => {
      productTaxmonomyServiceStub = sinon.createStubInstance<ProductTaxmonomyService>(ProductTaxmonomyService);
      productTaxmonomyServiceStub.retrieve.resolves({ headers: {} });

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
          productTaxmonomyService: () => productTaxmonomyServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        productTaxmonomyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ProductTaxmonomy, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(productTaxmonomyServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.productTaxmonomies[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProductTaxmonomyComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProductTaxmonomy, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        productTaxmonomyServiceStub.retrieve.reset();
        productTaxmonomyServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        productTaxmonomyServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProductTaxmonomy();
        await comp.$nextTick(); // clear components

        // THEN
        expect(productTaxmonomyServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(productTaxmonomyServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
