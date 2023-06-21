export interface IPurchaseReturn {
  id?: number;
  itemName?: string | null;
  kingdeeId?: string | null;
  docNumber?: string | null;
  supplier?: string | null;
  supplierEmail?: string | null;
  materialReturnDate?: string | null;
  reasonForReturn?: string | null;
  purchaseDept?: string | null;
  remarks?: string | null;
  docStatus?: string | null;
  itemCode?: string | null;
  supplierCode?: string | null;
}

export class PurchaseReturn implements IPurchaseReturn {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public kingdeeId?: string | null,
    public docNumber?: string | null,
    public supplier?: string | null,
    public supplierEmail?: string | null,
    public materialReturnDate?: string | null,
    public reasonForReturn?: string | null,
    public purchaseDept?: string | null,
    public remarks?: string | null,
    public docStatus?: string | null,
    public itemCode?: string | null,
    public supplierCode?: string | null
  ) {}
}
