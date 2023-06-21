package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Quotation;
import com.mycompany.myapp.repository.QuotationRepository;
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
 * Integration tests for the {@link QuotationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QuotationResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_QUOTATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_QUOTATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_INQUIRY_DOC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INQUIRY_DOC_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_NO = "AAAAAAAAAA";
    private static final String UPDATED_DOC_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/quotations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuotationMockMvc;

    private Quotation quotation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quotation createEntity(EntityManager em) {
        Quotation quotation = new Quotation()
            .itemName(DEFAULT_ITEM_NAME)
            .quotationDate(DEFAULT_QUOTATION_DATE)
            .inquiryDocNumber(DEFAULT_INQUIRY_DOC_NUMBER)
            .docNo(DEFAULT_DOC_NO)
            .docStatus(DEFAULT_DOC_STATUS)
            .supplier(DEFAULT_SUPPLIER)
            .kingdeeId(DEFAULT_KINGDEE_ID);
        return quotation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quotation createUpdatedEntity(EntityManager em) {
        Quotation quotation = new Quotation()
            .itemName(UPDATED_ITEM_NAME)
            .quotationDate(UPDATED_QUOTATION_DATE)
            .inquiryDocNumber(UPDATED_INQUIRY_DOC_NUMBER)
            .docNo(UPDATED_DOC_NO)
            .docStatus(UPDATED_DOC_STATUS)
            .supplier(UPDATED_SUPPLIER)
            .kingdeeId(UPDATED_KINGDEE_ID);
        return quotation;
    }

    @BeforeEach
    public void initTest() {
        quotation = createEntity(em);
    }

    @Test
    @Transactional
    void createQuotation() throws Exception {
        int databaseSizeBeforeCreate = quotationRepository.findAll().size();
        // Create the Quotation
        restQuotationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quotation)))
            .andExpect(status().isCreated());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeCreate + 1);
        Quotation testQuotation = quotationList.get(quotationList.size() - 1);
        assertThat(testQuotation.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testQuotation.getQuotationDate()).isEqualTo(DEFAULT_QUOTATION_DATE);
        assertThat(testQuotation.getInquiryDocNumber()).isEqualTo(DEFAULT_INQUIRY_DOC_NUMBER);
        assertThat(testQuotation.getDocNo()).isEqualTo(DEFAULT_DOC_NO);
        assertThat(testQuotation.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
        assertThat(testQuotation.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testQuotation.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
    }

    @Test
    @Transactional
    void createQuotationWithExistingId() throws Exception {
        // Create the Quotation with an existing ID
        quotation.setId(1L);

        int databaseSizeBeforeCreate = quotationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuotationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quotation)))
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQuotations() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        // Get all the quotationList
        restQuotationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotation.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].quotationDate").value(hasItem(DEFAULT_QUOTATION_DATE)))
            .andExpect(jsonPath("$.[*].inquiryDocNumber").value(hasItem(DEFAULT_INQUIRY_DOC_NUMBER)))
            .andExpect(jsonPath("$.[*].docNo").value(hasItem(DEFAULT_DOC_NO)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)));
    }

    @Test
    @Transactional
    void getQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        // Get the quotation
        restQuotationMockMvc
            .perform(get(ENTITY_API_URL_ID, quotation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(quotation.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.quotationDate").value(DEFAULT_QUOTATION_DATE))
            .andExpect(jsonPath("$.inquiryDocNumber").value(DEFAULT_INQUIRY_DOC_NUMBER))
            .andExpect(jsonPath("$.docNo").value(DEFAULT_DOC_NO))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID));
    }

    @Test
    @Transactional
    void getNonExistingQuotation() throws Exception {
        // Get the quotation
        restQuotationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();

        // Update the quotation
        Quotation updatedQuotation = quotationRepository.findById(quotation.getId()).get();
        // Disconnect from session so that the updates on updatedQuotation are not directly saved in db
        em.detach(updatedQuotation);
        updatedQuotation
            .itemName(UPDATED_ITEM_NAME)
            .quotationDate(UPDATED_QUOTATION_DATE)
            .inquiryDocNumber(UPDATED_INQUIRY_DOC_NUMBER)
            .docNo(UPDATED_DOC_NO)
            .docStatus(UPDATED_DOC_STATUS)
            .supplier(UPDATED_SUPPLIER)
            .kingdeeId(UPDATED_KINGDEE_ID);

        restQuotationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQuotation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedQuotation))
            )
            .andExpect(status().isOk());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
        Quotation testQuotation = quotationList.get(quotationList.size() - 1);
        assertThat(testQuotation.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testQuotation.getQuotationDate()).isEqualTo(UPDATED_QUOTATION_DATE);
        assertThat(testQuotation.getInquiryDocNumber()).isEqualTo(UPDATED_INQUIRY_DOC_NUMBER);
        assertThat(testQuotation.getDocNo()).isEqualTo(UPDATED_DOC_NO);
        assertThat(testQuotation.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testQuotation.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testQuotation.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
    }

    @Test
    @Transactional
    void putNonExistingQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();
        quotation.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuotationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, quotation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quotation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();
        quotation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuotationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(quotation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();
        quotation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuotationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(quotation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQuotationWithPatch() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();

        // Update the quotation using partial update
        Quotation partialUpdatedQuotation = new Quotation();
        partialUpdatedQuotation.setId(quotation.getId());

        partialUpdatedQuotation
            .itemName(UPDATED_ITEM_NAME)
            .quotationDate(UPDATED_QUOTATION_DATE)
            .docStatus(UPDATED_DOC_STATUS)
            .supplier(UPDATED_SUPPLIER)
            .kingdeeId(UPDATED_KINGDEE_ID);

        restQuotationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuotation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuotation))
            )
            .andExpect(status().isOk());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
        Quotation testQuotation = quotationList.get(quotationList.size() - 1);
        assertThat(testQuotation.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testQuotation.getQuotationDate()).isEqualTo(UPDATED_QUOTATION_DATE);
        assertThat(testQuotation.getInquiryDocNumber()).isEqualTo(DEFAULT_INQUIRY_DOC_NUMBER);
        assertThat(testQuotation.getDocNo()).isEqualTo(DEFAULT_DOC_NO);
        assertThat(testQuotation.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testQuotation.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testQuotation.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
    }

    @Test
    @Transactional
    void fullUpdateQuotationWithPatch() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();

        // Update the quotation using partial update
        Quotation partialUpdatedQuotation = new Quotation();
        partialUpdatedQuotation.setId(quotation.getId());

        partialUpdatedQuotation
            .itemName(UPDATED_ITEM_NAME)
            .quotationDate(UPDATED_QUOTATION_DATE)
            .inquiryDocNumber(UPDATED_INQUIRY_DOC_NUMBER)
            .docNo(UPDATED_DOC_NO)
            .docStatus(UPDATED_DOC_STATUS)
            .supplier(UPDATED_SUPPLIER)
            .kingdeeId(UPDATED_KINGDEE_ID);

        restQuotationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQuotation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedQuotation))
            )
            .andExpect(status().isOk());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
        Quotation testQuotation = quotationList.get(quotationList.size() - 1);
        assertThat(testQuotation.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testQuotation.getQuotationDate()).isEqualTo(UPDATED_QUOTATION_DATE);
        assertThat(testQuotation.getInquiryDocNumber()).isEqualTo(UPDATED_INQUIRY_DOC_NUMBER);
        assertThat(testQuotation.getDocNo()).isEqualTo(UPDATED_DOC_NO);
        assertThat(testQuotation.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testQuotation.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testQuotation.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();
        quotation.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuotationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, quotation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(quotation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();
        quotation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuotationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(quotation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();
        quotation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQuotationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(quotation))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        int databaseSizeBeforeDelete = quotationRepository.findAll().size();

        // Delete the quotation
        restQuotationMockMvc
            .perform(delete(ENTITY_API_URL_ID, quotation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
