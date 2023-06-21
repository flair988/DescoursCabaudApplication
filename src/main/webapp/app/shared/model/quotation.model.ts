export interface IQuotation {
  id?: number;
  itemName?: string | null;
  quotationDate?: string | null;
  inquiryDocNumber?: string | null;
  docNo?: string | null;
  docStatus?: string | null;
  supplier?: string | null;
  kingdeeId?: string | null;
}

export class Quotation implements IQuotation {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public quotationDate?: string | null,
    public inquiryDocNumber?: string | null,
    public docNo?: string | null,
    public docStatus?: string | null,
    public supplier?: string | null,
    public kingdeeId?: string | null
  ) {}
}
