/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ForwarderDetails from '../../../../../../main/webapp/app/entities/forwarder/forwarder-details.vue';
import ForwarderService from '../../../../../../main/webapp/app/entities/forwarder/forwarder.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ForwarderDetailsComponentType = InstanceType<typeof ForwarderDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const forwarderSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Forwarder Management Detail Component', () => {
    let forwarderServiceStub: SinonStubbedInstance<ForwarderService>;
    let mountOptions: MountingOptions<ForwarderDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      forwarderServiceStub = sinon.createStubInstance<ForwarderService>(ForwarderService);

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
          forwarderService: () => forwarderServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        forwarderServiceStub.find.resolves(forwarderSample);
        route = {
          params: {
            forwarderId: '' + 123,
          },
        };
        const wrapper = shallowMount(ForwarderDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.forwarder).toMatchObject(forwarderSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        forwarderServiceStub.find.resolves(forwarderSample);
        const wrapper = shallowMount(ForwarderDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
