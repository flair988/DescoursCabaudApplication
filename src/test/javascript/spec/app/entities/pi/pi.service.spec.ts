/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import PIService from '../../../../../../main/webapp/app/entities/pi/pi.service';
import { PI } from '../../../../../../main/webapp/app/shared/model/pi.model';

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
  describe('PI Service', () => {
    let service: PIService;
    let elemDefault;

    beforeEach(() => {
      service = new PIService();
      elemDefault = new PI(
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

      it('should create a PI', async () => {
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

      it('should not create a PI', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a PI', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            poNumber: 'BBBBBB',
            category: 'BBBBBB',
            client: 'BBBBBB',
            orderDate: 'BBBBBB',
            requestedEndOfProdDate: 'BBBBBB',
            countryOfOrigin: 'BBBBBB',
            countryOfFinalDestination: 'BBBBBB',
            consignee: 'BBBBBB',
            carriageBy: 'BBBBBB',
            termsOfDelivery: 'BBBBBB',
            termsOfPayment: 'BBBBBB',
            currency: 'BBBBBB',
            remarks: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            portOfDischarge: 'BBBBBB',
            portOfLoading: 'BBBBBB',
            docStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a PI', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a PI', async () => {
        const patchObject = Object.assign(
          {
            countryOfFinalDestination: 'BBBBBB',
            consignee: 'BBBBBB',
            carriageBy: 'BBBBBB',
            termsOfPayment: 'BBBBBB',
            portOfDischarge: 'BBBBBB',
            portOfLoading: 'BBBBBB',
          },
          new PI()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a PI', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of PI', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            poNumber: 'BBBBBB',
            category: 'BBBBBB',
            client: 'BBBBBB',
            orderDate: 'BBBBBB',
            requestedEndOfProdDate: 'BBBBBB',
            countryOfOrigin: 'BBBBBB',
            countryOfFinalDestination: 'BBBBBB',
            consignee: 'BBBBBB',
            carriageBy: 'BBBBBB',
            termsOfDelivery: 'BBBBBB',
            termsOfPayment: 'BBBBBB',
            currency: 'BBBBBB',
            remarks: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            portOfDischarge: 'BBBBBB',
            portOfLoading: 'BBBBBB',
            docStatus: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of PI', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a PI', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a PI', async () => {
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
