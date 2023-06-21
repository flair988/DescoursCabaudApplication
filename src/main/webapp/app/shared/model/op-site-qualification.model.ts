export interface IOPSiteQualification {
  id?: number;
  itemName?: string | null;
  cateGory?: string | null;
  supplier?: string | null;
  date?: Date | null;
  operationSite?: string | null;
  attributedLoRForThisSite?: string | null;
  siteQualificationStatus?: string | null;
  csrResult?: string | null;
  qualityProductionResult?: string | null;
  businessLiabilityResult?: string | null;
  comments?: string | null;
}

export class OPSiteQualification implements IOPSiteQualification {
  constructor(
    public id?: number,
    public itemName?: string | null,
    public cateGory?: string | null,
    public supplier?: string | null,
    public date?: Date | null,
    public operationSite?: string | null,
    public attributedLoRForThisSite?: string | null,
    public siteQualificationStatus?: string | null,
    public csrResult?: string | null,
    public qualityProductionResult?: string | null,
    public businessLiabilityResult?: string | null,
    public comments?: string | null
  ) {}
}
