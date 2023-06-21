export interface IUom {
  id?: number;
  itemId?: string | null;
  uom?: string | null;
  uomGroup?: string | null;
  description?: string | null;
  subItems?: string | null;
  parentItem?: string | null;
  boardId?: string | null;
  kingdeeId?: string | null;
}

export class Uom implements IUom {
  constructor(
    public id?: number,
    public itemId?: string | null,
    public uom?: string | null,
    public uomGroup?: string | null,
    public description?: string | null,
    public subItems?: string | null,
    public parentItem?: string | null,
    public boardId?: string | null,
    public kingdeeId?: string | null
  ) {}
}
