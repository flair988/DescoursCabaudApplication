export interface IOperationSite {
  id?: number;
  itemName?: string | null;
  linkSupplierFactory?: string | null;
  typeOfSite?: string | null;
  contact?: string | null;
  siteAddress?: string | null;
  cateGory?: string | null;
  country?: string | null;
  kingdeeId?: string | null;
  businessLicense?: string | null;
  sasDate?: Date | null;
  iso900ValidUtil?: Date | null;
  iso14001ValidUtil?: Date | null;
  attributeLor?: string | null;
  siteQualification?: string | null;
  qualificationScore?: string | null;
  pqvScore?: string | null;
  pqvDate?: string | null;
  pqvDecision?: string | null;
  technicalAuditDate?: string | null;
  technicalAuditScore?: string | null;
  thirdRdPartyDate?: string | null;
  thirdRdPartyScore?: string | null;
  bopeDate?: string | null;
  bopeScore?: string | null;
  capRequired?: string | null;
}

export class OperationSite implements IOperationSite {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public linkSupplierFactory?: string | null,
    public typeOfSite?: string | null,
    public contact?: string | null,
    public siteAddress?: string | null,
    public cateGory?: string | null,
    public country?: string | null,
    public kingdeeId?: string | null,
    public businessLicense?: string | null,
    public sasDate?: Date | null,
    public iso900ValidUtil?: Date | null,
    public iso14001ValidUtil?: Date | null,
    public attributeLor?: string | null,
    public siteQualification?: string | null,
    public qualificationScore?: string | null,
    public pqvScore?: string | null,
    public pqvDate?: string | null,
    public pqvDecision?: string | null,
    public technicalAuditDate?: string | null,
    public technicalAuditScore?: string | null,
    public thirdRdPartyDate?: string | null,
    public thirdRdPartyScore?: string | null,
    public bopeDate?: string | null,
    public bopeScore?: string | null,
    public capRequired?: string | null
  ) {}
}
