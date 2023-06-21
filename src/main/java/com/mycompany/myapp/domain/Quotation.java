package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Quotation.
 */
@Entity
@Table(name = "quotation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Quotation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quotation_date")
    private String quotationDate;

    @Column(name = "inquiry_doc_number")
    private String inquiryDocNumber;

    @Column(name = "doc_no")
    private String docNo;

    @Column(name = "doc_status")
    private String docStatus;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Quotation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Quotation itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuotationDate() {
        return this.quotationDate;
    }

    public Quotation quotationDate(String quotationDate) {
        this.setQuotationDate(quotationDate);
        return this;
    }

    public void setQuotationDate(String quotationDate) {
        this.quotationDate = quotationDate;
    }

    public String getInquiryDocNumber() {
        return this.inquiryDocNumber;
    }

    public Quotation inquiryDocNumber(String inquiryDocNumber) {
        this.setInquiryDocNumber(inquiryDocNumber);
        return this;
    }

    public void setInquiryDocNumber(String inquiryDocNumber) {
        this.inquiryDocNumber = inquiryDocNumber;
    }

    public String getDocNo() {
        return this.docNo;
    }

    public Quotation docNo(String docNo) {
        this.setDocNo(docNo);
        return this;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocStatus() {
        return this.docStatus;
    }

    public Quotation docStatus(String docStatus) {
        this.setDocStatus(docStatus);
        return this;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public Quotation supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public Quotation kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quotation)) {
            return false;
        }
        return id != null && id.equals(((Quotation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Quotation{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", quotationDate='" + getQuotationDate() + "'" +
            ", inquiryDocNumber='" + getInquiryDocNumber() + "'" +
            ", docNo='" + getDocNo() + "'" +
            ", docStatus='" + getDocStatus() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            "}";
    }
}
