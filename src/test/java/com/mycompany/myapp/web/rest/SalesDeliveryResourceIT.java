package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.SalesDelivery;
import com.mycompany.myapp.repository.SalesDeliveryRepository;
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
 * Integration tests for the {@link SalesDeliveryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesDeliveryResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_LOADING_PORT = "AAAAAAAAAA";
    private static final String UPDATED_LOADING_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_DISCHARGE_PORT = "AAAAAAAAAA";
    private static final String UPDATED_DISCHARGE_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSPORT_MODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSPORT_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_INCOTERM = "AAAAAAAAAA";
    private static final String UPDATED_INCOTERM = "BBBBBBBBBB";

    private static final String DEFAULT_FORWARDER = "AAAAAAAAAA";
    private static final String UPDATED_FORWARDER = "BBBBBBBBBB";

    private static final String DEFAULT_ETA = "AAAAAAAAAA";
    private static final String UPDATED_ETA = "BBBBBBBBBB";

    private static final String DEFAULT_ETD = "AAAAAAAAAA";
    private static final String UPDATED_ETD = "BBBBBBBBBB";

    private static final String DEFAULT_CONTAINER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTAINER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTAINER_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_CONTAINER_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_UNIQUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_UNIQUE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/sales-deliveries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesDeliveryRepository salesDeliveryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesDeliveryMockMvc;

    private SalesDelivery salesDelivery;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesDelivery createEntity(EntityManager em) {
        SalesDelivery salesDelivery = new SalesDelivery()
            .itemName(DEFAULT_ITEM_NAME)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .customer(DEFAULT_CUSTOMER)
            .orderDate(DEFAULT_ORDER_DATE)
            .loadingPort(DEFAULT_LOADING_PORT)
            .dischargePort(DEFAULT_DISCHARGE_PORT)
            .transportMode(DEFAULT_TRANSPORT_MODE)
            .incoterm(DEFAULT_INCOTERM)
            .forwarder(DEFAULT_FORWARDER)
            .eta(DEFAULT_ETA)
            .etd(DEFAULT_ETD)
            .containerType(DEFAULT_CONTAINER_TYPE)
            .containerSize(DEFAULT_CONTAINER_SIZE)
            .remark(DEFAULT_REMARK)
            .kingdeeUniqueId(DEFAULT_KINGDEE_UNIQUE_ID)
            .docStatus(DEFAULT_DOC_STATUS)
            .cateGory(DEFAULT_CATE_GORY);
        return salesDelivery;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesDelivery createUpdatedEntity(EntityManager em) {
        SalesDelivery salesDelivery = new SalesDelivery()
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .orderDate(UPDATED_ORDER_DATE)
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .incoterm(UPDATED_INCOTERM)
            .forwarder(UPDATED_FORWARDER)
            .eta(UPDATED_ETA)
            .etd(UPDATED_ETD)
            .containerType(UPDATED_CONTAINER_TYPE)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .remark(UPDATED_REMARK)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID)
            .docStatus(UPDATED_DOC_STATUS)
            .cateGory(UPDATED_CATE_GORY);
        return salesDelivery;
    }

    @BeforeEach
    public void initTest() {
        salesDelivery = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesDelivery() throws Exception {
        int databaseSizeBeforeCreate = salesDeliveryRepository.findAll().size();
        // Create the SalesDelivery
        restSalesDeliveryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesDelivery)))
            .andExpect(status().isCreated());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeCreate + 1);
        SalesDelivery testSalesDelivery = salesDeliveryList.get(salesDeliveryList.size() - 1);
        assertThat(testSalesDelivery.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSalesDelivery.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testSalesDelivery.getCustomer()).isEqualTo(DEFAULT_CUSTOMER);
        assertThat(testSalesDelivery.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testSalesDelivery.getLoadingPort()).isEqualTo(DEFAULT_LOADING_PORT);
        assertThat(testSalesDelivery.getDischargePort()).isEqualTo(DEFAULT_DISCHARGE_PORT);
        assertThat(testSalesDelivery.getTransportMode()).isEqualTo(DEFAULT_TRANSPORT_MODE);
        assertThat(testSalesDelivery.getIncoterm()).isEqualTo(DEFAULT_INCOTERM);
        assertThat(testSalesDelivery.getForwarder()).isEqualTo(DEFAULT_FORWARDER);
        assertThat(testSalesDelivery.getEta()).isEqualTo(DEFAULT_ETA);
        assertThat(testSalesDelivery.getEtd()).isEqualTo(DEFAULT_ETD);
        assertThat(testSalesDelivery.getContainerType()).isEqualTo(DEFAULT_CONTAINER_TYPE);
        assertThat(testSalesDelivery.getContainerSize()).isEqualTo(DEFAULT_CONTAINER_SIZE);
        assertThat(testSalesDelivery.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSalesDelivery.getKingdeeUniqueId()).isEqualTo(DEFAULT_KINGDEE_UNIQUE_ID);
        assertThat(testSalesDelivery.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
        assertThat(testSalesDelivery.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
    }

    @Test
    @Transactional
    void createSalesDeliveryWithExistingId() throws Exception {
        // Create the SalesDelivery with an existing ID
        salesDelivery.setId(1L);

        int databaseSizeBeforeCreate = salesDeliveryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesDeliveryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesDelivery)))
            .andExpect(status().isBadRequest());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesDeliveries() throws Exception {
        // Initialize the database
        salesDeliveryRepository.saveAndFlush(salesDelivery);

        // Get all the salesDeliveryList
        restSalesDeliveryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesDelivery.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].customer").value(hasItem(DEFAULT_CUSTOMER)))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(DEFAULT_ORDER_DATE)))
            .andExpect(jsonPath("$.[*].loadingPort").value(hasItem(DEFAULT_LOADING_PORT)))
            .andExpect(jsonPath("$.[*].dischargePort").value(hasItem(DEFAULT_DISCHARGE_PORT)))
            .andExpect(jsonPath("$.[*].transportMode").value(hasItem(DEFAULT_TRANSPORT_MODE)))
            .andExpect(jsonPath("$.[*].incoterm").value(hasItem(DEFAULT_INCOTERM)))
            .andExpect(jsonPath("$.[*].forwarder").value(hasItem(DEFAULT_FORWARDER)))
            .andExpect(jsonPath("$.[*].eta").value(hasItem(DEFAULT_ETA)))
            .andExpect(jsonPath("$.[*].etd").value(hasItem(DEFAULT_ETD)))
            .andExpect(jsonPath("$.[*].containerType").value(hasItem(DEFAULT_CONTAINER_TYPE)))
            .andExpect(jsonPath("$.[*].containerSize").value(hasItem(DEFAULT_CONTAINER_SIZE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].kingdeeUniqueId").value(hasItem(DEFAULT_KINGDEE_UNIQUE_ID)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)));
    }

    @Test
    @Transactional
    void getSalesDelivery() throws Exception {
        // Initialize the database
        salesDeliveryRepository.saveAndFlush(salesDelivery);

        // Get the salesDelivery
        restSalesDeliveryMockMvc
            .perform(get(ENTITY_API_URL_ID, salesDelivery.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesDelivery.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.customer").value(DEFAULT_CUSTOMER))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE))
            .andExpect(jsonPath("$.loadingPort").value(DEFAULT_LOADING_PORT))
            .andExpect(jsonPath("$.dischargePort").value(DEFAULT_DISCHARGE_PORT))
            .andExpect(jsonPath("$.transportMode").value(DEFAULT_TRANSPORT_MODE))
            .andExpect(jsonPath("$.incoterm").value(DEFAULT_INCOTERM))
            .andExpect(jsonPath("$.forwarder").value(DEFAULT_FORWARDER))
            .andExpect(jsonPath("$.eta").value(DEFAULT_ETA))
            .andExpect(jsonPath("$.etd").value(DEFAULT_ETD))
            .andExpect(jsonPath("$.containerType").value(DEFAULT_CONTAINER_TYPE))
            .andExpect(jsonPath("$.containerSize").value(DEFAULT_CONTAINER_SIZE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.kingdeeUniqueId").value(DEFAULT_KINGDEE_UNIQUE_ID))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY));
    }

    @Test
    @Transactional
    void getNonExistingSalesDelivery() throws Exception {
        // Get the salesDelivery
        restSalesDeliveryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesDelivery() throws Exception {
        // Initialize the database
        salesDeliveryRepository.saveAndFlush(salesDelivery);

        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();

        // Update the salesDelivery
        SalesDelivery updatedSalesDelivery = salesDeliveryRepository.findById(salesDelivery.getId()).get();
        // Disconnect from session so that the updates on updatedSalesDelivery are not directly saved in db
        em.detach(updatedSalesDelivery);
        updatedSalesDelivery
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .orderDate(UPDATED_ORDER_DATE)
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .incoterm(UPDATED_INCOTERM)
            .forwarder(UPDATED_FORWARDER)
            .eta(UPDATED_ETA)
            .etd(UPDATED_ETD)
            .containerType(UPDATED_CONTAINER_TYPE)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .remark(UPDATED_REMARK)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID)
            .docStatus(UPDATED_DOC_STATUS)
            .cateGory(UPDATED_CATE_GORY);

        restSalesDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSalesDelivery.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSalesDelivery))
            )
            .andExpect(status().isOk());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
        SalesDelivery testSalesDelivery = salesDeliveryList.get(salesDeliveryList.size() - 1);
        assertThat(testSalesDelivery.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSalesDelivery.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testSalesDelivery.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testSalesDelivery.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testSalesDelivery.getLoadingPort()).isEqualTo(UPDATED_LOADING_PORT);
        assertThat(testSalesDelivery.getDischargePort()).isEqualTo(UPDATED_DISCHARGE_PORT);
        assertThat(testSalesDelivery.getTransportMode()).isEqualTo(UPDATED_TRANSPORT_MODE);
        assertThat(testSalesDelivery.getIncoterm()).isEqualTo(UPDATED_INCOTERM);
        assertThat(testSalesDelivery.getForwarder()).isEqualTo(UPDATED_FORWARDER);
        assertThat(testSalesDelivery.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testSalesDelivery.getEtd()).isEqualTo(UPDATED_ETD);
        assertThat(testSalesDelivery.getContainerType()).isEqualTo(UPDATED_CONTAINER_TYPE);
        assertThat(testSalesDelivery.getContainerSize()).isEqualTo(UPDATED_CONTAINER_SIZE);
        assertThat(testSalesDelivery.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSalesDelivery.getKingdeeUniqueId()).isEqualTo(UPDATED_KINGDEE_UNIQUE_ID);
        assertThat(testSalesDelivery.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testSalesDelivery.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
    }

    @Test
    @Transactional
    void putNonExistingSalesDelivery() throws Exception {
        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();
        salesDelivery.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesDelivery.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesDelivery))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesDelivery() throws Exception {
        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();
        salesDelivery.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesDeliveryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesDelivery))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesDelivery() throws Exception {
        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();
        salesDelivery.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesDeliveryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesDelivery)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesDeliveryWithPatch() throws Exception {
        // Initialize the database
        salesDeliveryRepository.saveAndFlush(salesDelivery);

        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();

        // Update the salesDelivery using partial update
        SalesDelivery partialUpdatedSalesDelivery = new SalesDelivery();
        partialUpdatedSalesDelivery.setId(salesDelivery.getId());

        partialUpdatedSalesDelivery
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .incoterm(UPDATED_INCOTERM)
            .eta(UPDATED_ETA)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .remark(UPDATED_REMARK)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID)
            .docStatus(UPDATED_DOC_STATUS)
            .cateGory(UPDATED_CATE_GORY);

        restSalesDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesDelivery.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesDelivery))
            )
            .andExpect(status().isOk());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
        SalesDelivery testSalesDelivery = salesDeliveryList.get(salesDeliveryList.size() - 1);
        assertThat(testSalesDelivery.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSalesDelivery.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testSalesDelivery.getCustomer()).isEqualTo(DEFAULT_CUSTOMER);
        assertThat(testSalesDelivery.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testSalesDelivery.getLoadingPort()).isEqualTo(UPDATED_LOADING_PORT);
        assertThat(testSalesDelivery.getDischargePort()).isEqualTo(UPDATED_DISCHARGE_PORT);
        assertThat(testSalesDelivery.getTransportMode()).isEqualTo(UPDATED_TRANSPORT_MODE);
        assertThat(testSalesDelivery.getIncoterm()).isEqualTo(UPDATED_INCOTERM);
        assertThat(testSalesDelivery.getForwarder()).isEqualTo(DEFAULT_FORWARDER);
        assertThat(testSalesDelivery.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testSalesDelivery.getEtd()).isEqualTo(DEFAULT_ETD);
        assertThat(testSalesDelivery.getContainerType()).isEqualTo(DEFAULT_CONTAINER_TYPE);
        assertThat(testSalesDelivery.getContainerSize()).isEqualTo(UPDATED_CONTAINER_SIZE);
        assertThat(testSalesDelivery.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSalesDelivery.getKingdeeUniqueId()).isEqualTo(UPDATED_KINGDEE_UNIQUE_ID);
        assertThat(testSalesDelivery.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testSalesDelivery.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
    }

    @Test
    @Transactional
    void fullUpdateSalesDeliveryWithPatch() throws Exception {
        // Initialize the database
        salesDeliveryRepository.saveAndFlush(salesDelivery);

        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();

        // Update the salesDelivery using partial update
        SalesDelivery partialUpdatedSalesDelivery = new SalesDelivery();
        partialUpdatedSalesDelivery.setId(salesDelivery.getId());

        partialUpdatedSalesDelivery
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .customer(UPDATED_CUSTOMER)
            .orderDate(UPDATED_ORDER_DATE)
            .loadingPort(UPDATED_LOADING_PORT)
            .dischargePort(UPDATED_DISCHARGE_PORT)
            .transportMode(UPDATED_TRANSPORT_MODE)
            .incoterm(UPDATED_INCOTERM)
            .forwarder(UPDATED_FORWARDER)
            .eta(UPDATED_ETA)
            .etd(UPDATED_ETD)
            .containerType(UPDATED_CONTAINER_TYPE)
            .containerSize(UPDATED_CONTAINER_SIZE)
            .remark(UPDATED_REMARK)
            .kingdeeUniqueId(UPDATED_KINGDEE_UNIQUE_ID)
            .docStatus(UPDATED_DOC_STATUS)
            .cateGory(UPDATED_CATE_GORY);

        restSalesDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesDelivery.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesDelivery))
            )
            .andExpect(status().isOk());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
        SalesDelivery testSalesDelivery = salesDeliveryList.get(salesDeliveryList.size() - 1);
        assertThat(testSalesDelivery.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSalesDelivery.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testSalesDelivery.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testSalesDelivery.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testSalesDelivery.getLoadingPort()).isEqualTo(UPDATED_LOADING_PORT);
        assertThat(testSalesDelivery.getDischargePort()).isEqualTo(UPDATED_DISCHARGE_PORT);
        assertThat(testSalesDelivery.getTransportMode()).isEqualTo(UPDATED_TRANSPORT_MODE);
        assertThat(testSalesDelivery.getIncoterm()).isEqualTo(UPDATED_INCOTERM);
        assertThat(testSalesDelivery.getForwarder()).isEqualTo(UPDATED_FORWARDER);
        assertThat(testSalesDelivery.getEta()).isEqualTo(UPDATED_ETA);
        assertThat(testSalesDelivery.getEtd()).isEqualTo(UPDATED_ETD);
        assertThat(testSalesDelivery.getContainerType()).isEqualTo(UPDATED_CONTAINER_TYPE);
        assertThat(testSalesDelivery.getContainerSize()).isEqualTo(UPDATED_CONTAINER_SIZE);
        assertThat(testSalesDelivery.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSalesDelivery.getKingdeeUniqueId()).isEqualTo(UPDATED_KINGDEE_UNIQUE_ID);
        assertThat(testSalesDelivery.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testSalesDelivery.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
    }

    @Test
    @Transactional
    void patchNonExistingSalesDelivery() throws Exception {
        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();
        salesDelivery.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesDelivery.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesDelivery))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesDelivery() throws Exception {
        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();
        salesDelivery.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesDelivery))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesDelivery() throws Exception {
        int databaseSizeBeforeUpdate = salesDeliveryRepository.findAll().size();
        salesDelivery.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesDeliveryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(salesDelivery))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesDelivery in the database
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesDelivery() throws Exception {
        // Initialize the database
        salesDeliveryRepository.saveAndFlush(salesDelivery);

        int databaseSizeBeforeDelete = salesDeliveryRepository.findAll().size();

        // Delete the salesDelivery
        restSalesDeliveryMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesDelivery.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesDelivery> salesDeliveryList = salesDeliveryRepository.findAll();
        assertThat(salesDeliveryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
