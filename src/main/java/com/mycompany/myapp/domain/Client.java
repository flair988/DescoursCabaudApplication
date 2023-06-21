package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "board_id")
    private String boardId;

    @Column(name = "sub_items")
    private String subItems;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "customer_frence_name")
    private String customerFrenceName;

    @Column(name = "comment")
    private String comment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Client id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return this.itemId;
    }

    public Client itemId(String itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Client itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBoardId() {
        return this.boardId;
    }

    public Client boardId(String boardId) {
        this.setBoardId(boardId);
        return this;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getSubItems() {
        return this.subItems;
    }

    public Client subItems(String subItems) {
        this.setSubItems(subItems);
        return this;
    }

    public void setSubItems(String subItems) {
        this.subItems = subItems;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public Client kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Client customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public Client customerCode(String customerCode) {
        this.setCustomerCode(customerCode);
        return this;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerFrenceName() {
        return this.customerFrenceName;
    }

    public Client customerFrenceName(String customerFrenceName) {
        this.setCustomerFrenceName(customerFrenceName);
        return this;
    }

    public void setCustomerFrenceName(String customerFrenceName) {
        this.customerFrenceName = customerFrenceName;
    }

    public String getComment() {
        return this.comment;
    }

    public Client comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", itemId='" + getItemId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", boardId='" + getBoardId() + "'" +
            ", subItems='" + getSubItems() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", customerCode='" + getCustomerCode() + "'" +
            ", customerFrenceName='" + getCustomerFrenceName() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
