/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Uom from '../../../../../../main/webapp/app/entities/uom/uom.vue';
import UomService from '../../../../../../main/webapp/app/entities/uom/uom.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type UomComponentType = InstanceType<typeof Uom>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Uom Management Component', () => {
    let uomServiceStub: SinonStubbedInstance<UomService>;
    let mountOptions: MountingOptions<UomComponentType>['global'];

    beforeEach(() => {
      uomServiceStub = sinon.createStubInstance<UomService>(UomService);
      uomServiceStub.retrieve.resolves({ headers: {} });

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
          uomService: () => uomServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uomServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Uom, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(uomServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.uoms[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: UomComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Uom, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        uomServiceStub.retrieve.reset();
        uomServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        uomServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeUom();
        await comp.$nextTick(); // clear components

        // THEN
        expect(uomServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(uomServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
