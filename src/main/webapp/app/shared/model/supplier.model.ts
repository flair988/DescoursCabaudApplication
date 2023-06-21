export interface ISupplier {
  id?: number;
  client?: string | null;
  category?: string | null;
  subCategory?: string | null;
  capStatus?: string | null;
  supplierStatus?: string | null;
  qualificationScore?: string | null;
  bopeScore?: string | null;
  internalSupplierId?: string | null;
  contact?: string | null;
  contactPhoneNumber?: string | null;
  contactEmailAddress?: string | null;
  operationSite?: string | null;
  address?: string | null;
  website?: string | null;
  relationStartingYear?: string | null;
  businessLicense?: string | null;
  rexOriginStatus?: string | null;
  createDate?: Date | null;
  updateDate?: Date | null;
  item?: string | null;
  subItems?: string | null;
  date?: string | null;
  kingdeeId?: string | null;
  region?: string | null;
  supplierType?: string | null;
  remark?: string | null;
  frenchName?: string | null;
}

export class Supplier implements ISupplier {
  constructor(
    public id?: number,
    public client?: string | null,
    public category?: string | null,
    public subCategory?: string | null,
    public capStatus?: string | null,
    public supplierStatus?: string | null,
    public qualificationScore?: string | null,
    public bopeScore?: string | null,
    public internalSupplierId?: string | null,
    public contact?: string | null,
    public contactPhoneNumber?: string | null,
    public contactEmailAddress?: string | null,
    public operationSite?: string | null,
    public address?: string | null,
    public website?: string | null,
    public relationStartingYear?: string | null,
    public businessLicense?: string | null,
    public rexOriginStatus?: string | null,
    public createDate?: Date | null,
    public updateDate?: Date | null,
    public item?: string | null,
    public subItems?: string | null,
    public date?: string | null,
    public kingdeeId?: string | null,
    public region?: string | null,
    public supplierType?: string | null,
    public remark?: string | null,
    public frenchName?: string | null
  ) {}
}
