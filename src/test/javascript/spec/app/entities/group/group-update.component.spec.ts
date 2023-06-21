/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import GroupUpdate from '../../../../../../main/webapp/app/entities/group/group-update.vue';
import GroupService from '../../../../../../main/webapp/app/entities/group/group.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type GroupUpdateComponentType = InstanceType<typeof GroupUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const groupSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<GroupUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Group Management Update Component', () => {
    let comp: GroupUpdateComponentType;
    let groupServiceStub: SinonStubbedInstance<GroupService>;

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
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          groupService: () => groupServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(GroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.group = groupSample;
        groupServiceStub.update.resolves(groupSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(groupServiceStub.update.calledWith(groupSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        groupServiceStub.create.resolves(entity);
        const wrapper = shallowMount(GroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.group = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(groupServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        groupServiceStub.find.resolves(groupSample);
        groupServiceStub.retrieve.resolves([groupSample]);

        // WHEN
        route = {
          params: {
            groupId: '' + groupSample.id,
          },
        };
        const wrapper = shallowMount(GroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.group).toMatchObject(groupSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        groupServiceStub.find.resolves(groupSample);
        const wrapper = shallowMount(GroupUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
