/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ForwarderBookingService from '../../../../../../main/webapp/app/entities/forwarder-booking/forwarder-booking.service';
import { ForwarderBooking } from '../../../../../../main/webapp/app/shared/model/forwarder-booking.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('ForwarderBooking Service', () => {
    let service: ForwarderBookingService;
    let elemDefault;

    beforeEach(() => {
      service = new ForwarderBookingService();
      elemDefault = new ForwarderBooking(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a ForwarderBooking', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ForwarderBooking', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ForwarderBooking', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            customer: 'BBBBBB',
            orderDate: 'BBBBBB',
            forwarder: 'BBBBBB',
            totalQty: 'BBBBBB',
            loadingPort: 'BBBBBB',
            dischargePort: 'BBBBBB',
            containerType: 'BBBBBB',
            containerSize: 'BBBBBB',
            containerNumber: 'BBBBBB',
            supplier: 'BBBBBB',
            supplierEmail: 'BBBBBB',
            eta: 'BBBBBB',
            etd: 'BBBBBB',
            transportMode: 'BBBBBB',
            numberOfCartons: 'BBBBBB',
            numberOfRef: 'BBBBBB',
            totalVolume: 'BBBBBB',
            totalWeight: 'BBBBBB',
            remark: 'BBBBBB',
            client: 'BBBBBB',
            kingdeeUniqueId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ForwarderBooking', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ForwarderBooking', async () => {
        const patchObject = Object.assign(
          {
            itemName: 'BBBBBB',
            orderDate: 'BBBBBB',
            loadingPort: 'BBBBBB',
            supplierEmail: 'BBBBBB',
            numberOfRef: 'BBBBBB',
            client: 'BBBBBB',
          },
          new ForwarderBooking()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ForwarderBooking', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ForwarderBooking', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            customer: 'BBBBBB',
            orderDate: 'BBBBBB',
            forwarder: 'BBBBBB',
            totalQty: 'BBBBBB',
            loadingPort: 'BBBBBB',
            dischargePort: 'BBBBBB',
            containerType: 'BBBBBB',
            containerSize: 'BBBBBB',
            containerNumber: 'BBBBBB',
            supplier: 'BBBBBB',
            supplierEmail: 'BBBBBB',
            eta: 'BBBBBB',
            etd: 'BBBBBB',
            transportMode: 'BBBBBB',
            numberOfCartons: 'BBBBBB',
            numberOfRef: 'BBBBBB',
            totalVolume: 'BBBBBB',
            totalWeight: 'BBBBBB',
            remark: 'BBBBBB',
            client: 'BBBBBB',
            kingdeeUniqueId: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ForwarderBooking', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ForwarderBooking', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ForwarderBooking', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
