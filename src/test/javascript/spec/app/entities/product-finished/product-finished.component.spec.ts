/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import ProductFinished from '../../../../../../main/webapp/app/entities/product-finished/product-finished.vue';
import ProductFinishedService from '../../../../../../main/webapp/app/entities/product-finished/product-finished.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProductFinishedComponentType = InstanceType<typeof ProductFinished>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProductFinished Management Component', () => {
    let productFinishedServiceStub: SinonStubbedInstance<ProductFinishedService>;
    let mountOptions: MountingOptions<ProductFinishedComponentType>['global'];

    beforeEach(() => {
      productFinishedServiceStub = sinon.createStubInstance<ProductFinishedService>(ProductFinishedService);
      productFinishedServiceStub.retrieve.resolves({ headers: {} });

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
          productFinishedService: () => productFinishedServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        productFinishedServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ProductFinished, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(productFinishedServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.productFinisheds[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProductFinishedComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProductFinished, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        productFinishedServiceStub.retrieve.reset();
        productFinishedServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        productFinishedServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProductFinished();
        await comp.$nextTick(); // clear components

        // THEN
        expect(productFinishedServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(productFinishedServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
