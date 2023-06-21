package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PI.
 */
@Entity
@Table(name = "pi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PI implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "po_number")
    private String poNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "client")
    private String client;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "requested_end_of_prod_date")
    private String requestedEndOfProdDate;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "country_of_final_destination")
    private String countryOfFinalDestination;

    @Column(name = "consignee")
    private String consignee;

    @Column(name = "carriage_by")
    private String carriageBy;

    @Column(name = "terms_of_delivery")
    private String termsOfDelivery;

    @Column(name = "terms_of_payment")
    private String termsOfPayment;

    @Column(name = "currency")
    private String currency;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "port_of_discharge")
    private String portOfDischarge;

    @Column(name = "port_of_loading")
    private String portOfLoading;

    @Column(name = "doc_status")
    private String docStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PI id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public PI itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public PI poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getCategory() {
        return this.category;
    }

    public PI category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClient() {
        return this.client;
    }

    public PI client(String client) {
        this.setClient(client);
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public PI orderDate(String orderDate) {
        this.setOrderDate(orderDate);
        return this;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequestedEndOfProdDate() {
        return this.requestedEndOfProdDate;
    }

    public PI requestedEndOfProdDate(String requestedEndOfProdDate) {
        this.setRequestedEndOfProdDate(requestedEndOfProdDate);
        return this;
    }

    public void setRequestedEndOfProdDate(String requestedEndOfProdDate) {
        this.requestedEndOfProdDate = requestedEndOfProdDate;
    }

    public String getCountryOfOrigin() {
        return this.countryOfOrigin;
    }

    public PI countryOfOrigin(String countryOfOrigin) {
        this.setCountryOfOrigin(countryOfOrigin);
        return this;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getCountryOfFinalDestination() {
        return this.countryOfFinalDestination;
    }

    public PI countryOfFinalDestination(String countryOfFinalDestination) {
        this.setCountryOfFinalDestination(countryOfFinalDestination);
        return this;
    }

    public void setCountryOfFinalDestination(String countryOfFinalDestination) {
        this.countryOfFinalDestination = countryOfFinalDestination;
    }

    public String getConsignee() {
        return this.consignee;
    }

    public PI consignee(String consignee) {
        this.setConsignee(consignee);
        return this;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCarriageBy() {
        return this.carriageBy;
    }

    public PI carriageBy(String carriageBy) {
        this.setCarriageBy(carriageBy);
        return this;
    }

    public void setCarriageBy(String carriageBy) {
        this.carriageBy = carriageBy;
    }

    public String getTermsOfDelivery() {
        return this.termsOfDelivery;
    }

    public PI termsOfDelivery(String termsOfDelivery) {
        this.setTermsOfDelivery(termsOfDelivery);
        return this;
    }

    public void setTermsOfDelivery(String termsOfDelivery) {
        this.termsOfDelivery = termsOfDelivery;
    }

    public String getTermsOfPayment() {
        return this.termsOfPayment;
    }

    public PI termsOfPayment(String termsOfPayment) {
        this.setTermsOfPayment(termsOfPayment);
        return this;
    }

    public void setTermsOfPayment(String termsOfPayment) {
        this.termsOfPayment = termsOfPayment;
    }

    public String getCurrency() {
        return this.currency;
    }

    public PI currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public PI remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public PI kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getPortOfDischarge() {
        return this.portOfDischarge;
    }

    public PI portOfDischarge(String portOfDischarge) {
        this.setPortOfDischarge(portOfDischarge);
        return this;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

    public String getPortOfLoading() {
        return this.portOfLoading;
    }

    public PI portOfLoading(String portOfLoading) {
        this.setPortOfLoading(portOfLoading);
        return this;
    }

    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }

    public String getDocStatus() {
        return this.docStatus;
    }

    public PI docStatus(String docStatus) {
        this.setDocStatus(docStatus);
        return this;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PI)) {
            return false;
        }
        return id != null && id.equals(((PI) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PI{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", poNumber='" + getPoNumber() + "'" +
            ", category='" + getCategory() + "'" +
            ", client='" + getClient() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", requestedEndOfProdDate='" + getRequestedEndOfProdDate() + "'" +
            ", countryOfOrigin='" + getCountryOfOrigin() + "'" +
            ", countryOfFinalDestination='" + getCountryOfFinalDestination() + "'" +
            ", consignee='" + getConsignee() + "'" +
            ", carriageBy='" + getCarriageBy() + "'" +
            ", termsOfDelivery='" + getTermsOfDelivery() + "'" +
            ", termsOfPayment='" + getTermsOfPayment() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", portOfDischarge='" + getPortOfDischarge() + "'" +
            ", portOfLoading='" + getPortOfLoading() + "'" +
            ", docStatus='" + getDocStatus() + "'" +
            "}";
    }
}
