/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ItemService from '../../../../../../main/webapp/app/entities/item/item.service';
import { Item } from '../../../../../../main/webapp/app/shared/model/item.model';

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
  describe('Item Service', () => {
    let service: ItemService;
    let elemDefault;

    beforeEach(() => {
      service = new ItemService();
      elemDefault = new Item(
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

      it('should create a Item', async () => {
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

      it('should not create a Item', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Item', async () => {
        const returnedFromService = Object.assign(
          {
            itemStatus: 'BBBBBB',
            itemFranceName: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            itemName: 'BBBBBB',
            codeag: 'BBBBBB',
            technicalDocuments: 'BBBBBB',
            certification: 'BBBBBB',
            opportunitySheet: 'BBBBBB',
            packingType: 'BBBBBB',
            salePackageImage: 'BBBBBB',
            cartonLengthMilimeter: 'BBBBBB',
            cartonHeightMilimeter: 'BBBBBB',
            barcode: 'BBBBBB',
            cartonWeightKg: 'BBBBBB',
            cartonWeightGr: 'BBBBBB',
            cartonWidthMilimeter: 'BBBBBB',
            productDescriptionAndFonctionalities: 'BBBBBB',
            drawing: 'BBBBBB',
            userManual: 'BBBBBB',
            palletSize: 'BBBBBB',
            typeOfMarketing: 'BBBBBB',
            productPic: 'BBBBBB',
            label: 'BBBBBB',
            comment: 'BBBBBB',
            productTaxonomy: 'BBBBBB',
            netWeight: 'BBBBBB',
            grossWeight: 'BBBBBB',
            unitOfWeight: 'BBBBBB',
            cartonVolumeMilimeter: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Item', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Item', async () => {
        const patchObject = Object.assign(
          {
            kingdeeId: 'BBBBBB',
            itemName: 'BBBBBB',
            codeag: 'BBBBBB',
            certification: 'BBBBBB',
            salePackageImage: 'BBBBBB',
            cartonLengthMilimeter: 'BBBBBB',
            cartonHeightMilimeter: 'BBBBBB',
            barcode: 'BBBBBB',
            cartonWeightKg: 'BBBBBB',
            cartonWidthMilimeter: 'BBBBBB',
            drawing: 'BBBBBB',
            typeOfMarketing: 'BBBBBB',
            productPic: 'BBBBBB',
            comment: 'BBBBBB',
            productTaxonomy: 'BBBBBB',
            netWeight: 'BBBBBB',
            unitOfWeight: 'BBBBBB',
            cartonVolumeMilimeter: 'BBBBBB',
          },
          new Item()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Item', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Item', async () => {
        const returnedFromService = Object.assign(
          {
            itemStatus: 'BBBBBB',
            itemFranceName: 'BBBBBB',
            kingdeeId: 'BBBBBB',
            itemName: 'BBBBBB',
            codeag: 'BBBBBB',
            technicalDocuments: 'BBBBBB',
            certification: 'BBBBBB',
            opportunitySheet: 'BBBBBB',
            packingType: 'BBBBBB',
            salePackageImage: 'BBBBBB',
            cartonLengthMilimeter: 'BBBBBB',
            cartonHeightMilimeter: 'BBBBBB',
            barcode: 'BBBBBB',
            cartonWeightKg: 'BBBBBB',
            cartonWeightGr: 'BBBBBB',
            cartonWidthMilimeter: 'BBBBBB',
            productDescriptionAndFonctionalities: 'BBBBBB',
            drawing: 'BBBBBB',
            userManual: 'BBBBBB',
            palletSize: 'BBBBBB',
            typeOfMarketing: 'BBBBBB',
            productPic: 'BBBBBB',
            label: 'BBBBBB',
            comment: 'BBBBBB',
            productTaxonomy: 'BBBBBB',
            netWeight: 'BBBBBB',
            grossWeight: 'BBBBBB',
            unitOfWeight: 'BBBBBB',
            cartonVolumeMilimeter: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Item', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Item', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Item', async () => {
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
