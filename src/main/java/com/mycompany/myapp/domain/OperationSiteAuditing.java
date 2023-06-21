package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OperationSiteAuditing.
 */
@Entity
@Table(name = "operation_site_auditing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperationSiteAuditing implements Serializable {

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

    @Column(name = "operation_site")
    private String operationSite;

    @Column(name = "link_supplier_factory")
    private String linkSupplierFactory;

    @Column(name = "type_of_site")
    private String typeOfSite;

    @Column(name = "auditing_tool")
    private String auditingTool;

    @Column(name = "auditing_date")
    private LocalDate auditingDate;

    @Column(name = "csr_result")
    private String csrResult;

    @Column(name = "quality_production_result")
    private String qualityProductionResult;

    @Column(name = "business_liability_result")
    private String businessLiabilityResult;

    @Column(name = "comments")
    private String comments;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "closed_date")
    private LocalDate closedDate;

    @Column(name = "closed")
    private String closed;

    @Column(name = "status")
    private String status;

    @Column(name = "cap_comments")
    private String capComments;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OperationSiteAuditing id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public OperationSiteAuditing itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCateGory() {
        return this.cateGory;
    }

    public OperationSiteAuditing cateGory(String cateGory) {
        this.setCateGory(cateGory);
        return this;
    }

    public void setCateGory(String cateGory) {
        this.cateGory = cateGory;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public OperationSiteAuditing supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOperationSite() {
        return this.operationSite;
    }

    public OperationSiteAuditing operationSite(String operationSite) {
        this.setOperationSite(operationSite);
        return this;
    }

    public void setOperationSite(String operationSite) {
        this.operationSite = operationSite;
    }

    public String getLinkSupplierFactory() {
        return this.linkSupplierFactory;
    }

    public OperationSiteAuditing linkSupplierFactory(String linkSupplierFactory) {
        this.setLinkSupplierFactory(linkSupplierFactory);
        return this;
    }

    public void setLinkSupplierFactory(String linkSupplierFactory) {
        this.linkSupplierFactory = linkSupplierFactory;
    }

    public String getTypeOfSite() {
        return this.typeOfSite;
    }

    public OperationSiteAuditing typeOfSite(String typeOfSite) {
        this.setTypeOfSite(typeOfSite);
        return this;
    }

    public void setTypeOfSite(String typeOfSite) {
        this.typeOfSite = typeOfSite;
    }

    public String getAuditingTool() {
        return this.auditingTool;
    }

    public OperationSiteAuditing auditingTool(String auditingTool) {
        this.setAuditingTool(auditingTool);
        return this;
    }

    public void setAuditingTool(String auditingTool) {
        this.auditingTool = auditingTool;
    }

    public LocalDate getAuditingDate() {
        return this.auditingDate;
    }

    public OperationSiteAuditing auditingDate(LocalDate auditingDate) {
        this.setAuditingDate(auditingDate);
        return this;
    }

    public void setAuditingDate(LocalDate auditingDate) {
        this.auditingDate = auditingDate;
    }

    public String getCsrResult() {
        return this.csrResult;
    }

    public OperationSiteAuditing csrResult(String csrResult) {
        this.setCsrResult(csrResult);
        return this;
    }

    public void setCsrResult(String csrResult) {
        this.csrResult = csrResult;
    }

    public String getQualityProductionResult() {
        return this.qualityProductionResult;
    }

    public OperationSiteAuditing qualityProductionResult(String qualityProductionResult) {
        this.setQualityProductionResult(qualityProductionResult);
        return this;
    }

    public void setQualityProductionResult(String qualityProductionResult) {
        this.qualityProductionResult = qualityProductionResult;
    }

    public String getBusinessLiabilityResult() {
        return this.businessLiabilityResult;
    }

    public OperationSiteAuditing businessLiabilityResult(String businessLiabilityResult) {
        this.setBusinessLiabilityResult(businessLiabilityResult);
        return this;
    }

    public void setBusinessLiabilityResult(String businessLiabilityResult) {
        this.businessLiabilityResult = businessLiabilityResult;
    }

    public String getComments() {
        return this.comments;
    }

    public OperationSiteAuditing comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public OperationSiteAuditing issueDate(LocalDate issueDate) {
        this.setIssueDate(issueDate);
        return this;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public OperationSiteAuditing dueDate(LocalDate dueDate) {
        this.setDueDate(dueDate);
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getClosedDate() {
        return this.closedDate;
    }

    public OperationSiteAuditing closedDate(LocalDate closedDate) {
        this.setClosedDate(closedDate);
        return this;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public String getClosed() {
        return this.closed;
    }

    public OperationSiteAuditing closed(String closed) {
        this.setClosed(closed);
        return this;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getStatus() {
        return this.status;
    }

    public OperationSiteAuditing status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapComments() {
        return this.capComments;
    }

    public OperationSiteAuditing capComments(String capComments) {
        this.setCapComments(capComments);
        return this;
    }

    public void setCapComments(String capComments) {
        this.capComments = capComments;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationSiteAuditing)) {
            return false;
        }
        return id != null && id.equals(((OperationSiteAuditing) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationSiteAuditing{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", cateGory='" + getCateGory() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", operationSite='" + getOperationSite() + "'" +
            ", linkSupplierFactory='" + getLinkSupplierFactory() + "'" +
            ", typeOfSite='" + getTypeOfSite() + "'" +
            ", auditingTool='" + getAuditingTool() + "'" +
            ", auditingDate='" + getAuditingDate() + "'" +
            ", csrResult='" + getCsrResult() + "'" +
            ", qualityProductionResult='" + getQualityProductionResult() + "'" +
            ", businessLiabilityResult='" + getBusinessLiabilityResult() + "'" +
            ", comments='" + getComments() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            ", closed='" + getClosed() + "'" +
            ", status='" + getStatus() + "'" +
            ", capComments='" + getCapComments() + "'" +
            "}";
    }
}
