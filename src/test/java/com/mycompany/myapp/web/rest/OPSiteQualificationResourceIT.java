package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OPSiteQualification;
import com.mycompany.myapp.repository.OPSiteQualificationRepository;
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
 * Integration tests for the {@link OPSiteQualificationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OPSiteQualificationResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OPERATION_SITE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTED_LO_R_FOR_THIS_SITE = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTED_LO_R_FOR_THIS_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_QUALIFICATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SITE_QUALIFICATION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CSR_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_CSR_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITY_PRODUCTION_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_QUALITY_PRODUCTION_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LIABILITY_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LIABILITY_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/op-site-qualifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OPSiteQualificationRepository oPSiteQualificationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOPSiteQualificationMockMvc;

    private OPSiteQualification oPSiteQualification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OPSiteQualification createEntity(EntityManager em) {
        OPSiteQualification oPSiteQualification = new OPSiteQualification()
            .itemName(DEFAULT_ITEM_NAME)
            .cateGory(DEFAULT_CATE_GORY)
            .supplier(DEFAULT_SUPPLIER)
            .date(DEFAULT_DATE)
            .operationSite(DEFAULT_OPERATION_SITE)
            .attributedLoRForThisSite(DEFAULT_ATTRIBUTED_LO_R_FOR_THIS_SITE)
            .siteQualificationStatus(DEFAULT_SITE_QUALIFICATION_STATUS)
            .csrResult(DEFAULT_CSR_RESULT)
            .qualityProductionResult(DEFAULT_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(DEFAULT_BUSINESS_LIABILITY_RESULT)
            .comments(DEFAULT_COMMENTS);
        return oPSiteQualification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OPSiteQualification createUpdatedEntity(EntityManager em) {
        OPSiteQualification oPSiteQualification = new OPSiteQualification()
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .date(UPDATED_DATE)
            .operationSite(UPDATED_OPERATION_SITE)
            .attributedLoRForThisSite(UPDATED_ATTRIBUTED_LO_R_FOR_THIS_SITE)
            .siteQualificationStatus(UPDATED_SITE_QUALIFICATION_STATUS)
            .csrResult(UPDATED_CSR_RESULT)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS);
        return oPSiteQualification;
    }

    @BeforeEach
    public void initTest() {
        oPSiteQualification = createEntity(em);
    }

    @Test
    @Transactional
    void createOPSiteQualification() throws Exception {
        int databaseSizeBeforeCreate = oPSiteQualificationRepository.findAll().size();
        // Create the OPSiteQualification
        restOPSiteQualificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isCreated());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeCreate + 1);
        OPSiteQualification testOPSiteQualification = oPSiteQualificationList.get(oPSiteQualificationList.size() - 1);
        assertThat(testOPSiteQualification.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testOPSiteQualification.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testOPSiteQualification.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testOPSiteQualification.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testOPSiteQualification.getOperationSite()).isEqualTo(DEFAULT_OPERATION_SITE);
        assertThat(testOPSiteQualification.getAttributedLoRForThisSite()).isEqualTo(DEFAULT_ATTRIBUTED_LO_R_FOR_THIS_SITE);
        assertThat(testOPSiteQualification.getSiteQualificationStatus()).isEqualTo(DEFAULT_SITE_QUALIFICATION_STATUS);
        assertThat(testOPSiteQualification.getCsrResult()).isEqualTo(DEFAULT_CSR_RESULT);
        assertThat(testOPSiteQualification.getQualityProductionResult()).isEqualTo(DEFAULT_QUALITY_PRODUCTION_RESULT);
        assertThat(testOPSiteQualification.getBusinessLiabilityResult()).isEqualTo(DEFAULT_BUSINESS_LIABILITY_RESULT);
        assertThat(testOPSiteQualification.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    void createOPSiteQualificationWithExistingId() throws Exception {
        // Create the OPSiteQualification with an existing ID
        oPSiteQualification.setId(1L);

        int databaseSizeBeforeCreate = oPSiteQualificationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOPSiteQualificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOPSiteQualifications() throws Exception {
        // Initialize the database
        oPSiteQualificationRepository.saveAndFlush(oPSiteQualification);

        // Get all the oPSiteQualificationList
        restOPSiteQualificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oPSiteQualification.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].operationSite").value(hasItem(DEFAULT_OPERATION_SITE)))
            .andExpect(jsonPath("$.[*].attributedLoRForThisSite").value(hasItem(DEFAULT_ATTRIBUTED_LO_R_FOR_THIS_SITE)))
            .andExpect(jsonPath("$.[*].siteQualificationStatus").value(hasItem(DEFAULT_SITE_QUALIFICATION_STATUS)))
            .andExpect(jsonPath("$.[*].csrResult").value(hasItem(DEFAULT_CSR_RESULT)))
            .andExpect(jsonPath("$.[*].qualityProductionResult").value(hasItem(DEFAULT_QUALITY_PRODUCTION_RESULT)))
            .andExpect(jsonPath("$.[*].businessLiabilityResult").value(hasItem(DEFAULT_BUSINESS_LIABILITY_RESULT)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)));
    }

    @Test
    @Transactional
    void getOPSiteQualification() throws Exception {
        // Initialize the database
        oPSiteQualificationRepository.saveAndFlush(oPSiteQualification);

        // Get the oPSiteQualification
        restOPSiteQualificationMockMvc
            .perform(get(ENTITY_API_URL_ID, oPSiteQualification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(oPSiteQualification.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.operationSite").value(DEFAULT_OPERATION_SITE))
            .andExpect(jsonPath("$.attributedLoRForThisSite").value(DEFAULT_ATTRIBUTED_LO_R_FOR_THIS_SITE))
            .andExpect(jsonPath("$.siteQualificationStatus").value(DEFAULT_SITE_QUALIFICATION_STATUS))
            .andExpect(jsonPath("$.csrResult").value(DEFAULT_CSR_RESULT))
            .andExpect(jsonPath("$.qualityProductionResult").value(DEFAULT_QUALITY_PRODUCTION_RESULT))
            .andExpect(jsonPath("$.businessLiabilityResult").value(DEFAULT_BUSINESS_LIABILITY_RESULT))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS));
    }

    @Test
    @Transactional
    void getNonExistingOPSiteQualification() throws Exception {
        // Get the oPSiteQualification
        restOPSiteQualificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOPSiteQualification() throws Exception {
        // Initialize the database
        oPSiteQualificationRepository.saveAndFlush(oPSiteQualification);

        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();

        // Update the oPSiteQualification
        OPSiteQualification updatedOPSiteQualification = oPSiteQualificationRepository.findById(oPSiteQualification.getId()).get();
        // Disconnect from session so that the updates on updatedOPSiteQualification are not directly saved in db
        em.detach(updatedOPSiteQualification);
        updatedOPSiteQualification
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .date(UPDATED_DATE)
            .operationSite(UPDATED_OPERATION_SITE)
            .attributedLoRForThisSite(UPDATED_ATTRIBUTED_LO_R_FOR_THIS_SITE)
            .siteQualificationStatus(UPDATED_SITE_QUALIFICATION_STATUS)
            .csrResult(UPDATED_CSR_RESULT)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS);

        restOPSiteQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOPSiteQualification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOPSiteQualification))
            )
            .andExpect(status().isOk());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
        OPSiteQualification testOPSiteQualification = oPSiteQualificationList.get(oPSiteQualificationList.size() - 1);
        assertThat(testOPSiteQualification.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOPSiteQualification.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOPSiteQualification.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testOPSiteQualification.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testOPSiteQualification.getOperationSite()).isEqualTo(UPDATED_OPERATION_SITE);
        assertThat(testOPSiteQualification.getAttributedLoRForThisSite()).isEqualTo(UPDATED_ATTRIBUTED_LO_R_FOR_THIS_SITE);
        assertThat(testOPSiteQualification.getSiteQualificationStatus()).isEqualTo(UPDATED_SITE_QUALIFICATION_STATUS);
        assertThat(testOPSiteQualification.getCsrResult()).isEqualTo(UPDATED_CSR_RESULT);
        assertThat(testOPSiteQualification.getQualityProductionResult()).isEqualTo(UPDATED_QUALITY_PRODUCTION_RESULT);
        assertThat(testOPSiteQualification.getBusinessLiabilityResult()).isEqualTo(UPDATED_BUSINESS_LIABILITY_RESULT);
        assertThat(testOPSiteQualification.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void putNonExistingOPSiteQualification() throws Exception {
        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();
        oPSiteQualification.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOPSiteQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, oPSiteQualification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOPSiteQualification() throws Exception {
        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();
        oPSiteQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOPSiteQualificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOPSiteQualification() throws Exception {
        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();
        oPSiteQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOPSiteQualificationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOPSiteQualificationWithPatch() throws Exception {
        // Initialize the database
        oPSiteQualificationRepository.saveAndFlush(oPSiteQualification);

        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();

        // Update the oPSiteQualification using partial update
        OPSiteQualification partialUpdatedOPSiteQualification = new OPSiteQualification();
        partialUpdatedOPSiteQualification.setId(oPSiteQualification.getId());

        partialUpdatedOPSiteQualification
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .siteQualificationStatus(UPDATED_SITE_QUALIFICATION_STATUS)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .comments(UPDATED_COMMENTS);

        restOPSiteQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOPSiteQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOPSiteQualification))
            )
            .andExpect(status().isOk());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
        OPSiteQualification testOPSiteQualification = oPSiteQualificationList.get(oPSiteQualificationList.size() - 1);
        assertThat(testOPSiteQualification.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testOPSiteQualification.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOPSiteQualification.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testOPSiteQualification.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testOPSiteQualification.getOperationSite()).isEqualTo(DEFAULT_OPERATION_SITE);
        assertThat(testOPSiteQualification.getAttributedLoRForThisSite()).isEqualTo(DEFAULT_ATTRIBUTED_LO_R_FOR_THIS_SITE);
        assertThat(testOPSiteQualification.getSiteQualificationStatus()).isEqualTo(UPDATED_SITE_QUALIFICATION_STATUS);
        assertThat(testOPSiteQualification.getCsrResult()).isEqualTo(DEFAULT_CSR_RESULT);
        assertThat(testOPSiteQualification.getQualityProductionResult()).isEqualTo(UPDATED_QUALITY_PRODUCTION_RESULT);
        assertThat(testOPSiteQualification.getBusinessLiabilityResult()).isEqualTo(DEFAULT_BUSINESS_LIABILITY_RESULT);
        assertThat(testOPSiteQualification.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void fullUpdateOPSiteQualificationWithPatch() throws Exception {
        // Initialize the database
        oPSiteQualificationRepository.saveAndFlush(oPSiteQualification);

        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();

        // Update the oPSiteQualification using partial update
        OPSiteQualification partialUpdatedOPSiteQualification = new OPSiteQualification();
        partialUpdatedOPSiteQualification.setId(oPSiteQualification.getId());

        partialUpdatedOPSiteQualification
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .date(UPDATED_DATE)
            .operationSite(UPDATED_OPERATION_SITE)
            .attributedLoRForThisSite(UPDATED_ATTRIBUTED_LO_R_FOR_THIS_SITE)
            .siteQualificationStatus(UPDATED_SITE_QUALIFICATION_STATUS)
            .csrResult(UPDATED_CSR_RESULT)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS);

        restOPSiteQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOPSiteQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOPSiteQualification))
            )
            .andExpect(status().isOk());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
        OPSiteQualification testOPSiteQualification = oPSiteQualificationList.get(oPSiteQualificationList.size() - 1);
        assertThat(testOPSiteQualification.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOPSiteQualification.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOPSiteQualification.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testOPSiteQualification.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testOPSiteQualification.getOperationSite()).isEqualTo(UPDATED_OPERATION_SITE);
        assertThat(testOPSiteQualification.getAttributedLoRForThisSite()).isEqualTo(UPDATED_ATTRIBUTED_LO_R_FOR_THIS_SITE);
        assertThat(testOPSiteQualification.getSiteQualificationStatus()).isEqualTo(UPDATED_SITE_QUALIFICATION_STATUS);
        assertThat(testOPSiteQualification.getCsrResult()).isEqualTo(UPDATED_CSR_RESULT);
        assertThat(testOPSiteQualification.getQualityProductionResult()).isEqualTo(UPDATED_QUALITY_PRODUCTION_RESULT);
        assertThat(testOPSiteQualification.getBusinessLiabilityResult()).isEqualTo(UPDATED_BUSINESS_LIABILITY_RESULT);
        assertThat(testOPSiteQualification.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void patchNonExistingOPSiteQualification() throws Exception {
        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();
        oPSiteQualification.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOPSiteQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, oPSiteQualification.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOPSiteQualification() throws Exception {
        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();
        oPSiteQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOPSiteQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isBadRequest());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOPSiteQualification() throws Exception {
        int databaseSizeBeforeUpdate = oPSiteQualificationRepository.findAll().size();
        oPSiteQualification.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOPSiteQualificationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oPSiteQualification))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OPSiteQualification in the database
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOPSiteQualification() throws Exception {
        // Initialize the database
        oPSiteQualificationRepository.saveAndFlush(oPSiteQualification);

        int databaseSizeBeforeDelete = oPSiteQualificationRepository.findAll().size();

        // Delete the oPSiteQualification
        restOPSiteQualificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, oPSiteQualification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OPSiteQualification> oPSiteQualificationList = oPSiteQualificationRepository.findAll();
        assertThat(oPSiteQualificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
