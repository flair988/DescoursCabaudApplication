/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import OPSiteQualification from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification.vue';
import OPSiteQualificationService from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type OPSiteQualificationComponentType = InstanceType<typeof OPSiteQualification>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('OPSiteQualification Management Component', () => {
    let oPSiteQualificationServiceStub: SinonStubbedInstance<OPSiteQualificationService>;
    let mountOptions: MountingOptions<OPSiteQualificationComponentType>['global'];

    beforeEach(() => {
      oPSiteQualificationServiceStub = sinon.createStubInstance<OPSiteQualificationService>(OPSiteQualificationService);
      oPSiteQualificationServiceStub.retrieve.resolves({ headers: {} });

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
          oPSiteQualificationService: () => oPSiteQualificationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        oPSiteQualificationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(OPSiteQualification, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(oPSiteQualificationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.oPSiteQualifications[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OPSiteQualificationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(OPSiteQualification, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        oPSiteQualificationServiceStub.retrieve.reset();
        oPSiteQualificationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        oPSiteQualificationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOPSiteQualification();
        await comp.$nextTick(); // clear components

        // THEN
        expect(oPSiteQualificationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(oPSiteQualificationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
