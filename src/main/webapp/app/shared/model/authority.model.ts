export interface IAuthority {
  id?: number;
  name?: string | null;
}

export class Authority implements IAuthority {
  constructor(public id?: number, public name?: string | null) {}
}
