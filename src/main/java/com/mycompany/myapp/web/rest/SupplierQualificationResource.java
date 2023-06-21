package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SupplierQualification;
import com.mycompany.myapp.repository.SupplierQualificationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SupplierQualification}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SupplierQualificationResource {

    private final Logger log = LoggerFactory.getLogger(SupplierQualificationResource.class);

    private static final String ENTITY_NAME = "supplierQualification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupplierQualificationRepository supplierQualificationRepository;

    public SupplierQualificationResource(SupplierQualificationRepository supplierQualificationRepository) {
        this.supplierQualificationRepository = supplierQualificationRepository;
    }

    /**
     * {@code POST  /supplier-qualifications} : Create a new supplierQualification.
     *
     * @param supplierQualification the supplierQualification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supplierQualification, or with status {@code 400 (Bad Request)} if the supplierQualification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supplier-qualifications")
    public ResponseEntity<SupplierQualification> createSupplierQualification(@RequestBody SupplierQualification supplierQualification)
        throws URISyntaxException {
        log.debug("REST request to save SupplierQualification : {}", supplierQualification);
        if (supplierQualification.getId() != null) {
            throw new BadRequestAlertException("A new supplierQualification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplierQualification result = supplierQualificationRepository.save(supplierQualification);
        return ResponseEntity
            .created(new URI("/api/supplier-qualifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supplier-qualifications/:id} : Updates an existing supplierQualification.
     *
     * @param id the id of the supplierQualification to save.
     * @param supplierQualification the supplierQualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplierQualification,
     * or with status {@code 400 (Bad Request)} if the supplierQualification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supplierQualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supplier-qualifications/{id}")
    public ResponseEntity<SupplierQualification> updateSupplierQualification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SupplierQualification supplierQualification
    ) throws URISyntaxException {
        log.debug("REST request to update SupplierQualification : {}, {}", id, supplierQualification);
        if (supplierQualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supplierQualification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supplierQualificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SupplierQualification result = supplierQualificationRepository.save(supplierQualification);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supplierQualification.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /supplier-qualifications/:id} : Partial updates given fields of an existing supplierQualification, field will ignore if it is null
     *
     * @param id the id of the supplierQualification to save.
     * @param supplierQualification the supplierQualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplierQualification,
     * or with status {@code 400 (Bad Request)} if the supplierQualification is not valid,
     * or with status {@code 404 (Not Found)} if the supplierQualification is not found,
     * or with status {@code 500 (Internal Server Error)} if the supplierQualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/supplier-qualifications/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SupplierQualification> partialUpdateSupplierQualification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SupplierQualification supplierQualification
    ) throws URISyntaxException {
        log.debug("REST request to partial update SupplierQualification partially : {}, {}", id, supplierQualification);
        if (supplierQualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supplierQualification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supplierQualificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SupplierQualification> result = supplierQualificationRepository
            .findById(supplierQualification.getId())
            .map(existingSupplierQualification -> {
                if (supplierQualification.getItemName() != null) {
                    existingSupplierQualification.setItemName(supplierQualification.getItemName());
                }
                if (supplierQualification.getCateGory() != null) {
                    existingSupplierQualification.setCateGory(supplierQualification.getCateGory());
                }
                if (supplierQualification.getSupplier() != null) {
                    existingSupplierQualification.setSupplier(supplierQualification.getSupplier());
                }
                if (supplierQualification.getDate() != null) {
                    existingSupplierQualification.setDate(supplierQualification.getDate());
                }
                if (supplierQualification.getSupplierStatus() != null) {
                    existingSupplierQualification.setSupplierStatus(supplierQualification.getSupplierStatus());
                }
                if (supplierQualification.getEvaluationStatus() != null) {
                    existingSupplierQualification.setEvaluationStatus(supplierQualification.getEvaluationStatus());
                }
                if (supplierQualification.getBusinessLiabilityBopeScore() != null) {
                    existingSupplierQualification.setBusinessLiabilityBopeScore(supplierQualification.getBusinessLiabilityBopeScore());
                }
                if (supplierQualification.getComments() != null) {
                    existingSupplierQualification.setComments(supplierQualification.getComments());
                }

                return existingSupplierQualification;
            })
            .map(supplierQualificationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supplierQualification.getId().toString())
        );
    }

    /**
     * {@code GET  /supplier-qualifications} : get all the supplierQualifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supplierQualifications in body.
     */
    @GetMapping("/supplier-qualifications")
    public List<SupplierQualification> getAllSupplierQualifications() {
        log.debug("REST request to get all SupplierQualifications");
        return supplierQualificationRepository.findAll();
    }

    /**
     * {@code GET  /supplier-qualifications/:id} : get the "id" supplierQualification.
     *
     * @param id the id of the supplierQualification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supplierQualification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supplier-qualifications/{id}")
    public ResponseEntity<SupplierQualification> getSupplierQualification(@PathVariable Long id) {
        log.debug("REST request to get SupplierQualification : {}", id);
        Optional<SupplierQualification> supplierQualification = supplierQualificationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(supplierQualification);
    }

    /**
     * {@code DELETE  /supplier-qualifications/:id} : delete the "id" supplierQualification.
     *
     * @param id the id of the supplierQualification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supplier-qualifications/{id}")
    public ResponseEntity<Void> deleteSupplierQualification(@PathVariable Long id) {
        log.debug("REST request to delete SupplierQualification : {}", id);
        supplierQualificationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
