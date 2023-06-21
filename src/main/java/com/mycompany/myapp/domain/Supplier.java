package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Supplier.
 */
@Entity
@Table(name = "supplier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "client")
    private String client;

    @Column(name = "category")
    private String category;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "cap_status")
    private String capStatus;

    @Column(name = "supplier_status")
    private String supplierStatus;

    @Column(name = "qualification_score")
    private String qualificationScore;

    @Column(name = "bope_score")
    private String bopeScore;

    @Column(name = "internal_supplier_id")
    private String internalSupplierId;

    @Column(name = "contact")
    private String contact;

    @Column(name = "contact_phone_number")
    private String contactPhoneNumber;

    @Column(name = "contact_email_address")
    private String contactEmailAddress;

    @Column(name = "operation_site")
    private String operationSite;

    @Column(name = "address")
    private String address;

    @Column(name = "website")
    private String website;

    @Column(name = "relation_starting_year")
    private String relationStartingYear;

    @Column(name = "business_license")
    private String businessLicense;

    @Column(name = "rex_origin_status")
    private String rexOriginStatus;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "item")
    private String item;

    @Column(name = "sub_items")
    private String subItems;

    @Column(name = "date")
    private String date;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "region")
    private String region;

    @Column(name = "supplier_type")
    private String supplierType;

    @Column(name = "remark")
    private String remark;

    @Column(name = "french_name")
    private String frenchName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Supplier id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return this.client;
    }

    public Supplier client(String client) {
        this.setClient(client);
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCategory() {
        return this.category;
    }

    public Supplier category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public Supplier subCategory(String subCategory) {
        this.setSubCategory(subCategory);
        return this;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getCapStatus() {
        return this.capStatus;
    }

    public Supplier capStatus(String capStatus) {
        this.setCapStatus(capStatus);
        return this;
    }

    public void setCapStatus(String capStatus) {
        this.capStatus = capStatus;
    }

    public String getSupplierStatus() {
        return this.supplierStatus;
    }

    public Supplier supplierStatus(String supplierStatus) {
        this.setSupplierStatus(supplierStatus);
        return this;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getQualificationScore() {
        return this.qualificationScore;
    }

    public Supplier qualificationScore(String qualificationScore) {
        this.setQualificationScore(qualificationScore);
        return this;
    }

    public void setQualificationScore(String qualificationScore) {
        this.qualificationScore = qualificationScore;
    }

    public String getBopeScore() {
        return this.bopeScore;
    }

    public Supplier bopeScore(String bopeScore) {
        this.setBopeScore(bopeScore);
        return this;
    }

    public void setBopeScore(String bopeScore) {
        this.bopeScore = bopeScore;
    }

    public String getInternalSupplierId() {
        return this.internalSupplierId;
    }

    public Supplier internalSupplierId(String internalSupplierId) {
        this.setInternalSupplierId(internalSupplierId);
        return this;
    }

    public void setInternalSupplierId(String internalSupplierId) {
        this.internalSupplierId = internalSupplierId;
    }

    public String getContact() {
        return this.contact;
    }

    public Supplier contact(String contact) {
        this.setContact(contact);
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhoneNumber() {
        return this.contactPhoneNumber;
    }

    public Supplier contactPhoneNumber(String contactPhoneNumber) {
        this.setContactPhoneNumber(contactPhoneNumber);
        return this;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactEmailAddress() {
        return this.contactEmailAddress;
    }

    public Supplier contactEmailAddress(String contactEmailAddress) {
        this.setContactEmailAddress(contactEmailAddress);
        return this;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getOperationSite() {
        return this.operationSite;
    }

    public Supplier operationSite(String operationSite) {
        this.setOperationSite(operationSite);
        return this;
    }

    public void setOperationSite(String operationSite) {
        this.operationSite = operationSite;
    }

    public String getAddress() {
        return this.address;
    }

    public Supplier address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return this.website;
    }

    public Supplier website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRelationStartingYear() {
        return this.relationStartingYear;
    }

    public Supplier relationStartingYear(String relationStartingYear) {
        this.setRelationStartingYear(relationStartingYear);
        return this;
    }

    public void setRelationStartingYear(String relationStartingYear) {
        this.relationStartingYear = relationStartingYear;
    }

    public String getBusinessLicense() {
        return this.businessLicense;
    }

    public Supplier businessLicense(String businessLicense) {
        this.setBusinessLicense(businessLicense);
        return this;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getRexOriginStatus() {
        return this.rexOriginStatus;
    }

    public Supplier rexOriginStatus(String rexOriginStatus) {
        this.setRexOriginStatus(rexOriginStatus);
        return this;
    }

    public void setRexOriginStatus(String rexOriginStatus) {
        this.rexOriginStatus = rexOriginStatus;
    }

    public LocalDate getCreateDate() {
        return this.createDate;
    }

    public Supplier createDate(LocalDate createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return this.updateDate;
    }

    public Supplier updateDate(LocalDate updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getItem() {
        return this.item;
    }

    public Supplier item(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSubItems() {
        return this.subItems;
    }

    public Supplier subItems(String subItems) {
        this.setSubItems(subItems);
        return this;
    }

    public void setSubItems(String subItems) {
        this.subItems = subItems;
    }

    public String getDate() {
        return this.date;
    }

    public Supplier date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public Supplier kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getRegion() {
        return this.region;
    }

    public Supplier region(String region) {
        this.setRegion(region);
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSupplierType() {
        return this.supplierType;
    }

    public Supplier supplierType(String supplierType) {
        this.setSupplierType(supplierType);
        return this;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getRemark() {
        return this.remark;
    }

    public Supplier remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFrenchName() {
        return this.frenchName;
    }

    public Supplier frenchName(String frenchName) {
        this.setFrenchName(frenchName);
        return this;
    }

    public void setFrenchName(String frenchName) {
        this.frenchName = frenchName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Supplier)) {
            return false;
        }
        return id != null && id.equals(((Supplier) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Supplier{" +
            "id=" + getId() +
            ", client='" + getClient() + "'" +
            ", category='" + getCategory() + "'" +
            ", subCategory='" + getSubCategory() + "'" +
            ", capStatus='" + getCapStatus() + "'" +
            ", supplierStatus='" + getSupplierStatus() + "'" +
            ", qualificationScore='" + getQualificationScore() + "'" +
            ", bopeScore='" + getBopeScore() + "'" +
            ", internalSupplierId='" + getInternalSupplierId() + "'" +
            ", contact='" + getContact() + "'" +
            ", contactPhoneNumber='" + getContactPhoneNumber() + "'" +
            ", contactEmailAddress='" + getContactEmailAddress() + "'" +
            ", operationSite='" + getOperationSite() + "'" +
            ", address='" + getAddress() + "'" +
            ", website='" + getWebsite() + "'" +
            ", relationStartingYear='" + getRelationStartingYear() + "'" +
            ", businessLicense='" + getBusinessLicense() + "'" +
            ", rexOriginStatus='" + getRexOriginStatus() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", item='" + getItem() + "'" +
            ", subItems='" + getSubItems() + "'" +
            ", date='" + getDate() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", region='" + getRegion() + "'" +
            ", supplierType='" + getSupplierType() + "'" +
            ", remark='" + getRemark() + "'" +
            ", frenchName='" + getFrenchName() + "'" +
            "}";
    }
}
