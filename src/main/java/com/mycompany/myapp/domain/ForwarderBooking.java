package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ForwarderBooking.
 */
@Entity
@Table(name = "forwarder_booking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ForwarderBooking implements Serializable {

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

    @Column(name = "customer")
    private String customer;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "forwarder")
    private String forwarder;

    @Column(name = "total_qty")
    private String totalQty;

    @Column(name = "loading_port")
    private String loadingPort;

    @Column(name = "discharge_port")
    private String dischargePort;

    @Column(name = "container_type")
    private String containerType;

    @Column(name = "container_size")
    private String containerSize;

    @Column(name = "container_number")
    private String containerNumber;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "eta")
    private String eta;

    @Column(name = "etd")
    private String etd;

    @Column(name = "transport_mode")
    private String transportMode;

    @Column(name = "number_of_cartons")
    private String numberOfCartons;

    @Column(name = "number_of_ref")
    private String numberOfRef;

    @Column(name = "total_volume")
    private String totalVolume;

    @Column(name = "total_weight")
    private String totalWeight;

    @Column(name = "remark")
    private String remark;

    @Column(name = "client")
    private String client;

    @Column(name = "kingdee_unique_id")
    private String kingdeeUniqueId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ForwarderBooking id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ForwarderBooking itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public ForwarderBooking kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getCustomer() {
        return this.customer;
    }

    public ForwarderBooking customer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public ForwarderBooking orderDate(String orderDate) {
        this.setOrderDate(orderDate);
        return this;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getForwarder() {
        return this.forwarder;
    }

    public ForwarderBooking forwarder(String forwarder) {
        this.setForwarder(forwarder);
        return this;
    }

    public void setForwarder(String forwarder) {
        this.forwarder = forwarder;
    }

    public String getTotalQty() {
        return this.totalQty;
    }

    public ForwarderBooking totalQty(String totalQty) {
        this.setTotalQty(totalQty);
        return this;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
    }

    public String getLoadingPort() {
        return this.loadingPort;
    }

    public ForwarderBooking loadingPort(String loadingPort) {
        this.setLoadingPort(loadingPort);
        return this;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort;
    }

    public String getDischargePort() {
        return this.dischargePort;
    }

    public ForwarderBooking dischargePort(String dischargePort) {
        this.setDischargePort(dischargePort);
        return this;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getContainerType() {
        return this.containerType;
    }

    public ForwarderBooking containerType(String containerType) {
        this.setContainerType(containerType);
        return this;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getContainerSize() {
        return this.containerSize;
    }

    public ForwarderBooking containerSize(String containerSize) {
        this.setContainerSize(containerSize);
        return this;
    }

    public void setContainerSize(String containerSize) {
        this.containerSize = containerSize;
    }

    public String getContainerNumber() {
        return this.containerNumber;
    }

    public ForwarderBooking containerNumber(String containerNumber) {
        this.setContainerNumber(containerNumber);
        return this;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public ForwarderBooking supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierEmail() {
        return this.supplierEmail;
    }

    public ForwarderBooking supplierEmail(String supplierEmail) {
        this.setSupplierEmail(supplierEmail);
        return this;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getEta() {
        return this.eta;
    }

    public ForwarderBooking eta(String eta) {
        this.setEta(eta);
        return this;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEtd() {
        return this.etd;
    }

    public ForwarderBooking etd(String etd) {
        this.setEtd(etd);
        return this;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getTransportMode() {
        return this.transportMode;
    }

    public ForwarderBooking transportMode(String transportMode) {
        this.setTransportMode(transportMode);
        return this;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getNumberOfCartons() {
        return this.numberOfCartons;
    }

    public ForwarderBooking numberOfCartons(String numberOfCartons) {
        this.setNumberOfCartons(numberOfCartons);
        return this;
    }

    public void setNumberOfCartons(String numberOfCartons) {
        this.numberOfCartons = numberOfCartons;
    }

    public String getNumberOfRef() {
        return this.numberOfRef;
    }

    public ForwarderBooking numberOfRef(String numberOfRef) {
        this.setNumberOfRef(numberOfRef);
        return this;
    }

    public void setNumberOfRef(String numberOfRef) {
        this.numberOfRef = numberOfRef;
    }

    public String getTotalVolume() {
        return this.totalVolume;
    }

    public ForwarderBooking totalVolume(String totalVolume) {
        this.setTotalVolume(totalVolume);
        return this;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getTotalWeight() {
        return this.totalWeight;
    }

    public ForwarderBooking totalWeight(String totalWeight) {
        this.setTotalWeight(totalWeight);
        return this;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getRemark() {
        return this.remark;
    }

    public ForwarderBooking remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getClient() {
        return this.client;
    }

    public ForwarderBooking client(String client) {
        this.setClient(client);
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getKingdeeUniqueId() {
        return this.kingdeeUniqueId;
    }

    public ForwarderBooking kingdeeUniqueId(String kingdeeUniqueId) {
        this.setKingdeeUniqueId(kingdeeUniqueId);
        return this;
    }

    public void setKingdeeUniqueId(String kingdeeUniqueId) {
        this.kingdeeUniqueId = kingdeeUniqueId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ForwarderBooking)) {
            return false;
        }
        return id != null && id.equals(((ForwarderBooking) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ForwarderBooking{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", forwarder='" + getForwarder() + "'" +
            ", totalQty='" + getTotalQty() + "'" +
            ", loadingPort='" + getLoadingPort() + "'" +
            ", dischargePort='" + getDischargePort() + "'" +
            ", containerType='" + getContainerType() + "'" +
            ", containerSize='" + getContainerSize() + "'" +
            ", containerNumber='" + getContainerNumber() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", supplierEmail='" + getSupplierEmail() + "'" +
            ", eta='" + getEta() + "'" +
            ", etd='" + getEtd() + "'" +
            ", transportMode='" + getTransportMode() + "'" +
            ", numberOfCartons='" + getNumberOfCartons() + "'" +
            ", numberOfRef='" + getNumberOfRef() + "'" +
            ", totalVolume='" + getTotalVolume() + "'" +
            ", totalWeight='" + getTotalWeight() + "'" +
            ", remark='" + getRemark() + "'" +
            ", client='" + getClient() + "'" +
            ", kingdeeUniqueId='" + getKingdeeUniqueId() + "'" +
            "}";
    }
}
