/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ProductFinishedService from '../../../../../../main/webapp/app/entities/product-finished/product-finished.service';
import { ProductFinished } from '../../../../../../main/webapp/app/shared/model/product-finished.model';

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
  describe('ProductFinished Service', () => {
    let service: ProductFinishedService;
    let elemDefault;

    beforeEach(() => {
      service = new ProductFinishedService();
      elemDefault = new ProductFinished(
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

      it('should create a ProductFinished', async () => {
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

      it('should not create a ProductFinished', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ProductFinished', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            supplier: 'BBBBBB',
            supplierEmail: 'BBBBBB',
            orderDate: 'BBBBBB',
            cateGory: 'BBBBBB',
            remark: 'BBBBBB',
            materialReceiptDate: 'BBBBBB',
            docStatus: 'BBBBBB',
            supplierName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ProductFinished', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ProductFinished', async () => {
        const patchObject = Object.assign(
          {
            itemName: 'BBBBBB',
            supplier: 'BBBBBB',
            remark: 'BBBBBB',
            materialReceiptDate: 'BBBBBB',
            docStatus: 'BBBBBB',
          },
          new ProductFinished()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ProductFinished', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ProductFinished', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            supplier: 'BBBBBB',
            supplierEmail: 'BBBBBB',
            orderDate: 'BBBBBB',
            cateGory: 'BBBBBB',
            remark: 'BBBBBB',
            materialReceiptDate: 'BBBBBB',
            docStatus: 'BBBBBB',
            supplierName: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ProductFinished', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ProductFinished', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ProductFinished', async () => {
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
