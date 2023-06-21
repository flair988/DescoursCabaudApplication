/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ProcessLogDetails from '../../../../../../main/webapp/app/entities/process-log/process-log-details.vue';
import ProcessLogService from '../../../../../../main/webapp/app/entities/process-log/process-log.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ProcessLogDetailsComponentType = InstanceType<typeof ProcessLogDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const processLogSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProcessLog Management Detail Component', () => {
    let processLogServiceStub: SinonStubbedInstance<ProcessLogService>;
    let mountOptions: MountingOptions<ProcessLogDetailsComponentType>['global'];

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
          'router-link': true,
        },
        provide: {
          alertService,
          processLogService: () => processLogServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        processLogServiceStub.find.resolves(processLogSample);
        route = {
          params: {
            processLogId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProcessLogDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.processLog).toMatchObject(processLogSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        processLogServiceStub.find.resolves(processLogSample);
        const wrapper = shallowMount(ProcessLogDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
