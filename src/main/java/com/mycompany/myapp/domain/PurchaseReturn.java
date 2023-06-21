package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PurchaseReturn.
 */
@Entity
@Table(name = "purchase_return")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "doc_number")
    private String docNumber;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "material_return_date")
    private String materialReturnDate;

    @Column(name = "reason_for_return")
    private String reasonForReturn;

    @Column(name = "purchase_dept")
    private String purchaseDept;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "doc_status")
    private String docStatus;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "supplier_code")
    private String supplierCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PurchaseReturn id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public PurchaseReturn itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public PurchaseReturn kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getDocNumber() {
        return this.docNumber;
    }

    public PurchaseReturn docNumber(String docNumber) {
        this.setDocNumber(docNumber);
        return this;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public PurchaseReturn supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierEmail() {
        return this.supplierEmail;
    }

    public PurchaseReturn supplierEmail(String supplierEmail) {
        this.setSupplierEmail(supplierEmail);
        return this;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getMaterialReturnDate() {
        return this.materialReturnDate;
    }

    public PurchaseReturn materialReturnDate(String materialReturnDate) {
        this.setMaterialReturnDate(materialReturnDate);
        return this;
    }

    public void setMaterialReturnDate(String materialReturnDate) {
        this.materialReturnDate = materialReturnDate;
    }

    public String getReasonForReturn() {
        return this.reasonForReturn;
    }

    public PurchaseReturn reasonForReturn(String reasonForReturn) {
        this.setReasonForReturn(reasonForReturn);
        return this;
    }

    public void setReasonForReturn(String reasonForReturn) {
        this.reasonForReturn = reasonForReturn;
    }

    public String getPurchaseDept() {
        return this.purchaseDept;
    }

    public PurchaseReturn purchaseDept(String purchaseDept) {
        this.setPurchaseDept(purchaseDept);
        return this;
    }

    public void setPurchaseDept(String purchaseDept) {
        this.purchaseDept = purchaseDept;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public PurchaseReturn remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDocStatus() {
        return this.docStatus;
    }

    public PurchaseReturn docStatus(String docStatus) {
        this.setDocStatus(docStatus);
        return this;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public PurchaseReturn itemCode(String itemCode) {
        this.setItemCode(itemCode);
        return this;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSupplierCode() {
        return this.supplierCode;
    }

    public PurchaseReturn supplierCode(String supplierCode) {
        this.setSupplierCode(supplierCode);
        return this;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseReturn)) {
            return false;
        }
        return id != null && id.equals(((PurchaseReturn) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseReturn{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", docNumber='" + getDocNumber() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", supplierEmail='" + getSupplierEmail() + "'" +
            ", materialReturnDate='" + getMaterialReturnDate() + "'" +
            ", reasonForReturn='" + getReasonForReturn() + "'" +
            ", purchaseDept='" + getPurchaseDept() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", docStatus='" + getDocStatus() + "'" +
            ", itemCode='" + getItemCode() + "'" +
            ", supplierCode='" + getSupplierCode() + "'" +
            "}";
    }
}
