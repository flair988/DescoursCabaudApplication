/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import OperationSite from '../../../../../../main/webapp/app/entities/operation-site/operation-site.vue';
import OperationSiteService from '../../../../../../main/webapp/app/entities/operation-site/operation-site.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OperationSiteComponentType = InstanceType<typeof OperationSite>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OperationSite Management Component', () => {
    let operationSiteServiceStub: SinonStubbedInstance<OperationSiteService>;
    let mountOptions: MountingOptions<OperationSiteComponentType>['global'];

    beforeEach(() => {
      operationSiteServiceStub = sinon.createStubInstance<OperationSiteService>(OperationSiteService);
      operationSiteServiceStub.retrieve.resolves({ headers: {} });

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
          operationSiteService: () => operationSiteServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        operationSiteServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OperationSite, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(operationSiteServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.operationSites[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OperationSiteComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OperationSite, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        operationSiteServiceStub.retrieve.reset();
        operationSiteServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        operationSiteServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOperationSite();
        await comp.$nextTick(); // clear components

        // THEN
        expect(operationSiteServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(operationSiteServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
