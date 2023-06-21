export interface IInspection {
  id?: number;
  itemName?: string | null;
  kingdeeId?: string | null;
  supplierName?: string | null;
  email?: string | null;
  inspectionDate?: string | null;
  cateGory?: string | null;
  qCResult?: string | null;
  docStatus?: string | null;
  inspectionType?: string | null;
  inspectionBookingStatus?: string | null;
  inspectionEndDate?: string | null;
  supplierId?: string | null;
  reportNumber?: string | null;
}

export class Inspection implements IInspection {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public kingdeeId?: string | null,
    public supplierName?: string | null,
    public email?: string | null,
    public inspectionDate?: string | null,
    public cateGory?: string | null,
    public qCResult?: string | null,
    public docStatus?: string | null,
    public inspectionType?: string | null,
    public inspectionBookingStatus?: string | null,
    public inspectionEndDate?: string | null,
    public supplierId?: string | null,
    public reportNumber?: string | null
  ) {}
}
