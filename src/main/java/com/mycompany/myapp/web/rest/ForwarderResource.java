package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Forwarder;
import com.mycompany.myapp.repository.ForwarderRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Forwarder}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ForwarderResource {

    private final Logger log = LoggerFactory.getLogger(ForwarderResource.class);

    private static final String ENTITY_NAME = "forwarder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ForwarderRepository forwarderRepository;

    public ForwarderResource(ForwarderRepository forwarderRepository) {
        this.forwarderRepository = forwarderRepository;
    }

    /**
     * {@code POST  /forwarders} : Create a new forwarder.
     *
     * @param forwarder the forwarder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new forwarder, or with status {@code 400 (Bad Request)} if the forwarder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/forwarders")
    public ResponseEntity<Forwarder> createForwarder(@RequestBody Forwarder forwarder) throws URISyntaxException {
        log.debug("REST request to save Forwarder : {}", forwarder);
        if (forwarder.getId() != null) {
            throw new BadRequestAlertException("A new forwarder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Forwarder result = forwarderRepository.save(forwarder);
        return ResponseEntity
            .created(new URI("/api/forwarders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /forwarders/:id} : Updates an existing forwarder.
     *
     * @param id the id of the forwarder to save.
     * @param forwarder the forwarder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forwarder,
     * or with status {@code 400 (Bad Request)} if the forwarder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the forwarder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/forwarders/{id}")
    public ResponseEntity<Forwarder> updateForwarder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Forwarder forwarder
    ) throws URISyntaxException {
        log.debug("REST request to update Forwarder : {}, {}", id, forwarder);
        if (forwarder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, forwarder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!forwarderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Forwarder result = forwarderRepository.save(forwarder);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forwarder.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /forwarders/:id} : Partial updates given fields of an existing forwarder, field will ignore if it is null
     *
     * @param id the id of the forwarder to save.
     * @param forwarder the forwarder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forwarder,
     * or with status {@code 400 (Bad Request)} if the forwarder is not valid,
     * or with status {@code 404 (Not Found)} if the forwarder is not found,
     * or with status {@code 500 (Internal Server Error)} if the forwarder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/forwarders/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Forwarder> partialUpdateForwarder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Forwarder forwarder
    ) throws URISyntaxException {
        log.debug("REST request to partial update Forwarder partially : {}, {}", id, forwarder);
        if (forwarder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, forwarder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!forwarderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Forwarder> result = forwarderRepository
            .findById(forwarder.getId())
            .map(existingForwarder -> {
                if (forwarder.getItemName() != null) {
                    existingForwarder.setItemName(forwarder.getItemName());
                }
                if (forwarder.getItemId() != null) {
                    existingForwarder.setItemId(forwarder.getItemId());
                }
                if (forwarder.getBoardId() != null) {
                    existingForwarder.setBoardId(forwarder.getBoardId());
                }
                if (forwarder.getKingdeeId() != null) {
                    existingForwarder.setKingdeeId(forwarder.getKingdeeId());
                }
                if (forwarder.getContact() != null) {
                    existingForwarder.setContact(forwarder.getContact());
                }
                if (forwarder.getEmail() != null) {
                    existingForwarder.setEmail(forwarder.getEmail());
                }
                if (forwarder.getTelephone() != null) {
                    existingForwarder.setTelephone(forwarder.getTelephone());
                }

                return existingForwarder;
            })
            .map(forwarderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forwarder.getId().toString())
        );
    }

    /**
     * {@code GET  /forwarders} : get all the forwarders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of forwarders in body.
     */
    @GetMapping("/forwarders")
    public List<Forwarder> getAllForwarders() {
        log.debug("REST request to get all Forwarders");
        return forwarderRepository.findAll();
    }

    /**
     * {@code GET  /forwarders/:id} : get the "id" forwarder.
     *
     * @param id the id of the forwarder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the forwarder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/forwarders/{id}")
    public ResponseEntity<Forwarder> getForwarder(@PathVariable Long id) {
        log.debug("REST request to get Forwarder : {}", id);
        Optional<Forwarder> forwarder = forwarderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(forwarder);
    }

    /**
     * {@code DELETE  /forwarders/:id} : delete the "id" forwarder.
     *
     * @param id the id of the forwarder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/forwarders/{id}")
    public ResponseEntity<Void> deleteForwarder(@PathVariable Long id) {
        log.debug("REST request to delete Forwarder : {}", id);
        forwarderRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
