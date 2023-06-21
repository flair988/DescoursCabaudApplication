/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import Inspection from '../../../../../../main/webapp/app/entities/inspection/inspection.vue';
import InspectionService from '../../../../../../main/webapp/app/entities/inspection/inspection.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type InspectionComponentType = InstanceType<typeof Inspection>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Inspection Management Component', () => {
    let inspectionServiceStub: SinonStubbedInstance<InspectionService>;
    let mountOptions: MountingOptions<InspectionComponentType>['global'];

    beforeEach(() => {
      inspectionServiceStub = sinon.createStubInstance<InspectionService>(InspectionService);
      inspectionServiceStub.retrieve.resolves({ headers: {} });

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
          inspectionService: () => inspectionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        inspectionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Inspection, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(inspectionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.inspections[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: InspectionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Inspection, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        inspectionServiceStub.retrieve.reset();
        inspectionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        inspectionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeInspection();
        await comp.$nextTick(); // clear components

        // THEN
        expect(inspectionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(inspectionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
