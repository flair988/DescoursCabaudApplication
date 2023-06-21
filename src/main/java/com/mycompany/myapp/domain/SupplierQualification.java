package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SupplierQualification.
 */
@Entity
@Table(name = "supplier_qualification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SupplierQualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "cate_gory")
    private String cateGory;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "supplier_status")
    private String supplierStatus;

    @Column(name = "evaluation_status")
    private String evaluationStatus;

    @Column(name = "business_liability_bope_score")
    private String businessLiabilityBopeScore;

    @Column(name = "comments")
    private String comments;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SupplierQualification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public SupplierQualification itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public SupplierQualification cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public SupplierQualification supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public SupplierQualification date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplierStatus() {
        return this.supplierStatus;
    }

    public SupplierQualification supplierStatus(String supplierStatus) {
        this.setSupplierStatus(supplierStatus);
        return this;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getEvaluationStatus() {
        return this.evaluationStatus;
    }

    public SupplierQualification evaluationStatus(String evaluationStatus) {
        this.setEvaluationStatus(evaluationStatus);
        return this;
    }

    public void setEvaluationStatus(String evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public String getBusinessLiabilityBopeScore() {
        return this.businessLiabilityBopeScore;
    }

    public SupplierQualification businessLiabilityBopeScore(String businessLiabilityBopeScore) {
        this.setBusinessLiabilityBopeScore(businessLiabilityBopeScore);
        return this;
    }

    public void setBusinessLiabilityBopeScore(String businessLiabilityBopeScore) {
        this.businessLiabilityBopeScore = businessLiabilityBopeScore;
    }

    public String getComments() {
        return this.comments;
    }

    public SupplierQualification comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupplierQualification)) {
            return false;
        }
        return id != null && id.equals(((SupplierQualification) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupplierQualification{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", date='" + getDate() + "'" +
            ", supplierStatus='" + getSupplierStatus() + "'" +
            ", evaluationStatus='" + getEvaluationStatus() + "'" +
            ", businessLiabilityBopeScore='" + getBusinessLiabilityBopeScore() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
