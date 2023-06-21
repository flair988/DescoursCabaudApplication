package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PurchaseReturn;
import com.mycompany.myapp.repository.PurchaseReturnRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PurchaseReturn}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PurchaseReturnResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseReturnResource.class);

    private static final String ENTITY_NAME = "purchaseReturn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseReturnRepository purchaseReturnRepository;

    public PurchaseReturnResource(PurchaseReturnRepository purchaseReturnRepository) {
        this.purchaseReturnRepository = purchaseReturnRepository;
    }

    /**
     * {@code POST  /purchase-returns} : Create a new purchaseReturn.
     *
     * @param purchaseReturn the purchaseReturn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseReturn, or with status {@code 400 (Bad Request)} if the purchaseReturn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-returns")
    public ResponseEntity<PurchaseReturn> createPurchaseReturn(@RequestBody PurchaseReturn purchaseReturn) throws URISyntaxException {
        log.debug("REST request to save PurchaseReturn : {}", purchaseReturn);
        if (purchaseReturn.getId() != null) {
            throw new BadRequestAlertException("A new purchaseReturn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseReturn result = purchaseReturnRepository.save(purchaseReturn);
        return ResponseEntity
            .created(new URI("/api/purchase-returns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-returns/:id} : Updates an existing purchaseReturn.
     *
     * @param id the id of the purchaseReturn to save.
     * @param purchaseReturn the purchaseReturn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseReturn,
     * or with status {@code 400 (Bad Request)} if the purchaseReturn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseReturn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-returns/{id}")
    public ResponseEntity<PurchaseReturn> updatePurchaseReturn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseReturn purchaseReturn
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseReturn : {}, {}", id, purchaseReturn);
        if (purchaseReturn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseReturn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseReturnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseReturn result = purchaseReturnRepository.save(purchaseReturn);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseReturn.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-returns/:id} : Partial updates given fields of an existing purchaseReturn, field will ignore if it is null
     *
     * @param id the id of the purchaseReturn to save.
     * @param purchaseReturn the purchaseReturn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseReturn,
     * or with status {@code 400 (Bad Request)} if the purchaseReturn is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseReturn is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseReturn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/purchase-returns/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseReturn> partialUpdatePurchaseReturn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseReturn purchaseReturn
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseReturn partially : {}, {}", id, purchaseReturn);
        if (purchaseReturn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseReturn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseReturnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseReturn> result = purchaseReturnRepository
            .findById(purchaseReturn.getId())
            .map(existingPurchaseReturn -> {
                if (purchaseReturn.getItemName() != null) {
                    existingPurchaseReturn.setItemName(purchaseReturn.getItemName());
                }
                if (purchaseReturn.getKingdeeId() != null) {
                    existingPurchaseReturn.setKingdeeId(purchaseReturn.getKingdeeId());
                }
                if (purchaseReturn.getDocNumber() != null) {
                    existingPurchaseReturn.setDocNumber(purchaseReturn.getDocNumber());
                }
                if (purchaseReturn.getSupplier() != null) {
                    existingPurchaseReturn.setSupplier(purchaseReturn.getSupplier());
                }
                if (purchaseReturn.getSupplierEmail() != null) {
                    existingPurchaseReturn.setSupplierEmail(purchaseReturn.getSupplierEmail());
                }
                if (purchaseReturn.getMaterialReturnDate() != null) {
                    existingPurchaseReturn.setMaterialReturnDate(purchaseReturn.getMaterialReturnDate());
                }
                if (purchaseReturn.getReasonForReturn() != null) {
                    existingPurchaseReturn.setReasonForReturn(purchaseReturn.getReasonForReturn());
                }
                if (purchaseReturn.getPurchaseDept() != null) {
                    existingPurchaseReturn.setPurchaseDept(purchaseReturn.getPurchaseDept());
                }
                if (purchaseReturn.getRemarks() != null) {
                    existingPurchaseReturn.setRemarks(purchaseReturn.getRemarks());
                }
                if (purchaseReturn.getDocStatus() != null) {
                    existingPurchaseReturn.setDocStatus(purchaseReturn.getDocStatus());
                }
                if (purchaseReturn.getItemCode() != null) {
                    existingPurchaseReturn.setItemCode(purchaseReturn.getItemCode());
                }
                if (purchaseReturn.getSupplierCode() != null) {
                    existingPurchaseReturn.setSupplierCode(purchaseReturn.getSupplierCode());
                }

                return existingPurchaseReturn;
            })
            .map(purchaseReturnRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseReturn.getId().toString())
        );
    }

    /**
     * {@code GET  /purchase-returns} : get all the purchaseReturns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseReturns in body.
     */
    @GetMapping("/purchase-returns")
    public List<PurchaseReturn> getAllPurchaseReturns() {
        log.debug("REST request to get all PurchaseReturns");
        return purchaseReturnRepository.findAll();
    }

    /**
     * {@code GET  /purchase-returns/:id} : get the "id" purchaseReturn.
     *
     * @param id the id of the purchaseReturn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseReturn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-returns/{id}")
    public ResponseEntity<PurchaseReturn> getPurchaseReturn(@PathVariable Long id) {
        log.debug("REST request to get PurchaseReturn : {}", id);
        Optional<PurchaseReturn> purchaseReturn = purchaseReturnRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(purchaseReturn);
    }

    /**
     * {@code DELETE  /purchase-returns/:id} : delete the "id" purchaseReturn.
     *
     * @param id the id of the purchaseReturn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-returns/{id}")
    public ResponseEntity<Void> deletePurchaseReturn(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseReturn : {}", id);
        purchaseReturnRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
