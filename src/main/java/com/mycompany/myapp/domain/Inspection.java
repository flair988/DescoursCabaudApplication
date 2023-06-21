package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Inspection.
 */
@Entity
@Table(name = "inspection")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Inspection implements Serializable {

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

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "email")
    private String email;

    @Column(name = "inspection_date")
    private String inspectionDate;

    @Column(name = "cate_gory")
    private String cateGory;

    @Column(name = "q_c_result")
    private String qCResult;

    @Column(name = "doc_status")
    private String docStatus;

    @Column(name = "inspection_type")
    private String inspectionType;

    @Column(name = "inspection_booking_status")
    private String inspectionBookingStatus;

    @Column(name = "inspection_end_date")
    private String inspectionEndDate;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "report_number")
    private String reportNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Inspection id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Inspection itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public Inspection kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public Inspection supplierName(String supplierName) {
        this.setSupplierName(supplierName);
        return this;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return this.email;
    }

    public Inspection email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInspectionDate() {
        return this.inspectionDate;
    }

    public Inspection inspectionDate(String inspectionDate) {
        this.setInspectionDate(inspectionDate);
        return this;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public Inspection cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getqCResult() {
        return this.qCResult;
    }

    public Inspection qCResult(String qCResult) {
        this.setqCResult(qCResult);
        return this;
    }

    public void setqCResult(String qCResult) {
        this.qCResult = qCResult;
    }

    public String getDocStatus() {
        return this.docStatus;
    }

    public Inspection docStatus(String docStatus) {
        this.setDocStatus(docStatus);
        return this;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getInspectionType() {
        return this.inspectionType;
    }

    public Inspection inspectionType(String inspectionType) {
        this.setInspectionType(inspectionType);
        return this;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getInspectionBookingStatus() {
        return this.inspectionBookingStatus;
    }

    public Inspection inspectionBookingStatus(String inspectionBookingStatus) {
        this.setInspectionBookingStatus(inspectionBookingStatus);
        return this;
    }

    public void setInspectionBookingStatus(String inspectionBookingStatus) {
        this.inspectionBookingStatus = inspectionBookingStatus;
    }

    public String getInspectionEndDate() {
        return this.inspectionEndDate;
    }

    public Inspection inspectionEndDate(String inspectionEndDate) {
        this.setInspectionEndDate(inspectionEndDate);
        return this;
    }

    public void setInspectionEndDate(String inspectionEndDate) {
        this.inspectionEndDate = inspectionEndDate;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public Inspection supplierId(String supplierId) {
        this.setSupplierId(supplierId);
        return this;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getReportNumber() {
        return this.reportNumber;
    }

    public Inspection reportNumber(String reportNumber) {
        this.setReportNumber(reportNumber);
        return this;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inspection)) {
            return false;
        }
        return id != null && id.equals(((Inspection) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inspection{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", supplierName='" + getSupplierName() + "'" +
            ", email='" + getEmail() + "'" +
            ", inspectionDate='" + getInspectionDate() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", qCResult='" + getqCResult() + "'" +
            ", docStatus='" + getDocStatus() + "'" +
            ", inspectionType='" + getInspectionType() + "'" +
            ", inspectionBookingStatus='" + getInspectionBookingStatus() + "'" +
            ", inspectionEndDate='" + getInspectionEndDate() + "'" +
            ", supplierId='" + getSupplierId() + "'" +
            ", reportNumber='" + getReportNumber() + "'" +
            "}";
    }
}
