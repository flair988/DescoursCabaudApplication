package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductFinished.
 */
@Entity
@Table(name = "product_finished")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductFinished implements Serializable {

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

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "cate_gory")
    private String cateGory;

    @Column(name = "remark")
    private String remark;

    @Column(name = "material_receipt_date")
    private String materialReceiptDate;

    @Column(name = "doc_status")
    private String docStatus;

    @Column(name = "supplier_name")
    private String supplierName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductFinished id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ProductFinished itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public ProductFinished kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public ProductFinished supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierEmail() {
        return this.supplierEmail;
    }

    public ProductFinished supplierEmail(String supplierEmail) {
        this.setSupplierEmail(supplierEmail);
        return this;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public ProductFinished orderDate(String orderDate) {
        this.setOrderDate(orderDate);
        return this;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public ProductFinished cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getRemark() {
        return this.remark;
    }

    public ProductFinished remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaterialReceiptDate() {
        return this.materialReceiptDate;
    }

    public ProductFinished materialReceiptDate(String materialReceiptDate) {
        this.setMaterialReceiptDate(materialReceiptDate);
        return this;
    }

    public void setMaterialReceiptDate(String materialReceiptDate) {
        this.materialReceiptDate = materialReceiptDate;
    }

    public String getDocStatus() {
        return this.docStatus;
    }

    public ProductFinished docStatus(String docStatus) {
        this.setDocStatus(docStatus);
        return this;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public ProductFinished supplierName(String supplierName) {
        this.setSupplierName(supplierName);
        return this;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductFinished)) {
            return false;
        }
        return id != null && id.equals(((ProductFinished) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductFinished{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", supplierEmail='" + getSupplierEmail() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", remark='" + getRemark() + "'" +
            ", materialReceiptDate='" + getMaterialReceiptDate() + "'" +
            ", docStatus='" + getDocStatus() + "'" +
            ", supplierName='" + getSupplierName() + "'" +
            "}";
    }
}
