/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OPSiteQualificationDetails from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification-details.vue';
import OPSiteQualificationService from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OPSiteQualificationDetailsComponentType = InstanceType<typeof OPSiteQualificationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const oPSiteQualificationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OPSiteQualification Management Detail Component', () => {
    let oPSiteQualificationServiceStub: SinonStubbedInstance<OPSiteQualificationService>;
    let mountOptions: MountingOptions<OPSiteQualificationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      oPSiteQualificationServiceStub = sinon.createStubInstance<OPSiteQualificationService>(OPSiteQualificationService);

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
          oPSiteQualificationService: () => oPSiteQualificationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        oPSiteQualificationServiceStub.find.resolves(oPSiteQualificationSample);
        route = {
          params: {
            oPSiteQualificationId: '' + 123,
          },
        };
        const wrapper = shallowMount(OPSiteQualificationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.oPSiteQualification).toMatchObject(oPSiteQualificationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        oPSiteQualificationServiceStub.find.resolves(oPSiteQualificationSample);
        const wrapper = shallowMount(OPSiteQualificationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
