export interface IProcessLog {
  id?: number;
  type?: string | null;
  request?: string | null;
  response?: string | null;
  status?: string | null;
  reason?: string | null;
  processStartTime?: Date | null;
  processEndTime?: Date | null;
}

export class ProcessLog implements IProcessLog {
  constructor(
    public id?: number,
    public type?: string | null,
    public request?: string | null,
    public response?: string | null,
    public status?: string | null,
    public reason?: string | null,
    public processStartTime?: Date | null,
    public processEndTime?: Date | null
  ) {}
}
