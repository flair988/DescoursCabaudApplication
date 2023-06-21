/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '../../../../../../main/webapp/app/shared/composables/date-format';
import OperationSiteAuditingService from '../../../../../../main/webapp/app/entities/operation-site-auditing/operation-site-auditing.service';
import { OperationSiteAuditing } from '../../../../../../main/webapp/app/shared/model/operation-site-auditing.model';

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
  describe('OperationSiteAuditing Service', () => {
    let service: OperationSiteAuditingService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OperationSiteAuditingService();
      currentDate = new Date();
      elemDefault = new OperationSiteAuditing(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            auditingDate: dayjs(currentDate).format(DATE_FORMAT),
            issueDate: dayjs(currentDate).format(DATE_FORMAT),
            dueDate: dayjs(currentDate).format(DATE_FORMAT),
            closedDate: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a OperationSiteAuditing', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            auditingDate: dayjs(currentDate).format(DATE_FORMAT),
            issueDate: dayjs(currentDate).format(DATE_FORMAT),
            dueDate: dayjs(currentDate).format(DATE_FORMAT),
            closedDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            auditingDate: currentDate,
            issueDate: currentDate,
            dueDate: currentDate,
            closedDate: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OperationSiteAuditing', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OperationSiteAuditing', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            supplier: 'BBBBBB',
            operationSite: 'BBBBBB',
            linkSupplierFactory: 'BBBBBB',
            typeOfSite: 'BBBBBB',
            auditingTool: 'BBBBBB',
            auditingDate: dayjs(currentDate).format(DATE_FORMAT),
            csrResult: 'BBBBBB',
            qualityProductionResult: 'BBBBBB',
            businessLiabilityResult: 'BBBBBB',
            comments: 'BBBBBB',
            issueDate: dayjs(currentDate).format(DATE_FORMAT),
            dueDate: dayjs(currentDate).format(DATE_FORMAT),
            closedDate: dayjs(currentDate).format(DATE_FORMAT),
            closed: 'BBBBBB',
            status: 'BBBBBB',
            capComments: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            auditingDate: currentDate,
            issueDate: currentDate,
            dueDate: currentDate,
            closedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OperationSiteAuditing', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OperationSiteAuditing', async () => {
        const patchObject = Object.assign(
          {
            itemName: 'BBBBBB',
            supplier: 'BBBBBB',
            operationSite: 'BBBBBB',
            typeOfSite: 'BBBBBB',
            auditingDate: dayjs(currentDate).format(DATE_FORMAT),
            csrResult: 'BBBBBB',
            businessLiabilityResult: 'BBBBBB',
            comments: 'BBBBBB',
            issueDate: dayjs(currentDate).format(DATE_FORMAT),
            closedDate: dayjs(currentDate).format(DATE_FORMAT),
            status: 'BBBBBB',
          },
          new OperationSiteAuditing()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            auditingDate: currentDate,
            issueDate: currentDate,
            dueDate: currentDate,
            closedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OperationSiteAuditing', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OperationSiteAuditing', async () => {
        const returnedFromService = Object.assign(
          {
            itemName: 'BBBBBB',
            cateGory: 'BBBBBB',
            supplier: 'BBBBBB',
            operationSite: 'BBBBBB',
            linkSupplierFactory: 'BBBBBB',
            typeOfSite: 'BBBBBB',
            auditingTool: 'BBBBBB',
            auditingDate: dayjs(currentDate).format(DATE_FORMAT),
            csrResult: 'BBBBBB',
            qualityProductionResult: 'BBBBBB',
            businessLiabilityResult: 'BBBBBB',
            comments: 'BBBBBB',
            issueDate: dayjs(currentDate).format(DATE_FORMAT),
            dueDate: dayjs(currentDate).format(DATE_FORMAT),
            closedDate: dayjs(currentDate).format(DATE_FORMAT),
            closed: 'BBBBBB',
            status: 'BBBBBB',
            capComments: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            auditingDate: currentDate,
            issueDate: currentDate,
            dueDate: currentDate,
            closedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OperationSiteAuditing', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OperationSiteAuditing', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OperationSiteAuditing', async () => {
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
