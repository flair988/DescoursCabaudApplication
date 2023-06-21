package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SalesDelivery;
import com.mycompany.myapp.repository.SalesDeliveryRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SalesDelivery}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SalesDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(SalesDeliveryResource.class);

    private static final String ENTITY_NAME = "salesDelivery";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesDeliveryRepository salesDeliveryRepository;

    public SalesDeliveryResource(SalesDeliveryRepository salesDeliveryRepository) {
        this.salesDeliveryRepository = salesDeliveryRepository;
    }

    /**
     * {@code POST  /sales-deliveries} : Create a new salesDelivery.
     *
     * @param salesDelivery the salesDelivery to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesDelivery, or with status {@code 400 (Bad Request)} if the salesDelivery has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-deliveries")
    public ResponseEntity<SalesDelivery> createSalesDelivery(@RequestBody SalesDelivery salesDelivery) throws URISyntaxException {
        log.debug("REST request to save SalesDelivery : {}", salesDelivery);
        if (salesDelivery.getId() != null) {
            throw new BadRequestAlertException("A new salesDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesDelivery result = salesDeliveryRepository.save(salesDelivery);
        return ResponseEntity
            .created(new URI("/api/sales-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-deliveries/:id} : Updates an existing salesDelivery.
     *
     * @param id the id of the salesDelivery to save.
     * @param salesDelivery the salesDelivery to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesDelivery,
     * or with status {@code 400 (Bad Request)} if the salesDelivery is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesDelivery couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-deliveries/{id}")
    public ResponseEntity<SalesDelivery> updateSalesDelivery(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesDelivery salesDelivery
    ) throws URISyntaxException {
        log.debug("REST request to update SalesDelivery : {}, {}", id, salesDelivery);
        if (salesDelivery.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesDelivery.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesDeliveryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesDelivery result = salesDeliveryRepository.save(salesDelivery);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesDelivery.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sales-deliveries/:id} : Partial updates given fields of an existing salesDelivery, field will ignore if it is null
     *
     * @param id the id of the salesDelivery to save.
     * @param salesDelivery the salesDelivery to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesDelivery,
     * or with status {@code 400 (Bad Request)} if the salesDelivery is not valid,
     * or with status {@code 404 (Not Found)} if the salesDelivery is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesDelivery couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-deliveries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalesDelivery> partialUpdateSalesDelivery(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesDelivery salesDelivery
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesDelivery partially : {}, {}", id, salesDelivery);
        if (salesDelivery.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesDelivery.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesDeliveryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesDelivery> result = salesDeliveryRepository
            .findById(salesDelivery.getId())
            .map(existingSalesDelivery -> {
                if (salesDelivery.getItemName() != null) {
                    existingSalesDelivery.setItemName(salesDelivery.getItemName());
                }
                if (salesDelivery.getKingdeeId() != null) {
                    existingSalesDelivery.setKingdeeId(salesDelivery.getKingdeeId());
                }
                if (salesDelivery.getCustomer() != null) {
                    existingSalesDelivery.setCustomer(salesDelivery.getCustomer());
                }
                if (salesDelivery.getOrderDate() != null) {
                    existingSalesDelivery.setOrderDate(salesDelivery.getOrderDate());
                }
                if (salesDelivery.getLoadingPort() != null) {
                    existingSalesDelivery.setLoadingPort(salesDelivery.getLoadingPort());
                }
                if (salesDelivery.getDischargePort() != null) {
                    existingSalesDelivery.setDischargePort(salesDelivery.getDischargePort());
                }
                if (salesDelivery.getTransportMode() != null) {
                    existingSalesDelivery.setTransportMode(salesDelivery.getTransportMode());
                }
                if (salesDelivery.getIncoterm() != null) {
                    existingSalesDelivery.setIncoterm(salesDelivery.getIncoterm());
                }
                if (salesDelivery.getForwarder() != null) {
                    existingSalesDelivery.setForwarder(salesDelivery.getForwarder());
                }
                if (salesDelivery.getEta() != null) {
                    existingSalesDelivery.setEta(salesDelivery.getEta());
                }
                if (salesDelivery.getEtd() != null) {
                    existingSalesDelivery.setEtd(salesDelivery.getEtd());
                }
                if (salesDelivery.getContainerType() != null) {
                    existingSalesDelivery.setContainerType(salesDelivery.getContainerType());
                }
                if (salesDelivery.getContainerSize() != null) {
                    existingSalesDelivery.setContainerSize(salesDelivery.getContainerSize());
                }
                if (salesDelivery.getRemark() != null) {
                    existingSalesDelivery.setRemark(salesDelivery.getRemark());
                }
                if (salesDelivery.getKingdeeUniqueId() != null) {
                    existingSalesDelivery.setKingdeeUniqueId(salesDelivery.getKingdeeUniqueId());
                }
                if (salesDelivery.getDocStatus() != null) {
                    existingSalesDelivery.setDocStatus(salesDelivery.getDocStatus());
                }
                if (salesDelivery.getCateGory() != null) {
                    existingSalesDelivery.setCateGory(salesDelivery.getCateGory());
                }

                return existingSalesDelivery;
            })
            .map(salesDeliveryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesDelivery.getId().toString())
        );
    }

    /**
     * {@code GET  /sales-deliveries} : get all the salesDeliveries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesDeliveries in body.
     */
    @GetMapping("/sales-deliveries")
    public List<SalesDelivery> getAllSalesDeliveries() {
        log.debug("REST request to get all SalesDeliveries");
        return salesDeliveryRepository.findAll();
    }

    /**
     * {@code GET  /sales-deliveries/:id} : get the "id" salesDelivery.
     *
     * @param id the id of the salesDelivery to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesDelivery, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-deliveries/{id}")
    public ResponseEntity<SalesDelivery> getSalesDelivery(@PathVariable Long id) {
        log.debug("REST request to get SalesDelivery : {}", id);
        Optional<SalesDelivery> salesDelivery = salesDeliveryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(salesDelivery);
    }

    /**
     * {@code DELETE  /sales-deliveries/:id} : delete the "id" salesDelivery.
     *
     * @param id the id of the salesDelivery to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-deliveries/{id}")
    public ResponseEntity<Void> deleteSalesDelivery(@PathVariable Long id) {
        log.debug("REST request to delete SalesDelivery : {}", id);
        salesDeliveryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
