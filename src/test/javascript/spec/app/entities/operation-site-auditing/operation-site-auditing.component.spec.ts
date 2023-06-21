/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import OperationSiteAuditing from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing.vue';
import OperationSiteAuditingService from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OperationSiteAuditingComponentType = InstanceType<typeof OperationSiteAuditing>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OperationSiteAuditing Management Component', () => {
    let operationSiteAuditingServiceStub: SinonStubbedInstance<OperationSiteAuditingService>;
    let mountOptions: MountingOptions<OperationSiteAuditingComponentType>['global'];

    beforeEach(() => {
      operationSiteAuditingServiceStub = sinon.createStubInstance<OperationSiteAuditingService>(OperationSiteAuditingService);
      operationSiteAuditingServiceStub.retrieve.resolves({ headers: {} });

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
          operationSiteAuditingService: () => operationSiteAuditingServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        operationSiteAuditingServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OperationSiteAuditing, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(operationSiteAuditingServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.operationSiteAuditings[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OperationSiteAuditingComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OperationSiteAuditing, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        operationSiteAuditingServiceStub.retrieve.reset();
        operationSiteAuditingServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        operationSiteAuditingServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOperationSiteAuditing();
        await comp.$nextTick(); // clear components

        // THEN
        expect(operationSiteAuditingServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(operationSiteAuditingServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
