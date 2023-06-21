package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OperationSiteAuditing;
import com.mycompany.myapp.repository.OperationSiteAuditingRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.OperationSiteAuditing}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OperationSiteAuditingResource {

    private final Logger log = LoggerFactory.getLogger(OperationSiteAuditingResource.class);

    private static final String ENTITY_NAME = "operationSiteAuditing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperationSiteAuditingRepository operationSiteAuditingRepository;

    public OperationSiteAuditingResource(OperationSiteAuditingRepository operationSiteAuditingRepository) {
        this.operationSiteAuditingRepository = operationSiteAuditingRepository;
    }

    /**
     * {@code POST  /operation-site-auditings} : Create a new operationSiteAuditing.
     *
     * @param operationSiteAuditing the operationSiteAuditing to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operationSiteAuditing, or with status {@code 400 (Bad Request)} if the operationSiteAuditing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operation-site-auditings")
    public ResponseEntity<OperationSiteAuditing> createOperationSiteAuditing(@RequestBody OperationSiteAuditing operationSiteAuditing)
        throws URISyntaxException {
        log.debug("REST request to save OperationSiteAuditing : {}", operationSiteAuditing);
        if (operationSiteAuditing.getId() != null) {
            throw new BadRequestAlertException("A new operationSiteAuditing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperationSiteAuditing result = operationSiteAuditingRepository.save(operationSiteAuditing);
        return ResponseEntity
            .created(new URI("/api/operation-site-auditings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operation-site-auditings/:id} : Updates an existing operationSiteAuditing.
     *
     * @param id the id of the operationSiteAuditing to save.
     * @param operationSiteAuditing the operationSiteAuditing to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationSiteAuditing,
     * or with status {@code 400 (Bad Request)} if the operationSiteAuditing is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operationSiteAuditing couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operation-site-auditings/{id}")
    public ResponseEntity<OperationSiteAuditing> updateOperationSiteAuditing(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OperationSiteAuditing operationSiteAuditing
    ) throws URISyntaxException {
        log.debug("REST request to update OperationSiteAuditing : {}, {}", id, operationSiteAuditing);
        if (operationSiteAuditing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operationSiteAuditing.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operationSiteAuditingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OperationSiteAuditing result = operationSiteAuditingRepository.save(operationSiteAuditing);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operationSiteAuditing.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /operation-site-auditings/:id} : Partial updates given fields of an existing operationSiteAuditing, field will ignore if it is null
     *
     * @param id the id of the operationSiteAuditing to save.
     * @param operationSiteAuditing the operationSiteAuditing to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationSiteAuditing,
     * or with status {@code 400 (Bad Request)} if the operationSiteAuditing is not valid,
     * or with status {@code 404 (Not Found)} if the operationSiteAuditing is not found,
     * or with status {@code 500 (Internal Server Error)} if the operationSiteAuditing couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/operation-site-auditings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OperationSiteAuditing> partialUpdateOperationSiteAuditing(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OperationSiteAuditing operationSiteAuditing
    ) throws URISyntaxException {
        log.debug("REST request to partial update OperationSiteAuditing partially : {}, {}", id, operationSiteAuditing);
        if (operationSiteAuditing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operationSiteAuditing.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operationSiteAuditingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OperationSiteAuditing> result = operationSiteAuditingRepository
            .findById(operationSiteAuditing.getId())
            .map(existingOperationSiteAuditing -> {
                if (operationSiteAuditing.getItemName() != null) {
                    existingOperationSiteAuditing.setItemName(operationSiteAuditing.getItemName());
                }
                if (operationSiteAuditing.getCateGory() != null) {
                    existingOperationSiteAuditing.setCateGory(operationSiteAuditing.getCateGory());
                }
                if (operationSiteAuditing.getSupplier() != null) {
                    existingOperationSiteAuditing.setSupplier(operationSiteAuditing.getSupplier());
                }
                if (operationSiteAuditing.getOperationSite() != null) {
                    existingOperationSiteAuditing.setOperationSite(operationSiteAuditing.getOperationSite());
                }
                if (operationSiteAuditing.getLinkSupplierFactory() != null) {
                    existingOperationSiteAuditing.setLinkSupplierFactory(operationSiteAuditing.getLinkSupplierFactory());
                }
                if (operationSiteAuditing.getTypeOfSite() != null) {
                    existingOperationSiteAuditing.setTypeOfSite(operationSiteAuditing.getTypeOfSite());
                }
                if (operationSiteAuditing.getAuditingTool() != null) {
                    existingOperationSiteAuditing.setAuditingTool(operationSiteAuditing.getAuditingTool());
                }
                if (operationSiteAuditing.getAuditingDate() != null) {
                    existingOperationSiteAuditing.setAuditingDate(operationSiteAuditing.getAuditingDate());
                }
                if (operationSiteAuditing.getCsrResult() != null) {
                    existingOperationSiteAuditing.setCsrResult(operationSiteAuditing.getCsrResult());
                }
                if (operationSiteAuditing.getQualityProductionResult() != null) {
                    existingOperationSiteAuditing.setQualityProductionResult(operationSiteAuditing.getQualityProductionResult());
                }
                if (operationSiteAuditing.getBusinessLiabilityResult() != null) {
                    existingOperationSiteAuditing.setBusinessLiabilityResult(operationSiteAuditing.getBusinessLiabilityResult());
                }
                if (operationSiteAuditing.getComments() != null) {
                    existingOperationSiteAuditing.setComments(operationSiteAuditing.getComments());
                }
                if (operationSiteAuditing.getIssueDate() != null) {
                    existingOperationSiteAuditing.setIssueDate(operationSiteAuditing.getIssueDate());
                }
                if (operationSiteAuditing.getDueDate() != null) {
                    existingOperationSiteAuditing.setDueDate(operationSiteAuditing.getDueDate());
                }
                if (operationSiteAuditing.getClosedDate() != null) {
                    existingOperationSiteAuditing.setClosedDate(operationSiteAuditing.getClosedDate());
                }
                if (operationSiteAuditing.getClosed() != null) {
                    existingOperationSiteAuditing.setClosed(operationSiteAuditing.getClosed());
                }
                if (operationSiteAuditing.getStatus() != null) {
                    existingOperationSiteAuditing.setStatus(operationSiteAuditing.getStatus());
                }
                if (operationSiteAuditing.getCapComments() != null) {
                    existingOperationSiteAuditing.setCapComments(operationSiteAuditing.getCapComments());
                }

                return existingOperationSiteAuditing;
            })
            .map(operationSiteAuditingRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operationSiteAuditing.getId().toString())
        );
    }

    /**
     * {@code GET  /operation-site-auditings} : get all the operationSiteAuditings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operationSiteAuditings in body.
     */
    @GetMapping("/operation-site-auditings")
    public List<OperationSiteAuditing> getAllOperationSiteAuditings() {
        log.debug("REST request to get all OperationSiteAuditings");
        return operationSiteAuditingRepository.findAll();
    }

    /**
     * {@code GET  /operation-site-auditings/:id} : get the "id" operationSiteAuditing.
     *
     * @param id the id of the operationSiteAuditing to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operationSiteAuditing, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operation-site-auditings/{id}")
    public ResponseEntity<OperationSiteAuditing> getOperationSiteAuditing(@PathVariable Long id) {
        log.debug("REST request to get OperationSiteAuditing : {}", id);
        Optional<OperationSiteAuditing> operationSiteAuditing = operationSiteAuditingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(operationSiteAuditing);
    }

    /**
     * {@code DELETE  /operation-site-auditings/:id} : delete the "id" operationSiteAuditing.
     *
     * @param id the id of the operationSiteAuditing to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operation-site-auditings/{id}")
    public ResponseEntity<Void> deleteOperationSiteAuditing(@PathVariable Long id) {
        log.debug("REST request to delete OperationSiteAuditing : {}", id);
        operationSiteAuditingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
