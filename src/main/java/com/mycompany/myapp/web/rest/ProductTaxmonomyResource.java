package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ProductTaxmonomy;
import com.mycompany.myapp.repository.ProductTaxmonomyRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ProductTaxmonomy}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProductTaxmonomyResource {

    private final Logger log = LoggerFactory.getLogger(ProductTaxmonomyResource.class);

    private static final String ENTITY_NAME = "productTaxmonomy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductTaxmonomyRepository productTaxmonomyRepository;

    public ProductTaxmonomyResource(ProductTaxmonomyRepository productTaxmonomyRepository) {
        this.productTaxmonomyRepository = productTaxmonomyRepository;
    }

    /**
     * {@code POST  /product-taxmonomies} : Create a new productTaxmonomy.
     *
     * @param productTaxmonomy the productTaxmonomy to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productTaxmonomy, or with status {@code 400 (Bad Request)} if the productTaxmonomy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-taxmonomies")
    public ResponseEntity<ProductTaxmonomy> createProductTaxmonomy(@RequestBody ProductTaxmonomy productTaxmonomy)
        throws URISyntaxException {
        log.debug("REST request to save ProductTaxmonomy : {}", productTaxmonomy);
        if (productTaxmonomy.getId() != null) {
            throw new BadRequestAlertException("A new productTaxmonomy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductTaxmonomy result = productTaxmonomyRepository.save(productTaxmonomy);
        return ResponseEntity
            .created(new URI("/api/product-taxmonomies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-taxmonomies/:id} : Updates an existing productTaxmonomy.
     *
     * @param id the id of the productTaxmonomy to save.
     * @param productTaxmonomy the productTaxmonomy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productTaxmonomy,
     * or with status {@code 400 (Bad Request)} if the productTaxmonomy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productTaxmonomy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-taxmonomies/{id}")
    public ResponseEntity<ProductTaxmonomy> updateProductTaxmonomy(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductTaxmonomy productTaxmonomy
    ) throws URISyntaxException {
        log.debug("REST request to update ProductTaxmonomy : {}, {}", id, productTaxmonomy);
        if (productTaxmonomy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productTaxmonomy.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productTaxmonomyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProductTaxmonomy result = productTaxmonomyRepository.save(productTaxmonomy);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productTaxmonomy.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /product-taxmonomies/:id} : Partial updates given fields of an existing productTaxmonomy, field will ignore if it is null
     *
     * @param id the id of the productTaxmonomy to save.
     * @param productTaxmonomy the productTaxmonomy to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productTaxmonomy,
     * or with status {@code 400 (Bad Request)} if the productTaxmonomy is not valid,
     * or with status {@code 404 (Not Found)} if the productTaxmonomy is not found,
     * or with status {@code 500 (Internal Server Error)} if the productTaxmonomy couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/product-taxmonomies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProductTaxmonomy> partialUpdateProductTaxmonomy(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProductTaxmonomy productTaxmonomy
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductTaxmonomy partially : {}, {}", id, productTaxmonomy);
        if (productTaxmonomy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productTaxmonomy.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!productTaxmonomyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProductTaxmonomy> result = productTaxmonomyRepository
            .findById(productTaxmonomy.getId())
            .map(existingProductTaxmonomy -> {
                if (productTaxmonomy.getItemId() != null) {
                    existingProductTaxmonomy.setItemId(productTaxmonomy.getItemId());
                }
                if (productTaxmonomy.getKingdeeId() != null) {
                    existingProductTaxmonomy.setKingdeeId(productTaxmonomy.getKingdeeId());
                }
                if (productTaxmonomy.getItemName() != null) {
                    existingProductTaxmonomy.setItemName(productTaxmonomy.getItemName());
                }
                if (productTaxmonomy.getGroupName() != null) {
                    existingProductTaxmonomy.setGroupName(productTaxmonomy.getGroupName());
                }
                if (productTaxmonomy.getParentGroupName() != null) {
                    existingProductTaxmonomy.setParentGroupName(productTaxmonomy.getParentGroupName());
                }
                if (productTaxmonomy.getSubGroupName() != null) {
                    existingProductTaxmonomy.setSubGroupName(productTaxmonomy.getSubGroupName());
                }
                if (productTaxmonomy.getDescription() != null) {
                    existingProductTaxmonomy.setDescription(productTaxmonomy.getDescription());
                }

                return existingProductTaxmonomy;
            })
            .map(productTaxmonomyRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productTaxmonomy.getId().toString())
        );
    }

    /**
     * {@code GET  /product-taxmonomies} : get all the productTaxmonomies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productTaxmonomies in body.
     */
    @GetMapping("/product-taxmonomies")
    public List<ProductTaxmonomy> getAllProductTaxmonomies() {
        log.debug("REST request to get all ProductTaxmonomies");
        return productTaxmonomyRepository.findAll();
    }

    /**
     * {@code GET  /product-taxmonomies/:id} : get the "id" productTaxmonomy.
     *
     * @param id the id of the productTaxmonomy to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productTaxmonomy, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-taxmonomies/{id}")
    public ResponseEntity<ProductTaxmonomy> getProductTaxmonomy(@PathVariable Long id) {
        log.debug("REST request to get ProductTaxmonomy : {}", id);
        Optional<ProductTaxmonomy> productTaxmonomy = productTaxmonomyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productTaxmonomy);
    }

    /**
     * {@code DELETE  /product-taxmonomies/:id} : delete the "id" productTaxmonomy.
     *
     * @param id the id of the productTaxmonomy to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-taxmonomies/{id}")
    public ResponseEntity<Void> deleteProductTaxmonomy(@PathVariable Long id) {
        log.debug("REST request to delete ProductTaxmonomy : {}", id);
        productTaxmonomyRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
