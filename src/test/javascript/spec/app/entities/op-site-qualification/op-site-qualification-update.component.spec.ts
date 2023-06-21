/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OPSiteQualificationUpdate from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification-update.vue';
import OPSiteQualificationService from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OPSiteQualificationUpdateComponentType = InstanceType<typeof OPSiteQualificationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const oPSiteQualificationSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OPSiteQualificationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OPSiteQualification Management Update Component', () => {
    let comp: OPSiteQualificationUpdateComponentType;
    let oPSiteQualificationServiceStub: SinonStubbedInstance<OPSiteQualificationService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          oPSiteQualificationService: () => oPSiteQualificationServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(OPSiteQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.oPSiteQualification = oPSiteQualificationSample;
        oPSiteQualificationServiceStub.update.resolves(oPSiteQualificationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(oPSiteQualificationServiceStub.update.calledWith(oPSiteQualificationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        oPSiteQualificationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OPSiteQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.oPSiteQualification = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(oPSiteQualificationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        oPSiteQualificationServiceStub.find.resolves(oPSiteQualificationSample);
        oPSiteQualificationServiceStub.retrieve.resolves([oPSiteQualificationSample]);

        // WHEN
        route = {
          params: {
            oPSiteQualificationId: '' + oPSiteQualificationSample.id,
          },
        };
        const wrapper = shallowMount(OPSiteQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.oPSiteQualification).toMatchObject(oPSiteQualificationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        oPSiteQualificationServiceStub.find.resolves(oPSiteQualificationSample);
        const wrapper = shallowMount(OPSiteQualificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
