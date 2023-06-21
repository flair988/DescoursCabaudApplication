export interface IForwarderBooking {
  id?: number;
  itemName?: string | null;
  kingdeeId?: string | null;
  customer?: string | null;
  orderDate?: string | null;
  forwarder?: string | null;
  totalQty?: string | null;
  loadingPort?: string | null;
  dischargePort?: string | null;
  containerType?: string | null;
  containerSize?: string | null;
  containerNumber?: string | null;
  supplier?: string | null;
  supplierEmail?: string | null;
  eta?: string | null;
  etd?: string | null;
  transportMode?: string | null;
  numberOfCartons?: string | null;
  numberOfRef?: string | null;
  totalVolume?: string | null;
  totalWeight?: string | null;
  remark?: string | null;
  client?: string | null;
  kingdeeUniqueId?: string | null;
}

export class ForwarderBooking implements IForwarderBooking {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public kingdeeId?: string | null,
    public customer?: string | null,
    public orderDate?: string | null,
    public forwarder?: string | null,
    public totalQty?: string | null,
    public loadingPort?: string | null,
    public dischargePort?: string | null,
    public containerType?: string | null,
    public containerSize?: string | null,
    public containerNumber?: string | null,
    public supplier?: string | null,
    public supplierEmail?: string | null,
    public eta?: string | null,
    public etd?: string | null,
    public transportMode?: string | null,
    public numberOfCartons?: string | null,
    public numberOfRef?: string | null,
    public totalVolume?: string | null,
    public totalWeight?: string | null,
    public remark?: string | null,
    public client?: string | null,
    public kingdeeUniqueId?: string | null
  ) {}
}
