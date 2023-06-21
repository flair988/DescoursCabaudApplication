/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Group from '../../../../../../main/webapp/app/entities/group/group.vue';
import GroupService from '../../../../../../main/webapp/app/entities/group/group.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type GroupComponentType = InstanceType<typeof Group>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Group Management Component', () => {
    let groupServiceStub: SinonStubbedInstance<GroupService>;
    let mountOptions: MountingOptions<GroupComponentType>['global'];

    beforeEach(() => {
      groupServiceStub = sinon.createStubInstance<GroupService>(GroupService);
      groupServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          groupService: () => groupServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        groupServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Group, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(groupServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.groups[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: GroupComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Group, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        groupServiceStub.retrieve.reset();
        groupServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        groupServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeGroup();
        await comp.$nextTick(); // clear components

        // THEN
        expect(groupServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(groupServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
