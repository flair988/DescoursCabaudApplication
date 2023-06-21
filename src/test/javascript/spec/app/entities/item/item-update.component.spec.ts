/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ItemUpdate from '../../../../../../main/webapp/app/entities/item/item-update.vue';
import ItemService from '../../../../../../main/webapp/app/entities/item/item.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ItemUpdateComponentType = InstanceType<typeof ItemUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const itemSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ItemUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Item Management Update Component', () => {
    let comp: ItemUpdateComponentType;
    let itemServiceStub: SinonStubbedInstance<ItemService>;

    beforeEach(() => {
      route = {};
      itemServiceStub = sinon.createStubInstance<ItemService>(ItemService);

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
          itemService: () => itemServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.item = itemSample;
        itemServiceStub.update.resolves(itemSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(itemServiceStub.update.calledWith(itemSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        itemServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.item = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(itemServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        itemServiceStub.find.resolves(itemSample);
        itemServiceStub.retrieve.resolves([itemSample]);

        // WHEN
        route = {
          params: {
            itemId: '' + itemSample.id,
          },
        };
        const wrapper = shallowMount(ItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.item).toMatchObject(itemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        itemServiceStub.find.resolves(itemSample);
        const wrapper = shallowMount(ItemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
