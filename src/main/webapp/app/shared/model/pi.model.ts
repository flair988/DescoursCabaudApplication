export interface IPI {
  id?: number;
  itemName?: string | null;
  poNumber?: string | null;
  category?: string | null;
  client?: string | null;
  orderDate?: string | null;
  requestedEndOfProdDate?: string | null;
  countryOfOrigin?: string | null;
  countryOfFinalDestination?: string | null;
  consignee?: string | null;
  carriageBy?: string | null;
  termsOfDelivery?: string | null;
  termsOfPayment?: string | null;
  currency?: string | null;
  remarks?: string | null;
  kingdeeId?: string | null;
  portOfDischarge?: string | null;
  portOfLoading?: string | null;
  docStatus?: string | null;
}

export class PI implements IPI {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public poNumber?: string | null,
    public category?: string | null,
    public client?: string | null,
    public orderDate?: string | null,
    public requestedEndOfProdDate?: string | null,
    public countryOfOrigin?: string | null,
    public countryOfFinalDestination?: string | null,
    public consignee?: string | null,
    public carriageBy?: string | null,
    public termsOfDelivery?: string | null,
    public termsOfPayment?: string | null,
    public currency?: string | null,
    public remarks?: string | null,
    public kingdeeId?: string | null,
    public portOfDischarge?: string | null,
    public portOfLoading?: string | null,
    public docStatus?: string | null
  ) {}
}
