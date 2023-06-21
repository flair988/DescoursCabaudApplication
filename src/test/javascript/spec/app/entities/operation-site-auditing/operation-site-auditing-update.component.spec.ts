/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import OperationSiteAuditingUpdate from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing-update.vue';
import OperationSiteAuditingService from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OperationSiteAuditingUpdateComponentType = InstanceType<typeof OperationSiteAuditingUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const operationSiteAuditingSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OperationSiteAuditingUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OperationSiteAuditing Management Update Component', () => {
    let comp: OperationSiteAuditingUpdateComponentType;
    let operationSiteAuditingServiceStub: SinonStubbedInstance<OperationSiteAuditingService>;

    beforeEach(() => {
      route = {};
      operationSiteAuditingServiceStub = sinon.createStubInstance<OperationSiteAuditingService>(OperationSiteAuditingService);

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
          operationSiteAuditingService: () => operationSiteAuditingServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(OperationSiteAuditingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.operationSiteAuditing = operationSiteAuditingSample;
        operationSiteAuditingServiceStub.update.resolves(operationSiteAuditingSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(operationSiteAuditingServiceStub.update.calledWith(operationSiteAuditingSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        operationSiteAuditingServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OperationSiteAuditingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.operationSiteAuditing = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(operationSiteAuditingServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        operationSiteAuditingServiceStub.find.resolves(operationSiteAuditingSample);
        operationSiteAuditingServiceStub.retrieve.resolves([operationSiteAuditingSample]);

        // WHEN
        route = {
          params: {
            operationSiteAuditingId: '' + operationSiteAuditingSample.id,
          },
        };
        const wrapper = shallowMount(OperationSiteAuditingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.operationSiteAuditing).toMatchObject(operationSiteAuditingSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        operationSiteAuditingServiceStub.find.resolves(operationSiteAuditingSample);
        const wrapper = shallowMount(OperationSiteAuditingUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
