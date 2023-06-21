/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import PIUpdate from '../../../../../../main/webapp/app/entities/pi/pi-update.vue';
import PIService from '../../../../../../main/webapp/app/entities/pi/pi.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type PIUpdateComponentType = InstanceType<typeof PIUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pISample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PIUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('PI Management Update Component', () => {
    let comp: PIUpdateComponentType;
    let pIServiceStub: SinonStubbedInstance<PIService>;

    beforeEach(() => {
      route = {};
      pIServiceStub = sinon.createStubInstance<PIService>(PIService);

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
          pIService: () => pIServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(PIUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pI = pISample;
        pIServiceStub.update.resolves(pISample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pIServiceStub.update.calledWith(pISample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        pIServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PIUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.pI = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pIServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        pIServiceStub.find.resolves(pISample);
        pIServiceStub.retrieve.resolves([pISample]);

        // WHEN
        route = {
          params: {
            pIId: '' + pISample.id,
          },
        };
        const wrapper = shallowMount(PIUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.pI).toMatchObject(pISample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pIServiceStub.find.resolves(pISample);
        const wrapper = shallowMount(PIUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
