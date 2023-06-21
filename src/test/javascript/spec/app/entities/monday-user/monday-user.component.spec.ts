/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import MondayUser from '../../../../../../main/webapp/app/entities/monday-user/monday-user.vue';
import MondayUserService from '../../../../../../main/webapp/app/entities/monday-user/monday-user.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type MondayUserComponentType = InstanceType<typeof MondayUser>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MondayUser Management Component', () => {
    let mondayUserServiceStub: SinonStubbedInstance<MondayUserService>;
    let mountOptions: MountingOptions<MondayUserComponentType>['global'];

    beforeEach(() => {
      mondayUserServiceStub = sinon.createStubInstance<MondayUserService>(MondayUserService);
      mondayUserServiceStub.retrieve.resolves({ headers: {} });

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
          mondayUserService: () => mondayUserServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        mondayUserServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MondayUser, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(mondayUserServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.mondayUsers[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: MondayUserComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MondayUser, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        mondayUserServiceStub.retrieve.reset();
        mondayUserServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        mondayUserServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMondayUser();
        await comp.$nextTick(); // clear components

        // THEN
        expect(mondayUserServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(mondayUserServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
