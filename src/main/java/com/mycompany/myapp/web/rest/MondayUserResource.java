package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MondayUser;
import com.mycompany.myapp.repository.MondayUserRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MondayUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MondayUserResource {

    private final Logger log = LoggerFactory.getLogger(MondayUserResource.class);

    private static final String ENTITY_NAME = "mondayUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MondayUserRepository mondayUserRepository;

    public MondayUserResource(MondayUserRepository mondayUserRepository) {
        this.mondayUserRepository = mondayUserRepository;
    }

    /**
     * {@code POST  /monday-users} : Create a new mondayUser.
     *
     * @param mondayUser the mondayUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mondayUser, or with status {@code 400 (Bad Request)} if the mondayUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/monday-users")
    public ResponseEntity<MondayUser> createMondayUser(@RequestBody MondayUser mondayUser) throws URISyntaxException {
        log.debug("REST request to save MondayUser : {}", mondayUser);
        if (mondayUser.getId() != null) {
            throw new BadRequestAlertException("A new mondayUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MondayUser result = mondayUserRepository.save(mondayUser);
        return ResponseEntity
            .created(new URI("/api/monday-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monday-users/:id} : Updates an existing mondayUser.
     *
     * @param id the id of the mondayUser to save.
     * @param mondayUser the mondayUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mondayUser,
     * or with status {@code 400 (Bad Request)} if the mondayUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mondayUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/monday-users/{id}")
    public ResponseEntity<MondayUser> updateMondayUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MondayUser mondayUser
    ) throws URISyntaxException {
        log.debug("REST request to update MondayUser : {}, {}", id, mondayUser);
        if (mondayUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mondayUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mondayUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MondayUser result = mondayUserRepository.save(mondayUser);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mondayUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /monday-users/:id} : Partial updates given fields of an existing mondayUser, field will ignore if it is null
     *
     * @param id the id of the mondayUser to save.
     * @param mondayUser the mondayUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mondayUser,
     * or with status {@code 400 (Bad Request)} if the mondayUser is not valid,
     * or with status {@code 404 (Not Found)} if the mondayUser is not found,
     * or with status {@code 500 (Internal Server Error)} if the mondayUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/monday-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MondayUser> partialUpdateMondayUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MondayUser mondayUser
    ) throws URISyntaxException {
        log.debug("REST request to partial update MondayUser partially : {}, {}", id, mondayUser);
        if (mondayUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mondayUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mondayUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MondayUser> result = mondayUserRepository
            .findById(mondayUser.getId())
            .map(existingMondayUser -> {
                if (mondayUser.getMondayId() != null) {
                    existingMondayUser.setMondayId(mondayUser.getMondayId());
                }
                if (mondayUser.getName() != null) {
                    existingMondayUser.setName(mondayUser.getName());
                }
                if (mondayUser.getEmail() != null) {
                    existingMondayUser.setEmail(mondayUser.getEmail());
                }
                if (mondayUser.getUrl() != null) {
                    existingMondayUser.setUrl(mondayUser.getUrl());
                }

                return existingMondayUser;
            })
            .map(mondayUserRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mondayUser.getId().toString())
        );
    }

    /**
     * {@code GET  /monday-users} : get all the mondayUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mondayUsers in body.
     */
    @GetMapping("/monday-users")
    public List<MondayUser> getAllMondayUsers() {
        log.debug("REST request to get all MondayUsers");
        return mondayUserRepository.findAll();
    }

    /**
     * {@code GET  /monday-users/:id} : get the "id" mondayUser.
     *
     * @param id the id of the mondayUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mondayUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/monday-users/{id}")
    public ResponseEntity<MondayUser> getMondayUser(@PathVariable Long id) {
        log.debug("REST request to get MondayUser : {}", id);
        Optional<MondayUser> mondayUser = mondayUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mondayUser);
    }

    /**
     * {@code DELETE  /monday-users/:id} : delete the "id" mondayUser.
     *
     * @param id the id of the mondayUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/monday-users/{id}")
    public ResponseEntity<Void> deleteMondayUser(@PathVariable Long id) {
        log.debug("REST request to delete MondayUser : {}", id);
        mondayUserRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
