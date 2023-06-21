export interface ISupplierQualification {
  id?: number;
  itemName?: string | null;
  cateGory?: string | null;
  supplier?: string | null;
  date?: Date | null;
  supplierStatus?: string | null;
  evaluationStatus?: string | null;
  businessLiabilityBopeScore?: string | null;
  comments?: string | null;
}

export class SupplierQualification implements ISupplierQualification {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public cateGory?: string | null,
    public supplier?: string | null,
    public date?: Date | null,
    public supplierStatus?: string | null,
    public evaluationStatus?: string | null,
    public businessLiabilityBopeScore?: string | null,
    public comments?: string | null
  ) {}
}
