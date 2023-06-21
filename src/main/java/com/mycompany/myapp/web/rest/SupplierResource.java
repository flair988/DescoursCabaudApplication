package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Supplier;
import com.mycompany.myapp.repository.SupplierRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Supplier}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SupplierResource {

    private final Logger log = LoggerFactory.getLogger(SupplierResource.class);

    private static final String ENTITY_NAME = "supplier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupplierRepository supplierRepository;

    public SupplierResource(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    /**
     * {@code POST  /suppliers} : Create a new supplier.
     *
     * @param supplier the supplier to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supplier, or with status {@code 400 (Bad Request)} if the supplier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) throws URISyntaxException {
        log.debug("REST request to save Supplier : {}", supplier);
        if (supplier.getId() != null) {
            throw new BadRequestAlertException("A new supplier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Supplier result = supplierRepository.save(supplier);
        return ResponseEntity
            .created(new URI("/api/suppliers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /suppliers/:id} : Updates an existing supplier.
     *
     * @param id the id of the supplier to save.
     * @param supplier the supplier to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplier,
     * or with status {@code 400 (Bad Request)} if the supplier is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supplier couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> updateSupplier(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Supplier supplier
    ) throws URISyntaxException {
        log.debug("REST request to update Supplier : {}, {}", id, supplier);
        if (supplier.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supplier.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supplierRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Supplier result = supplierRepository.save(supplier);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supplier.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /suppliers/:id} : Partial updates given fields of an existing supplier, field will ignore if it is null
     *
     * @param id the id of the supplier to save.
     * @param supplier the supplier to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplier,
     * or with status {@code 400 (Bad Request)} if the supplier is not valid,
     * or with status {@code 404 (Not Found)} if the supplier is not found,
     * or with status {@code 500 (Internal Server Error)} if the supplier couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/suppliers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Supplier> partialUpdateSupplier(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Supplier supplier
    ) throws URISyntaxException {
        log.debug("REST request to partial update Supplier partially : {}, {}", id, supplier);
        if (supplier.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supplier.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supplierRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Supplier> result = supplierRepository
            .findById(supplier.getId())
            .map(existingSupplier -> {
                if (supplier.getClient() != null) {
                    existingSupplier.setClient(supplier.getClient());
                }
                if (supplier.getCategory() != null) {
                    existingSupplier.setCategory(supplier.getCategory());
                }
                if (supplier.getSubCategory() != null) {
                    existingSupplier.setSubCategory(supplier.getSubCategory());
                }
                if (supplier.getCapStatus() != null) {
                    existingSupplier.setCapStatus(supplier.getCapStatus());
                }
                if (supplier.getSupplierStatus() != null) {
                    existingSupplier.setSupplierStatus(supplier.getSupplierStatus());
                }
                if (supplier.getQualificationScore() != null) {
                    existingSupplier.setQualificationScore(supplier.getQualificationScore());
                }
                if (supplier.getBopeScore() != null) {
                    existingSupplier.setBopeScore(supplier.getBopeScore());
                }
                if (supplier.getInternalSupplierId() != null) {
                    existingSupplier.setInternalSupplierId(supplier.getInternalSupplierId());
                }
                if (supplier.getContact() != null) {
                    existingSupplier.setContact(supplier.getContact());
                }
                if (supplier.getContactPhoneNumber() != null) {
                    existingSupplier.setContactPhoneNumber(supplier.getContactPhoneNumber());
                }
                if (supplier.getContactEmailAddress() != null) {
                    existingSupplier.setContactEmailAddress(supplier.getContactEmailAddress());
                }
                if (supplier.getOperationSite() != null) {
                    existingSupplier.setOperationSite(supplier.getOperationSite());
                }
                if (supplier.getAddress() != null) {
                    existingSupplier.setAddress(supplier.getAddress());
                }
                if (supplier.getWebsite() != null) {
                    existingSupplier.setWebsite(supplier.getWebsite());
                }
                if (supplier.getRelationStartingYear() != null) {
                    existingSupplier.setRelationStartingYear(supplier.getRelationStartingYear());
                }
                if (supplier.getBusinessLicense() != null) {
                    existingSupplier.setBusinessLicense(supplier.getBusinessLicense());
                }
                if (supplier.getRexOriginStatus() != null) {
                    existingSupplier.setRexOriginStatus(supplier.getRexOriginStatus());
                }
                if (supplier.getCreateDate() != null) {
                    existingSupplier.setCreateDate(supplier.getCreateDate());
                }
                if (supplier.getUpdateDate() != null) {
                    existingSupplier.setUpdateDate(supplier.getUpdateDate());
                }
                if (supplier.getItem() != null) {
                    existingSupplier.setItem(supplier.getItem());
                }
                if (supplier.getSubItems() != null) {
                    existingSupplier.setSubItems(supplier.getSubItems());
                }
                if (supplier.getDate() != null) {
                    existingSupplier.setDate(supplier.getDate());
                }
                if (supplier.getKingdeeId() != null) {
                    existingSupplier.setKingdeeId(supplier.getKingdeeId());
                }
                if (supplier.getRegion() != null) {
                    existingSupplier.setRegion(supplier.getRegion());
                }
                if (supplier.getSupplierType() != null) {
                    existingSupplier.setSupplierType(supplier.getSupplierType());
                }
                if (supplier.getRemark() != null) {
                    existingSupplier.setRemark(supplier.getRemark());
                }
                if (supplier.getFrenchName() != null) {
                    existingSupplier.setFrenchName(supplier.getFrenchName());
                }

                return existingSupplier;
            })
            .map(supplierRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, supplier.getId().toString())
        );
    }

    /**
     * {@code GET  /suppliers} : get all the suppliers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suppliers in body.
     */
    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        log.debug("REST request to get all Suppliers");
        return supplierRepository.findAll();
    }

    /**
     * {@code GET  /suppliers/:id} : get the "id" supplier.
     *
     * @param id the id of the supplier to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supplier, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long id) {
        log.debug("REST request to get Supplier : {}", id);
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(supplier);
    }

    /**
     * {@code DELETE  /suppliers/:id} : delete the "id" supplier.
     *
     * @param id the id of the supplier to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        log.debug("REST request to delete Supplier : {}", id);
        supplierRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
