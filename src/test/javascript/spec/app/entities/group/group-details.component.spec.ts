/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import GroupDetails from '../../../../../../main/webapp/app/entities/group/group-details.vue';
import GroupService from '../../../../../../main/webapp/app/entities/group/group.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type GroupDetailsComponentType = InstanceType<typeof GroupDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const groupSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Group Management Detail Component', () => {
    let groupServiceStub: SinonStubbedInstance<GroupService>;
    let mountOptions: MountingOptions<GroupDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      groupServiceStub = sinon.createStubInstance<GroupService>(GroupService);

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
          groupService: () => groupServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        groupServiceStub.find.resolves(groupSample);
        route = {
          params: {
            groupId: '' + 123,
          },
        };
        const wrapper = shallowMount(GroupDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.group).toMatchObject(groupSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        groupServiceStub.find.resolves(groupSample);
        const wrapper = shallowMount(GroupDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
