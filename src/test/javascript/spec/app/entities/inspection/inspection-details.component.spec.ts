/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import InspectionDetails from '../../../../../../main/webapp/app/entities/inspection/inspection-details.vue';
import InspectionService from '../../../../../../main/webapp/app/entities/inspection/inspection.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type InspectionDetailsComponentType = InstanceType<typeof InspectionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const inspectionSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Inspection Management Detail Component', () => {
    let inspectionServiceStub: SinonStubbedInstance<InspectionService>;
    let mountOptions: MountingOptions<InspectionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      inspectionServiceStub = sinon.createStubInstance<InspectionService>(InspectionService);

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
          inspectionService: () => inspectionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        inspectionServiceStub.find.resolves(inspectionSample);
        route = {
          params: {
            inspectionId: '' + 123,
          },
        };
        const wrapper = shallowMount(InspectionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.inspection).toMatchObject(inspectionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        inspectionServiceStub.find.resolves(inspectionSample);
        const wrapper = shallowMount(InspectionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
