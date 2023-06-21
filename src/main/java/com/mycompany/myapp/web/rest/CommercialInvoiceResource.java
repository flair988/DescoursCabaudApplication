package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CommercialInvoice;
import com.mycompany.myapp.repository.CommercialInvoiceRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CommercialInvoice}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CommercialInvoiceResource {

    private final Logger log = LoggerFactory.getLogger(CommercialInvoiceResource.class);

    private static final String ENTITY_NAME = "commercialInvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommercialInvoiceRepository commercialInvoiceRepository;

    public CommercialInvoiceResource(CommercialInvoiceRepository commercialInvoiceRepository) {
        this.commercialInvoiceRepository = commercialInvoiceRepository;
    }

    /**
     * {@code POST  /commercial-invoices} : Create a new commercialInvoice.
     *
     * @param commercialInvoice the commercialInvoice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commercialInvoice, or with status {@code 400 (Bad Request)} if the commercialInvoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commercial-invoices")
    public ResponseEntity<CommercialInvoice> createCommercialInvoice(@RequestBody CommercialInvoice commercialInvoice)
        throws URISyntaxException {
        log.debug("REST request to save CommercialInvoice : {}", commercialInvoice);
        if (commercialInvoice.getId() != null) {
            throw new BadRequestAlertException("A new commercialInvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommercialInvoice result = commercialInvoiceRepository.save(commercialInvoice);
        return ResponseEntity
            .created(new URI("/api/commercial-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commercial-invoices/:id} : Updates an existing commercialInvoice.
     *
     * @param id the id of the commercialInvoice to save.
     * @param commercialInvoice the commercialInvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commercialInvoice,
     * or with status {@code 400 (Bad Request)} if the commercialInvoice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commercialInvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commercial-invoices/{id}")
    public ResponseEntity<CommercialInvoice> updateCommercialInvoice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommercialInvoice commercialInvoice
    ) throws URISyntaxException {
        log.debug("REST request to update CommercialInvoice : {}, {}", id, commercialInvoice);
        if (commercialInvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commercialInvoice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commercialInvoiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CommercialInvoice result = commercialInvoiceRepository.save(commercialInvoice);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commercialInvoice.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /commercial-invoices/:id} : Partial updates given fields of an existing commercialInvoice, field will ignore if it is null
     *
     * @param id the id of the commercialInvoice to save.
     * @param commercialInvoice the commercialInvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commercialInvoice,
     * or with status {@code 400 (Bad Request)} if the commercialInvoice is not valid,
     * or with status {@code 404 (Not Found)} if the commercialInvoice is not found,
     * or with status {@code 500 (Internal Server Error)} if the commercialInvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/commercial-invoices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommercialInvoice> partialUpdateCommercialInvoice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommercialInvoice commercialInvoice
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommercialInvoice partially : {}, {}", id, commercialInvoice);
        if (commercialInvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commercialInvoice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commercialInvoiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommercialInvoice> result = commercialInvoiceRepository
            .findById(commercialInvoice.getId())
            .map(existingCommercialInvoice -> {
                if (commercialInvoice.getItemName() != null) {
                    existingCommercialInvoice.setItemName(commercialInvoice.getItemName());
                }
                if (commercialInvoice.getKingdeeId() != null) {
                    existingCommercialInvoice.setKingdeeId(commercialInvoice.getKingdeeId());
                }
                if (commercialInvoice.getDate() != null) {
                    existingCommercialInvoice.setDate(commercialInvoice.getDate());
                }
                if (commercialInvoice.getClient() != null) {
                    existingCommercialInvoice.setClient(commercialInvoice.getClient());
                }
                if (commercialInvoice.getCateGory() != null) {
                    existingCommercialInvoice.setCateGory(commercialInvoice.getCateGory());
                }
                if (commercialInvoice.getTotalPrice() != null) {
                    existingCommercialInvoice.setTotalPrice(commercialInvoice.getTotalPrice());
                }
                if (commercialInvoice.getCurrency() != null) {
                    existingCommercialInvoice.setCurrency(commercialInvoice.getCurrency());
                }
                if (commercialInvoice.getRemarks() != null) {
                    existingCommercialInvoice.setRemarks(commercialInvoice.getRemarks());
                }
                if (commercialInvoice.getClientId() != null) {
                    existingCommercialInvoice.setClientId(commercialInvoice.getClientId());
                }
                if (commercialInvoice.getDocStatus() != null) {
                    existingCommercialInvoice.setDocStatus(commercialInvoice.getDocStatus());
                }

                return existingCommercialInvoice;
            })
            .map(commercialInvoiceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commercialInvoice.getId().toString())
        );
    }

    /**
     * {@code GET  /commercial-invoices} : get all the commercialInvoices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commercialInvoices in body.
     */
    @GetMapping("/commercial-invoices")
    public List<CommercialInvoice> getAllCommercialInvoices() {
        log.debug("REST request to get all CommercialInvoices");
        return commercialInvoiceRepository.findAll();
    }

    /**
     * {@code GET  /commercial-invoices/:id} : get the "id" commercialInvoice.
     *
     * @param id the id of the commercialInvoice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commercialInvoice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commercial-invoices/{id}")
    public ResponseEntity<CommercialInvoice> getCommercialInvoice(@PathVariable Long id) {
        log.debug("REST request to get CommercialInvoice : {}", id);
        Optional<CommercialInvoice> commercialInvoice = commercialInvoiceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(commercialInvoice);
    }

    /**
     * {@code DELETE  /commercial-invoices/:id} : delete the "id" commercialInvoice.
     *
     * @param id the id of the commercialInvoice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commercial-invoices/{id}")
    public ResponseEntity<Void> deleteCommercialInvoice(@PathVariable Long id) {
        log.debug("REST request to delete CommercialInvoice : {}", id);
        commercialInvoiceRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
