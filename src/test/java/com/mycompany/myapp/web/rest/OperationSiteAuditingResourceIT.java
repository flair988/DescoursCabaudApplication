package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OperationSiteAuditing;
import com.mycompany.myapp.repository.OperationSiteAuditingRepository;
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
 * Integration tests for the {@link OperationSiteAuditingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OperationSiteAuditingResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATION_SITE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_SUPPLIER_FACTORY = "AAAAAAAAAA";
    private static final String UPDATED_LINK_SUPPLIER_FACTORY = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_SITE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_AUDITING_TOOL = "AAAAAAAAAA";
    private static final String UPDATED_AUDITING_TOOL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_AUDITING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUDITING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CSR_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_CSR_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITY_PRODUCTION_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_QUALITY_PRODUCTION_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LIABILITY_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LIABILITY_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ISSUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISSUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CLOSED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CLOSED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLOSED = "AAAAAAAAAA";
    private static final String UPDATED_CLOSED = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CAP_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_CAP_COMMENTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/operation-site-auditings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OperationSiteAuditingRepository operationSiteAuditingRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOperationSiteAuditingMockMvc;

    private OperationSiteAuditing operationSiteAuditing;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperationSiteAuditing createEntity(EntityManager em) {
        OperationSiteAuditing operationSiteAuditing = new OperationSiteAuditing()
            .itemName(DEFAULT_ITEM_NAME)
            .cateGory(DEFAULT_CATE_GORY)
            .supplier(DEFAULT_SUPPLIER)
            .operationSite(DEFAULT_OPERATION_SITE)
            .linkSupplierFactory(DEFAULT_LINK_SUPPLIER_FACTORY)
            .typeOfSite(DEFAULT_TYPE_OF_SITE)
            .auditingTool(DEFAULT_AUDITING_TOOL)
            .auditingDate(DEFAULT_AUDITING_DATE)
            .csrResult(DEFAULT_CSR_RESULT)
            .qualityProductionResult(DEFAULT_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(DEFAULT_BUSINESS_LIABILITY_RESULT)
            .comments(DEFAULT_COMMENTS)
            .issueDate(DEFAULT_ISSUE_DATE)
            .dueDate(DEFAULT_DUE_DATE)
            .closedDate(DEFAULT_CLOSED_DATE)
            .closed(DEFAULT_CLOSED)
            .status(DEFAULT_STATUS)
            .capComments(DEFAULT_CAP_COMMENTS);
        return operationSiteAuditing;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperationSiteAuditing createUpdatedEntity(EntityManager em) {
        OperationSiteAuditing operationSiteAuditing = new OperationSiteAuditing()
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .operationSite(UPDATED_OPERATION_SITE)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .auditingTool(UPDATED_AUDITING_TOOL)
            .auditingDate(UPDATED_AUDITING_DATE)
            .csrResult(UPDATED_CSR_RESULT)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS)
            .issueDate(UPDATED_ISSUE_DATE)
            .dueDate(UPDATED_DUE_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .closed(UPDATED_CLOSED)
            .status(UPDATED_STATUS)
            .capComments(UPDATED_CAP_COMMENTS);
        return operationSiteAuditing;
    }

    @BeforeEach
    public void initTest() {
        operationSiteAuditing = createEntity(em);
    }

    @Test
    @Transactional
    void createOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeCreate = operationSiteAuditingRepository.findAll().size();
        // Create the OperationSiteAuditing
        restOperationSiteAuditingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isCreated());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeCreate + 1);
        OperationSiteAuditing testOperationSiteAuditing = operationSiteAuditingList.get(operationSiteAuditingList.size() - 1);
        assertThat(testOperationSiteAuditing.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testOperationSiteAuditing.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testOperationSiteAuditing.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testOperationSiteAuditing.getOperationSite()).isEqualTo(DEFAULT_OPERATION_SITE);
        assertThat(testOperationSiteAuditing.getLinkSupplierFactory()).isEqualTo(DEFAULT_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSiteAuditing.getTypeOfSite()).isEqualTo(DEFAULT_TYPE_OF_SITE);
        assertThat(testOperationSiteAuditing.getAuditingTool()).isEqualTo(DEFAULT_AUDITING_TOOL);
        assertThat(testOperationSiteAuditing.getAuditingDate()).isEqualTo(DEFAULT_AUDITING_DATE);
        assertThat(testOperationSiteAuditing.getCsrResult()).isEqualTo(DEFAULT_CSR_RESULT);
        assertThat(testOperationSiteAuditing.getQualityProductionResult()).isEqualTo(DEFAULT_QUALITY_PRODUCTION_RESULT);
        assertThat(testOperationSiteAuditing.getBusinessLiabilityResult()).isEqualTo(DEFAULT_BUSINESS_LIABILITY_RESULT);
        assertThat(testOperationSiteAuditing.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testOperationSiteAuditing.getIssueDate()).isEqualTo(DEFAULT_ISSUE_DATE);
        assertThat(testOperationSiteAuditing.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testOperationSiteAuditing.getClosedDate()).isEqualTo(DEFAULT_CLOSED_DATE);
        assertThat(testOperationSiteAuditing.getClosed()).isEqualTo(DEFAULT_CLOSED);
        assertThat(testOperationSiteAuditing.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOperationSiteAuditing.getCapComments()).isEqualTo(DEFAULT_CAP_COMMENTS);
    }

    @Test
    @Transactional
    void createOperationSiteAuditingWithExistingId() throws Exception {
        // Create the OperationSiteAuditing with an existing ID
        operationSiteAuditing.setId(1L);

        int databaseSizeBeforeCreate = operationSiteAuditingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperationSiteAuditingMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOperationSiteAuditings() throws Exception {
        // Initialize the database
        operationSiteAuditingRepository.saveAndFlush(operationSiteAuditing);

        // Get all the operationSiteAuditingList
        restOperationSiteAuditingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operationSiteAuditing.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].operationSite").value(hasItem(DEFAULT_OPERATION_SITE)))
            .andExpect(jsonPath("$.[*].linkSupplierFactory").value(hasItem(DEFAULT_LINK_SUPPLIER_FACTORY)))
            .andExpect(jsonPath("$.[*].typeOfSite").value(hasItem(DEFAULT_TYPE_OF_SITE)))
            .andExpect(jsonPath("$.[*].auditingTool").value(hasItem(DEFAULT_AUDITING_TOOL)))
            .andExpect(jsonPath("$.[*].auditingDate").value(hasItem(DEFAULT_AUDITING_DATE.toString())))
            .andExpect(jsonPath("$.[*].csrResult").value(hasItem(DEFAULT_CSR_RESULT)))
            .andExpect(jsonPath("$.[*].qualityProductionResult").value(hasItem(DEFAULT_QUALITY_PRODUCTION_RESULT)))
            .andExpect(jsonPath("$.[*].businessLiabilityResult").value(hasItem(DEFAULT_BUSINESS_LIABILITY_RESULT)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedDate").value(hasItem(DEFAULT_CLOSED_DATE.toString())))
            .andExpect(jsonPath("$.[*].closed").value(hasItem(DEFAULT_CLOSED)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].capComments").value(hasItem(DEFAULT_CAP_COMMENTS)));
    }

    @Test
    @Transactional
    void getOperationSiteAuditing() throws Exception {
        // Initialize the database
        operationSiteAuditingRepository.saveAndFlush(operationSiteAuditing);

        // Get the operationSiteAuditing
        restOperationSiteAuditingMockMvc
            .perform(get(ENTITY_API_URL_ID, operationSiteAuditing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operationSiteAuditing.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.operationSite").value(DEFAULT_OPERATION_SITE))
            .andExpect(jsonPath("$.linkSupplierFactory").value(DEFAULT_LINK_SUPPLIER_FACTORY))
            .andExpect(jsonPath("$.typeOfSite").value(DEFAULT_TYPE_OF_SITE))
            .andExpect(jsonPath("$.auditingTool").value(DEFAULT_AUDITING_TOOL))
            .andExpect(jsonPath("$.auditingDate").value(DEFAULT_AUDITING_DATE.toString()))
            .andExpect(jsonPath("$.csrResult").value(DEFAULT_CSR_RESULT))
            .andExpect(jsonPath("$.qualityProductionResult").value(DEFAULT_QUALITY_PRODUCTION_RESULT))
            .andExpect(jsonPath("$.businessLiabilityResult").value(DEFAULT_BUSINESS_LIABILITY_RESULT))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE.toString()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.closedDate").value(DEFAULT_CLOSED_DATE.toString()))
            .andExpect(jsonPath("$.closed").value(DEFAULT_CLOSED))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.capComments").value(DEFAULT_CAP_COMMENTS));
    }

    @Test
    @Transactional
    void getNonExistingOperationSiteAuditing() throws Exception {
        // Get the operationSiteAuditing
        restOperationSiteAuditingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOperationSiteAuditing() throws Exception {
        // Initialize the database
        operationSiteAuditingRepository.saveAndFlush(operationSiteAuditing);

        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();

        // Update the operationSiteAuditing
        OperationSiteAuditing updatedOperationSiteAuditing = operationSiteAuditingRepository.findById(operationSiteAuditing.getId()).get();
        // Disconnect from session so that the updates on updatedOperationSiteAuditing are not directly saved in db
        em.detach(updatedOperationSiteAuditing);
        updatedOperationSiteAuditing
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .operationSite(UPDATED_OPERATION_SITE)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .auditingTool(UPDATED_AUDITING_TOOL)
            .auditingDate(UPDATED_AUDITING_DATE)
            .csrResult(UPDATED_CSR_RESULT)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS)
            .issueDate(UPDATED_ISSUE_DATE)
            .dueDate(UPDATED_DUE_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .closed(UPDATED_CLOSED)
            .status(UPDATED_STATUS)
            .capComments(UPDATED_CAP_COMMENTS);

        restOperationSiteAuditingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOperationSiteAuditing.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOperationSiteAuditing))
            )
            .andExpect(status().isOk());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
        OperationSiteAuditing testOperationSiteAuditing = operationSiteAuditingList.get(operationSiteAuditingList.size() - 1);
        assertThat(testOperationSiteAuditing.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOperationSiteAuditing.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOperationSiteAuditing.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testOperationSiteAuditing.getOperationSite()).isEqualTo(UPDATED_OPERATION_SITE);
        assertThat(testOperationSiteAuditing.getLinkSupplierFactory()).isEqualTo(UPDATED_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSiteAuditing.getTypeOfSite()).isEqualTo(UPDATED_TYPE_OF_SITE);
        assertThat(testOperationSiteAuditing.getAuditingTool()).isEqualTo(UPDATED_AUDITING_TOOL);
        assertThat(testOperationSiteAuditing.getAuditingDate()).isEqualTo(UPDATED_AUDITING_DATE);
        assertThat(testOperationSiteAuditing.getCsrResult()).isEqualTo(UPDATED_CSR_RESULT);
        assertThat(testOperationSiteAuditing.getQualityProductionResult()).isEqualTo(UPDATED_QUALITY_PRODUCTION_RESULT);
        assertThat(testOperationSiteAuditing.getBusinessLiabilityResult()).isEqualTo(UPDATED_BUSINESS_LIABILITY_RESULT);
        assertThat(testOperationSiteAuditing.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testOperationSiteAuditing.getIssueDate()).isEqualTo(UPDATED_ISSUE_DATE);
        assertThat(testOperationSiteAuditing.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testOperationSiteAuditing.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testOperationSiteAuditing.getClosed()).isEqualTo(UPDATED_CLOSED);
        assertThat(testOperationSiteAuditing.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOperationSiteAuditing.getCapComments()).isEqualTo(UPDATED_CAP_COMMENTS);
    }

    @Test
    @Transactional
    void putNonExistingOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();
        operationSiteAuditing.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperationSiteAuditingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, operationSiteAuditing.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();
        operationSiteAuditing.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteAuditingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();
        operationSiteAuditing.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteAuditingMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOperationSiteAuditingWithPatch() throws Exception {
        // Initialize the database
        operationSiteAuditingRepository.saveAndFlush(operationSiteAuditing);

        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();

        // Update the operationSiteAuditing using partial update
        OperationSiteAuditing partialUpdatedOperationSiteAuditing = new OperationSiteAuditing();
        partialUpdatedOperationSiteAuditing.setId(operationSiteAuditing.getId());

        partialUpdatedOperationSiteAuditing
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .csrResult(UPDATED_CSR_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS)
            .closedDate(UPDATED_CLOSED_DATE)
            .closed(UPDATED_CLOSED)
            .status(UPDATED_STATUS)
            .capComments(UPDATED_CAP_COMMENTS);

        restOperationSiteAuditingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOperationSiteAuditing.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOperationSiteAuditing))
            )
            .andExpect(status().isOk());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
        OperationSiteAuditing testOperationSiteAuditing = operationSiteAuditingList.get(operationSiteAuditingList.size() - 1);
        assertThat(testOperationSiteAuditing.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOperationSiteAuditing.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOperationSiteAuditing.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testOperationSiteAuditing.getOperationSite()).isEqualTo(DEFAULT_OPERATION_SITE);
        assertThat(testOperationSiteAuditing.getLinkSupplierFactory()).isEqualTo(UPDATED_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSiteAuditing.getTypeOfSite()).isEqualTo(UPDATED_TYPE_OF_SITE);
        assertThat(testOperationSiteAuditing.getAuditingTool()).isEqualTo(DEFAULT_AUDITING_TOOL);
        assertThat(testOperationSiteAuditing.getAuditingDate()).isEqualTo(DEFAULT_AUDITING_DATE);
        assertThat(testOperationSiteAuditing.getCsrResult()).isEqualTo(UPDATED_CSR_RESULT);
        assertThat(testOperationSiteAuditing.getQualityProductionResult()).isEqualTo(DEFAULT_QUALITY_PRODUCTION_RESULT);
        assertThat(testOperationSiteAuditing.getBusinessLiabilityResult()).isEqualTo(UPDATED_BUSINESS_LIABILITY_RESULT);
        assertThat(testOperationSiteAuditing.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testOperationSiteAuditing.getIssueDate()).isEqualTo(DEFAULT_ISSUE_DATE);
        assertThat(testOperationSiteAuditing.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testOperationSiteAuditing.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testOperationSiteAuditing.getClosed()).isEqualTo(UPDATED_CLOSED);
        assertThat(testOperationSiteAuditing.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOperationSiteAuditing.getCapComments()).isEqualTo(UPDATED_CAP_COMMENTS);
    }

    @Test
    @Transactional
    void fullUpdateOperationSiteAuditingWithPatch() throws Exception {
        // Initialize the database
        operationSiteAuditingRepository.saveAndFlush(operationSiteAuditing);

        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();

        // Update the operationSiteAuditing using partial update
        OperationSiteAuditing partialUpdatedOperationSiteAuditing = new OperationSiteAuditing();
        partialUpdatedOperationSiteAuditing.setId(operationSiteAuditing.getId());

        partialUpdatedOperationSiteAuditing
            .itemName(UPDATED_ITEM_NAME)
            .cateGory(UPDATED_CATE_GORY)
            .supplier(UPDATED_SUPPLIER)
            .operationSite(UPDATED_OPERATION_SITE)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .auditingTool(UPDATED_AUDITING_TOOL)
            .auditingDate(UPDATED_AUDITING_DATE)
            .csrResult(UPDATED_CSR_RESULT)
            .qualityProductionResult(UPDATED_QUALITY_PRODUCTION_RESULT)
            .businessLiabilityResult(UPDATED_BUSINESS_LIABILITY_RESULT)
            .comments(UPDATED_COMMENTS)
            .issueDate(UPDATED_ISSUE_DATE)
            .dueDate(UPDATED_DUE_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .closed(UPDATED_CLOSED)
            .status(UPDATED_STATUS)
            .capComments(UPDATED_CAP_COMMENTS);

        restOperationSiteAuditingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOperationSiteAuditing.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOperationSiteAuditing))
            )
            .andExpect(status().isOk());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
        OperationSiteAuditing testOperationSiteAuditing = operationSiteAuditingList.get(operationSiteAuditingList.size() - 1);
        assertThat(testOperationSiteAuditing.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOperationSiteAuditing.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOperationSiteAuditing.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testOperationSiteAuditing.getOperationSite()).isEqualTo(UPDATED_OPERATION_SITE);
        assertThat(testOperationSiteAuditing.getLinkSupplierFactory()).isEqualTo(UPDATED_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSiteAuditing.getTypeOfSite()).isEqualTo(UPDATED_TYPE_OF_SITE);
        assertThat(testOperationSiteAuditing.getAuditingTool()).isEqualTo(UPDATED_AUDITING_TOOL);
        assertThat(testOperationSiteAuditing.getAuditingDate()).isEqualTo(UPDATED_AUDITING_DATE);
        assertThat(testOperationSiteAuditing.getCsrResult()).isEqualTo(UPDATED_CSR_RESULT);
        assertThat(testOperationSiteAuditing.getQualityProductionResult()).isEqualTo(UPDATED_QUALITY_PRODUCTION_RESULT);
        assertThat(testOperationSiteAuditing.getBusinessLiabilityResult()).isEqualTo(UPDATED_BUSINESS_LIABILITY_RESULT);
        assertThat(testOperationSiteAuditing.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testOperationSiteAuditing.getIssueDate()).isEqualTo(UPDATED_ISSUE_DATE);
        assertThat(testOperationSiteAuditing.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testOperationSiteAuditing.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testOperationSiteAuditing.getClosed()).isEqualTo(UPDATED_CLOSED);
        assertThat(testOperationSiteAuditing.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOperationSiteAuditing.getCapComments()).isEqualTo(UPDATED_CAP_COMMENTS);
    }

    @Test
    @Transactional
    void patchNonExistingOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();
        operationSiteAuditing.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperationSiteAuditingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, operationSiteAuditing.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();
        operationSiteAuditing.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteAuditingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOperationSiteAuditing() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteAuditingRepository.findAll().size();
        operationSiteAuditing.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteAuditingMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operationSiteAuditing))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OperationSiteAuditing in the database
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOperationSiteAuditing() throws Exception {
        // Initialize the database
        operationSiteAuditingRepository.saveAndFlush(operationSiteAuditing);

        int databaseSizeBeforeDelete = operationSiteAuditingRepository.findAll().size();

        // Delete the operationSiteAuditing
        restOperationSiteAuditingMockMvc
            .perform(delete(ENTITY_API_URL_ID, operationSiteAuditing.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperationSiteAuditing> operationSiteAuditingList = operationSiteAuditingRepository.findAll();
        assertThat(operationSiteAuditingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
