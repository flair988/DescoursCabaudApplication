export interface IForwarder {
  id?: number;
  itemName?: string | null;
  itemId?: string | null;
  boardId?: string | null;
  kingdeeId?: string | null;
  contact?: string | null;
  email?: string | null;
  telephone?: string | null;
}

export class Forwarder implements IForwarder {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public itemId?: string | null,
    public boardId?: string | null,
    public kingdeeId?: string | null,
    public contact?: string | null,
    public email?: string | null,
    public telephone?: string | null
  ) {}
}
