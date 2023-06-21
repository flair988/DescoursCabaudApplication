export interface IProductTaxmonomy {
  id?: number;
  itemId?: string | null;
  kingdeeId?: string | null;
  itemName?: string | null;
  groupName?: string | null;
  parentGroupName?: string | null;
  subGroupName?: string | null;
  description?: string | null;
}

export class ProductTaxmonomy implements IProductTaxmonomy {
  constructor(
    public id?: number,
    public itemId?: string | null,
    public kingdeeId?: string | null,
    public itemName?: string | null,
    public groupName?: string | null,
    public parentGroupName?: string | null,
    public subGroupName?: string | null,
    public description?: string | null
  ) {}
}
