/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import MondayColumn from '../../../../../../main/webapp/app/entities/monday-column/monday-column.vue';
import MondayColumnService from '../../../../../../main/webapp/app/entities/monday-column/monday-column.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type MondayColumnComponentType = InstanceType<typeof MondayColumn>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MondayColumn Management Component', () => {
    let mondayColumnServiceStub: SinonStubbedInstance<MondayColumnService>;
    let mountOptions: MountingOptions<MondayColumnComponentType>['global'];

    beforeEach(() => {
      mondayColumnServiceStub = sinon.createStubInstance<MondayColumnService>(MondayColumnService);
      mondayColumnServiceStub.retrieve.resolves({ headers: {} });

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
          mondayColumnService: () => mondayColumnServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        mondayColumnServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MondayColumn, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(mondayColumnServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.mondayColumns[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: MondayColumnComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MondayColumn, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        mondayColumnServiceStub.retrieve.reset();
        mondayColumnServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        mondayColumnServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMondayColumn();
        await comp.$nextTick(); // clear components

        // THEN
        expect(mondayColumnServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(mondayColumnServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
