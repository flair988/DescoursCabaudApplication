/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import PIDetails from '../../../../../../main/webapp/app/entities/pi/pi-details.vue';
import PIService from '../../../../../../main/webapp/app/entities/pi/pi.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type PIDetailsComponentType = InstanceType<typeof PIDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pISample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('PI Management Detail Component', () => {
    let pIServiceStub: SinonStubbedInstance<PIService>;
    let mountOptions: MountingOptions<PIDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      pIServiceStub = sinon.createStubInstance<PIService>(PIService);

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
          pIService: () => pIServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pIServiceStub.find.resolves(pISample);
        route = {
          params: {
            pIId: '' + 123,
          },
        };
        const wrapper = shallowMount(PIDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.pI).toMatchObject(pISample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pIServiceStub.find.resolves(pISample);
        const wrapper = shallowMount(PIDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
