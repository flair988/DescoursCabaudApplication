package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ProductFinished;
import com.mycompany.myapp.repository.ProductFinishedRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductFinished}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProductFinishedResource {

    private final Logger log = LoggerFactory.getLogger(ProductFinishedResource.class);

    private static final String ENTITY_NAME = "productFinished";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductFinishedRepository productFinishedRepository;

    public ProductFinishedResource(ProductFinishedRepository productFinishedRepository) {
        this.productFinishedRepository = productFinishedRepository;
    }

    /**
     * {@code POST  /product-finisheds} : Create a new productFinished.
     *
     * @param productFinished the productFinished to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productFinished, or with status {@code 400 (Bad Request)} if the productFinished has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-finisheds")
    public ResponseEntity<ProductFinished> createProductFinished(@RequestBody ProductFinished productFinished) throws URISyntaxException {
        log.debug("REST request to save ProductFinished : {}", productFinished);
        if (productFinished.getId() != null) {
            throw new BadRequestAlertException("A new productFinished cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductFinished result = productFinishedRepository.save(productFinished);
        return ResponseEntity
            .created(new URI("/api/product-finisheds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-finisheds/:id} : Updates an existing productFinished.
     *
     * @param id the id of the productFinished to save.
     * @param productFinished the productFinished to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productFinished,
     * or with status {@code 400 (Bad Request)} if the productFinished is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productFinished couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-finisheds/{id}")
    public ResponseEntity<ProductFinished> updateProductFinished(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductFinished productFinished
    ) throws URISyntaxException {
        log.debug("REST request to update ProductFinished : {}, {}", id, productFinished);
        if (productFinished.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productFinished.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productFinishedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductFinished result = productFinishedRepository.save(productFinished);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productFinished.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /product-finisheds/:id} : Partial updates given fields of an existing productFinished, field will ignore if it is null
     *
     * @param id the id of the productFinished to save.
     * @param productFinished the productFinished to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productFinished,
     * or with status {@code 400 (Bad Request)} if the productFinished is not valid,
     * or with status {@code 404 (Not Found)} if the productFinished is not found,
     * or with status {@code 500 (Internal Server Error)} if the productFinished couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/product-finisheds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProductFinished> partialUpdateProductFinished(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductFinished productFinished
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductFinished partially : {}, {}", id, productFinished);
        if (productFinished.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productFinished.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productFinishedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductFinished> result = productFinishedRepository
            .findById(productFinished.getId())
            .map(existingProductFinished -> {
                if (productFinished.getItemName() != null) {
                    existingProductFinished.setItemName(productFinished.getItemName());
                }
                if (productFinished.getKingdeeId() != null) {
                    existingProductFinished.setKingdeeId(productFinished.getKingdeeId());
                }
                if (productFinished.getSupplier() != null) {
                    existingProductFinished.setSupplier(productFinished.getSupplier());
                }
                if (productFinished.getSupplierEmail() != null) {
                    existingProductFinished.setSupplierEmail(productFinished.getSupplierEmail());
                }
                if (productFinished.getOrderDate() != null) {
                    existingProductFinished.setOrderDate(productFinished.getOrderDate());
                }
                if (productFinished.getCateGory() != null) {
                    existingProductFinished.setCateGory(productFinished.getCateGory());
                }
                if (productFinished.getRemark() != null) {
                    existingProductFinished.setRemark(productFinished.getRemark());
                }
                if (productFinished.getMaterialReceiptDate() != null) {
                    existingProductFinished.setMaterialReceiptDate(productFinished.getMaterialReceiptDate());
                }
                if (productFinished.getDocStatus() != null) {
                    existingProductFinished.setDocStatus(productFinished.getDocStatus());
                }
                if (productFinished.getSupplierName() != null) {
                    existingProductFinished.setSupplierName(productFinished.getSupplierName());
                }

                return existingProductFinished;
            })
            .map(productFinishedRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productFinished.getId().toString())
        );
    }

    /**
     * {@code GET  /product-finisheds} : get all the productFinisheds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productFinisheds in body.
     */
    @GetMapping("/product-finisheds")
    public List<ProductFinished> getAllProductFinisheds() {
        log.debug("REST request to get all ProductFinisheds");
        return productFinishedRepository.findAll();
    }

    /**
     * {@code GET  /product-finisheds/:id} : get the "id" productFinished.
     *
     * @param id the id of the productFinished to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productFinished, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-finisheds/{id}")
    public ResponseEntity<ProductFinished> getProductFinished(@PathVariable Long id) {
        log.debug("REST request to get ProductFinished : {}", id);
        Optional<ProductFinished> productFinished = productFinishedRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productFinished);
    }

    /**
     * {@code DELETE  /product-finisheds/:id} : delete the "id" productFinished.
     *
     * @param id the id of the productFinished to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-finisheds/{id}")
    public ResponseEntity<Void> deleteProductFinished(@PathVariable Long id) {
        log.debug("REST request to delete ProductFinished : {}", id);
        productFinishedRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
