package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProductTaxmonomy.
 */
@Entity
@Table(name = "product_taxmonomy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductTaxmonomy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "parent_group_name")
    private String parentGroupName;

    @Column(name = "sub_group_name")
    private String subGroupName;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProductTaxmonomy id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return this.itemId;
    }

    public ProductTaxmonomy itemId(String itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public ProductTaxmonomy kingdeeId(String kingdeeId) {
        this.setKingdeeId(kingdeeId);
        return this;
    }

    public void setKingdeeId(String kingdeeId) {
        this.kingdeeId = kingdeeId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ProductTaxmonomy itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public ProductTaxmonomy groupName(String groupName) {
        this.setGroupName(groupName);
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParentGroupName() {
        return this.parentGroupName;
    }

    public ProductTaxmonomy parentGroupName(String parentGroupName) {
        this.setParentGroupName(parentGroupName);
        return this;
    }

    public void setParentGroupName(String parentGroupName) {
        this.parentGroupName = parentGroupName;
    }

    public String getSubGroupName() {
        return this.subGroupName;
    }

    public ProductTaxmonomy subGroupName(String subGroupName) {
        this.setSubGroupName(subGroupName);
        return this;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductTaxmonomy description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductTaxmonomy)) {
            return false;
        }
        return id != null && id.equals(((ProductTaxmonomy) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductTaxmonomy{" +
            "id=" + getId() +
            ", itemId='" + getItemId() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", parentGroupName='" + getParentGroupName() + "'" +
            ", subGroupName='" + getSubGroupName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
