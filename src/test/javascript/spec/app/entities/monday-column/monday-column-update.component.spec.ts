/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import MondayColumnUpdate from '../../../../../../main/webapp/app/entities/monday-column/monday-column-update.vue';
import MondayColumnService from '../../../../../../main/webapp/app/entities/monday-column/monday-column.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type MondayColumnUpdateComponentType = InstanceType<typeof MondayColumnUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const mondayColumnSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MondayColumnUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MondayColumn Management Update Component', () => {
    let comp: MondayColumnUpdateComponentType;
    let mondayColumnServiceStub: SinonStubbedInstance<MondayColumnService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          mondayColumnService: () => mondayColumnServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(MondayColumnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.mondayColumn = mondayColumnSample;
        mondayColumnServiceStub.update.resolves(mondayColumnSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mondayColumnServiceStub.update.calledWith(mondayColumnSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        mondayColumnServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MondayColumnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.mondayColumn = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mondayColumnServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        mondayColumnServiceStub.find.resolves(mondayColumnSample);
        mondayColumnServiceStub.retrieve.resolves([mondayColumnSample]);

        // WHEN
        route = {
          params: {
            mondayColumnId: '' + mondayColumnSample.id,
          },
        };
        const wrapper = shallowMount(MondayColumnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.mondayColumn).toMatchObject(mondayColumnSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        mondayColumnServiceStub.find.resolves(mondayColumnSample);
        const wrapper = shallowMount(MondayColumnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
