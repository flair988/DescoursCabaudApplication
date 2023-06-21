package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.PI;
import com.mycompany.myapp.repository.PIRepository;
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
 * Integration tests for the {@link PIResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PIResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PO_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PO_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUESTED_END_OF_PROD_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTED_END_OF_PROD_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_FINAL_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_FINAL_DESTINATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONSIGNEE = "AAAAAAAAAA";
    private static final String UPDATED_CONSIGNEE = "BBBBBBBBBB";

    private static final String DEFAULT_CARRIAGE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CARRIAGE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_TERMS_OF_DELIVERY = "AAAAAAAAAA";
    private static final String UPDATED_TERMS_OF_DELIVERY = "BBBBBBBBBB";

    private static final String DEFAULT_TERMS_OF_PAYMENT = "AAAAAAAAAA";
    private static final String UPDATED_TERMS_OF_PAYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PORT_OF_DISCHARGE = "AAAAAAAAAA";
    private static final String UPDATED_PORT_OF_DISCHARGE = "BBBBBBBBBB";

    private static final String DEFAULT_PORT_OF_LOADING = "AAAAAAAAAA";
    private static final String UPDATED_PORT_OF_LOADING = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PIRepository pIRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPIMockMvc;

    private PI pI;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PI createEntity(EntityManager em) {
        PI pI = new PI()
            .itemName(DEFAULT_ITEM_NAME)
            .poNumber(DEFAULT_PO_NUMBER)
            .category(DEFAULT_CATEGORY)
            .client(DEFAULT_CLIENT)
            .orderDate(DEFAULT_ORDER_DATE)
            .requestedEndOfProdDate(DEFAULT_REQUESTED_END_OF_PROD_DATE)
            .countryOfOrigin(DEFAULT_COUNTRY_OF_ORIGIN)
            .countryOfFinalDestination(DEFAULT_COUNTRY_OF_FINAL_DESTINATION)
            .consignee(DEFAULT_CONSIGNEE)
            .carriageBy(DEFAULT_CARRIAGE_BY)
            .termsOfDelivery(DEFAULT_TERMS_OF_DELIVERY)
            .termsOfPayment(DEFAULT_TERMS_OF_PAYMENT)
            .currency(DEFAULT_CURRENCY)
            .remarks(DEFAULT_REMARKS)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .portOfDischarge(DEFAULT_PORT_OF_DISCHARGE)
            .portOfLoading(DEFAULT_PORT_OF_LOADING)
            .docStatus(DEFAULT_DOC_STATUS);
        return pI;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PI createUpdatedEntity(EntityManager em) {
        PI pI = new PI()
            .itemName(UPDATED_ITEM_NAME)
            .poNumber(UPDATED_PO_NUMBER)
            .category(UPDATED_CATEGORY)
            .client(UPDATED_CLIENT)
            .orderDate(UPDATED_ORDER_DATE)
            .requestedEndOfProdDate(UPDATED_REQUESTED_END_OF_PROD_DATE)
            .countryOfOrigin(UPDATED_COUNTRY_OF_ORIGIN)
            .countryOfFinalDestination(UPDATED_COUNTRY_OF_FINAL_DESTINATION)
            .consignee(UPDATED_CONSIGNEE)
            .carriageBy(UPDATED_CARRIAGE_BY)
            .termsOfDelivery(UPDATED_TERMS_OF_DELIVERY)
            .termsOfPayment(UPDATED_TERMS_OF_PAYMENT)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .portOfDischarge(UPDATED_PORT_OF_DISCHARGE)
            .portOfLoading(UPDATED_PORT_OF_LOADING)
            .docStatus(UPDATED_DOC_STATUS);
        return pI;
    }

    @BeforeEach
    public void initTest() {
        pI = createEntity(em);
    }

    @Test
    @Transactional
    void createPI() throws Exception {
        int databaseSizeBeforeCreate = pIRepository.findAll().size();
        // Create the PI
        restPIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pI)))
            .andExpect(status().isCreated());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeCreate + 1);
        PI testPI = pIList.get(pIList.size() - 1);
        assertThat(testPI.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPI.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPI.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testPI.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testPI.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testPI.getRequestedEndOfProdDate()).isEqualTo(DEFAULT_REQUESTED_END_OF_PROD_DATE);
        assertThat(testPI.getCountryOfOrigin()).isEqualTo(DEFAULT_COUNTRY_OF_ORIGIN);
        assertThat(testPI.getCountryOfFinalDestination()).isEqualTo(DEFAULT_COUNTRY_OF_FINAL_DESTINATION);
        assertThat(testPI.getConsignee()).isEqualTo(DEFAULT_CONSIGNEE);
        assertThat(testPI.getCarriageBy()).isEqualTo(DEFAULT_CARRIAGE_BY);
        assertThat(testPI.getTermsOfDelivery()).isEqualTo(DEFAULT_TERMS_OF_DELIVERY);
        assertThat(testPI.getTermsOfPayment()).isEqualTo(DEFAULT_TERMS_OF_PAYMENT);
        assertThat(testPI.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testPI.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPI.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testPI.getPortOfDischarge()).isEqualTo(DEFAULT_PORT_OF_DISCHARGE);
        assertThat(testPI.getPortOfLoading()).isEqualTo(DEFAULT_PORT_OF_LOADING);
        assertThat(testPI.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
    }

    @Test
    @Transactional
    void createPIWithExistingId() throws Exception {
        // Create the PI with an existing ID
        pI.setId(1L);

        int databaseSizeBeforeCreate = pIRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pI)))
            .andExpect(status().isBadRequest());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPIS() throws Exception {
        // Initialize the database
        pIRepository.saveAndFlush(pI);

        // Get all the pIList
        restPIMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pI.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].poNumber").value(hasItem(DEFAULT_PO_NUMBER)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(DEFAULT_ORDER_DATE)))
            .andExpect(jsonPath("$.[*].requestedEndOfProdDate").value(hasItem(DEFAULT_REQUESTED_END_OF_PROD_DATE)))
            .andExpect(jsonPath("$.[*].countryOfOrigin").value(hasItem(DEFAULT_COUNTRY_OF_ORIGIN)))
            .andExpect(jsonPath("$.[*].countryOfFinalDestination").value(hasItem(DEFAULT_COUNTRY_OF_FINAL_DESTINATION)))
            .andExpect(jsonPath("$.[*].consignee").value(hasItem(DEFAULT_CONSIGNEE)))
            .andExpect(jsonPath("$.[*].carriageBy").value(hasItem(DEFAULT_CARRIAGE_BY)))
            .andExpect(jsonPath("$.[*].termsOfDelivery").value(hasItem(DEFAULT_TERMS_OF_DELIVERY)))
            .andExpect(jsonPath("$.[*].termsOfPayment").value(hasItem(DEFAULT_TERMS_OF_PAYMENT)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].portOfDischarge").value(hasItem(DEFAULT_PORT_OF_DISCHARGE)))
            .andExpect(jsonPath("$.[*].portOfLoading").value(hasItem(DEFAULT_PORT_OF_LOADING)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)));
    }

    @Test
    @Transactional
    void getPI() throws Exception {
        // Initialize the database
        pIRepository.saveAndFlush(pI);

        // Get the pI
        restPIMockMvc
            .perform(get(ENTITY_API_URL_ID, pI.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pI.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.poNumber").value(DEFAULT_PO_NUMBER))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE))
            .andExpect(jsonPath("$.requestedEndOfProdDate").value(DEFAULT_REQUESTED_END_OF_PROD_DATE))
            .andExpect(jsonPath("$.countryOfOrigin").value(DEFAULT_COUNTRY_OF_ORIGIN))
            .andExpect(jsonPath("$.countryOfFinalDestination").value(DEFAULT_COUNTRY_OF_FINAL_DESTINATION))
            .andExpect(jsonPath("$.consignee").value(DEFAULT_CONSIGNEE))
            .andExpect(jsonPath("$.carriageBy").value(DEFAULT_CARRIAGE_BY))
            .andExpect(jsonPath("$.termsOfDelivery").value(DEFAULT_TERMS_OF_DELIVERY))
            .andExpect(jsonPath("$.termsOfPayment").value(DEFAULT_TERMS_OF_PAYMENT))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.portOfDischarge").value(DEFAULT_PORT_OF_DISCHARGE))
            .andExpect(jsonPath("$.portOfLoading").value(DEFAULT_PORT_OF_LOADING))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingPI() throws Exception {
        // Get the pI
        restPIMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPI() throws Exception {
        // Initialize the database
        pIRepository.saveAndFlush(pI);

        int databaseSizeBeforeUpdate = pIRepository.findAll().size();

        // Update the pI
        PI updatedPI = pIRepository.findById(pI.getId()).get();
        // Disconnect from session so that the updates on updatedPI are not directly saved in db
        em.detach(updatedPI);
        updatedPI
            .itemName(UPDATED_ITEM_NAME)
            .poNumber(UPDATED_PO_NUMBER)
            .category(UPDATED_CATEGORY)
            .client(UPDATED_CLIENT)
            .orderDate(UPDATED_ORDER_DATE)
            .requestedEndOfProdDate(UPDATED_REQUESTED_END_OF_PROD_DATE)
            .countryOfOrigin(UPDATED_COUNTRY_OF_ORIGIN)
            .countryOfFinalDestination(UPDATED_COUNTRY_OF_FINAL_DESTINATION)
            .consignee(UPDATED_CONSIGNEE)
            .carriageBy(UPDATED_CARRIAGE_BY)
            .termsOfDelivery(UPDATED_TERMS_OF_DELIVERY)
            .termsOfPayment(UPDATED_TERMS_OF_PAYMENT)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .portOfDischarge(UPDATED_PORT_OF_DISCHARGE)
            .portOfLoading(UPDATED_PORT_OF_LOADING)
            .docStatus(UPDATED_DOC_STATUS);

        restPIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPI.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPI))
            )
            .andExpect(status().isOk());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
        PI testPI = pIList.get(pIList.size() - 1);
        assertThat(testPI.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPI.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPI.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testPI.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testPI.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testPI.getRequestedEndOfProdDate()).isEqualTo(UPDATED_REQUESTED_END_OF_PROD_DATE);
        assertThat(testPI.getCountryOfOrigin()).isEqualTo(UPDATED_COUNTRY_OF_ORIGIN);
        assertThat(testPI.getCountryOfFinalDestination()).isEqualTo(UPDATED_COUNTRY_OF_FINAL_DESTINATION);
        assertThat(testPI.getConsignee()).isEqualTo(UPDATED_CONSIGNEE);
        assertThat(testPI.getCarriageBy()).isEqualTo(UPDATED_CARRIAGE_BY);
        assertThat(testPI.getTermsOfDelivery()).isEqualTo(UPDATED_TERMS_OF_DELIVERY);
        assertThat(testPI.getTermsOfPayment()).isEqualTo(UPDATED_TERMS_OF_PAYMENT);
        assertThat(testPI.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPI.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPI.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testPI.getPortOfDischarge()).isEqualTo(UPDATED_PORT_OF_DISCHARGE);
        assertThat(testPI.getPortOfLoading()).isEqualTo(UPDATED_PORT_OF_LOADING);
        assertThat(testPI.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingPI() throws Exception {
        int databaseSizeBeforeUpdate = pIRepository.findAll().size();
        pI.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pI.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pI))
            )
            .andExpect(status().isBadRequest());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPI() throws Exception {
        int databaseSizeBeforeUpdate = pIRepository.findAll().size();
        pI.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pI))
            )
            .andExpect(status().isBadRequest());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPI() throws Exception {
        int databaseSizeBeforeUpdate = pIRepository.findAll().size();
        pI.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPIMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pI)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePIWithPatch() throws Exception {
        // Initialize the database
        pIRepository.saveAndFlush(pI);

        int databaseSizeBeforeUpdate = pIRepository.findAll().size();

        // Update the pI using partial update
        PI partialUpdatedPI = new PI();
        partialUpdatedPI.setId(pI.getId());

        partialUpdatedPI
            .itemName(UPDATED_ITEM_NAME)
            .category(UPDATED_CATEGORY)
            .requestedEndOfProdDate(UPDATED_REQUESTED_END_OF_PROD_DATE)
            .countryOfOrigin(UPDATED_COUNTRY_OF_ORIGIN)
            .consignee(UPDATED_CONSIGNEE)
            .termsOfPayment(UPDATED_TERMS_OF_PAYMENT)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .portOfDischarge(UPDATED_PORT_OF_DISCHARGE)
            .portOfLoading(UPDATED_PORT_OF_LOADING)
            .docStatus(UPDATED_DOC_STATUS);

        restPIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPI.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPI))
            )
            .andExpect(status().isOk());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
        PI testPI = pIList.get(pIList.size() - 1);
        assertThat(testPI.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPI.getPoNumber()).isEqualTo(DEFAULT_PO_NUMBER);
        assertThat(testPI.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testPI.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testPI.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testPI.getRequestedEndOfProdDate()).isEqualTo(UPDATED_REQUESTED_END_OF_PROD_DATE);
        assertThat(testPI.getCountryOfOrigin()).isEqualTo(UPDATED_COUNTRY_OF_ORIGIN);
        assertThat(testPI.getCountryOfFinalDestination()).isEqualTo(DEFAULT_COUNTRY_OF_FINAL_DESTINATION);
        assertThat(testPI.getConsignee()).isEqualTo(UPDATED_CONSIGNEE);
        assertThat(testPI.getCarriageBy()).isEqualTo(DEFAULT_CARRIAGE_BY);
        assertThat(testPI.getTermsOfDelivery()).isEqualTo(DEFAULT_TERMS_OF_DELIVERY);
        assertThat(testPI.getTermsOfPayment()).isEqualTo(UPDATED_TERMS_OF_PAYMENT);
        assertThat(testPI.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPI.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPI.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testPI.getPortOfDischarge()).isEqualTo(UPDATED_PORT_OF_DISCHARGE);
        assertThat(testPI.getPortOfLoading()).isEqualTo(UPDATED_PORT_OF_LOADING);
        assertThat(testPI.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
    }

    @Test
    @Transactional
    void fullUpdatePIWithPatch() throws Exception {
        // Initialize the database
        pIRepository.saveAndFlush(pI);

        int databaseSizeBeforeUpdate = pIRepository.findAll().size();

        // Update the pI using partial update
        PI partialUpdatedPI = new PI();
        partialUpdatedPI.setId(pI.getId());

        partialUpdatedPI
            .itemName(UPDATED_ITEM_NAME)
            .poNumber(UPDATED_PO_NUMBER)
            .category(UPDATED_CATEGORY)
            .client(UPDATED_CLIENT)
            .orderDate(UPDATED_ORDER_DATE)
            .requestedEndOfProdDate(UPDATED_REQUESTED_END_OF_PROD_DATE)
            .countryOfOrigin(UPDATED_COUNTRY_OF_ORIGIN)
            .countryOfFinalDestination(UPDATED_COUNTRY_OF_FINAL_DESTINATION)
            .consignee(UPDATED_CONSIGNEE)
            .carriageBy(UPDATED_CARRIAGE_BY)
            .termsOfDelivery(UPDATED_TERMS_OF_DELIVERY)
            .termsOfPayment(UPDATED_TERMS_OF_PAYMENT)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .portOfDischarge(UPDATED_PORT_OF_DISCHARGE)
            .portOfLoading(UPDATED_PORT_OF_LOADING)
            .docStatus(UPDATED_DOC_STATUS);

        restPIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPI.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPI))
            )
            .andExpect(status().isOk());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
        PI testPI = pIList.get(pIList.size() - 1);
        assertThat(testPI.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPI.getPoNumber()).isEqualTo(UPDATED_PO_NUMBER);
        assertThat(testPI.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testPI.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testPI.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testPI.getRequestedEndOfProdDate()).isEqualTo(UPDATED_REQUESTED_END_OF_PROD_DATE);
        assertThat(testPI.getCountryOfOrigin()).isEqualTo(UPDATED_COUNTRY_OF_ORIGIN);
        assertThat(testPI.getCountryOfFinalDestination()).isEqualTo(UPDATED_COUNTRY_OF_FINAL_DESTINATION);
        assertThat(testPI.getConsignee()).isEqualTo(UPDATED_CONSIGNEE);
        assertThat(testPI.getCarriageBy()).isEqualTo(UPDATED_CARRIAGE_BY);
        assertThat(testPI.getTermsOfDelivery()).isEqualTo(UPDATED_TERMS_OF_DELIVERY);
        assertThat(testPI.getTermsOfPayment()).isEqualTo(UPDATED_TERMS_OF_PAYMENT);
        assertThat(testPI.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPI.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPI.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testPI.getPortOfDischarge()).isEqualTo(UPDATED_PORT_OF_DISCHARGE);
        assertThat(testPI.getPortOfLoading()).isEqualTo(UPDATED_PORT_OF_LOADING);
        assertThat(testPI.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingPI() throws Exception {
        int databaseSizeBeforeUpdate = pIRepository.findAll().size();
        pI.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pI.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pI))
            )
            .andExpect(status().isBadRequest());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPI() throws Exception {
        int databaseSizeBeforeUpdate = pIRepository.findAll().size();
        pI.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pI))
            )
            .andExpect(status().isBadRequest());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPI() throws Exception {
        int databaseSizeBeforeUpdate = pIRepository.findAll().size();
        pI.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPIMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(pI)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PI in the database
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePI() throws Exception {
        // Initialize the database
        pIRepository.saveAndFlush(pI);

        int databaseSizeBeforeDelete = pIRepository.findAll().size();

        // Delete the pI
        restPIMockMvc.perform(delete(ENTITY_API_URL_ID, pI.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PI> pIList = pIRepository.findAll();
        assertThat(pIList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
