export interface IMondayColumn {
  id?: number;
  boardId?: string | null;
  columnId?: string | null;
  title?: string | null;
  type?: string | null;
}

export class MondayColumn implements IMondayColumn {
  constructor(
    public id?: number,
    public boardId?: string | null,
    public columnId?: string | null,
    public title?: string | null,
    public type?: string | null
  ) {}
}
