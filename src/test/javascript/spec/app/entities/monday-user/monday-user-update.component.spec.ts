/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import MondayUserUpdate from '../../../../../../main/webapp/app/entities/monday-user/monday-user-update.vue';
import MondayUserService from '../../../../../../main/webapp/app/entities/monday-user/monday-user.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type MondayUserUpdateComponentType = InstanceType<typeof MondayUserUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const mondayUserSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MondayUserUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MondayUser Management Update Component', () => {
    let comp: MondayUserUpdateComponentType;
    let mondayUserServiceStub: SinonStubbedInstance<MondayUserService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          mondayUserService: () => mondayUserServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(MondayUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.mondayUser = mondayUserSample;
        mondayUserServiceStub.update.resolves(mondayUserSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mondayUserServiceStub.update.calledWith(mondayUserSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        mondayUserServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MondayUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.mondayUser = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(mondayUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        mondayUserServiceStub.find.resolves(mondayUserSample);
        mondayUserServiceStub.retrieve.resolves([mondayUserSample]);

        // WHEN
        route = {
          params: {
            mondayUserId: '' + mondayUserSample.id,
          },
        };
        const wrapper = shallowMount(MondayUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.mondayUser).toMatchObject(mondayUserSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        mondayUserServiceStub.find.resolves(mondayUserSample);
        const wrapper = shallowMount(MondayUserUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
