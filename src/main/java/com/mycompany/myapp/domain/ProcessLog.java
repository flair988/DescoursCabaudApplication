package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProcessLog.
 */
@Entity
@Table(name = "process_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProcessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "process_start_time")
    private LocalDate processStartTime;

    @Column(name = "process_end_time")
    private LocalDate processEndTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProcessLog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public ProcessLog type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest() {
        return this.request;
    }

    public ProcessLog request(String request) {
        this.setRequest(request);
        return this;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return this.response;
    }

    public ProcessLog response(String response) {
        this.setResponse(response);
        return this;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return this.status;
    }

    public ProcessLog status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return this.reason;
    }

    public ProcessLog reason(String reason) {
        this.setReason(reason);
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getProcessStartTime() {
        return this.processStartTime;
    }

    public ProcessLog processStartTime(LocalDate processStartTime) {
        this.setProcessStartTime(processStartTime);
        return this;
    }

    public void setProcessStartTime(LocalDate processStartTime) {
        this.processStartTime = processStartTime;
    }

    public LocalDate getProcessEndTime() {
        return this.processEndTime;
    }

    public ProcessLog processEndTime(LocalDate processEndTime) {
        this.setProcessEndTime(processEndTime);
        return this;
    }

    public void setProcessEndTime(LocalDate processEndTime) {
        this.processEndTime = processEndTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessLog)) {
            return false;
        }
        return id != null && id.equals(((ProcessLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcessLog{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", request='" + getRequest() + "'" +
            ", response='" + getResponse() + "'" +
            ", status='" + getStatus() + "'" +
            ", reason='" + getReason() + "'" +
            ", processStartTime='" + getProcessStartTime() + "'" +
            ", processEndTime='" + getProcessEndTime() + "'" +
            "}";
    }
}
