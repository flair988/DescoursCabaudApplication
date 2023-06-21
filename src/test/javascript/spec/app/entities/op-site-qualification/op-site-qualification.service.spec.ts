/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '../../../../../../main/webapp/app/shared/composables/date-format';
import OPSiteQualificationService from '../../../../../../main/webapp/app/entities/op-site-qualification/op-site-qualification.service';
import { OPSiteQualification } from '../../../../../../main/webapp/app/shared/model/op-site-qualification.model';

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
  describe('OPSiteQualification Service', () => {
    let service: OPSiteQualificationService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OPSiteQualificationService();
      currentDate = new Date();
      elemDefault = new OPSiteQualification(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
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

      it('should create a OPSiteQualification', async () => {
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

      it('should not create a OPSiteQualification', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OPSiteQualification', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            supplier: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            operationSite: 'BBBBBB',
            attributedLoRForThisSite: 'BBBBBB',
            siteQualificationStatus: 'BBBBBB',
            csrResult: 'BBBBBB',
            qualityProductionResult: 'BBBBBB',
            businessLiabilityResult: 'BBBBBB',
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

      it('should not update a OPSiteQualification', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OPSiteQualification', async () => {
        const patchObject = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            siteQualificationStatus: 'BBBBBB',
            csrResult: 'BBBBBB',
            qualityProductionResult: 'BBBBBB',
            businessLiabilityResult: 'BBBBBB',
            comments: 'BBBBBB',
          },
          new OPSiteQualification()
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

      it('should not partial update a OPSiteQualification', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OPSiteQualification', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            supplier: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            operationSite: 'BBBBBB',
            attributedLoRForThisSite: 'BBBBBB',
            siteQualificationStatus: 'BBBBBB',
            csrResult: 'BBBBBB',
            qualityProductionResult: 'BBBBBB',
            businessLiabilityResult: 'BBBBBB',
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

      it('should not return a list of OPSiteQualification', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OPSiteQualification', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OPSiteQualification', async () => {
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
