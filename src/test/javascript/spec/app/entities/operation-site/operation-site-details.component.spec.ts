/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OperationSiteDetails from '../../../../../../main/webapp/app/entities/operation-site/operation-site-details.vue';
import OperationSiteService from '../../../../../../main/webapp/app/entities/operation-site/operation-site.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OperationSiteDetailsComponentType = InstanceType<typeof OperationSiteDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const operationSiteSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OperationSite Management Detail Component', () => {
    let operationSiteServiceStub: SinonStubbedInstance<OperationSiteService>;
    let mountOptions: MountingOptions<OperationSiteDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      operationSiteServiceStub = sinon.createStubInstance<OperationSiteService>(OperationSiteService);

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
          operationSiteService: () => operationSiteServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        operationSiteServiceStub.find.resolves(operationSiteSample);
        route = {
          params: {
            operationSiteId: '' + 123,
          },
        };
        const wrapper = shallowMount(OperationSiteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.operationSite).toMatchObject(operationSiteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        operationSiteServiceStub.find.resolves(operationSiteSample);
        const wrapper = shallowMount(OperationSiteDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
