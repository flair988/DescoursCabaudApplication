/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import AuthorityDetails from '../../../../../../main/webapp/app/entities/authority/authority-details.vue';
import AuthorityService from '../../../../../../main/webapp/app/entities/authority/authority.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type AuthorityDetailsComponentType = InstanceType<typeof AuthorityDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const authoritySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Authority Management Detail Component', () => {
    let authorityServiceStub: SinonStubbedInstance<AuthorityService>;
    let mountOptions: MountingOptions<AuthorityDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      authorityServiceStub = sinon.createStubInstance<AuthorityService>(AuthorityService);

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
          authorityService: () => authorityServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        authorityServiceStub.find.resolves(authoritySample);
        route = {
          params: {
            authorityId: '' + 123,
          },
        };
        const wrapper = shallowMount(AuthorityDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.authority).toMatchObject(authoritySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        authorityServiceStub.find.resolves(authoritySample);
        const wrapper = shallowMount(AuthorityDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
