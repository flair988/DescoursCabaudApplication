/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, MountingOptions } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { RouteLocation } from 'vue-router';

import PurchaseReturnDetails from '../../../../../../main/webapp/app/entities/purchase-return/purchase-return-details.vue';
import PurchaseReturnService from '../../../../../../main/webapp/app/entities/purchase-return/purchase-return.service';
import AlertService from '../../../../../../main/webapp/app/shared/alert/alert.service';

type PurchaseReturnDetailsComponentType = InstanceType<typeof PurchaseReturnDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const purchaseReturnSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('PurchaseReturn Management Detail Component', () => {
    let purchaseReturnServiceStub: SinonStubbedInstance<PurchaseReturnService>;
    let mountOptions: MountingOptions<PurchaseReturnDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      purchaseReturnServiceStub = sinon.createStubInstance<PurchaseReturnService>(PurchaseReturnService);

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
          purchaseReturnService: () => purchaseReturnServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        purchaseReturnServiceStub.find.resolves(purchaseReturnSample);
        route = {
          params: {
            purchaseReturnId: '' + 123,
          },
        };
        const wrapper = shallowMount(PurchaseReturnDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.purchaseReturn).toMatchObject(purchaseReturnSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        purchaseReturnServiceStub.find.resolves(purchaseReturnSample);
        const wrapper = shallowMount(PurchaseReturnDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
