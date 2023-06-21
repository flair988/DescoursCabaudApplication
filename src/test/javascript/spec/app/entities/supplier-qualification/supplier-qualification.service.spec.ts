/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '../../../../../../main/webapp/app/shared/composables/date-format';
import SupplierQualificationService from '../../../../../../main/webapp/app/entities/supplier-qualification/supplier-qualification.service';
import { SupplierQualification } from '../../../../../../main/webapp/app/shared/model/supplier-qualification.model';

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
  describe('SupplierQualification Service', () => {
    let service: SupplierQualificationService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new SupplierQualificationService();
      currentDate = new Date();
      elemDefault = new SupplierQualification(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            date: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
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

      it('should create a SupplierQualification', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            date: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a SupplierQualification', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SupplierQualification', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            supplier: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            supplierStatus: 'BBBBBB',
            evaluationStatus: 'BBBBBB',
            businessLiabilityBopeScore: 'BBBBBB',
            comments: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SupplierQualification', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a SupplierQualification', async () => {
        const patchObject = Object.assign(
          {
            cateGory: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            supplierStatus: 'BBBBBB',
            comments: 'BBBBBB',
          },
          new SupplierQualification()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a SupplierQualification', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SupplierQualification', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            supplier: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            supplierStatus: 'BBBBBB',
            evaluationStatus: 'BBBBBB',
            businessLiabilityBopeScore: 'BBBBBB',
            comments: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SupplierQualification', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SupplierQualification', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SupplierQualification', async () => {
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
