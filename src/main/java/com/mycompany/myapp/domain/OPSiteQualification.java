package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OPSiteQualification.
 */
@Entity
@Table(name = "op_site_qualification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OPSiteQualification implements Serializable {

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

    @Column(name = "operation_site")
    private String operationSite;

    @Column(name = "attributed_lo_r_for_this_site")
    private String attributedLoRForThisSite;

    @Column(name = "site_qualification_status")
    private String siteQualificationStatus;

    @Column(name = "csr_result")
    private String csrResult;

    @Column(name = "quality_production_result")
    private String qualityProductionResult;

    @Column(name = "business_liability_result")
    private String businessLiabilityResult;

    @Column(name = "comments")
    private String comments;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OPSiteQualification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public OPSiteQualification itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public OPSiteQualification cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public OPSiteQualification supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public OPSiteQualification date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOperationSite() {
        return this.operationSite;
    }

    public OPSiteQualification operationSite(String operationSite) {
        this.setOperationSite(operationSite);
        return this;
    }

    public void setOperationSite(String operationSite) {
        this.operationSite = operationSite;
    }

    public String getAttributedLoRForThisSite() {
        return this.attributedLoRForThisSite;
    }

    public OPSiteQualification attributedLoRForThisSite(String attributedLoRForThisSite) {
        this.setAttributedLoRForThisSite(attributedLoRForThisSite);
        return this;
    }

    public void setAttributedLoRForThisSite(String attributedLoRForThisSite) {
        this.attributedLoRForThisSite = attributedLoRForThisSite;
    }

    public String getSiteQualificationStatus() {
        return this.siteQualificationStatus;
    }

    public OPSiteQualification siteQualificationStatus(String siteQualificationStatus) {
        this.setSiteQualificationStatus(siteQualificationStatus);
        return this;
    }

    public void setSiteQualificationStatus(String siteQualificationStatus) {
        this.siteQualificationStatus = siteQualificationStatus;
    }

    public String getCsrResult() {
        return this.csrResult;
    }

    public OPSiteQualification csrResult(String csrResult) {
        this.setCsrResult(csrResult);
        return this;
    }

    public void setCsrResult(String csrResult) {
        this.csrResult = csrResult;
    }

    public String getQualityProductionResult() {
        return this.qualityProductionResult;
    }

    public OPSiteQualification qualityProductionResult(String qualityProductionResult) {
        this.setQualityProductionResult(qualityProductionResult);
        return this;
    }

    public void setQualityProductionResult(String qualityProductionResult) {
        this.qualityProductionResult = qualityProductionResult;
    }

    public String getBusinessLiabilityResult() {
        return this.businessLiabilityResult;
    }

    public OPSiteQualification businessLiabilityResult(String businessLiabilityResult) {
        this.setBusinessLiabilityResult(businessLiabilityResult);
        return this;
    }

    public void setBusinessLiabilityResult(String businessLiabilityResult) {
        this.businessLiabilityResult = businessLiabilityResult;
    }

    public String getComments() {
        return this.comments;
    }

    public OPSiteQualification comments(String comments) {
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
        if (!(o instanceof OPSiteQualification)) {
            return false;
        }
        return id != null && id.equals(((OPSiteQualification) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OPSiteQualification{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", date='" + getDate() + "'" +
            ", operationSite='" + getOperationSite() + "'" +
            ", attributedLoRForThisSite='" + getAttributedLoRForThisSite() + "'" +
            ", siteQualificationStatus='" + getSiteQualificationStatus() + "'" +
            ", csrResult='" + getCsrResult() + "'" +
            ", qualityProductionResult='" + getQualityProductionResult() + "'" +
            ", businessLiabilityResult='" + getBusinessLiabilityResult() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
