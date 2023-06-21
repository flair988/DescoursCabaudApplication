package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OPSiteQualification;
import com.mycompany.myapp.repository.OPSiteQualificationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OPSiteQualification}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OPSiteQualificationResource {

    private final Logger log = LoggerFactory.getLogger(OPSiteQualificationResource.class);

    private static final String ENTITY_NAME = "oPSiteQualification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OPSiteQualificationRepository oPSiteQualificationRepository;

    public OPSiteQualificationResource(OPSiteQualificationRepository oPSiteQualificationRepository) {
        this.oPSiteQualificationRepository = oPSiteQualificationRepository;
    }

    /**
     * {@code POST  /op-site-qualifications} : Create a new oPSiteQualification.
     *
     * @param oPSiteQualification the oPSiteQualification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oPSiteQualification, or with status {@code 400 (Bad Request)} if the oPSiteQualification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/op-site-qualifications")
    public ResponseEntity<OPSiteQualification> createOPSiteQualification(@RequestBody OPSiteQualification oPSiteQualification)
        throws URISyntaxException {
        log.debug("REST request to save OPSiteQualification : {}", oPSiteQualification);
        if (oPSiteQualification.getId() != null) {
            throw new BadRequestAlertException("A new oPSiteQualification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OPSiteQualification result = oPSiteQualificationRepository.save(oPSiteQualification);
        return ResponseEntity
            .created(new URI("/api/op-site-qualifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /op-site-qualifications/:id} : Updates an existing oPSiteQualification.
     *
     * @param id the id of the oPSiteQualification to save.
     * @param oPSiteQualification the oPSiteQualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oPSiteQualification,
     * or with status {@code 400 (Bad Request)} if the oPSiteQualification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oPSiteQualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/op-site-qualifications/{id}")
    public ResponseEntity<OPSiteQualification> updateOPSiteQualification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OPSiteQualification oPSiteQualification
    ) throws URISyntaxException {
        log.debug("REST request to update OPSiteQualification : {}, {}", id, oPSiteQualification);
        if (oPSiteQualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, oPSiteQualification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!oPSiteQualificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OPSiteQualification result = oPSiteQualificationRepository.save(oPSiteQualification);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, oPSiteQualification.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /op-site-qualifications/:id} : Partial updates given fields of an existing oPSiteQualification, field will ignore if it is null
     *
     * @param id the id of the oPSiteQualification to save.
     * @param oPSiteQualification the oPSiteQualification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oPSiteQualification,
     * or with status {@code 400 (Bad Request)} if the oPSiteQualification is not valid,
     * or with status {@code 404 (Not Found)} if the oPSiteQualification is not found,
     * or with status {@code 500 (Internal Server Error)} if the oPSiteQualification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/op-site-qualifications/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OPSiteQualification> partialUpdateOPSiteQualification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OPSiteQualification oPSiteQualification
    ) throws URISyntaxException {
        log.debug("REST request to partial update OPSiteQualification partially : {}, {}", id, oPSiteQualification);
        if (oPSiteQualification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, oPSiteQualification.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!oPSiteQualificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OPSiteQualification> result = oPSiteQualificationRepository
            .findById(oPSiteQualification.getId())
            .map(existingOPSiteQualification -> {
                if (oPSiteQualification.getItemName() != null) {
                    existingOPSiteQualification.setItemName(oPSiteQualification.getItemName());
                }
                if (oPSiteQualification.getCateGory() != null) {
                    existingOPSiteQualification.setCateGory(oPSiteQualification.getCateGory());
                }
                if (oPSiteQualification.getSupplier() != null) {
                    existingOPSiteQualification.setSupplier(oPSiteQualification.getSupplier());
                }
                if (oPSiteQualification.getDate() != null) {
                    existingOPSiteQualification.setDate(oPSiteQualification.getDate());
                }
                if (oPSiteQualification.getOperationSite() != null) {
                    existingOPSiteQualification.setOperationSite(oPSiteQualification.getOperationSite());
                }
                if (oPSiteQualification.getAttributedLoRForThisSite() != null) {
                    existingOPSiteQualification.setAttributedLoRForThisSite(oPSiteQualification.getAttributedLoRForThisSite());
                }
                if (oPSiteQualification.getSiteQualificationStatus() != null) {
                    existingOPSiteQualification.setSiteQualificationStatus(oPSiteQualification.getSiteQualificationStatus());
                }
                if (oPSiteQualification.getCsrResult() != null) {
                    existingOPSiteQualification.setCsrResult(oPSiteQualification.getCsrResult());
                }
                if (oPSiteQualification.getQualityProductionResult() != null) {
                    existingOPSiteQualification.setQualityProductionResult(oPSiteQualification.getQualityProductionResult());
                }
                if (oPSiteQualification.getBusinessLiabilityResult() != null) {
                    existingOPSiteQualification.setBusinessLiabilityResult(oPSiteQualification.getBusinessLiabilityResult());
                }
                if (oPSiteQualification.getComments() != null) {
                    existingOPSiteQualification.setComments(oPSiteQualification.getComments());
                }

                return existingOPSiteQualification;
            })
            .map(oPSiteQualificationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, oPSiteQualification.getId().toString())
        );
    }

    /**
     * {@code GET  /op-site-qualifications} : get all the oPSiteQualifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oPSiteQualifications in body.
     */
    @GetMapping("/op-site-qualifications")
    public List<OPSiteQualification> getAllOPSiteQualifications() {
        log.debug("REST request to get all OPSiteQualifications");
        return oPSiteQualificationRepository.findAll();
    }

    /**
     * {@code GET  /op-site-qualifications/:id} : get the "id" oPSiteQualification.
     *
     * @param id the id of the oPSiteQualification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oPSiteQualification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/op-site-qualifications/{id}")
    public ResponseEntity<OPSiteQualification> getOPSiteQualification(@PathVariable Long id) {
        log.debug("REST request to get OPSiteQualification : {}", id);
        Optional<OPSiteQualification> oPSiteQualification = oPSiteQualificationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oPSiteQualification);
    }

    /**
     * {@code DELETE  /op-site-qualifications/:id} : delete the "id" oPSiteQualification.
     *
     * @param id the id of the oPSiteQualification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/op-site-qualifications/{id}")
    public ResponseEntity<Void> deleteOPSiteQualification(@PathVariable Long id) {
        log.debug("REST request to delete OPSiteQualification : {}", id);
        oPSiteQualificationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
