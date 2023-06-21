/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import ItemDetails from '../../../../../../main/webapp/app/entities/item/item-details.vue';
import ItemService from '../../../../../../main/webapp/app/entities/item/item.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ItemDetailsComponentType = InstanceType<typeof ItemDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const itemSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Item Management Detail Component', () => {
    let itemServiceStub: SinonStubbedInstance<ItemService>;
    let mountOptions: MountingOptions<ItemDetailsComponentType>['global'];

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
          'router-link': true,
        },
        provide: {
          alertService,
          itemService: () => itemServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        itemServiceStub.find.resolves(itemSample);
        route = {
          params: {
            itemId: '' + 123,
          },
        };
        const wrapper = shallowMount(ItemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.item).toMatchObject(itemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        itemServiceStub.find.resolves(itemSample);
        const wrapper = shallowMount(ItemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
