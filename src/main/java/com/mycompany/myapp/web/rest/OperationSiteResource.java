package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OperationSite;
import com.mycompany.myapp.repository.OperationSiteRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OperationSite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OperationSiteResource {

    private final Logger log = LoggerFactory.getLogger(OperationSiteResource.class);

    private static final String ENTITY_NAME = "operationSite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperationSiteRepository operationSiteRepository;

    public OperationSiteResource(OperationSiteRepository operationSiteRepository) {
        this.operationSiteRepository = operationSiteRepository;
    }

    /**
     * {@code POST  /operation-sites} : Create a new operationSite.
     *
     * @param operationSite the operationSite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operationSite, or with status {@code 400 (Bad Request)} if the operationSite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operation-sites")
    public ResponseEntity<OperationSite> createOperationSite(@RequestBody OperationSite operationSite) throws URISyntaxException {
        log.debug("REST request to save OperationSite : {}", operationSite);
        if (operationSite.getId() != null) {
            throw new BadRequestAlertException("A new operationSite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperationSite result = operationSiteRepository.save(operationSite);
        return ResponseEntity
            .created(new URI("/api/operation-sites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operation-sites/:id} : Updates an existing operationSite.
     *
     * @param id the id of the operationSite to save.
     * @param operationSite the operationSite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationSite,
     * or with status {@code 400 (Bad Request)} if the operationSite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operationSite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operation-sites/{id}")
    public ResponseEntity<OperationSite> updateOperationSite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OperationSite operationSite
    ) throws URISyntaxException {
        log.debug("REST request to update OperationSite : {}, {}", id, operationSite);
        if (operationSite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operationSite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operationSiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OperationSite result = operationSiteRepository.save(operationSite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operationSite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /operation-sites/:id} : Partial updates given fields of an existing operationSite, field will ignore if it is null
     *
     * @param id the id of the operationSite to save.
     * @param operationSite the operationSite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operationSite,
     * or with status {@code 400 (Bad Request)} if the operationSite is not valid,
     * or with status {@code 404 (Not Found)} if the operationSite is not found,
     * or with status {@code 500 (Internal Server Error)} if the operationSite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/operation-sites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OperationSite> partialUpdateOperationSite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OperationSite operationSite
    ) throws URISyntaxException {
        log.debug("REST request to partial update OperationSite partially : {}, {}", id, operationSite);
        if (operationSite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operationSite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operationSiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OperationSite> result = operationSiteRepository
            .findById(operationSite.getId())
            .map(existingOperationSite -> {
                if (operationSite.getItemName() != null) {
                    existingOperationSite.setItemName(operationSite.getItemName());
                }
                if (operationSite.getLinkSupplierFactory() != null) {
                    existingOperationSite.setLinkSupplierFactory(operationSite.getLinkSupplierFactory());
                }
                if (operationSite.getTypeOfSite() != null) {
                    existingOperationSite.setTypeOfSite(operationSite.getTypeOfSite());
                }
                if (operationSite.getContact() != null) {
                    existingOperationSite.setContact(operationSite.getContact());
                }
                if (operationSite.getSiteAddress() != null) {
                    existingOperationSite.setSiteAddress(operationSite.getSiteAddress());
                }
                if (operationSite.getCateGory() != null) {
                    existingOperationSite.setCateGory(operationSite.getCateGory());
                }
                if (operationSite.getCountry() != null) {
                    existingOperationSite.setCountry(operationSite.getCountry());
                }
                if (operationSite.getKingdeeId() != null) {
                    existingOperationSite.setKingdeeId(operationSite.getKingdeeId());
                }
                if (operationSite.getBusinessLicense() != null) {
                    existingOperationSite.setBusinessLicense(operationSite.getBusinessLicense());
                }
                if (operationSite.getSasDate() != null) {
                    existingOperationSite.setSasDate(operationSite.getSasDate());
                }
                if (operationSite.getIso900ValidUtil() != null) {
                    existingOperationSite.setIso900ValidUtil(operationSite.getIso900ValidUtil());
                }
                if (operationSite.getIso14001ValidUtil() != null) {
                    existingOperationSite.setIso14001ValidUtil(operationSite.getIso14001ValidUtil());
                }
                if (operationSite.getAttributeLor() != null) {
                    existingOperationSite.setAttributeLor(operationSite.getAttributeLor());
                }
                if (operationSite.getSiteQualification() != null) {
                    existingOperationSite.setSiteQualification(operationSite.getSiteQualification());
                }
                if (operationSite.getQualificationScore() != null) {
                    existingOperationSite.setQualificationScore(operationSite.getQualificationScore());
                }
                if (operationSite.getPqvScore() != null) {
                    existingOperationSite.setPqvScore(operationSite.getPqvScore());
                }
                if (operationSite.getPqvDate() != null) {
                    existingOperationSite.setPqvDate(operationSite.getPqvDate());
                }
                if (operationSite.getPqvDecision() != null) {
                    existingOperationSite.setPqvDecision(operationSite.getPqvDecision());
                }
                if (operationSite.getTechnicalAuditDate() != null) {
                    existingOperationSite.setTechnicalAuditDate(operationSite.getTechnicalAuditDate());
                }
                if (operationSite.getTechnicalAuditScore() != null) {
                    existingOperationSite.setTechnicalAuditScore(operationSite.getTechnicalAuditScore());
                }
                if (operationSite.getThirdRdPartyDate() != null) {
                    existingOperationSite.setThirdRdPartyDate(operationSite.getThirdRdPartyDate());
                }
                if (operationSite.getThirdRdPartyScore() != null) {
                    existingOperationSite.setThirdRdPartyScore(operationSite.getThirdRdPartyScore());
                }
                if (operationSite.getBopeDate() != null) {
                    existingOperationSite.setBopeDate(operationSite.getBopeDate());
                }
                if (operationSite.getBopeScore() != null) {
                    existingOperationSite.setBopeScore(operationSite.getBopeScore());
                }
                if (operationSite.getCapRequired() != null) {
                    existingOperationSite.setCapRequired(operationSite.getCapRequired());
                }

                return existingOperationSite;
            })
            .map(operationSiteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operationSite.getId().toString())
        );
    }

    /**
     * {@code GET  /operation-sites} : get all the operationSites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operationSites in body.
     */
    @GetMapping("/operation-sites")
    public List<OperationSite> getAllOperationSites() {
        log.debug("REST request to get all OperationSites");
        return operationSiteRepository.findAll();
    }

    /**
     * {@code GET  /operation-sites/:id} : get the "id" operationSite.
     *
     * @param id the id of the operationSite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operationSite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operation-sites/{id}")
    public ResponseEntity<OperationSite> getOperationSite(@PathVariable Long id) {
        log.debug("REST request to get OperationSite : {}", id);
        Optional<OperationSite> operationSite = operationSiteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(operationSite);
    }

    /**
     * {@code DELETE  /operation-sites/:id} : delete the "id" operationSite.
     *
     * @param id the id of the operationSite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operation-sites/{id}")
    public ResponseEntity<Void> deleteOperationSite(@PathVariable Long id) {
        log.debug("REST request to delete OperationSite : {}", id);
        operationSiteRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
