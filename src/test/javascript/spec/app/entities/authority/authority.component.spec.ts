/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Authority from '../../../../../../main/webapp/app/entities/authority/authority.vue';
import AuthorityService from '../../../../../../main/webapp/app/entities/authority/authority.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type AuthorityComponentType = InstanceType<typeof Authority>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Authority Management Component', () => {
    let authorityServiceStub: SinonStubbedInstance<AuthorityService>;
    let mountOptions: MountingOptions<AuthorityComponentType>['global'];

    beforeEach(() => {
      authorityServiceStub = sinon.createStubInstance<AuthorityService>(AuthorityService);
      authorityServiceStub.retrieve.resolves({ headers: {} });

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
          authorityService: () => authorityServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        authorityServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Authority, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(authorityServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.authorities[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: AuthorityComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Authority, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        authorityServiceStub.retrieve.reset();
        authorityServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        authorityServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeAuthority();
        await comp.$nextTick(); // clear components

        // THEN
        expect(authorityServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(authorityServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
