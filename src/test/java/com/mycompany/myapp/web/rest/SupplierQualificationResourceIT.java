package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.SupplierQualification;
import com.mycompany.myapp.repository.SupplierQualificationRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link SupplierQualificationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SupplierQualificationResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SUPPLIER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EVALUATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EVALUATION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LIABILITY_BOPE_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LIABILITY_BOPE_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/supplier-qualifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SupplierQualificationRepository supplierQualificationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSupplierQualificationMockMvc;

    private SupplierQualification supplierQualification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupplierQualification createEntity(EntityManager em) {
        SupplierQualification supplierQualification = new SupplierQualification()
            .itemName(DEFAULT_ITEM_NAME)
            .cateGory(DEFAULT_CATE_GORY)
            .supplier(DEFAULT_SUPPLIER)
            .date(DEFAULT_DATE)
            .supplierStatus(DEFAULT_SUPPLIER_STATUS)
            .evaluationStatus(DEFAULT_EVALUATION_STATUS)
            .businessLiabilityBopeScore(DEFAULT_BUSINESS_LIABILITY_BOPE_SCORE)
            .comments(DEFAULT_COMMENTS);
        return supplierQualification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupplierQualification createUpdatedEntity(EntityManager em) {
        SupplierQualification supplierQualification = new SupplierQualification()
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .date(UPDATED_DATE)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .evaluationStatus(UPDATED_EVALUATION_STATUS)
            .businessLiabilityBopeScore(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE)
            .comments(UPDATED_COMMENTS);
        return supplierQualification;
    }

    @BeforeEach
    public void initTest() {
        supplierQualification = createEntity(em);
    }

    @Test
    @Transactional
    void createSupplierQualification() throws Exception {
        int databaseSizeBeforeCreate = supplierQualificationRepository.findAll().size();
        // Create the SupplierQualification
        restSupplierQualificationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isCreated());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeCreate + 1);
        SupplierQualification testSupplierQualification = supplierQualificationList.get(supplierQualificationList.size() - 1);
        assertThat(testSupplierQualification.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testSupplierQualification.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testSupplierQualification.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testSupplierQualification.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSupplierQualification.getSupplierStatus()).isEqualTo(DEFAULT_SUPPLIER_STATUS);
        assertThat(testSupplierQualification.getEvaluationStatus()).isEqualTo(DEFAULT_EVALUATION_STATUS);
        assertThat(testSupplierQualification.getBusinessLiabilityBopeScore()).isEqualTo(DEFAULT_BUSINESS_LIABILITY_BOPE_SCORE);
        assertThat(testSupplierQualification.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    void createSupplierQualificationWithExistingId() throws Exception {
        // Create the SupplierQualification with an existing ID
        supplierQualification.setId(1L);

        int databaseSizeBeforeCreate = supplierQualificationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierQualificationMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSupplierQualifications() throws Exception {
        // Initialize the database
        supplierQualificationRepository.saveAndFlush(supplierQualification);

        // Get all the supplierQualificationList
        restSupplierQualificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplierQualification.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].supplierStatus").value(hasItem(DEFAULT_SUPPLIER_STATUS)))
            .andExpect(jsonPath("$.[*].evaluationStatus").value(hasItem(DEFAULT_EVALUATION_STATUS)))
            .andExpect(jsonPath("$.[*].businessLiabilityBopeScore").value(hasItem(DEFAULT_BUSINESS_LIABILITY_BOPE_SCORE)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)));
    }

    @Test
    @Transactional
    void getSupplierQualification() throws Exception {
        // Initialize the database
        supplierQualificationRepository.saveAndFlush(supplierQualification);

        // Get the supplierQualification
        restSupplierQualificationMockMvc
            .perform(get(ENTITY_API_URL_ID, supplierQualification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(supplierQualification.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.supplierStatus").value(DEFAULT_SUPPLIER_STATUS))
            .andExpect(jsonPath("$.evaluationStatus").value(DEFAULT_EVALUATION_STATUS))
            .andExpect(jsonPath("$.businessLiabilityBopeScore").value(DEFAULT_BUSINESS_LIABILITY_BOPE_SCORE))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS));
    }

    @Test
    @Transactional
    void getNonExistingSupplierQualification() throws Exception {
        // Get the supplierQualification
        restSupplierQualificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSupplierQualification() throws Exception {
        // Initialize the database
        supplierQualificationRepository.saveAndFlush(supplierQualification);

        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();

        // Update the supplierQualification
        SupplierQualification updatedSupplierQualification = supplierQualificationRepository.findById(supplierQualification.getId()).get();
        // Disconnect from session so that the updates on updatedSupplierQualification are not directly saved in db
        em.detach(updatedSupplierQualification);
        updatedSupplierQualification
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .date(UPDATED_DATE)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .evaluationStatus(UPDATED_EVALUATION_STATUS)
            .businessLiabilityBopeScore(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE)
            .comments(UPDATED_COMMENTS);

        restSupplierQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSupplierQualification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSupplierQualification))
            )
            .andExpect(status().isOk());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
        SupplierQualification testSupplierQualification = supplierQualificationList.get(supplierQualificationList.size() - 1);
        assertThat(testSupplierQualification.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSupplierQualification.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testSupplierQualification.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testSupplierQualification.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSupplierQualification.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplierQualification.getEvaluationStatus()).isEqualTo(UPDATED_EVALUATION_STATUS);
        assertThat(testSupplierQualification.getBusinessLiabilityBopeScore()).isEqualTo(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE);
        assertThat(testSupplierQualification.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void putNonExistingSupplierQualification() throws Exception {
        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();
        supplierQualification.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplierQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, supplierQualification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSupplierQualification() throws Exception {
        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();
        supplierQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSupplierQualification() throws Exception {
        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();
        supplierQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierQualificationMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSupplierQualificationWithPatch() throws Exception {
        // Initialize the database
        supplierQualificationRepository.saveAndFlush(supplierQualification);

        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();

        // Update the supplierQualification using partial update
        SupplierQualification partialUpdatedSupplierQualification = new SupplierQualification();
        partialUpdatedSupplierQualification.setId(supplierQualification.getId());

        partialUpdatedSupplierQualification
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .evaluationStatus(UPDATED_EVALUATION_STATUS)
            .businessLiabilityBopeScore(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE);

        restSupplierQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSupplierQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSupplierQualification))
            )
            .andExpect(status().isOk());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
        SupplierQualification testSupplierQualification = supplierQualificationList.get(supplierQualificationList.size() - 1);
        assertThat(testSupplierQualification.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSupplierQualification.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testSupplierQualification.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testSupplierQualification.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSupplierQualification.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplierQualification.getEvaluationStatus()).isEqualTo(UPDATED_EVALUATION_STATUS);
        assertThat(testSupplierQualification.getBusinessLiabilityBopeScore()).isEqualTo(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE);
        assertThat(testSupplierQualification.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    void fullUpdateSupplierQualificationWithPatch() throws Exception {
        // Initialize the database
        supplierQualificationRepository.saveAndFlush(supplierQualification);

        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();

        // Update the supplierQualification using partial update
        SupplierQualification partialUpdatedSupplierQualification = new SupplierQualification();
        partialUpdatedSupplierQualification.setId(supplierQualification.getId());

        partialUpdatedSupplierQualification
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .date(UPDATED_DATE)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .evaluationStatus(UPDATED_EVALUATION_STATUS)
            .businessLiabilityBopeScore(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE)
            .comments(UPDATED_COMMENTS);

        restSupplierQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSupplierQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSupplierQualification))
            )
            .andExpect(status().isOk());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
        SupplierQualification testSupplierQualification = supplierQualificationList.get(supplierQualificationList.size() - 1);
        assertThat(testSupplierQualification.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testSupplierQualification.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testSupplierQualification.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testSupplierQualification.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSupplierQualification.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplierQualification.getEvaluationStatus()).isEqualTo(UPDATED_EVALUATION_STATUS);
        assertThat(testSupplierQualification.getBusinessLiabilityBopeScore()).isEqualTo(UPDATED_BUSINESS_LIABILITY_BOPE_SCORE);
        assertThat(testSupplierQualification.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void patchNonExistingSupplierQualification() throws Exception {
        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();
        supplierQualification.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplierQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, supplierQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSupplierQualification() throws Exception {
        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();
        supplierQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSupplierQualification() throws Exception {
        int databaseSizeBeforeUpdate = supplierQualificationRepository.findAll().size();
        supplierQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplierQualification))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SupplierQualification in the database
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSupplierQualification() throws Exception {
        // Initialize the database
        supplierQualificationRepository.saveAndFlush(supplierQualification);

        int databaseSizeBeforeDelete = supplierQualificationRepository.findAll().size();

        // Delete the supplierQualification
        restSupplierQualificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, supplierQualification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SupplierQualification> supplierQualificationList = supplierQualificationRepository.findAll();
        assertThat(supplierQualificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
