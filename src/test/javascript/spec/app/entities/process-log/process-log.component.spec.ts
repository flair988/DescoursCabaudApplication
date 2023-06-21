/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import ProcessLog from '../../../../../../main/webapp/app/entities/process-log/process-log.vue';
import ProcessLogService from '../../../../../../main/webapp/app/entities/process-log/process-log.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProcessLogComponentType = InstanceType<typeof ProcessLog>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProcessLog Management Component', () => {
    let processLogServiceStub: SinonStubbedInstance<ProcessLogService>;
    let mountOptions: MountingOptions<ProcessLogComponentType>['global'];

    beforeEach(() => {
      processLogServiceStub = sinon.createStubInstance<ProcessLogService>(ProcessLogService);
      processLogServiceStub.retrieve.resolves({ headers: {} });

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
          processLogService: () => processLogServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        processLogServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ProcessLog, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(processLogServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.processLogs[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProcessLogComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProcessLog, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        processLogServiceStub.retrieve.reset();
        processLogServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        processLogServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProcessLog();
        await comp.$nextTick(); // clear components

        // THEN
        expect(processLogServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(processLogServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
