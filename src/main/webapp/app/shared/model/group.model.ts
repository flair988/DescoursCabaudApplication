export interface IGroup {
  id?: number;
  groupId?: number | null;
  groupNumber?: string | null;
  parentId?: number | null;
  groupName?: string | null;
  description?: string | null;
}

export class Group implements IGroup {
  constructor(
    public id?: number,
    public groupId?: number | null,
    public groupNumber?: string | null,
    public parentId?: number | null,
    public groupName?: string | null,
    public description?: string | null
  ) {}
}
