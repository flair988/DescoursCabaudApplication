/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import AuthorityUpdate from '../../../../../../main/webapp/app/entities/authority/authority-update.vue';
import AuthorityService from '../../../../../../main/webapp/app/entities/authority/authority.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type AuthorityUpdateComponentType = InstanceType<typeof AuthorityUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const authoritySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<AuthorityUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Authority Management Update Component', () => {
    let comp: AuthorityUpdateComponentType;
    let authorityServiceStub: SinonStubbedInstance<AuthorityService>;

    beforeEach(() => {
      route = {};
      authorityServiceStub = sinon.createStubInstance<AuthorityService>(AuthorityService);

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
          authorityService: () => authorityServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(AuthorityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.authority = authoritySample;
        authorityServiceStub.update.resolves(authoritySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(authorityServiceStub.update.calledWith(authoritySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        authorityServiceStub.create.resolves(entity);
        const wrapper = shallowMount(AuthorityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.authority = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(authorityServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        authorityServiceStub.find.resolves(authoritySample);
        authorityServiceStub.retrieve.resolves([authoritySample]);

        // WHEN
        route = {
          params: {
            authorityId: '' + authoritySample.id,
          },
        };
        const wrapper = shallowMount(AuthorityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.authority).toMatchObject(authoritySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        authorityServiceStub.find.resolves(authoritySample);
        const wrapper = shallowMount(AuthorityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
