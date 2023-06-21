package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ForwarderBooking;
import com.mycompany.myapp.repository.ForwarderBookingRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ForwarderBooking}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ForwarderBookingResource {

    private final Logger log = LoggerFactory.getLogger(ForwarderBookingResource.class);

    private static final String ENTITY_NAME = "forwarderBooking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ForwarderBookingRepository forwarderBookingRepository;

    public ForwarderBookingResource(ForwarderBookingRepository forwarderBookingRepository) {
        this.forwarderBookingRepository = forwarderBookingRepository;
    }

    /**
     * {@code POST  /forwarder-bookings} : Create a new forwarderBooking.
     *
     * @param forwarderBooking the forwarderBooking to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new forwarderBooking, or with status {@code 400 (Bad Request)} if the forwarderBooking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/forwarder-bookings")
    public ResponseEntity<ForwarderBooking> createForwarderBooking(@RequestBody ForwarderBooking forwarderBooking)
        throws URISyntaxException {
        log.debug("REST request to save ForwarderBooking : {}", forwarderBooking);
        if (forwarderBooking.getId() != null) {
            throw new BadRequestAlertException("A new forwarderBooking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ForwarderBooking result = forwarderBookingRepository.save(forwarderBooking);
        return ResponseEntity
            .created(new URI("/api/forwarder-bookings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /forwarder-bookings/:id} : Updates an existing forwarderBooking.
     *
     * @param id the id of the forwarderBooking to save.
     * @param forwarderBooking the forwarderBooking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forwarderBooking,
     * or with status {@code 400 (Bad Request)} if the forwarderBooking is not valid,
     * or with status {@code 500 (Internal Server Error)} if the forwarderBooking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/forwarder-bookings/{id}")
    public ResponseEntity<ForwarderBooking> updateForwarderBooking(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ForwarderBooking forwarderBooking
    ) throws URISyntaxException {
        log.debug("REST request to update ForwarderBooking : {}, {}", id, forwarderBooking);
        if (forwarderBooking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, forwarderBooking.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!forwarderBookingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ForwarderBooking result = forwarderBookingRepository.save(forwarderBooking);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forwarderBooking.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /forwarder-bookings/:id} : Partial updates given fields of an existing forwarderBooking, field will ignore if it is null
     *
     * @param id the id of the forwarderBooking to save.
     * @param forwarderBooking the forwarderBooking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated forwarderBooking,
     * or with status {@code 400 (Bad Request)} if the forwarderBooking is not valid,
     * or with status {@code 404 (Not Found)} if the forwarderBooking is not found,
     * or with status {@code 500 (Internal Server Error)} if the forwarderBooking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/forwarder-bookings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ForwarderBooking> partialUpdateForwarderBooking(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ForwarderBooking forwarderBooking
    ) throws URISyntaxException {
        log.debug("REST request to partial update ForwarderBooking partially : {}, {}", id, forwarderBooking);
        if (forwarderBooking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, forwarderBooking.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!forwarderBookingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ForwarderBooking> result = forwarderBookingRepository
            .findById(forwarderBooking.getId())
            .map(existingForwarderBooking -> {
                if (forwarderBooking.getItemName() != null) {
                    existingForwarderBooking.setItemName(forwarderBooking.getItemName());
                }
                if (forwarderBooking.getKingdeeId() != null) {
                    existingForwarderBooking.setKingdeeId(forwarderBooking.getKingdeeId());
                }
                if (forwarderBooking.getCustomer() != null) {
                    existingForwarderBooking.setCustomer(forwarderBooking.getCustomer());
                }
                if (forwarderBooking.getOrderDate() != null) {
                    existingForwarderBooking.setOrderDate(forwarderBooking.getOrderDate());
                }
                if (forwarderBooking.getForwarder() != null) {
                    existingForwarderBooking.setForwarder(forwarderBooking.getForwarder());
                }
                if (forwarderBooking.getTotalQty() != null) {
                    existingForwarderBooking.setTotalQty(forwarderBooking.getTotalQty());
                }
                if (forwarderBooking.getLoadingPort() != null) {
                    existingForwarderBooking.setLoadingPort(forwarderBooking.getLoadingPort());
                }
                if (forwarderBooking.getDischargePort() != null) {
                    existingForwarderBooking.setDischargePort(forwarderBooking.getDischargePort());
                }
                if (forwarderBooking.getContainerType() != null) {
                    existingForwarderBooking.setContainerType(forwarderBooking.getContainerType());
                }
                if (forwarderBooking.getContainerSize() != null) {
                    existingForwarderBooking.setContainerSize(forwarderBooking.getContainerSize());
                }
                if (forwarderBooking.getContainerNumber() != null) {
                    existingForwarderBooking.setContainerNumber(forwarderBooking.getContainerNumber());
                }
                if (forwarderBooking.getSupplier() != null) {
                    existingForwarderBooking.setSupplier(forwarderBooking.getSupplier());
                }
                if (forwarderBooking.getSupplierEmail() != null) {
                    existingForwarderBooking.setSupplierEmail(forwarderBooking.getSupplierEmail());
                }
                if (forwarderBooking.getEta() != null) {
                    existingForwarderBooking.setEta(forwarderBooking.getEta());
                }
                if (forwarderBooking.getEtd() != null) {
                    existingForwarderBooking.setEtd(forwarderBooking.getEtd());
                }
                if (forwarderBooking.getTransportMode() != null) {
                    existingForwarderBooking.setTransportMode(forwarderBooking.getTransportMode());
                }
                if (forwarderBooking.getNumberOfCartons() != null) {
                    existingForwarderBooking.setNumberOfCartons(forwarderBooking.getNumberOfCartons());
                }
                if (forwarderBooking.getNumberOfRef() != null) {
                    existingForwarderBooking.setNumberOfRef(forwarderBooking.getNumberOfRef());
                }
                if (forwarderBooking.getTotalVolume() != null) {
                    existingForwarderBooking.setTotalVolume(forwarderBooking.getTotalVolume());
                }
                if (forwarderBooking.getTotalWeight() != null) {
                    existingForwarderBooking.setTotalWeight(forwarderBooking.getTotalWeight());
                }
                if (forwarderBooking.getRemark() != null) {
                    existingForwarderBooking.setRemark(forwarderBooking.getRemark());
                }
                if (forwarderBooking.getClient() != null) {
                    existingForwarderBooking.setClient(forwarderBooking.getClient());
                }
                if (forwarderBooking.getKingdeeUniqueId() != null) {
                    existingForwarderBooking.setKingdeeUniqueId(forwarderBooking.getKingdeeUniqueId());
                }

                return existingForwarderBooking;
            })
            .map(forwarderBookingRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, forwarderBooking.getId().toString())
        );
    }

    /**
     * {@code GET  /forwarder-bookings} : get all the forwarderBookings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of forwarderBookings in body.
     */
    @GetMapping("/forwarder-bookings")
    public List<ForwarderBooking> getAllForwarderBookings() {
        log.debug("REST request to get all ForwarderBookings");
        return forwarderBookingRepository.findAll();
    }

    /**
     * {@code GET  /forwarder-bookings/:id} : get the "id" forwarderBooking.
     *
     * @param id the id of the forwarderBooking to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the forwarderBooking, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/forwarder-bookings/{id}")
    public ResponseEntity<ForwarderBooking> getForwarderBooking(@PathVariable Long id) {
        log.debug("REST request to get ForwarderBooking : {}", id);
        Optional<ForwarderBooking> forwarderBooking = forwarderBookingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(forwarderBooking);
    }

    /**
     * {@code DELETE  /forwarder-bookings/:id} : delete the "id" forwarderBooking.
     *
     * @param id the id of the forwarderBooking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/forwarder-bookings/{id}")
    public ResponseEntity<Void> deleteForwarderBooking(@PathVariable Long id) {
        log.debug("REST request to delete ForwarderBooking : {}", id);
        forwarderBookingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
