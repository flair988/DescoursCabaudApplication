/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OperationSiteUpdate from '../../../../../../main/webapp/app/entities/operation-site/operation-site-update.vue';
import OperationSiteService from '../../../../../../main/webapp/app/entities/operation-site/operation-site.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OperationSiteUpdateComponentType = InstanceType<typeof OperationSiteUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const operationSiteSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OperationSiteUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OperationSite Management Update Component', () => {
    let comp: OperationSiteUpdateComponentType;
    let operationSiteServiceStub: SinonStubbedInstance<OperationSiteService>;

    beforeEach(() => {
      route = {};
      operationSiteServiceStub = sinon.createStubInstance<OperationSiteService>(OperationSiteService);

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
          operationSiteService: () => operationSiteServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(OperationSiteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.operationSite = operationSiteSample;
        operationSiteServiceStub.update.resolves(operationSiteSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(operationSiteServiceStub.update.calledWith(operationSiteSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        operationSiteServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OperationSiteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.operationSite = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(operationSiteServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        operationSiteServiceStub.find.resolves(operationSiteSample);
        operationSiteServiceStub.retrieve.resolves([operationSiteSample]);

        // WHEN
        route = {
          params: {
            operationSiteId: '' + operationSiteSample.id,
          },
        };
        const wrapper = shallowMount(OperationSiteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.operationSite).toMatchObject(operationSiteSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        operationSiteServiceStub.find.resolves(operationSiteSample);
        const wrapper = shallowMount(OperationSiteUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
