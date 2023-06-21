package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_status")
    private String itemStatus;

    @Column(name = "item_france_name")
    private String itemFranceName;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "codeag")
    private String codeag;

    @Column(name = "technical_documents")
    private String technicalDocuments;

    @Column(name = "certification")
    private String certification;

    @Column(name = "opportunity_sheet")
    private String opportunitySheet;

    @Column(name = "packing_type")
    private String packingType;

    @Column(name = "sale_package_image")
    private String salePackageImage;

    @Column(name = "carton_length_milimeter")
    private String cartonLengthMilimeter;

    @Column(name = "carton_height_milimeter")
    private String cartonHeightMilimeter;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "carton_weight_kg")
    private String cartonWeightKg;

    @Column(name = "carton_weight_gr")
    private String cartonWeightGr;

    @Column(name = "carton_width_milimeter")
    private String cartonWidthMilimeter;

    @Column(name = "product_description_and_fonctionalities")
    private String productDescriptionAndFonctionalities;

    @Column(name = "drawing")
    private String drawing;

    @Column(name = "user_manual")
    private String userManual;

    @Column(name = "pallet_size")
    private String palletSize;

    @Column(name = "type_of_marketing")
    private String typeOfMarketing;

    @Column(name = "product_pic")
    private String productPic;

    @Column(name = "label")
    private String label;

    @Column(name = "comment")
    private String comment;

    @Column(name = "product_taxonomy")
    private String productTaxonomy;

    @Column(name = "net_weight")
    private String netWeight;

    @Column(name = "gross_weight")
    private String grossWeight;

    @Column(name = "unit_of_weight")
    private String unitOfWeight;

    @Column(name = "carton_volume_milimeter")
    private String cartonVolumeMilimeter;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Item id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemStatus() {
        return this.itemStatus;
    }

    public Item itemStatus(String itemStatus) {
        this.setItemStatus(itemStatus);
        return this;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemFranceName() {
        return this.itemFranceName;
    }

    public Item itemFranceName(String itemFranceName) {
        this.setItemFranceName(itemFranceName);
        return this;
    }

    public void setItemFranceName(String itemFranceName) {
        this.itemFranceName = itemFranceName;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public Item kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Item itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCodeag() {
        return this.codeag;
    }

    public Item codeag(String codeag) {
        this.setCodeag(codeag);
        return this;
    }

    public void setCodeag(String codeag) {
        this.codeag = codeag;
    }

    public String getTechnicalDocuments() {
        return this.technicalDocuments;
    }

    public Item technicalDocuments(String technicalDocuments) {
        this.setTechnicalDocuments(technicalDocuments);
        return this;
    }

    public void setTechnicalDocuments(String technicalDocuments) {
        this.technicalDocuments = technicalDocuments;
    }

    public String getCertification() {
        return this.certification;
    }

    public Item certification(String certification) {
        this.setCertification(certification);
        return this;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getOpportunitySheet() {
        return this.opportunitySheet;
    }

    public Item opportunitySheet(String opportunitySheet) {
        this.setOpportunitySheet(opportunitySheet);
        return this;
    }

    public void setOpportunitySheet(String opportunitySheet) {
        this.opportunitySheet = opportunitySheet;
    }

    public String getPackingType() {
        return this.packingType;
    }

    public Item packingType(String packingType) {
        this.setPackingType(packingType);
        return this;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public String getSalePackageImage() {
        return this.salePackageImage;
    }

    public Item salePackageImage(String salePackageImage) {
        this.setSalePackageImage(salePackageImage);
        return this;
    }

    public void setSalePackageImage(String salePackageImage) {
        this.salePackageImage = salePackageImage;
    }

    public String getCartonLengthMilimeter() {
        return this.cartonLengthMilimeter;
    }

    public Item cartonLengthMilimeter(String cartonLengthMilimeter) {
        this.setCartonLengthMilimeter(cartonLengthMilimeter);
        return this;
    }

    public void setCartonLengthMilimeter(String cartonLengthMilimeter) {
        this.cartonLengthMilimeter = cartonLengthMilimeter;
    }

    public String getCartonHeightMilimeter() {
        return this.cartonHeightMilimeter;
    }

    public Item cartonHeightMilimeter(String cartonHeightMilimeter) {
        this.setCartonHeightMilimeter(cartonHeightMilimeter);
        return this;
    }

    public void setCartonHeightMilimeter(String cartonHeightMilimeter) {
        this.cartonHeightMilimeter = cartonHeightMilimeter;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public Item barcode(String barcode) {
        this.setBarcode(barcode);
        return this;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCartonWeightKg() {
        return this.cartonWeightKg;
    }

    public Item cartonWeightKg(String cartonWeightKg) {
        this.setCartonWeightKg(cartonWeightKg);
        return this;
    }

    public void setCartonWeightKg(String cartonWeightKg) {
        this.cartonWeightKg = cartonWeightKg;
    }

    public String getCartonWeightGr() {
        return this.cartonWeightGr;
    }

    public Item cartonWeightGr(String cartonWeightGr) {
        this.setCartonWeightGr(cartonWeightGr);
        return this;
    }

    public void setCartonWeightGr(String cartonWeightGr) {
        this.cartonWeightGr = cartonWeightGr;
    }

    public String getCartonWidthMilimeter() {
        return this.cartonWidthMilimeter;
    }

    public Item cartonWidthMilimeter(String cartonWidthMilimeter) {
        this.setCartonWidthMilimeter(cartonWidthMilimeter);
        return this;
    }

    public void setCartonWidthMilimeter(String cartonWidthMilimeter) {
        this.cartonWidthMilimeter = cartonWidthMilimeter;
    }

    public String getProductDescriptionAndFonctionalities() {
        return this.productDescriptionAndFonctionalities;
    }

    public Item productDescriptionAndFonctionalities(String productDescriptionAndFonctionalities) {
        this.setProductDescriptionAndFonctionalities(productDescriptionAndFonctionalities);
        return this;
    }

    public void setProductDescriptionAndFonctionalities(String productDescriptionAndFonctionalities) {
        this.productDescriptionAndFonctionalities = productDescriptionAndFonctionalities;
    }

    public String getDrawing() {
        return this.drawing;
    }

    public Item drawing(String drawing) {
        this.setDrawing(drawing);
        return this;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public String getUserManual() {
        return this.userManual;
    }

    public Item userManual(String userManual) {
        this.setUserManual(userManual);
        return this;
    }

    public void setUserManual(String userManual) {
        this.userManual = userManual;
    }

    public String getPalletSize() {
        return this.palletSize;
    }

    public Item palletSize(String palletSize) {
        this.setPalletSize(palletSize);
        return this;
    }

    public void setPalletSize(String palletSize) {
        this.palletSize = palletSize;
    }

    public String getTypeOfMarketing() {
        return this.typeOfMarketing;
    }

    public Item typeOfMarketing(String typeOfMarketing) {
        this.setTypeOfMarketing(typeOfMarketing);
        return this;
    }

    public void setTypeOfMarketing(String typeOfMarketing) {
        this.typeOfMarketing = typeOfMarketing;
    }

    public String getProductPic() {
        return this.productPic;
    }

    public Item productPic(String productPic) {
        this.setProductPic(productPic);
        return this;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getLabel() {
        return this.label;
    }

    public Item label(String label) {
        this.setLabel(label);
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getComment() {
        return this.comment;
    }

    public Item comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductTaxonomy() {
        return this.productTaxonomy;
    }

    public Item productTaxonomy(String productTaxonomy) {
        this.setProductTaxonomy(productTaxonomy);
        return this;
    }

    public void setProductTaxonomy(String productTaxonomy) {
        this.productTaxonomy = productTaxonomy;
    }

    public String getNetWeight() {
        return this.netWeight;
    }

    public Item netWeight(String netWeight) {
        this.setNetWeight(netWeight);
        return this;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getGrossWeight() {
        return this.grossWeight;
    }

    public Item grossWeight(String grossWeight) {
        this.setGrossWeight(grossWeight);
        return this;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getUnitOfWeight() {
        return this.unitOfWeight;
    }

    public Item unitOfWeight(String unitOfWeight) {
        this.setUnitOfWeight(unitOfWeight);
        return this;
    }

    public void setUnitOfWeight(String unitOfWeight) {
        this.unitOfWeight = unitOfWeight;
    }

    public String getCartonVolumeMilimeter() {
        return this.cartonVolumeMilimeter;
    }

    public Item cartonVolumeMilimeter(String cartonVolumeMilimeter) {
        this.setCartonVolumeMilimeter(cartonVolumeMilimeter);
        return this;
    }

    public void setCartonVolumeMilimeter(String cartonVolumeMilimeter) {
        this.cartonVolumeMilimeter = cartonVolumeMilimeter;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", itemStatus='" + getItemStatus() + "'" +
            ", itemFranceName='" + getItemFranceName() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", codeag='" + getCodeag() + "'" +
            ", technicalDocuments='" + getTechnicalDocuments() + "'" +
            ", certification='" + getCertification() + "'" +
            ", opportunitySheet='" + getOpportunitySheet() + "'" +
            ", packingType='" + getPackingType() + "'" +
            ", salePackageImage='" + getSalePackageImage() + "'" +
            ", cartonLengthMilimeter='" + getCartonLengthMilimeter() + "'" +
            ", cartonHeightMilimeter='" + getCartonHeightMilimeter() + "'" +
            ", barcode='" + getBarcode() + "'" +
            ", cartonWeightKg='" + getCartonWeightKg() + "'" +
            ", cartonWeightGr='" + getCartonWeightGr() + "'" +
            ", cartonWidthMilimeter='" + getCartonWidthMilimeter() + "'" +
            ", productDescriptionAndFonctionalities='" + getProductDescriptionAndFonctionalities() + "'" +
            ", drawing='" + getDrawing() + "'" +
            ", userManual='" + getUserManual() + "'" +
            ", palletSize='" + getPalletSize() + "'" +
            ", typeOfMarketing='" + getTypeOfMarketing() + "'" +
            ", productPic='" + getProductPic() + "'" +
            ", label='" + getLabel() + "'" +
            ", comment='" + getComment() + "'" +
            ", productTaxonomy='" + getProductTaxonomy() + "'" +
            ", netWeight='" + getNetWeight() + "'" +
            ", grossWeight='" + getGrossWeight() + "'" +
            ", unitOfWeight='" + getUnitOfWeight() + "'" +
            ", cartonVolumeMilimeter='" + getCartonVolumeMilimeter() + "'" +
            "}";
    }
}
