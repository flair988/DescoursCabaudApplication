import axios from 'axios';

import { IOperationSiteAuditing } from '@/shared/model/operation-site-auditing.model';

const baseApiUrl = 'api/operation-site-auditings';

export default class OperationSiteAuditingService {
  public find(id: number): Promise<IOperationSiteAuditing> {
    return new Promise<IOperationSiteAuditing>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .delete(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IOperationSiteAuditing): Promise<IOperationSiteAuditing> {
    return new Promise<IOperationSiteAuditing>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public update(entity: IOperationSiteAuditing): Promise<IOperationSiteAuditing> {
    return new Promise<IOperationSiteAuditing>((resolve, reject) => {
      axios
        .put(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public partialUpdate(entity: IOperationSiteAuditing): Promise<IOperationSiteAuditing> {
    return new Promise<IOperationSiteAuditing>((resolve, reject) => {
      axios
        .patch(`${baseApiUrl}/${entity.id}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
