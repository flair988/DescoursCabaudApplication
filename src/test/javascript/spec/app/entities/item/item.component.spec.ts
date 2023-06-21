/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Item from '../../../../../../main/webapp/app/entities/item/item.vue';
import ItemService from '../../../../../../main/webapp/app/entities/item/item.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type ItemComponentType = InstanceType<typeof Item>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Item Management Component', () => {
    let itemServiceStub: SinonStubbedInstance<ItemService>;
    let mountOptions: MountingOptions<ItemComponentType>['global'];

    beforeEach(() => {
      itemServiceStub = sinon.createStubInstance<ItemService>(ItemService);
      itemServiceStub.retrieve.resolves({ headers: {} });

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
          itemService: () => itemServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        itemServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Item, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(itemServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.items[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ItemComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Item, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        itemServiceStub.retrieve.reset();
        itemServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        itemServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeItem();
        await comp.$nextTick(); // clear components

        // THEN
        expect(itemServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(itemServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
