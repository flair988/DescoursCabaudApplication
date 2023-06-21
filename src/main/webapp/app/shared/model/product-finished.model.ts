export interface IProductFinished {
  id?: number;
  itemName?: string | null;
  kingdeeId?: string | null;
  supplier?: string | null;
  supplierEmail?: string | null;
  orderDate?: string | null;
  cateGory?: string | null;
  remark?: string | null;
  materialReceiptDate?: string | null;
  docStatus?: string | null;
  supplierName?: string | null;
}

export class ProductFinished implements IProductFinished {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public kingdeeId?: string | null,
    public supplier?: string | null,
    public supplierEmail?: string | null,
    public orderDate?: string | null,
    public cateGory?: string | null,
    public remark?: string | null,
    public materialReceiptDate?: string | null,
    public docStatus?: string | null,
    public supplierName?: string | null
  ) {}
}
