package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OrderFollowUp;
import com.mycompany.myapp.repository.OrderFollowUpRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderFollowUp}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OrderFollowUpResource {

    private final Logger log = LoggerFactory.getLogger(OrderFollowUpResource.class);

    private static final String ENTITY_NAME = "orderFollowUp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderFollowUpRepository orderFollowUpRepository;

    public OrderFollowUpResource(OrderFollowUpRepository orderFollowUpRepository) {
        this.orderFollowUpRepository = orderFollowUpRepository;
    }

    /**
     * {@code POST  /order-follow-ups} : Create a new orderFollowUp.
     *
     * @param orderFollowUp the orderFollowUp to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderFollowUp, or with status {@code 400 (Bad Request)} if the orderFollowUp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-follow-ups")
    public ResponseEntity<OrderFollowUp> createOrderFollowUp(@RequestBody OrderFollowUp orderFollowUp) throws URISyntaxException {
        log.debug("REST request to save OrderFollowUp : {}", orderFollowUp);
        if (orderFollowUp.getId() != null) {
            throw new BadRequestAlertException("A new orderFollowUp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderFollowUp result = orderFollowUpRepository.save(orderFollowUp);
        return ResponseEntity
            .created(new URI("/api/order-follow-ups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-follow-ups/:id} : Updates an existing orderFollowUp.
     *
     * @param id the id of the orderFollowUp to save.
     * @param orderFollowUp the orderFollowUp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderFollowUp,
     * or with status {@code 400 (Bad Request)} if the orderFollowUp is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderFollowUp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-follow-ups/{id}")
    public ResponseEntity<OrderFollowUp> updateOrderFollowUp(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderFollowUp orderFollowUp
    ) throws URISyntaxException {
        log.debug("REST request to update OrderFollowUp : {}, {}", id, orderFollowUp);
        if (orderFollowUp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderFollowUp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderFollowUpRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderFollowUp result = orderFollowUpRepository.save(orderFollowUp);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderFollowUp.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /order-follow-ups/:id} : Partial updates given fields of an existing orderFollowUp, field will ignore if it is null
     *
     * @param id the id of the orderFollowUp to save.
     * @param orderFollowUp the orderFollowUp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderFollowUp,
     * or with status {@code 400 (Bad Request)} if the orderFollowUp is not valid,
     * or with status {@code 404 (Not Found)} if the orderFollowUp is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderFollowUp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/order-follow-ups/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderFollowUp> partialUpdateOrderFollowUp(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderFollowUp orderFollowUp
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderFollowUp partially : {}, {}", id, orderFollowUp);
        if (orderFollowUp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderFollowUp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderFollowUpRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderFollowUp> result = orderFollowUpRepository
            .findById(orderFollowUp.getId())
            .map(existingOrderFollowUp -> {
                if (orderFollowUp.getPoNumber() != null) {
                    existingOrderFollowUp.setPoNumber(orderFollowUp.getPoNumber());
                }
                if (orderFollowUp.getSupplier() != null) {
                    existingOrderFollowUp.setSupplier(orderFollowUp.getSupplier());
                }
                if (orderFollowUp.getOrderDate() != null) {
                    existingOrderFollowUp.setOrderDate(orderFollowUp.getOrderDate());
                }
                if (orderFollowUp.getCateGory() != null) {
                    existingOrderFollowUp.setCateGory(orderFollowUp.getCateGory());
                }
                if (orderFollowUp.getInspectionDate() != null) {
                    existingOrderFollowUp.setInspectionDate(orderFollowUp.getInspectionDate());
                }
                if (orderFollowUp.getRequestEndOfProdDate() != null) {
                    existingOrderFollowUp.setRequestEndOfProdDate(orderFollowUp.getRequestEndOfProdDate());
                }
                if (orderFollowUp.getTotalAmount() != null) {
                    existingOrderFollowUp.setTotalAmount(orderFollowUp.getTotalAmount());
                }
                if (orderFollowUp.getTotalDiscount() != null) {
                    existingOrderFollowUp.setTotalDiscount(orderFollowUp.getTotalDiscount());
                }
                if (orderFollowUp.getDisCountRate() != null) {
                    existingOrderFollowUp.setDisCountRate(orderFollowUp.getDisCountRate());
                }
                if (orderFollowUp.getRegularCheck() != null) {
                    existingOrderFollowUp.setRegularCheck(orderFollowUp.getRegularCheck());
                }
                if (orderFollowUp.getEtd() != null) {
                    existingOrderFollowUp.setEtd(orderFollowUp.getEtd());
                }
                if (orderFollowUp.getAtd() != null) {
                    existingOrderFollowUp.setAtd(orderFollowUp.getAtd());
                }
                if (orderFollowUp.getEta() != null) {
                    existingOrderFollowUp.setEta(orderFollowUp.getEta());
                }
                if (orderFollowUp.getUpdatedEta() != null) {
                    existingOrderFollowUp.setUpdatedEta(orderFollowUp.getUpdatedEta());
                }
                if (orderFollowUp.getDocumentStatus() != null) {
                    existingOrderFollowUp.setDocumentStatus(orderFollowUp.getDocumentStatus());
                }
                if (orderFollowUp.getCustomInstruction() != null) {
                    existingOrderFollowUp.setCustomInstruction(orderFollowUp.getCustomInstruction());
                }
                if (orderFollowUp.getCustomInspection() != null) {
                    existingOrderFollowUp.setCustomInspection(orderFollowUp.getCustomInspection());
                }
                if (orderFollowUp.getDepositPaymentDate() != null) {
                    existingOrderFollowUp.setDepositPaymentDate(orderFollowUp.getDepositPaymentDate());
                }
                if (orderFollowUp.getBalanceOfTotalPaymentDate() != null) {
                    existingOrderFollowUp.setBalanceOfTotalPaymentDate(orderFollowUp.getBalanceOfTotalPaymentDate());
                }
                if (orderFollowUp.getAmountDepositPayment() != null) {
                    existingOrderFollowUp.setAmountDepositPayment(orderFollowUp.getAmountDepositPayment());
                }
                if (orderFollowUp.getAmountPayment() != null) {
                    existingOrderFollowUp.setAmountPayment(orderFollowUp.getAmountPayment());
                }
                if (orderFollowUp.getDcMember() != null) {
                    existingOrderFollowUp.setDcMember(orderFollowUp.getDcMember());
                }
                if (orderFollowUp.getComment() != null) {
                    existingOrderFollowUp.setComment(orderFollowUp.getComment());
                }
                if (orderFollowUp.getItemName() != null) {
                    existingOrderFollowUp.setItemName(orderFollowUp.getItemName());
                }
                if (orderFollowUp.getKingdeeId() != null) {
                    existingOrderFollowUp.setKingdeeId(orderFollowUp.getKingdeeId());
                }
                if (orderFollowUp.getParentKingdeeId() != null) {
                    existingOrderFollowUp.setParentKingdeeId(orderFollowUp.getParentKingdeeId());
                }
                if (orderFollowUp.getQty() != null) {
                    existingOrderFollowUp.setQty(orderFollowUp.getQty());
                }
                if (orderFollowUp.getItemCode() != null) {
                    existingOrderFollowUp.setItemCode(orderFollowUp.getItemCode());
                }
                if (orderFollowUp.getContractEndOfProdDate() != null) {
                    existingOrderFollowUp.setContractEndOfProdDate(orderFollowUp.getContractEndOfProdDate());
                }
                if (orderFollowUp.getSupplierId() != null) {
                    existingOrderFollowUp.setSupplierId(orderFollowUp.getSupplierId());
                }

                return existingOrderFollowUp;
            })
            .map(orderFollowUpRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderFollowUp.getId().toString())
        );
    }

    /**
     * {@code GET  /order-follow-ups} : get all the orderFollowUps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderFollowUps in body.
     */
    @GetMapping("/order-follow-ups")
    public List<OrderFollowUp> getAllOrderFollowUps() {
        log.debug("REST request to get all OrderFollowUps");
        return orderFollowUpRepository.findAll();
    }

    /**
     * {@code GET  /order-follow-ups/:id} : get the "id" orderFollowUp.
     *
     * @param id the id of the orderFollowUp to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderFollowUp, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-follow-ups/{id}")
    public ResponseEntity<OrderFollowUp> getOrderFollowUp(@PathVariable Long id) {
        log.debug("REST request to get OrderFollowUp : {}", id);
        Optional<OrderFollowUp> orderFollowUp = orderFollowUpRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(orderFollowUp);
    }

    /**
     * {@code DELETE  /order-follow-ups/:id} : delete the "id" orderFollowUp.
     *
     * @param id the id of the orderFollowUp to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-follow-ups/{id}")
    public ResponseEntity<Void> deleteOrderFollowUp(@PathVariable Long id) {
        log.debug("REST request to delete OrderFollowUp : {}", id);
        orderFollowUpRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
