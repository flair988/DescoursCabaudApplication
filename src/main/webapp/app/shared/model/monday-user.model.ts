export interface IMondayUser {
  id?: number;
  mondayId?: number | null;
  name?: string | null;
  email?: string | null;
  url?: string | null;
}

export class MondayUser implements IMondayUser {
  constructor(
    public id?: number,
    public mondayId?: number | null,
    public name?: string | null,
    public email?: string | null,
    public url?: string | null
  ) {}
}
