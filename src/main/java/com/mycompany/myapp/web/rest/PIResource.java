package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PI;
import com.mycompany.myapp.repository.PIRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PI}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PIResource {

    private final Logger log = LoggerFactory.getLogger(PIResource.class);

    private static final String ENTITY_NAME = "pI";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PIRepository pIRepository;

    public PIResource(PIRepository pIRepository) {
        this.pIRepository = pIRepository;
    }

    /**
     * {@code POST  /pis} : Create a new pI.
     *
     * @param pI the pI to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pI, or with status {@code 400 (Bad Request)} if the pI has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pis")
    public ResponseEntity<PI> createPI(@RequestBody PI pI) throws URISyntaxException {
        log.debug("REST request to save PI : {}", pI);
        if (pI.getId() != null) {
            throw new BadRequestAlertException("A new pI cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PI result = pIRepository.save(pI);
        return ResponseEntity
            .created(new URI("/api/pis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pis/:id} : Updates an existing pI.
     *
     * @param id the id of the pI to save.
     * @param pI the pI to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pI,
     * or with status {@code 400 (Bad Request)} if the pI is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pI couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pis/{id}")
    public ResponseEntity<PI> updatePI(@PathVariable(value = "id", required = false) final Long id, @RequestBody PI pI)
        throws URISyntaxException {
        log.debug("REST request to update PI : {}, {}", id, pI);
        if (pI.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pI.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PI result = pIRepository.save(pI);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pI.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pis/:id} : Partial updates given fields of an existing pI, field will ignore if it is null
     *
     * @param id the id of the pI to save.
     * @param pI the pI to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pI,
     * or with status {@code 400 (Bad Request)} if the pI is not valid,
     * or with status {@code 404 (Not Found)} if the pI is not found,
     * or with status {@code 500 (Internal Server Error)} if the pI couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pis/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PI> partialUpdatePI(@PathVariable(value = "id", required = false) final Long id, @RequestBody PI pI)
        throws URISyntaxException {
        log.debug("REST request to partial update PI partially : {}, {}", id, pI);
        if (pI.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pI.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PI> result = pIRepository
            .findById(pI.getId())
            .map(existingPI -> {
                if (pI.getItemName() != null) {
                    existingPI.setItemName(pI.getItemName());
                }
                if (pI.getPoNumber() != null) {
                    existingPI.setPoNumber(pI.getPoNumber());
                }
                if (pI.getCategory() != null) {
                    existingPI.setCategory(pI.getCategory());
                }
                if (pI.getClient() != null) {
                    existingPI.setClient(pI.getClient());
                }
                if (pI.getOrderDate() != null) {
                    existingPI.setOrderDate(pI.getOrderDate());
                }
                if (pI.getRequestedEndOfProdDate() != null) {
                    existingPI.setRequestedEndOfProdDate(pI.getRequestedEndOfProdDate());
                }
                if (pI.getCountryOfOrigin() != null) {
                    existingPI.setCountryOfOrigin(pI.getCountryOfOrigin());
                }
                if (pI.getCountryOfFinalDestination() != null) {
                    existingPI.setCountryOfFinalDestination(pI.getCountryOfFinalDestination());
                }
                if (pI.getConsignee() != null) {
                    existingPI.setConsignee(pI.getConsignee());
                }
                if (pI.getCarriageBy() != null) {
                    existingPI.setCarriageBy(pI.getCarriageBy());
                }
                if (pI.getTermsOfDelivery() != null) {
                    existingPI.setTermsOfDelivery(pI.getTermsOfDelivery());
                }
                if (pI.getTermsOfPayment() != null) {
                    existingPI.setTermsOfPayment(pI.getTermsOfPayment());
                }
                if (pI.getCurrency() != null) {
                    existingPI.setCurrency(pI.getCurrency());
                }
                if (pI.getRemarks() != null) {
                    existingPI.setRemarks(pI.getRemarks());
                }
                if (pI.getKingdeeId() != null) {
                    existingPI.setKingdeeId(pI.getKingdeeId());
                }
                if (pI.getPortOfDischarge() != null) {
                    existingPI.setPortOfDischarge(pI.getPortOfDischarge());
                }
                if (pI.getPortOfLoading() != null) {
                    existingPI.setPortOfLoading(pI.getPortOfLoading());
                }
                if (pI.getDocStatus() != null) {
                    existingPI.setDocStatus(pI.getDocStatus());
                }

                return existingPI;
            })
            .map(pIRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pI.getId().toString())
        );
    }

    /**
     * {@code GET  /pis} : get all the pIS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pIS in body.
     */
    @GetMapping("/pis")
    public List<PI> getAllPIS() {
        log.debug("REST request to get all PIS");
        return pIRepository.findAll();
    }

    /**
     * {@code GET  /pis/:id} : get the "id" pI.
     *
     * @param id the id of the pI to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pI, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pis/{id}")
    public ResponseEntity<PI> getPI(@PathVariable Long id) {
        log.debug("REST request to get PI : {}", id);
        Optional<PI> pI = pIRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pI);
    }

    /**
     * {@code DELETE  /pis/:id} : delete the "id" pI.
     *
     * @param id the id of the pI to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pis/{id}")
    public ResponseEntity<Void> deletePI(@PathVariable Long id) {
        log.debug("REST request to delete PI : {}", id);
        pIRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
