package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MondayColumn;
import com.mycompany.myapp.repository.MondayColumnRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MondayColumn}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MondayColumnResource {

    private final Logger log = LoggerFactory.getLogger(MondayColumnResource.class);

    private static final String ENTITY_NAME = "mondayColumn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MondayColumnRepository mondayColumnRepository;

    public MondayColumnResource(MondayColumnRepository mondayColumnRepository) {
        this.mondayColumnRepository = mondayColumnRepository;
    }

    /**
     * {@code POST  /monday-columns} : Create a new mondayColumn.
     *
     * @param mondayColumn the mondayColumn to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mondayColumn, or with status {@code 400 (Bad Request)} if the mondayColumn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/monday-columns")
    public ResponseEntity<MondayColumn> createMondayColumn(@RequestBody MondayColumn mondayColumn) throws URISyntaxException {
        log.debug("REST request to save MondayColumn : {}", mondayColumn);
        if (mondayColumn.getId() != null) {
            throw new BadRequestAlertException("A new mondayColumn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MondayColumn result = mondayColumnRepository.save(mondayColumn);
        return ResponseEntity
            .created(new URI("/api/monday-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monday-columns/:id} : Updates an existing mondayColumn.
     *
     * @param id the id of the mondayColumn to save.
     * @param mondayColumn the mondayColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mondayColumn,
     * or with status {@code 400 (Bad Request)} if the mondayColumn is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mondayColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/monday-columns/{id}")
    public ResponseEntity<MondayColumn> updateMondayColumn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MondayColumn mondayColumn
    ) throws URISyntaxException {
        log.debug("REST request to update MondayColumn : {}, {}", id, mondayColumn);
        if (mondayColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mondayColumn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mondayColumnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MondayColumn result = mondayColumnRepository.save(mondayColumn);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mondayColumn.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /monday-columns/:id} : Partial updates given fields of an existing mondayColumn, field will ignore if it is null
     *
     * @param id the id of the mondayColumn to save.
     * @param mondayColumn the mondayColumn to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mondayColumn,
     * or with status {@code 400 (Bad Request)} if the mondayColumn is not valid,
     * or with status {@code 404 (Not Found)} if the mondayColumn is not found,
     * or with status {@code 500 (Internal Server Error)} if the mondayColumn couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/monday-columns/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MondayColumn> partialUpdateMondayColumn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MondayColumn mondayColumn
    ) throws URISyntaxException {
        log.debug("REST request to partial update MondayColumn partially : {}, {}", id, mondayColumn);
        if (mondayColumn.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mondayColumn.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mondayColumnRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MondayColumn> result = mondayColumnRepository
            .findById(mondayColumn.getId())
            .map(existingMondayColumn -> {
                if (mondayColumn.getBoardId() != null) {
                    existingMondayColumn.setBoardId(mondayColumn.getBoardId());
                }
                if (mondayColumn.getColumnId() != null) {
                    existingMondayColumn.setColumnId(mondayColumn.getColumnId());
                }
                if (mondayColumn.getTitle() != null) {
                    existingMondayColumn.setTitle(mondayColumn.getTitle());
                }
                if (mondayColumn.getType() != null) {
                    existingMondayColumn.setType(mondayColumn.getType());
                }

                return existingMondayColumn;
            })
            .map(mondayColumnRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mondayColumn.getId().toString())
        );
    }

    /**
     * {@code GET  /monday-columns} : get all the mondayColumns.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mondayColumns in body.
     */
    @GetMapping("/monday-columns")
    public List<MondayColumn> getAllMondayColumns() {
        log.debug("REST request to get all MondayColumns");
        return mondayColumnRepository.findAll();
    }

    /**
     * {@code GET  /monday-columns/:id} : get the "id" mondayColumn.
     *
     * @param id the id of the mondayColumn to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mondayColumn, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/monday-columns/{id}")
    public ResponseEntity<MondayColumn> getMondayColumn(@PathVariable Long id) {
        log.debug("REST request to get MondayColumn : {}", id);
        Optional<MondayColumn> mondayColumn = mondayColumnRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mondayColumn);
    }

    /**
     * {@code DELETE  /monday-columns/:id} : delete the "id" mondayColumn.
     *
     * @param id the id of the mondayColumn to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/monday-columns/{id}")
    public ResponseEntity<Void> deleteMondayColumn(@PathVariable Long id) {
        log.debug("REST request to delete MondayColumn : {}", id);
        mondayColumnRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
