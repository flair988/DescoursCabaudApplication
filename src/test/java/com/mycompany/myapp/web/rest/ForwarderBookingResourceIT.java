package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ForwarderBooking;
import com.mycompany.myapp.repository.ForwarderBookingRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ForwarderBookingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ForwarderBookingResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_FORWARDER = "AAAAAAAAAA";
    private static final String UPDATED_FORWARDER = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_QTY = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_QTY = "BBBBBBBBBB";

    private static final String DEFAULT_LOADING_PORT = "AAAAAAAAAA";
    private static final String UPDATED_LOADING_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_DISCHARGE_PORT = "AAAAAAAAAA";
    private static final String UPDATED_DISCHARGE_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTAINER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTAINER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTAINER_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_CONTAINER_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTAINER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTAINER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ETA = "AAAAAAAAAA";
    private static final String UPDATED_ETA = "BBBBBBBBBB";

    private static final String DEFAULT_ETD = "AAAAAAAAAA";
    private static final String UPDATED_ETD = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSPORT_MODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSPORT_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_OF_CARTONS = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_OF_CARTONS = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_OF_REF = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_OF_REF = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_VOLUME = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_VOLUME = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_UNIQUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_UNIQUE_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/forwarder-bookings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ForwarderBookingRepository forwarderBookingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restForwarderBookingMockMvc;

    private ForwarderBooking forwarderBooking;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ForwarderBooking createEntity(EntityManager em) {
        ForwarderBooking forwarderBooking = new ForwarderBooking()
            .itemName(DEFAULT_ITEM_NAME)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .customer(DEFAULT_CUSTOMER)
            .orderDate(DEFAULT_ORDER_DATE)
            .forwarder(DEFAULT_FORWARDER)
            .totalQty(DEFAULT_TOTAL_QTY)
            .loadingPort(DEFAULT_LOADING_PORT)
            .dischargePort(DEFAULT_DISCHARGE_PORT)
            .containerType(DEFAULT_CONTAINER_TYPE)
            .containerSize(DEFAULT_CONTAINER_SIZE)
            .containerNumber(DEFAULT_CONTAINER_NUMBER)
            .supplier(DEFAULT_SUPPLIER)
            .supplierEmail(DEFAULT_SUPPLIER_EMAIL)
            .eta(DEFAULT_ETA)
            .etd(DEFAULT_ETD)
            .transportMode(DEFAULT_TRANSPORT_MODE)
            .numberOfCartons(DEFAULT_NUMBER_OF_CARTONS)
            .numberOfRef(DEFAULT_NUMBER_OF_REF)
            .totalVolume(DEFAULT_TOTAL_VOLUME)
            .totalWeight(DEFAULT_TOTAL_WEIGHT)
            .remark(DEFAULT_REMARK)
            .client(DEFAULT_CLIENT)
            .kingdeeUniqueId(DEFAULT_KINGDEE_UNIQUE_ID);
        return forwarderBooking;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ForwarderBooking createUpdatedEntity(EntityManager em) {
        ForwarderBooking forwarderBooking = new ForwarderBooking()
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .orderDate(UPDATED_ORDER_DATE)
            .forwarder(UPDATED_FORWARDER)
            .totalQty(UPDATED_TOTAL_QTY)
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .containerType(UPDATED_CONTAINER_TYPE)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .containerNumber(UPDATED_CONTAINER_NUMBER)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .eta(UPDATED_ETA)
            .etd(UPDATED_ETD)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .numberOfCartons(UPDATED_NUMBER_OF_CARTONS)
            .numberOfRef(UPDATED_NUMBER_OF_REF)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .remark(UPDATED_REMARK)
            .client(UPDATED_CLIENT)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID);
        return forwarderBooking;
    }

    @BeforeEach
    public void initTest() {
        forwarderBooking = createEntity(em);
    }

    @Test
    @Transactional
    void createForwarderBooking() throws Exception {
        int databaseSizeBeforeCreate = forwarderBookingRepository.findAll().size();
        // Create the ForwarderBooking
        restForwarderBookingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isCreated());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeCreate + 1);
        ForwarderBooking testForwarderBooking = forwarderBookingList.get(forwarderBookingList.size() - 1);
        assertThat(testForwarderBooking.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testForwarderBooking.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testForwarderBooking.getCustomer()).isEqualTo(DEFAULT_CUSTOMER);
        assertThat(testForwarderBooking.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testForwarderBooking.getForwarder()).isEqualTo(DEFAULT_FORWARDER);
        assertThat(testForwarderBooking.getTotalQty()).isEqualTo(DEFAULT_TOTAL_QTY);
        assertThat(testForwarderBooking.getLoadingPort()).isEqualTo(DEFAULT_LOADING_PORT);
        assertThat(testForwarderBooking.getDischargePort()).isEqualTo(DEFAULT_DISCHARGE_PORT);
        assertThat(testForwarderBooking.getContainerType()).isEqualTo(DEFAULT_CONTAINER_TYPE);
        assertThat(testForwarderBooking.getContainerSize()).isEqualTo(DEFAULT_CONTAINER_SIZE);
        assertThat(testForwarderBooking.getContainerNumber()).isEqualTo(DEFAULT_CONTAINER_NUMBER);
        assertThat(testForwarderBooking.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testForwarderBooking.getSupplierEmail()).isEqualTo(DEFAULT_SUPPLIER_EMAIL);
        assertThat(testForwarderBooking.getEta()).isEqualTo(DEFAULT_ETA);
        assertThat(testForwarderBooking.getEtd()).isEqualTo(DEFAULT_ETD);
        assertThat(testForwarderBooking.getTransportMode()).isEqualTo(DEFAULT_TRANSPORT_MODE);
        assertThat(testForwarderBooking.getNumberOfCartons()).isEqualTo(DEFAULT_NUMBER_OF_CARTONS);
        assertThat(testForwarderBooking.getNumberOfRef()).isEqualTo(DEFAULT_NUMBER_OF_REF);
        assertThat(testForwarderBooking.getTotalVolume()).isEqualTo(DEFAULT_TOTAL_VOLUME);
        assertThat(testForwarderBooking.getTotalWeight()).isEqualTo(DEFAULT_TOTAL_WEIGHT);
        assertThat(testForwarderBooking.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testForwarderBooking.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testForwarderBooking.getKingdeeUniqueId()).isEqualTo(DEFAULT_KINGDEE_UNIQUE_ID);
    }

    @Test
    @Transactional
    void createForwarderBookingWithExistingId() throws Exception {
        // Create the ForwarderBooking with an existing ID
        forwarderBooking.setId(1L);

        int databaseSizeBeforeCreate = forwarderBookingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restForwarderBookingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isBadRequest());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllForwarderBookings() throws Exception {
        // Initialize the database
        forwarderBookingRepository.saveAndFlush(forwarderBooking);

        // Get all the forwarderBookingList
        restForwarderBookingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(forwarderBooking.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].customer").value(hasItem(DEFAULT_CUSTOMER)))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(DEFAULT_ORDER_DATE)))
            .andExpect(jsonPath("$.[*].forwarder").value(hasItem(DEFAULT_FORWARDER)))
            .andExpect(jsonPath("$.[*].totalQty").value(hasItem(DEFAULT_TOTAL_QTY)))
            .andExpect(jsonPath("$.[*].loadingPort").value(hasItem(DEFAULT_LOADING_PORT)))
            .andExpect(jsonPath("$.[*].dischargePort").value(hasItem(DEFAULT_DISCHARGE_PORT)))
            .andExpect(jsonPath("$.[*].containerType").value(hasItem(DEFAULT_CONTAINER_TYPE)))
            .andExpect(jsonPath("$.[*].containerSize").value(hasItem(DEFAULT_CONTAINER_SIZE)))
            .andExpect(jsonPath("$.[*].containerNumber").value(hasItem(DEFAULT_CONTAINER_NUMBER)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].supplierEmail").value(hasItem(DEFAULT_SUPPLIER_EMAIL)))
            .andExpect(jsonPath("$.[*].eta").value(hasItem(DEFAULT_ETA)))
            .andExpect(jsonPath("$.[*].etd").value(hasItem(DEFAULT_ETD)))
            .andExpect(jsonPath("$.[*].transportMode").value(hasItem(DEFAULT_TRANSPORT_MODE)))
            .andExpect(jsonPath("$.[*].numberOfCartons").value(hasItem(DEFAULT_NUMBER_OF_CARTONS)))
            .andExpect(jsonPath("$.[*].numberOfRef").value(hasItem(DEFAULT_NUMBER_OF_REF)))
            .andExpect(jsonPath("$.[*].totalVolume").value(hasItem(DEFAULT_TOTAL_VOLUME)))
            .andExpect(jsonPath("$.[*].totalWeight").value(hasItem(DEFAULT_TOTAL_WEIGHT)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].kingdeeUniqueId").value(hasItem(DEFAULT_KINGDEE_UNIQUE_ID)));
    }

    @Test
    @Transactional
    void getForwarderBooking() throws Exception {
        // Initialize the database
        forwarderBookingRepository.saveAndFlush(forwarderBooking);

        // Get the forwarderBooking
        restForwarderBookingMockMvc
            .perform(get(ENTITY_API_URL_ID, forwarderBooking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(forwarderBooking.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.customer").value(DEFAULT_CUSTOMER))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE))
            .andExpect(jsonPath("$.forwarder").value(DEFAULT_FORWARDER))
            .andExpect(jsonPath("$.totalQty").value(DEFAULT_TOTAL_QTY))
            .andExpect(jsonPath("$.loadingPort").value(DEFAULT_LOADING_PORT))
            .andExpect(jsonPath("$.dischargePort").value(DEFAULT_DISCHARGE_PORT))
            .andExpect(jsonPath("$.containerType").value(DEFAULT_CONTAINER_TYPE))
            .andExpect(jsonPath("$.containerSize").value(DEFAULT_CONTAINER_SIZE))
            .andExpect(jsonPath("$.containerNumber").value(DEFAULT_CONTAINER_NUMBER))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.supplierEmail").value(DEFAULT_SUPPLIER_EMAIL))
            .andExpect(jsonPath("$.eta").value(DEFAULT_ETA))
            .andExpect(jsonPath("$.etd").value(DEFAULT_ETD))
            .andExpect(jsonPath("$.transportMode").value(DEFAULT_TRANSPORT_MODE))
            .andExpect(jsonPath("$.numberOfCartons").value(DEFAULT_NUMBER_OF_CARTONS))
            .andExpect(jsonPath("$.numberOfRef").value(DEFAULT_NUMBER_OF_REF))
            .andExpect(jsonPath("$.totalVolume").value(DEFAULT_TOTAL_VOLUME))
            .andExpect(jsonPath("$.totalWeight").value(DEFAULT_TOTAL_WEIGHT))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.kingdeeUniqueId").value(DEFAULT_KINGDEE_UNIQUE_ID));
    }

    @Test
    @Transactional
    void getNonExistingForwarderBooking() throws Exception {
        // Get the forwarderBooking
        restForwarderBookingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingForwarderBooking() throws Exception {
        // Initialize the database
        forwarderBookingRepository.saveAndFlush(forwarderBooking);

        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();

        // Update the forwarderBooking
        ForwarderBooking updatedForwarderBooking = forwarderBookingRepository.findById(forwarderBooking.getId()).get();
        // Disconnect from session so that the updates on updatedForwarderBooking are not directly saved in db
        em.detach(updatedForwarderBooking);
        updatedForwarderBooking
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .orderDate(UPDATED_ORDER_DATE)
            .forwarder(UPDATED_FORWARDER)
            .totalQty(UPDATED_TOTAL_QTY)
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .containerType(UPDATED_CONTAINER_TYPE)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .containerNumber(UPDATED_CONTAINER_NUMBER)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .eta(UPDATED_ETA)
            .etd(UPDATED_ETD)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .numberOfCartons(UPDATED_NUMBER_OF_CARTONS)
            .numberOfRef(UPDATED_NUMBER_OF_REF)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .remark(UPDATED_REMARK)
            .client(UPDATED_CLIENT)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID);

        restForwarderBookingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedForwarderBooking.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedForwarderBooking))
            )
            .andExpect(status().isOk());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
        ForwarderBooking testForwarderBooking = forwarderBookingList.get(forwarderBookingList.size() - 1);
        assertThat(testForwarderBooking.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testForwarderBooking.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testForwarderBooking.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testForwarderBooking.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testForwarderBooking.getForwarder()).isEqualTo(UPDATED_FORWARDER);
        assertThat(testForwarderBooking.getTotalQty()).isEqualTo(UPDATED_TOTAL_QTY);
        assertThat(testForwarderBooking.getLoadingPort()).isEqualTo(UPDATED_LOADING_PORT);
        assertThat(testForwarderBooking.getDischargePort()).isEqualTo(UPDATED_DISCHARGE_PORT);
        assertThat(testForwarderBooking.getContainerType()).isEqualTo(UPDATED_CONTAINER_TYPE);
        assertThat(testForwarderBooking.getContainerSize()).isEqualTo(UPDATED_CONTAINER_SIZE);
        assertThat(testForwarderBooking.getContainerNumber()).isEqualTo(UPDATED_CONTAINER_NUMBER);
        assertThat(testForwarderBooking.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testForwarderBooking.getSupplierEmail()).isEqualTo(UPDATED_SUPPLIER_EMAIL);
        assertThat(testForwarderBooking.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testForwarderBooking.getEtd()).isEqualTo(UPDATED_ETD);
        assertThat(testForwarderBooking.getTransportMode()).isEqualTo(UPDATED_TRANSPORT_MODE);
        assertThat(testForwarderBooking.getNumberOfCartons()).isEqualTo(UPDATED_NUMBER_OF_CARTONS);
        assertThat(testForwarderBooking.getNumberOfRef()).isEqualTo(UPDATED_NUMBER_OF_REF);
        assertThat(testForwarderBooking.getTotalVolume()).isEqualTo(UPDATED_TOTAL_VOLUME);
        assertThat(testForwarderBooking.getTotalWeight()).isEqualTo(UPDATED_TOTAL_WEIGHT);
        assertThat(testForwarderBooking.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testForwarderBooking.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testForwarderBooking.getKingdeeUniqueId()).isEqualTo(UPDATED_KINGDEE_UNIQUE_ID);
    }

    @Test
    @Transactional
    void putNonExistingForwarderBooking() throws Exception {
        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();
        forwarderBooking.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restForwarderBookingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, forwarderBooking.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isBadRequest());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchForwarderBooking() throws Exception {
        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();
        forwarderBooking.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderBookingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isBadRequest());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamForwarderBooking() throws Exception {
        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();
        forwarderBooking.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderBookingMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateForwarderBookingWithPatch() throws Exception {
        // Initialize the database
        forwarderBookingRepository.saveAndFlush(forwarderBooking);

        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();

        // Update the forwarderBooking using partial update
        ForwarderBooking partialUpdatedForwarderBooking = new ForwarderBooking();
        partialUpdatedForwarderBooking.setId(forwarderBooking.getId());

        partialUpdatedForwarderBooking
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .forwarder(UPDATED_FORWARDER)
            .totalQty(UPDATED_TOTAL_QTY)
            .eta(UPDATED_ETA)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .numberOfCartons(UPDATED_NUMBER_OF_CARTONS)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID);

        restForwarderBookingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedForwarderBooking.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedForwarderBooking))
            )
            .andExpect(status().isOk());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
        ForwarderBooking testForwarderBooking = forwarderBookingList.get(forwarderBookingList.size() - 1);
        assertThat(testForwarderBooking.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testForwarderBooking.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testForwarderBooking.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testForwarderBooking.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testForwarderBooking.getForwarder()).isEqualTo(UPDATED_FORWARDER);
        assertThat(testForwarderBooking.getTotalQty()).isEqualTo(UPDATED_TOTAL_QTY);
        assertThat(testForwarderBooking.getLoadingPort()).isEqualTo(DEFAULT_LOADING_PORT);
        assertThat(testForwarderBooking.getDischargePort()).isEqualTo(DEFAULT_DISCHARGE_PORT);
        assertThat(testForwarderBooking.getContainerType()).isEqualTo(DEFAULT_CONTAINER_TYPE);
        assertThat(testForwarderBooking.getContainerSize()).isEqualTo(DEFAULT_CONTAINER_SIZE);
        assertThat(testForwarderBooking.getContainerNumber()).isEqualTo(DEFAULT_CONTAINER_NUMBER);
        assertThat(testForwarderBooking.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testForwarderBooking.getSupplierEmail()).isEqualTo(DEFAULT_SUPPLIER_EMAIL);
        assertThat(testForwarderBooking.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testForwarderBooking.getEtd()).isEqualTo(DEFAULT_ETD);
        assertThat(testForwarderBooking.getTransportMode()).isEqualTo(UPDATED_TRANSPORT_MODE);
        assertThat(testForwarderBooking.getNumberOfCartons()).isEqualTo(UPDATED_NUMBER_OF_CARTONS);
        assertThat(testForwarderBooking.getNumberOfRef()).isEqualTo(DEFAULT_NUMBER_OF_REF);
        assertThat(testForwarderBooking.getTotalVolume()).isEqualTo(DEFAULT_TOTAL_VOLUME);
        assertThat(testForwarderBooking.getTotalWeight()).isEqualTo(DEFAULT_TOTAL_WEIGHT);
        assertThat(testForwarderBooking.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testForwarderBooking.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testForwarderBooking.getKingdeeUniqueId()).isEqualTo(UPDATED_KINGDEE_UNIQUE_ID);
    }

    @Test
    @Transactional
    void fullUpdateForwarderBookingWithPatch() throws Exception {
        // Initialize the database
        forwarderBookingRepository.saveAndFlush(forwarderBooking);

        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();

        // Update the forwarderBooking using partial update
        ForwarderBooking partialUpdatedForwarderBooking = new ForwarderBooking();
        partialUpdatedForwarderBooking.setId(forwarderBooking.getId());

        partialUpdatedForwarderBooking
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .orderDate(UPDATED_ORDER_DATE)
            .forwarder(UPDATED_FORWARDER)
            .totalQty(UPDATED_TOTAL_QTY)
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .containerType(UPDATED_CONTAINER_TYPE)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .containerNumber(UPDATED_CONTAINER_NUMBER)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .eta(UPDATED_ETA)
            .etd(UPDATED_ETD)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .numberOfCartons(UPDATED_NUMBER_OF_CARTONS)
            .numberOfRef(UPDATED_NUMBER_OF_REF)
            .totalVolume(UPDATED_TOTAL_VOLUME)
            .totalWeight(UPDATED_TOTAL_WEIGHT)
            .remark(UPDATED_REMARK)
            .client(UPDATED_CLIENT)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID);

        restForwarderBookingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedForwarderBooking.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedForwarderBooking))
            )
            .andExpect(status().isOk());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
        ForwarderBooking testForwarderBooking = forwarderBookingList.get(forwarderBookingList.size() - 1);
        assertThat(testForwarderBooking.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testForwarderBooking.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testForwarderBooking.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testForwarderBooking.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testForwarderBooking.getForwarder()).isEqualTo(UPDATED_FORWARDER);
        assertThat(testForwarderBooking.getTotalQty()).isEqualTo(UPDATED_TOTAL_QTY);
        assertThat(testForwarderBooking.getLoadingPort()).isEqualTo(UPDATED_LOADING_PORT);
        assertThat(testForwarderBooking.getDischargePort()).isEqualTo(UPDATED_DISCHARGE_PORT);
        assertThat(testForwarderBooking.getContainerType()).isEqualTo(UPDATED_CONTAINER_TYPE);
        assertThat(testForwarderBooking.getContainerSize()).isEqualTo(UPDATED_CONTAINER_SIZE);
        assertThat(testForwarderBooking.getContainerNumber()).isEqualTo(UPDATED_CONTAINER_NUMBER);
        assertThat(testForwarderBooking.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testForwarderBooking.getSupplierEmail()).isEqualTo(UPDATED_SUPPLIER_EMAIL);
        assertThat(testForwarderBooking.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testForwarderBooking.getEtd()).isEqualTo(UPDATED_ETD);
        assertThat(testForwarderBooking.getTransportMode()).isEqualTo(UPDATED_TRANSPORT_MODE);
        assertThat(testForwarderBooking.getNumberOfCartons()).isEqualTo(UPDATED_NUMBER_OF_CARTONS);
        assertThat(testForwarderBooking.getNumberOfRef()).isEqualTo(UPDATED_NUMBER_OF_REF);
        assertThat(testForwarderBooking.getTotalVolume()).isEqualTo(UPDATED_TOTAL_VOLUME);
        assertThat(testForwarderBooking.getTotalWeight()).isEqualTo(UPDATED_TOTAL_WEIGHT);
        assertThat(testForwarderBooking.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testForwarderBooking.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testForwarderBooking.getKingdeeUniqueId()).isEqualTo(UPDATED_KINGDEE_UNIQUE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingForwarderBooking() throws Exception {
        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();
        forwarderBooking.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restForwarderBookingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, forwarderBooking.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isBadRequest());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchForwarderBooking() throws Exception {
        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();
        forwarderBooking.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderBookingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isBadRequest());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamForwarderBooking() throws Exception {
        int databaseSizeBeforeUpdate = forwarderBookingRepository.findAll().size();
        forwarderBooking.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderBookingMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(forwarderBooking))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ForwarderBooking in the database
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteForwarderBooking() throws Exception {
        // Initialize the database
        forwarderBookingRepository.saveAndFlush(forwarderBooking);

        int databaseSizeBeforeDelete = forwarderBookingRepository.findAll().size();

        // Delete the forwarderBooking
        restForwarderBookingMockMvc
            .perform(delete(ENTITY_API_URL_ID, forwarderBooking.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ForwarderBooking> forwarderBookingList = forwarderBookingRepository.findAll();
        assertThat(forwarderBookingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
