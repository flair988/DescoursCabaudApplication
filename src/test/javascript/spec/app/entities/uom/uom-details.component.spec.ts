/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import UomDetails from '../../../../../../main/webapp/app/entities/uom/uom-details.vue';
import UomService from '../../../../../../main/webapp/app/entities/uom/uom.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type UomDetailsComponentType = InstanceType<typeof UomDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uomSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Uom Management Detail Component', () => {
    let uomServiceStub: SinonStubbedInstance<UomService>;
    let mountOptions: MountingOptions<UomDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      uomServiceStub = sinon.createStubInstance<UomService>(UomService);

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
          uomService: () => uomServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        uomServiceStub.find.resolves(uomSample);
        route = {
          params: {
            uomId: '' + 123,
          },
        };
        const wrapper = shallowMount(UomDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.uom).toMatchObject(uomSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uomServiceStub.find.resolves(uomSample);
        const wrapper = shallowMount(UomDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
