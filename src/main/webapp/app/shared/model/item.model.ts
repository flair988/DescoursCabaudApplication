export interface IItem {
  id?: number;
  itemStatus?: string | null;
  itemFranceName?: string | null;
  kingdeeId?: string | null;
  itemName?: string | null;
  codeag?: string | null;
  technicalDocuments?: string | null;
  certification?: string | null;
  opportunitySheet?: string | null;
  packingType?: string | null;
  salePackageImage?: string | null;
  cartonLengthMilimeter?: string | null;
  cartonHeightMilimeter?: string | null;
  barcode?: string | null;
  cartonWeightKg?: string | null;
  cartonWeightGr?: string | null;
  cartonWidthMilimeter?: string | null;
  productDescriptionAndFonctionalities?: string | null;
  drawing?: string | null;
  userManual?: string | null;
  palletSize?: string | null;
  typeOfMarketing?: string | null;
  productPic?: string | null;
  label?: string | null;
  comment?: string | null;
  productTaxonomy?: string | null;
  netWeight?: string | null;
  grossWeight?: string | null;
  unitOfWeight?: string | null;
  cartonVolumeMilimeter?: string | null;
}

export class Item implements IItem {
  constructor(
    public id?: number,
    public itemStatus?: string | null,
    public itemFranceName?: string | null,
    public kingdeeId?: string | null,
    public itemName?: string | null,
    public codeag?: string | null,
    public technicalDocuments?: string | null,
    public certification?: string | null,
    public opportunitySheet?: string | null,
    public packingType?: string | null,
    public salePackageImage?: string | null,
    public cartonLengthMilimeter?: string | null,
    public cartonHeightMilimeter?: string | null,
    public barcode?: string | null,
    public cartonWeightKg?: string | null,
    public cartonWeightGr?: string | null,
    public cartonWidthMilimeter?: string | null,
    public productDescriptionAndFonctionalities?: string | null,
    public drawing?: string | null,
    public userManual?: string | null,
    public palletSize?: string | null,
    public typeOfMarketing?: string | null,
    public productPic?: string | null,
    public label?: string | null,
    public comment?: string | null,
    public productTaxonomy?: string | null,
    public netWeight?: string | null,
    public grossWeight?: string | null,
    public unitOfWeight?: string | null,
    public cartonVolumeMilimeter?: string | null
  ) {}
}
