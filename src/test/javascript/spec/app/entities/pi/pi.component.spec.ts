/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import PI from '../../../../../../main/webapp/app/entities/pi/pi.vue';
import PIService from '../../../../../../main/webapp/app/entities/pi/pi.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type PIComponentType = InstanceType<typeof PI>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('PI Management Component', () => {
    let pIServiceStub: SinonStubbedInstance<PIService>;
    let mountOptions: MountingOptions<PIComponentType>['global'];

    beforeEach(() => {
      pIServiceStub = sinon.createStubInstance<PIService>(PIService);
      pIServiceStub.retrieve.resolves({ headers: {} });

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
          pIService: () => pIServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pIServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(PI, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(pIServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.pIS[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PIComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(PI, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        pIServiceStub.retrieve.reset();
        pIServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        pIServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePI();
        await comp.$nextTick(); // clear components

        // THEN
        expect(pIServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(pIServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
