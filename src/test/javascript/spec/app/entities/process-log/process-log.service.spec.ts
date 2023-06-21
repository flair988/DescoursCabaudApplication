/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '../../../../../../main/webapp/app/shared/composables/date-format';
import ProcessLogService from '../../../../../../main/webapp/app/entities/process-log/process-log.service';
import { ProcessLog } from '../../../../../../main/webapp/app/shared/model/process-log.model';

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
  describe('ProcessLog Service', () => {
    let service: ProcessLogService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ProcessLogService();
      currentDate = new Date();
      elemDefault = new ProcessLog(123, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            processStartTime: dayjs(currentDate).format(DATE_FORMAT),
            processEndTime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a ProcessLog', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            processStartTime: dayjs(currentDate).format(DATE_FORMAT),
            processEndTime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            processStartTime: currentDate,
            processEndTime: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ProcessLog', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ProcessLog', async () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            request: 'BBBBBB',
            response: 'BBBBBB',
            status: 'BBBBBB',
            reason: 'BBBBBB',
            processStartTime: dayjs(currentDate).format(DATE_FORMAT),
            processEndTime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            processStartTime: currentDate,
            processEndTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ProcessLog', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ProcessLog', async () => {
        const patchObject = Object.assign(
          {
            response: 'BBBBBB',
            processEndTime: dayjs(currentDate).format(DATE_FORMAT),
          },
          new ProcessLog()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            processStartTime: currentDate,
            processEndTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ProcessLog', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ProcessLog', async () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            request: 'BBBBBB',
            response: 'BBBBBB',
            status: 'BBBBBB',
            reason: 'BBBBBB',
            processStartTime: dayjs(currentDate).format(DATE_FORMAT),
            processEndTime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            processStartTime: currentDate,
            processEndTime: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ProcessLog', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ProcessLog', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ProcessLog', async () => {
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
