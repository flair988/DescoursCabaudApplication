export interface ISalesDelivery {
  id?: number;
  itemName?: string | null;
  kingdeeId?: string | null;
  customer?: string | null;
  orderDate?: string | null;
  loadingPort?: string | null;
  dischargePort?: string | null;
  transportMode?: string | null;
  incoterm?: string | null;
  forwarder?: string | null;
  eta?: string | null;
  etd?: string | null;
  containerType?: string | null;
  containerSize?: string | null;
  remark?: string | null;
  kingdeeUniqueId?: string | null;
  docStatus?: string | null;
  cateGory?: string | null;
}

export class SalesDelivery implements ISalesDelivery {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public kingdeeId?: string | null,
    public customer?: string | null,
    public orderDate?: string | null,
    public loadingPort?: string | null,
    public dischargePort?: string | null,
    public transportMode?: string | null,
    public incoterm?: string | null,
    public forwarder?: string | null,
    public eta?: string | null,
    public etd?: string | null,
    public containerType?: string | null,
    public containerSize?: string | null,
    public remark?: string | null,
    public kingdeeUniqueId?: string | null,
    public docStatus?: string | null,
    public cateGory?: string | null
  ) {}
}
