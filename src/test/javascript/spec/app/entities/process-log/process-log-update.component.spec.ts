/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ProcessLogUpdate from '../../../../../../main/webapp/app/entities/process-log/process-log-update.vue';
import ProcessLogService from '../../../../../../main/webapp/app/entities/process-log/process-log.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProcessLogUpdateComponentType = InstanceType<typeof ProcessLogUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const processLogSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProcessLogUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ProcessLog Management Update Component', () => {
    let comp: ProcessLogUpdateComponentType;
    let processLogServiceStub: SinonStubbedInstance<ProcessLogService>;

    beforeEach(() => {
      route = {};
      processLogServiceStub = sinon.createStubInstance<ProcessLogService>(ProcessLogService);

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
          processLogService: () => processLogServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ProcessLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.processLog = processLogSample;
        processLogServiceStub.update.resolves(processLogSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(processLogServiceStub.update.calledWith(processLogSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        processLogServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProcessLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.processLog = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(processLogServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        processLogServiceStub.find.resolves(processLogSample);
        processLogServiceStub.retrieve.resolves([processLogSample]);

        // WHEN
        route = {
          params: {
            processLogId: '' + processLogSample.id,
          },
        };
        const wrapper = shallowMount(ProcessLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.processLog).toMatchObject(processLogSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        processLogServiceStub.find.resolves(processLogSample);
        const wrapper = shallowMount(ProcessLogUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
