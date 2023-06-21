export interface IOperationSiteAuditing {
  id?: number;
  itemName?: string | null;
  cateGory?: string | null;
  supplier?: string | null;
  operationSite?: string | null;
  linkSupplierFactory?: string | null;
  typeOfSite?: string | null;
  auditingTool?: string | null;
  auditingDate?: Date | null;
  csrResult?: string | null;
  qualityProductionResult?: string | null;
  businessLiabilityResult?: string | null;
  comments?: string | null;
  issueDate?: Date | null;
  dueDate?: Date | null;
  closedDate?: Date | null;
  closed?: string | null;
  status?: string | null;
  capComments?: string | null;
}

export class OperationSiteAuditing implements IOperationSiteAuditing {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public cateGory?: string | null,
    public supplier?: string | null,
    public operationSite?: string | null,
    public linkSupplierFactory?: string | null,
    public typeOfSite?: string | null,
    public auditingTool?: string | null,
    public auditingDate?: Date | null,
    public csrResult?: string | null,
    public qualityProductionResult?: string | null,
    public businessLiabilityResult?: string | null,
    public comments?: string | null,
    public issueDate?: Date | null,
    public dueDate?: Date | null,
    public closedDate?: Date | null,
    public closed?: string | null,
    public status?: string | null,
    public capComments?: string | null
  ) {}
}
