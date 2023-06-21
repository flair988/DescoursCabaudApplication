package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Uom;
import com.mycompany.myapp.repository.UomRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Uom}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UomResource {

    private final Logger log = LoggerFactory.getLogger(UomResource.class);

    private static final String ENTITY_NAME = "uom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UomRepository uomRepository;

    public UomResource(UomRepository uomRepository) {
        this.uomRepository = uomRepository;
    }

    /**
     * {@code POST  /uoms} : Create a new uom.
     *
     * @param uom the uom to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uom, or with status {@code 400 (Bad Request)} if the uom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/uoms")
    public ResponseEntity<Uom> createUom(@RequestBody Uom uom) throws URISyntaxException {
        log.debug("REST request to save Uom : {}", uom);
        if (uom.getId() != null) {
            throw new BadRequestAlertException("A new uom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Uom result = uomRepository.save(uom);
        return ResponseEntity
            .created(new URI("/api/uoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /uoms/:id} : Updates an existing uom.
     *
     * @param id the id of the uom to save.
     * @param uom the uom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uom,
     * or with status {@code 400 (Bad Request)} if the uom is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/uoms/{id}")
    public ResponseEntity<Uom> updateUom(@PathVariable(value = "id", required = false) final Long id, @RequestBody Uom uom)
        throws URISyntaxException {
        log.debug("REST request to update Uom : {}, {}", id, uom);
        if (uom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uom.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uomRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Uom result = uomRepository.save(uom);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uom.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /uoms/:id} : Partial updates given fields of an existing uom, field will ignore if it is null
     *
     * @param id the id of the uom to save.
     * @param uom the uom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uom,
     * or with status {@code 400 (Bad Request)} if the uom is not valid,
     * or with status {@code 404 (Not Found)} if the uom is not found,
     * or with status {@code 500 (Internal Server Error)} if the uom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/uoms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Uom> partialUpdateUom(@PathVariable(value = "id", required = false) final Long id, @RequestBody Uom uom)
        throws URISyntaxException {
        log.debug("REST request to partial update Uom partially : {}, {}", id, uom);
        if (uom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, uom.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!uomRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Uom> result = uomRepository
            .findById(uom.getId())
            .map(existingUom -> {
                if (uom.getItemId() != null) {
                    existingUom.setItemId(uom.getItemId());
                }
                if (uom.getUom() != null) {
                    existingUom.setUom(uom.getUom());
                }
                if (uom.getUomGroup() != null) {
                    existingUom.setUomGroup(uom.getUomGroup());
                }
                if (uom.getDescription() != null) {
                    existingUom.setDescription(uom.getDescription());
                }
                if (uom.getSubItems() != null) {
                    existingUom.setSubItems(uom.getSubItems());
                }
                if (uom.getParentItem() != null) {
                    existingUom.setParentItem(uom.getParentItem());
                }
                if (uom.getBoardId() != null) {
                    existingUom.setBoardId(uom.getBoardId());
                }
                if (uom.getKingdeeId() != null) {
                    existingUom.setKingdeeId(uom.getKingdeeId());
                }

                return existingUom;
            })
            .map(uomRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uom.getId().toString())
        );
    }

    /**
     * {@code GET  /uoms} : get all the uoms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uoms in body.
     */
    @GetMapping("/uoms")
    public List<Uom> getAllUoms() {
        log.debug("REST request to get all Uoms");
        return uomRepository.findAll();
    }

    /**
     * {@code GET  /uoms/:id} : get the "id" uom.
     *
     * @param id the id of the uom to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uom, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/uoms/{id}")
    public ResponseEntity<Uom> getUom(@PathVariable Long id) {
        log.debug("REST request to get Uom : {}", id);
        Optional<Uom> uom = uomRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(uom);
    }

    /**
     * {@code DELETE  /uoms/:id} : delete the "id" uom.
     *
     * @param id the id of the uom to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/uoms/{id}")
    public ResponseEntity<Void> deleteUom(@PathVariable Long id) {
        log.debug("REST request to delete Uom : {}", id);
        uomRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
