/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OperationSiteAuditingDetails from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing-details.vue';
import OperationSiteAuditingService from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OperationSiteAuditingDetailsComponentType = InstanceType<typeof OperationSiteAuditingDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const operationSiteAuditingSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OperationSiteAuditing Management Detail Component', () => {
    let operationSiteAuditingServiceStub: SinonStubbedInstance<OperationSiteAuditingService>;
    let mountOptions: MountingOptions<OperationSiteAuditingDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      operationSiteAuditingServiceStub = sinon.createStubInstance<OperationSiteAuditingService>(OperationSiteAuditingService);

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
          operationSiteAuditingService: () => operationSiteAuditingServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        operationSiteAuditingServiceStub.find.resolves(operationSiteAuditingSample);
        route = {
          params: {
            operationSiteAuditingId: '' + 123,
          },
        };
        const wrapper = shallowMount(OperationSiteAuditingDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.operationSiteAuditing).toMatchObject(operationSiteAuditingSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        operationSiteAuditingServiceStub.find.resolves(operationSiteAuditingSample);
        const wrapper = shallowMount(OperationSiteAuditingDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
