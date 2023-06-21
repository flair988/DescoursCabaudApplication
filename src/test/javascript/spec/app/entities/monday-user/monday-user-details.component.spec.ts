/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import MondayUserDetails from '../../../../../../main/webapp/app/entities/monday-user/monday-user-details.vue';
import MondayUserService from '../../../../../../main/webapp/app/entities/monday-user/monday-user.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type MondayUserDetailsComponentType = InstanceType<typeof MondayUserDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const mondayUserSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MondayUser Management Detail Component', () => {
    let mondayUserServiceStub: SinonStubbedInstance<MondayUserService>;
    let mountOptions: MountingOptions<MondayUserDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      mondayUserServiceStub = sinon.createStubInstance<MondayUserService>(MondayUserService);

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
          mondayUserService: () => mondayUserServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        mondayUserServiceStub.find.resolves(mondayUserSample);
        route = {
          params: {
            mondayUserId: '' + 123,
          },
        };
        const wrapper = shallowMount(MondayUserDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.mondayUser).toMatchObject(mondayUserSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        mondayUserServiceStub.find.resolves(mondayUserSample);
        const wrapper = shallowMount(MondayUserDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
