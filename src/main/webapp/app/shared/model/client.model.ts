export interface IClient {
  id?: number;
  itemId?: string | null;
  itemName?: string | null;
  boardId?: string | null;
  subItems?: string | null;
  kingdeeId?: string | null;
  customerName?: string | null;
  customerCode?: string | null;
  customerFrenceName?: string | null;
  comment?: string | null;
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public itemId?: string | null,
    public itemName?: string | null,
    public boardId?: string | null,
    public subItems?: string | null,
    public kingdeeId?: string | null,
    public customerName?: string | null,
    public customerCode?: string | null,
    public customerFrenceName?: string | null,
    public comment?: string | null
  ) {}
}
