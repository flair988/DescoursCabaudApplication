package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrderFollowUp.
 */
@Entity
@Table(name = "order_follow_up")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderFollowUp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "po_number")
    private String poNumber;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "cate_gory")
    private String cateGory;

    @Column(name = "inspection_date")
    private String inspectionDate;

    @Column(name = "request_end_of_prod_date")
    private String requestEndOfProdDate;

    @Column(name = "total_amount")
    private String totalAmount;

    @Column(name = "total_discount")
    private String totalDiscount;

    @Column(name = "dis_count_rate")
    private String disCountRate;

    @Column(name = "regular_check")
    private String regularCheck;

    @Column(name = "etd")
    private String etd;

    @Column(name = "atd")
    private String atd;

    @Column(name = "eta")
    private String eta;

    @Column(name = "updated_eta")
    private String updatedEta;

    @Column(name = "document_status")
    private String documentStatus;

    @Column(name = "custom_instruction")
    private String customInstruction;

    @Column(name = "custom_inspection")
    private String customInspection;

    @Column(name = "deposit_payment_date")
    private String depositPaymentDate;

    @Column(name = "balance_of_total_payment_date")
    private String balanceOfTotalPaymentDate;

    @Column(name = "amount_deposit_payment")
    private String amountDepositPayment;

    @Column(name = "amount_payment")
    private String amountPayment;

    @Column(name = "dc_member")
    private String dcMember;

    @Column(name = "comment")
    private String comment;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "parent_kingdee_id")
    private String parentKingdeeId;

    @Column(name = "qty")
    private String qty;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "contract_end_of_prod_date")
    private String contractEndOfProdDate;

    @Column(name = "supplier_id")
    private String supplierId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderFollowUp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public OrderFollowUp poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public OrderFollowUp supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public OrderFollowUp orderDate(String orderDate) {
        this.setOrderDate(orderDate);
        return this;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public OrderFollowUp cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getInspectionDate() {
        return this.inspectionDate;
    }

    public OrderFollowUp inspectionDate(String inspectionDate) {
        this.setInspectionDate(inspectionDate);
        return this;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getRequestEndOfProdDate() {
        return this.requestEndOfProdDate;
    }

    public OrderFollowUp requestEndOfProdDate(String requestEndOfProdDate) {
        this.setRequestEndOfProdDate(requestEndOfProdDate);
        return this;
    }

    public void setRequestEndOfProdDate(String requestEndOfProdDate) {
        this.requestEndOfProdDate = requestEndOfProdDate;
    }

    public String getTotalAmount() {
        return this.totalAmount;
    }

    public OrderFollowUp totalAmount(String totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalDiscount() {
        return this.totalDiscount;
    }

    public OrderFollowUp totalDiscount(String totalDiscount) {
        this.setTotalDiscount(totalDiscount);
        return this;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getDisCountRate() {
        return this.disCountRate;
    }

    public OrderFollowUp disCountRate(String disCountRate) {
        this.setDisCountRate(disCountRate);
        return this;
    }

    public void setDisCountRate(String disCountRate) {
        this.disCountRate = disCountRate;
    }

    public String getRegularCheck() {
        return this.regularCheck;
    }

    public OrderFollowUp regularCheck(String regularCheck) {
        this.setRegularCheck(regularCheck);
        return this;
    }

    public void setRegularCheck(String regularCheck) {
        this.regularCheck = regularCheck;
    }

    public String getEtd() {
        return this.etd;
    }

    public OrderFollowUp etd(String etd) {
        this.setEtd(etd);
        return this;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getAtd() {
        return this.atd;
    }

    public OrderFollowUp atd(String atd) {
        this.setAtd(atd);
        return this;
    }

    public void setAtd(String atd) {
        this.atd = atd;
    }

    public String getEta() {
        return this.eta;
    }

    public OrderFollowUp eta(String eta) {
        this.setEta(eta);
        return this;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getUpdatedEta() {
        return this.updatedEta;
    }

    public OrderFollowUp updatedEta(String updatedEta) {
        this.setUpdatedEta(updatedEta);
        return this;
    }

    public void setUpdatedEta(String updatedEta) {
        this.updatedEta = updatedEta;
    }

    public String getDocumentStatus() {
        return this.documentStatus;
    }

    public OrderFollowUp documentStatus(String documentStatus) {
        this.setDocumentStatus(documentStatus);
        return this;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getCustomInstruction() {
        return this.customInstruction;
    }

    public OrderFollowUp customInstruction(String customInstruction) {
        this.setCustomInstruction(customInstruction);
        return this;
    }

    public void setCustomInstruction(String customInstruction) {
        this.customInstruction = customInstruction;
    }

    public String getCustomInspection() {
        return this.customInspection;
    }

    public OrderFollowUp customInspection(String customInspection) {
        this.setCustomInspection(customInspection);
        return this;
    }

    public void setCustomInspection(String customInspection) {
        this.customInspection = customInspection;
    }

    public String getDepositPaymentDate() {
        return this.depositPaymentDate;
    }

    public OrderFollowUp depositPaymentDate(String depositPaymentDate) {
        this.setDepositPaymentDate(depositPaymentDate);
        return this;
    }

    public void setDepositPaymentDate(String depositPaymentDate) {
        this.depositPaymentDate = depositPaymentDate;
    }

    public String getBalanceOfTotalPaymentDate() {
        return this.balanceOfTotalPaymentDate;
    }

    public OrderFollowUp balanceOfTotalPaymentDate(String balanceOfTotalPaymentDate) {
        this.setBalanceOfTotalPaymentDate(balanceOfTotalPaymentDate);
        return this;
    }

    public void setBalanceOfTotalPaymentDate(String balanceOfTotalPaymentDate) {
        this.balanceOfTotalPaymentDate = balanceOfTotalPaymentDate;
    }

    public String getAmountDepositPayment() {
        return this.amountDepositPayment;
    }

    public OrderFollowUp amountDepositPayment(String amountDepositPayment) {
        this.setAmountDepositPayment(amountDepositPayment);
        return this;
    }

    public void setAmountDepositPayment(String amountDepositPayment) {
        this.amountDepositPayment = amountDepositPayment;
    }

    public String getAmountPayment() {
        return this.amountPayment;
    }

    public OrderFollowUp amountPayment(String amountPayment) {
        this.setAmountPayment(amountPayment);
        return this;
    }

    public void setAmountPayment(String amountPayment) {
        this.amountPayment = amountPayment;
    }

    public String getDcMember() {
        return this.dcMember;
    }

    public OrderFollowUp dcMember(String dcMember) {
        this.setDcMember(dcMember);
        return this;
    }

    public void setDcMember(String dcMember) {
        this.dcMember = dcMember;
    }

    public String getComment() {
        return this.comment;
    }

    public OrderFollowUp comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getItemName() {
        return this.itemName;
    }

    public OrderFollowUp itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public OrderFollowUp kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getParentKingdeeId() {
        return this.parentKingdeeId;
    }

    public OrderFollowUp parentKingdeeId(String parentKingdeeId) {
        this.setParentKingdeeId(parentKingdeeId);
        return this;
    }

    public void setParentKingdeeId(String parentKingdeeId) {
        this.parentKingdeeId = parentKingdeeId;
    }

    public String getQty() {
        return this.qty;
    }

    public OrderFollowUp qty(String qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public OrderFollowUp itemCode(String itemCode) {
        this.setItemCode(itemCode);
        return this;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getContractEndOfProdDate() {
        return this.contractEndOfProdDate;
    }

    public OrderFollowUp contractEndOfProdDate(String contractEndOfProdDate) {
        this.setContractEndOfProdDate(contractEndOfProdDate);
        return this;
    }

    public void setContractEndOfProdDate(String contractEndOfProdDate) {
        this.contractEndOfProdDate = contractEndOfProdDate;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public OrderFollowUp supplierId(String supplierId) {
        this.setSupplierId(supplierId);
        return this;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderFollowUp)) {
            return false;
        }
        return id != null && id.equals(((OrderFollowUp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderFollowUp{" +
            "id=" + getId() +
            ", poNumber='" + getPoNumber() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", inspectionDate='" + getInspectionDate() + "'" +
            ", requestEndOfProdDate='" + getRequestEndOfProdDate() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            ", totalDiscount='" + getTotalDiscount() + "'" +
            ", disCountRate='" + getDisCountRate() + "'" +
            ", regularCheck='" + getRegularCheck() + "'" +
            ", etd='" + getEtd() + "'" +
            ", atd='" + getAtd() + "'" +
            ", eta='" + getEta() + "'" +
            ", updatedEta='" + getUpdatedEta() + "'" +
            ", documentStatus='" + getDocumentStatus() + "'" +
            ", customInstruction='" + getCustomInstruction() + "'" +
            ", customInspection='" + getCustomInspection() + "'" +
            ", depositPaymentDate='" + getDepositPaymentDate() + "'" +
            ", balanceOfTotalPaymentDate='" + getBalanceOfTotalPaymentDate() + "'" +
            ", amountDepositPayment='" + getAmountDepositPayment() + "'" +
            ", amountPayment='" + getAmountPayment() + "'" +
            ", dcMember='" + getDcMember() + "'" +
            ", comment='" + getComment() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", parentKingdeeId='" + getParentKingdeeId() + "'" +
            ", qty='" + getQty() + "'" +
            ", itemCode='" + getItemCode() + "'" +
            ", contractEndOfProdDate='" + getContractEndOfProdDate() + "'" +
            ", supplierId='" + getSupplierId() + "'" +
            "}";
    }
}
