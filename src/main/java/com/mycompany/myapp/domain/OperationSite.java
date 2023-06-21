package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OperationSite.
 */
@Entity
@Table(name = "operation_site")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperationSite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "link_supplier_factory")
    private String linkSupplierFactory;

    @Column(name = "type_of_site")
    private String typeOfSite;

    @Column(name = "contact")
    private String contact;

    @Column(name = "site_address")
    private String siteAddress;

    @Column(name = "cate_gory")
    private String cateGory;

    @Column(name = "country")
    private String country;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "business_license")
    private String businessLicense;

    @Column(name = "sas_date")
    private LocalDate sasDate;

    @Column(name = "iso_900_valid_util")
    private LocalDate iso900ValidUtil;

    @Column(name = "iso_14001_valid_util")
    private LocalDate iso14001ValidUtil;

    @Column(name = "attribute_lor")
    private String attributeLor;

    @Column(name = "site_qualification")
    private String siteQualification;

    @Column(name = "qualification_score")
    private String qualificationScore;

    @Column(name = "pqv_score")
    private String pqvScore;

    @Column(name = "pqv_date")
    private String pqvDate;

    @Column(name = "pqv_decision")
    private String pqvDecision;

    @Column(name = "technical_audit_date")
    private String technicalAuditDate;

    @Column(name = "technical_audit_score")
    private String technicalAuditScore;

    @Column(name = "third_rd_party_date")
    private String thirdRdPartyDate;

    @Column(name = "third_rd_party_score")
    private String thirdRdPartyScore;

    @Column(name = "bope_date")
    private String bopeDate;

    @Column(name = "bope_score")
    private String bopeScore;

    @Column(name = "cap_required")
    private String capRequired;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OperationSite id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public OperationSite itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLinkSupplierFactory() {
        return this.linkSupplierFactory;
    }

    public OperationSite linkSupplierFactory(String linkSupplierFactory) {
        this.setLinkSupplierFactory(linkSupplierFactory);
        return this;
    }

    public void setLinkSupplierFactory(String linkSupplierFactory) {
        this.linkSupplierFactory = linkSupplierFactory;
    }

    public String getTypeOfSite() {
        return this.typeOfSite;
    }

    public OperationSite typeOfSite(String typeOfSite) {
        this.setTypeOfSite(typeOfSite);
        return this;
    }

    public void setTypeOfSite(String typeOfSite) {
        this.typeOfSite = typeOfSite;
    }

    public String getContact() {
        return this.contact;
    }

    public OperationSite contact(String contact) {
        this.setContact(contact);
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSiteAddress() {
        return this.siteAddress;
    }

    public OperationSite siteAddress(String siteAddress) {
        this.setSiteAddress(siteAddress);
        return this;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public OperationSite cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getCountry() {
        return this.country;
    }

    public OperationSite country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public OperationSite kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getBusinessLicense() {
        return this.businessLicense;
    }

    public OperationSite businessLicense(String businessLicense) {
        this.setBusinessLicense(businessLicense);
        return this;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public LocalDate getSasDate() {
        return this.sasDate;
    }

    public OperationSite sasDate(LocalDate sasDate) {
        this.setSasDate(sasDate);
        return this;
    }

    public void setSasDate(LocalDate sasDate) {
        this.sasDate = sasDate;
    }

    public LocalDate getIso900ValidUtil() {
        return this.iso900ValidUtil;
    }

    public OperationSite iso900ValidUtil(LocalDate iso900ValidUtil) {
        this.setIso900ValidUtil(iso900ValidUtil);
        return this;
    }

    public void setIso900ValidUtil(LocalDate iso900ValidUtil) {
        this.iso900ValidUtil = iso900ValidUtil;
    }

    public LocalDate getIso14001ValidUtil() {
        return this.iso14001ValidUtil;
    }

    public OperationSite iso14001ValidUtil(LocalDate iso14001ValidUtil) {
        this.setIso14001ValidUtil(iso14001ValidUtil);
        return this;
    }

    public void setIso14001ValidUtil(LocalDate iso14001ValidUtil) {
        this.iso14001ValidUtil = iso14001ValidUtil;
    }

    public String getAttributeLor() {
        return this.attributeLor;
    }

    public OperationSite attributeLor(String attributeLor) {
        this.setAttributeLor(attributeLor);
        return this;
    }

    public void setAttributeLor(String attributeLor) {
        this.attributeLor = attributeLor;
    }

    public String getSiteQualification() {
        return this.siteQualification;
    }

    public OperationSite siteQualification(String siteQualification) {
        this.setSiteQualification(siteQualification);
        return this;
    }

    public void setSiteQualification(String siteQualification) {
        this.siteQualification = siteQualification;
    }

    public String getQualificationScore() {
        return this.qualificationScore;
    }

    public OperationSite qualificationScore(String qualificationScore) {
        this.setQualificationScore(qualificationScore);
        return this;
    }

    public void setQualificationScore(String qualificationScore) {
        this.qualificationScore = qualificationScore;
    }

    public String getPqvScore() {
        return this.pqvScore;
    }

    public OperationSite pqvScore(String pqvScore) {
        this.setPqvScore(pqvScore);
        return this;
    }

    public void setPqvScore(String pqvScore) {
        this.pqvScore = pqvScore;
    }

    public String getPqvDate() {
        return this.pqvDate;
    }

    public OperationSite pqvDate(String pqvDate) {
        this.setPqvDate(pqvDate);
        return this;
    }

    public void setPqvDate(String pqvDate) {
        this.pqvDate = pqvDate;
    }

    public String getPqvDecision() {
        return this.pqvDecision;
    }

    public OperationSite pqvDecision(String pqvDecision) {
        this.setPqvDecision(pqvDecision);
        return this;
    }

    public void setPqvDecision(String pqvDecision) {
        this.pqvDecision = pqvDecision;
    }

    public String getTechnicalAuditDate() {
        return this.technicalAuditDate;
    }

    public OperationSite technicalAuditDate(String technicalAuditDate) {
        this.setTechnicalAuditDate(technicalAuditDate);
        return this;
    }

    public void setTechnicalAuditDate(String technicalAuditDate) {
        this.technicalAuditDate = technicalAuditDate;
    }

    public String getTechnicalAuditScore() {
        return this.technicalAuditScore;
    }

    public OperationSite technicalAuditScore(String technicalAuditScore) {
        this.setTechnicalAuditScore(technicalAuditScore);
        return this;
    }

    public void setTechnicalAuditScore(String technicalAuditScore) {
        this.technicalAuditScore = technicalAuditScore;
    }

    public String getThirdRdPartyDate() {
        return this.thirdRdPartyDate;
    }

    public OperationSite thirdRdPartyDate(String thirdRdPartyDate) {
        this.setThirdRdPartyDate(thirdRdPartyDate);
        return this;
    }

    public void setThirdRdPartyDate(String thirdRdPartyDate) {
        this.thirdRdPartyDate = thirdRdPartyDate;
    }

    public String getThirdRdPartyScore() {
        return this.thirdRdPartyScore;
    }

    public OperationSite thirdRdPartyScore(String thirdRdPartyScore) {
        this.setThirdRdPartyScore(thirdRdPartyScore);
        return this;
    }

    public void setThirdRdPartyScore(String thirdRdPartyScore) {
        this.thirdRdPartyScore = thirdRdPartyScore;
    }

    public String getBopeDate() {
        return this.bopeDate;
    }

    public OperationSite bopeDate(String bopeDate) {
        this.setBopeDate(bopeDate);
        return this;
    }

    public void setBopeDate(String bopeDate) {
        this.bopeDate = bopeDate;
    }

    public String getBopeScore() {
        return this.bopeScore;
    }

    public OperationSite bopeScore(String bopeScore) {
        this.setBopeScore(bopeScore);
        return this;
    }

    public void setBopeScore(String bopeScore) {
        this.bopeScore = bopeScore;
    }

    public String getCapRequired() {
        return this.capRequired;
    }

    public OperationSite capRequired(String capRequired) {
        this.setCapRequired(capRequired);
        return this;
    }

    public void setCapRequired(String capRequired) {
        this.capRequired = capRequired;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationSite)) {
            return false;
        }
        return id != null && id.equals(((OperationSite) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationSite{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", linkSupplierFactory='" + getLinkSupplierFactory() + "'" +
            ", typeOfSite='" + getTypeOfSite() + "'" +
            ", contact='" + getContact() + "'" +
            ", siteAddress='" + getSiteAddress() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", country='" + getCountry() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", businessLicense='" + getBusinessLicense() + "'" +
            ", sasDate='" + getSasDate() + "'" +
            ", iso900ValidUtil='" + getIso900ValidUtil() + "'" +
            ", iso14001ValidUtil='" + getIso14001ValidUtil() + "'" +
            ", attributeLor='" + getAttributeLor() + "'" +
            ", siteQualification='" + getSiteQualification() + "'" +
            ", qualificationScore='" + getQualificationScore() + "'" +
            ", pqvScore='" + getPqvScore() + "'" +
            ", pqvDate='" + getPqvDate() + "'" +
            ", pqvDecision='" + getPqvDecision() + "'" +
            ", technicalAuditDate='" + getTechnicalAuditDate() + "'" +
            ", technicalAuditScore='" + getTechnicalAuditScore() + "'" +
            ", thirdRdPartyDate='" + getThirdRdPartyDate() + "'" +
            ", thirdRdPartyScore='" + getThirdRdPartyScore() + "'" +
            ", bopeDate='" + getBopeDate() + "'" +
            ", bopeScore='" + getBopeScore() + "'" +
            ", capRequired='" + getCapRequired() + "'" +
            "}";
    }
}
