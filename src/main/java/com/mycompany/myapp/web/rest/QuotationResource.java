package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Quotation;
import com.mycompany.myapp.repository.QuotationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Quotation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class QuotationResource {

    private final Logger log = LoggerFactory.getLogger(QuotationResource.class);

    private static final String ENTITY_NAME = "quotation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuotationRepository quotationRepository;

    public QuotationResource(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    /**
     * {@code POST  /quotations} : Create a new quotation.
     *
     * @param quotation the quotation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quotation, or with status {@code 400 (Bad Request)} if the quotation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quotations")
    public ResponseEntity<Quotation> createQuotation(@RequestBody Quotation quotation) throws URISyntaxException {
        log.debug("REST request to save Quotation : {}", quotation);
        if (quotation.getId() != null) {
            throw new BadRequestAlertException("A new quotation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Quotation result = quotationRepository.save(quotation);
        return ResponseEntity
            .created(new URI("/api/quotations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quotations/:id} : Updates an existing quotation.
     *
     * @param id the id of the quotation to save.
     * @param quotation the quotation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quotation,
     * or with status {@code 400 (Bad Request)} if the quotation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quotation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quotations/{id}")
    public ResponseEntity<Quotation> updateQuotation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Quotation quotation
    ) throws URISyntaxException {
        log.debug("REST request to update Quotation : {}, {}", id, quotation);
        if (quotation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, quotation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quotationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Quotation result = quotationRepository.save(quotation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quotation.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /quotations/:id} : Partial updates given fields of an existing quotation, field will ignore if it is null
     *
     * @param id the id of the quotation to save.
     * @param quotation the quotation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quotation,
     * or with status {@code 400 (Bad Request)} if the quotation is not valid,
     * or with status {@code 404 (Not Found)} if the quotation is not found,
     * or with status {@code 500 (Internal Server Error)} if the quotation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/quotations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Quotation> partialUpdateQuotation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Quotation quotation
    ) throws URISyntaxException {
        log.debug("REST request to partial update Quotation partially : {}, {}", id, quotation);
        if (quotation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, quotation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!quotationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Quotation> result = quotationRepository
            .findById(quotation.getId())
            .map(existingQuotation -> {
                if (quotation.getItemName() != null) {
                    existingQuotation.setItemName(quotation.getItemName());
                }
                if (quotation.getQuotationDate() != null) {
                    existingQuotation.setQuotationDate(quotation.getQuotationDate());
                }
                if (quotation.getInquiryDocNumber() != null) {
                    existingQuotation.setInquiryDocNumber(quotation.getInquiryDocNumber());
                }
                if (quotation.getDocNo() != null) {
                    existingQuotation.setDocNo(quotation.getDocNo());
                }
                if (quotation.getDocStatus() != null) {
                    existingQuotation.setDocStatus(quotation.getDocStatus());
                }
                if (quotation.getSupplier() != null) {
                    existingQuotation.setSupplier(quotation.getSupplier());
                }
                if (quotation.getKingdeeId() != null) {
                    existingQuotation.setKingdeeId(quotation.getKingdeeId());
                }

                return existingQuotation;
            })
            .map(quotationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quotation.getId().toString())
        );
    }

    /**
     * {@code GET  /quotations} : get all the quotations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quotations in body.
     */
    @GetMapping("/quotations")
    public List<Quotation> getAllQuotations() {
        log.debug("REST request to get all Quotations");
        return quotationRepository.findAll();
    }

    /**
     * {@code GET  /quotations/:id} : get the "id" quotation.
     *
     * @param id the id of the quotation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quotation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quotations/{id}")
    public ResponseEntity<Quotation> getQuotation(@PathVariable Long id) {
        log.debug("REST request to get Quotation : {}", id);
        Optional<Quotation> quotation = quotationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quotation);
    }

    /**
     * {@code DELETE  /quotations/:id} : delete the "id" quotation.
     *
     * @param id the id of the quotation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quotations/{id}")
    public ResponseEntity<Void> deleteQuotation(@PathVariable Long id) {
        log.debug("REST request to delete Quotation : {}", id);
        quotationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
