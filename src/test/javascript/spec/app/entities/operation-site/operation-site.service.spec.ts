/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '../../../../../../main/webapp/app/shared/composables/date-format';
import OperationSiteService from '../../../../../../main/webapp/app/entities/operation-site/operation-site.service';
import { OperationSite } from '../../../../../../main/webapp/app/shared/model/operation-site.model';

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
  describe('OperationSite Service', () => {
    let service: OperationSiteService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OperationSiteService();
      currentDate = new Date();
      elemDefault = new OperationSite(
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
        currentDate,
        currentDate,
        currentDate,
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
        const returnedFromService = Object.assign(
          {
            sasDate: dayjs(currentDate).format(DATE_FORMAT),
            iso900ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
            iso14001ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a OperationSite', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            sasDate: dayjs(currentDate).format(DATE_FORMAT),
            iso900ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
            iso14001ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            sasDate: currentDate,
            iso900ValidUtil: currentDate,
            iso14001ValidUtil: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OperationSite', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OperationSite', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            linkSupplierFactory: 'BBBBBB',
            typeOfSite: 'BBBBBB',
            contact: 'BBBBBB',
            siteAddress: 'BBBBBB',
            cateGory: 'BBBBBB',
            country: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            businessLicense: 'BBBBBB',
            sasDate: dayjs(currentDate).format(DATE_FORMAT),
            iso900ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
            iso14001ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
            attributeLor: 'BBBBBB',
            siteQualification: 'BBBBBB',
            qualificationScore: 'BBBBBB',
            pqvScore: 'BBBBBB',
            pqvDate: 'BBBBBB',
            pqvDecision: 'BBBBBB',
            technicalAuditDate: 'BBBBBB',
            technicalAuditScore: 'BBBBBB',
            thirdRdPartyDate: 'BBBBBB',
            thirdRdPartyScore: 'BBBBBB',
            bopeDate: 'BBBBBB',
            bopeScore: 'BBBBBB',
            capRequired: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            sasDate: currentDate,
            iso900ValidUtil: currentDate,
            iso14001ValidUtil: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OperationSite', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OperationSite', async () => {
        const patchObject = Object.assign(
          {
            itemName: 'BBBBBB',
            linkSupplierFactory: 'BBBBBB',
            contact: 'BBBBBB',
            siteAddress: 'BBBBBB',
            cateGory: 'BBBBBB',
            country: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            businessLicense: 'BBBBBB',
            sasDate: dayjs(currentDate).format(DATE_FORMAT),
            siteQualification: 'BBBBBB',
            qualificationScore: 'BBBBBB',
            technicalAuditScore: 'BBBBBB',
            thirdRdPartyDate: 'BBBBBB',
            bopeDate: 'BBBBBB',
            capRequired: 'BBBBBB',
          },
          new OperationSite()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            sasDate: currentDate,
            iso900ValidUtil: currentDate,
            iso14001ValidUtil: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OperationSite', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OperationSite', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            linkSupplierFactory: 'BBBBBB',
            typeOfSite: 'BBBBBB',
            contact: 'BBBBBB',
            siteAddress: 'BBBBBB',
            cateGory: 'BBBBBB',
            country: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            businessLicense: 'BBBBBB',
            sasDate: dayjs(currentDate).format(DATE_FORMAT),
            iso900ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
            iso14001ValidUtil: dayjs(currentDate).format(DATE_FORMAT),
            attributeLor: 'BBBBBB',
            siteQualification: 'BBBBBB',
            qualificationScore: 'BBBBBB',
            pqvScore: 'BBBBBB',
            pqvDate: 'BBBBBB',
            pqvDecision: 'BBBBBB',
            technicalAuditDate: 'BBBBBB',
            technicalAuditScore: 'BBBBBB',
            thirdRdPartyDate: 'BBBBBB',
            thirdRdPartyScore: 'BBBBBB',
            bopeDate: 'BBBBBB',
            bopeScore: 'BBBBBB',
            capRequired: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            sasDate: currentDate,
            iso900ValidUtil: currentDate,
            iso14001ValidUtil: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OperationSite', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OperationSite', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OperationSite', async () => {
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
