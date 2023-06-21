/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Forwarder from '../../../../../../main/webapp/app/entities/forwarder/forwarder.vue';
import ForwarderService from '../../../../../../main/webapp/app/entities/forwarder/forwarder.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ForwarderComponentType = InstanceType<typeof Forwarder>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Forwarder Management Component', () => {
    let forwarderServiceStub: SinonStubbedInstance<ForwarderService>;
    let mountOptions: MountingOptions<ForwarderComponentType>['global'];

    beforeEach(() => {
      forwarderServiceStub = sinon.createStubInstance<ForwarderService>(ForwarderService);
      forwarderServiceStub.retrieve.resolves({ headers: {} });

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
          forwarderService: () => forwarderServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        forwarderServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Forwarder, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(forwarderServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.forwarders[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ForwarderComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Forwarder, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        forwarderServiceStub.retrieve.reset();
        forwarderServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        forwarderServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeForwarder();
        await comp.$nextTick(); // clear components

        // THEN
        expect(forwarderServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(forwarderServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
