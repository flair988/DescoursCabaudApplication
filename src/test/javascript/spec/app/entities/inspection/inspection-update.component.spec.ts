/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import InspectionUpdate from '../../../../../../main/webapp/app/entities/inspection/inspection-update.vue';
import InspectionService from '../../../../../../main/webapp/app/entities/inspection/inspection.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type InspectionUpdateComponentType = InstanceType<typeof InspectionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const inspectionSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<InspectionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Inspection Management Update Component', () => {
    let comp: InspectionUpdateComponentType;
    let inspectionServiceStub: SinonStubbedInstance<InspectionService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          inspectionService: () => inspectionServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(InspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.inspection = inspectionSample;
        inspectionServiceStub.update.resolves(inspectionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(inspectionServiceStub.update.calledWith(inspectionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        inspectionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(InspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.inspection = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(inspectionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        inspectionServiceStub.find.resolves(inspectionSample);
        inspectionServiceStub.retrieve.resolves([inspectionSample]);

        // WHEN
        route = {
          params: {
            inspectionId: '' + inspectionSample.id,
          },
        };
        const wrapper = shallowMount(InspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.inspection).toMatchObject(inspectionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        inspectionServiceStub.find.resolves(inspectionSample);
        const wrapper = shallowMount(InspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
