/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import MondayColumnDetails from '../../../../../../main/webapp/app/entities/monday-column/monday-column-details.vue';
import MondayColumnService from '../../../../../../main/webapp/app/entities/monday-column/monday-column.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type MondayColumnDetailsComponentType = InstanceType<typeof MondayColumnDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const mondayColumnSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MondayColumn Management Detail Component', () => {
    let mondayColumnServiceStub: SinonStubbedInstance<MondayColumnService>;
    let mountOptions: MountingOptions<MondayColumnDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      mondayColumnServiceStub = sinon.createStubInstance<MondayColumnService>(MondayColumnService);

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
          mondayColumnService: () => mondayColumnServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        mondayColumnServiceStub.find.resolves(mondayColumnSample);
        route = {
          params: {
            mondayColumnId: '' + 123,
          },
        };
        const wrapper = shallowMount(MondayColumnDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.mondayColumn).toMatchObject(mondayColumnSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        mondayColumnServiceStub.find.resolves(mondayColumnSample);
        const wrapper = shallowMount(MondayColumnDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
