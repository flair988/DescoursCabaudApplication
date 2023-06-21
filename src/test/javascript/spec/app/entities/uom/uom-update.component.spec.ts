/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import UomUpdate from '../../../../../../main/webapp/app/entities/uom/uom-update.vue';
import UomService from '../../../../../../main/webapp/app/entities/uom/uom.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type UomUpdateComponentType = InstanceType<typeof UomUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const uomSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<UomUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Uom Management Update Component', () => {
    let comp: UomUpdateComponentType;
    let uomServiceStub: SinonStubbedInstance<UomService>;

    beforeEach(() => {
      route = {};
      uomServiceStub = sinon.createStubInstance<UomService>(UomService);

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
          uomService: () => uomServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(UomUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uom = uomSample;
        uomServiceStub.update.resolves(uomSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uomServiceStub.update.calledWith(uomSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        uomServiceStub.create.resolves(entity);
        const wrapper = shallowMount(UomUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.uom = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(uomServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        uomServiceStub.find.resolves(uomSample);
        uomServiceStub.retrieve.resolves([uomSample]);

        // WHEN
        route = {
          params: {
            uomId: '' + uomSample.id,
          },
        };
        const wrapper = shallowMount(UomUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.uom).toMatchObject(uomSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        uomServiceStub.find.resolves(uomSample);
        const wrapper = shallowMount(UomUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
