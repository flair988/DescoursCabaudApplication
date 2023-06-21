package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Uom.
 */
@Entity
@Table(name = "uom")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Uom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "uom")
    private String uom;

    @Column(name = "uom_group")
    private String uomGroup;

    @Column(name = "description")
    private String description;

    @Column(name = "sub_items")
    private String subItems;

    @Column(name = "parent_item")
    private String parentItem;

    @Column(name = "board_id")
    private String boardId;

    @Column(name = "kingdee_id")
    private String kingdeeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Uom id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return this.itemId;
    }

    public Uom itemId(String itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUom() {
        return this.uom;
    }

    public Uom uom(String uom) {
        this.setUom(uom);
        return this;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUomGroup() {
        return this.uomGroup;
    }

    public Uom uomGroup(String uomGroup) {
        this.setUomGroup(uomGroup);
        return this;
    }

    public void setUomGroup(String uomGroup) {
        this.uomGroup = uomGroup;
    }

    public String getDescription() {
        return this.description;
    }

    public Uom description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubItems() {
        return this.subItems;
    }

    public Uom subItems(String subItems) {
        this.setSubItems(subItems);
        return this;
    }

    public void setSubItems(String subItems) {
        this.subItems = subItems;
    }

    public String getParentItem() {
        return this.parentItem;
    }

    public Uom parentItem(String parentItem) {
        this.setParentItem(parentItem);
        return this;
    }

    public void setParentItem(String parentItem) {
        this.parentItem = parentItem;
    }

    public String getBoardId() {
        return this.boardId;
    }

    public Uom boardId(String boardId) {
        this.setBoardId(boardId);
        return this;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getKingdeeId() {
        return this.kingdeeId;
    }

    public Uom kingdeeId(String kingdeeId) {
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
        if (!(o instanceof Uom)) {
            return false;
        }
        return id != null && id.equals(((Uom) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Uom{" +
            "id=" + getId() +
            ", itemId='" + getItemId() + "'" +
            ", uom='" + getUom() + "'" +
            ", uomGroup='" + getUomGroup() + "'" +
            ", description='" + getDescription() + "'" +
            ", subItems='" + getSubItems() + "'" +
            ", parentItem='" + getParentItem() + "'" +
            ", boardId='" + getBoardId() + "'" +
            ", kingdeeId='" + getKingdeeId() + "'" +
            "}";
    }
}
