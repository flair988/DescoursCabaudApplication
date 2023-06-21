package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Supplier;
import com.mycompany.myapp.repository.SupplierRepository;
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
 * Integration tests for the {@link SupplierResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SupplierResourceIT {

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_CAP_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CAP_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_QUALIFICATION_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFICATION_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_BOPE_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_BOPE_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_SUPPLIER_ID = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_SUPPLIER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATION_SITE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_RELATION_STARTING_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_RELATION_STARTING_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LICENSE = "BBBBBBBBBB";

    private static final String DEFAULT_REX_ORIGIN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_REX_ORIGIN_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ITEMS = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ITEMS = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_FRENCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FRENCH_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/suppliers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSupplierMockMvc;

    private Supplier supplier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Supplier createEntity(EntityManager em) {
        Supplier supplier = new Supplier()
            .client(DEFAULT_CLIENT)
            .category(DEFAULT_CATEGORY)
            .subCategory(DEFAULT_SUB_CATEGORY)
            .capStatus(DEFAULT_CAP_STATUS)
            .supplierStatus(DEFAULT_SUPPLIER_STATUS)
            .qualificationScore(DEFAULT_QUALIFICATION_SCORE)
            .bopeScore(DEFAULT_BOPE_SCORE)
            .internalSupplierId(DEFAULT_INTERNAL_SUPPLIER_ID)
            .contact(DEFAULT_CONTACT)
            .contactPhoneNumber(DEFAULT_CONTACT_PHONE_NUMBER)
            .contactEmailAddress(DEFAULT_CONTACT_EMAIL_ADDRESS)
            .operationSite(DEFAULT_OPERATION_SITE)
            .address(DEFAULT_ADDRESS)
            .website(DEFAULT_WEBSITE)
            .relationStartingYear(DEFAULT_RELATION_STARTING_YEAR)
            .businessLicense(DEFAULT_BUSINESS_LICENSE)
            .rexOriginStatus(DEFAULT_REX_ORIGIN_STATUS)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .item(DEFAULT_ITEM)
            .subItems(DEFAULT_SUB_ITEMS)
            .date(DEFAULT_DATE)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .region(DEFAULT_REGION)
            .supplierType(DEFAULT_SUPPLIER_TYPE)
            .remark(DEFAULT_REMARK)
            .frenchName(DEFAULT_FRENCH_NAME);
        return supplier;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Supplier createUpdatedEntity(EntityManager em) {
        Supplier supplier = new Supplier()
            .client(UPDATED_CLIENT)
            .category(UPDATED_CATEGORY)
            .subCategory(UPDATED_SUB_CATEGORY)
            .capStatus(UPDATED_CAP_STATUS)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .qualificationScore(UPDATED_QUALIFICATION_SCORE)
            .bopeScore(UPDATED_BOPE_SCORE)
            .internalSupplierId(UPDATED_INTERNAL_SUPPLIER_ID)
            .contact(UPDATED_CONTACT)
            .contactPhoneNumber(UPDATED_CONTACT_PHONE_NUMBER)
            .contactEmailAddress(UPDATED_CONTACT_EMAIL_ADDRESS)
            .operationSite(UPDATED_OPERATION_SITE)
            .address(UPDATED_ADDRESS)
            .website(UPDATED_WEBSITE)
            .relationStartingYear(UPDATED_RELATION_STARTING_YEAR)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .rexOriginStatus(UPDATED_REX_ORIGIN_STATUS)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .item(UPDATED_ITEM)
            .subItems(UPDATED_SUB_ITEMS)
            .date(UPDATED_DATE)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .region(UPDATED_REGION)
            .supplierType(UPDATED_SUPPLIER_TYPE)
            .remark(UPDATED_REMARK)
            .frenchName(UPDATED_FRENCH_NAME);
        return supplier;
    }

    @BeforeEach
    public void initTest() {
        supplier = createEntity(em);
    }

    @Test
    @Transactional
    void createSupplier() throws Exception {
        int databaseSizeBeforeCreate = supplierRepository.findAll().size();
        // Create the Supplier
        restSupplierMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(supplier)))
            .andExpect(status().isCreated());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeCreate + 1);
        Supplier testSupplier = supplierList.get(supplierList.size() - 1);
        assertThat(testSupplier.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testSupplier.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testSupplier.getSubCategory()).isEqualTo(DEFAULT_SUB_CATEGORY);
        assertThat(testSupplier.getCapStatus()).isEqualTo(DEFAULT_CAP_STATUS);
        assertThat(testSupplier.getSupplierStatus()).isEqualTo(DEFAULT_SUPPLIER_STATUS);
        assertThat(testSupplier.getQualificationScore()).isEqualTo(DEFAULT_QUALIFICATION_SCORE);
        assertThat(testSupplier.getBopeScore()).isEqualTo(DEFAULT_BOPE_SCORE);
        assertThat(testSupplier.getInternalSupplierId()).isEqualTo(DEFAULT_INTERNAL_SUPPLIER_ID);
        assertThat(testSupplier.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testSupplier.getContactPhoneNumber()).isEqualTo(DEFAULT_CONTACT_PHONE_NUMBER);
        assertThat(testSupplier.getContactEmailAddress()).isEqualTo(DEFAULT_CONTACT_EMAIL_ADDRESS);
        assertThat(testSupplier.getOperationSite()).isEqualTo(DEFAULT_OPERATION_SITE);
        assertThat(testSupplier.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSupplier.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testSupplier.getRelationStartingYear()).isEqualTo(DEFAULT_RELATION_STARTING_YEAR);
        assertThat(testSupplier.getBusinessLicense()).isEqualTo(DEFAULT_BUSINESS_LICENSE);
        assertThat(testSupplier.getRexOriginStatus()).isEqualTo(DEFAULT_REX_ORIGIN_STATUS);
        assertThat(testSupplier.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testSupplier.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testSupplier.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testSupplier.getSubItems()).isEqualTo(DEFAULT_SUB_ITEMS);
        assertThat(testSupplier.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSupplier.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testSupplier.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testSupplier.getSupplierType()).isEqualTo(DEFAULT_SUPPLIER_TYPE);
        assertThat(testSupplier.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testSupplier.getFrenchName()).isEqualTo(DEFAULT_FRENCH_NAME);
    }

    @Test
    @Transactional
    void createSupplierWithExistingId() throws Exception {
        // Create the Supplier with an existing ID
        supplier.setId(1L);

        int databaseSizeBeforeCreate = supplierRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(supplier)))
            .andExpect(status().isBadRequest());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSuppliers() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        // Get all the supplierList
        restSupplierMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplier.getId().intValue())))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].subCategory").value(hasItem(DEFAULT_SUB_CATEGORY)))
            .andExpect(jsonPath("$.[*].capStatus").value(hasItem(DEFAULT_CAP_STATUS)))
            .andExpect(jsonPath("$.[*].supplierStatus").value(hasItem(DEFAULT_SUPPLIER_STATUS)))
            .andExpect(jsonPath("$.[*].qualificationScore").value(hasItem(DEFAULT_QUALIFICATION_SCORE)))
            .andExpect(jsonPath("$.[*].bopeScore").value(hasItem(DEFAULT_BOPE_SCORE)))
            .andExpect(jsonPath("$.[*].internalSupplierId").value(hasItem(DEFAULT_INTERNAL_SUPPLIER_ID)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].contactPhoneNumber").value(hasItem(DEFAULT_CONTACT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].contactEmailAddress").value(hasItem(DEFAULT_CONTACT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].operationSite").value(hasItem(DEFAULT_OPERATION_SITE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].relationStartingYear").value(hasItem(DEFAULT_RELATION_STARTING_YEAR)))
            .andExpect(jsonPath("$.[*].businessLicense").value(hasItem(DEFAULT_BUSINESS_LICENSE)))
            .andExpect(jsonPath("$.[*].rexOriginStatus").value(hasItem(DEFAULT_REX_ORIGIN_STATUS)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].subItems").value(hasItem(DEFAULT_SUB_ITEMS)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].supplierType").value(hasItem(DEFAULT_SUPPLIER_TYPE)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].frenchName").value(hasItem(DEFAULT_FRENCH_NAME)));
    }

    @Test
    @Transactional
    void getSupplier() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        // Get the supplier
        restSupplierMockMvc
            .perform(get(ENTITY_API_URL_ID, supplier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(supplier.getId().intValue()))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.subCategory").value(DEFAULT_SUB_CATEGORY))
            .andExpect(jsonPath("$.capStatus").value(DEFAULT_CAP_STATUS))
            .andExpect(jsonPath("$.supplierStatus").value(DEFAULT_SUPPLIER_STATUS))
            .andExpect(jsonPath("$.qualificationScore").value(DEFAULT_QUALIFICATION_SCORE))
            .andExpect(jsonPath("$.bopeScore").value(DEFAULT_BOPE_SCORE))
            .andExpect(jsonPath("$.internalSupplierId").value(DEFAULT_INTERNAL_SUPPLIER_ID))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.contactPhoneNumber").value(DEFAULT_CONTACT_PHONE_NUMBER))
            .andExpect(jsonPath("$.contactEmailAddress").value(DEFAULT_CONTACT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.operationSite").value(DEFAULT_OPERATION_SITE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.relationStartingYear").value(DEFAULT_RELATION_STARTING_YEAR))
            .andExpect(jsonPath("$.businessLicense").value(DEFAULT_BUSINESS_LICENSE))
            .andExpect(jsonPath("$.rexOriginStatus").value(DEFAULT_REX_ORIGIN_STATUS))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.subItems").value(DEFAULT_SUB_ITEMS))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.supplierType").value(DEFAULT_SUPPLIER_TYPE))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.frenchName").value(DEFAULT_FRENCH_NAME));
    }

    @Test
    @Transactional
    void getNonExistingSupplier() throws Exception {
        // Get the supplier
        restSupplierMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSupplier() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();

        // Update the supplier
        Supplier updatedSupplier = supplierRepository.findById(supplier.getId()).get();
        // Disconnect from session so that the updates on updatedSupplier are not directly saved in db
        em.detach(updatedSupplier);
        updatedSupplier
            .client(UPDATED_CLIENT)
            .category(UPDATED_CATEGORY)
            .subCategory(UPDATED_SUB_CATEGORY)
            .capStatus(UPDATED_CAP_STATUS)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .qualificationScore(UPDATED_QUALIFICATION_SCORE)
            .bopeScore(UPDATED_BOPE_SCORE)
            .internalSupplierId(UPDATED_INTERNAL_SUPPLIER_ID)
            .contact(UPDATED_CONTACT)
            .contactPhoneNumber(UPDATED_CONTACT_PHONE_NUMBER)
            .contactEmailAddress(UPDATED_CONTACT_EMAIL_ADDRESS)
            .operationSite(UPDATED_OPERATION_SITE)
            .address(UPDATED_ADDRESS)
            .website(UPDATED_WEBSITE)
            .relationStartingYear(UPDATED_RELATION_STARTING_YEAR)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .rexOriginStatus(UPDATED_REX_ORIGIN_STATUS)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .item(UPDATED_ITEM)
            .subItems(UPDATED_SUB_ITEMS)
            .date(UPDATED_DATE)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .region(UPDATED_REGION)
            .supplierType(UPDATED_SUPPLIER_TYPE)
            .remark(UPDATED_REMARK)
            .frenchName(UPDATED_FRENCH_NAME);

        restSupplierMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSupplier.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSupplier))
            )
            .andExpect(status().isOk());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
        Supplier testSupplier = supplierList.get(supplierList.size() - 1);
        assertThat(testSupplier.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testSupplier.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testSupplier.getSubCategory()).isEqualTo(UPDATED_SUB_CATEGORY);
        assertThat(testSupplier.getCapStatus()).isEqualTo(UPDATED_CAP_STATUS);
        assertThat(testSupplier.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplier.getQualificationScore()).isEqualTo(UPDATED_QUALIFICATION_SCORE);
        assertThat(testSupplier.getBopeScore()).isEqualTo(UPDATED_BOPE_SCORE);
        assertThat(testSupplier.getInternalSupplierId()).isEqualTo(UPDATED_INTERNAL_SUPPLIER_ID);
        assertThat(testSupplier.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testSupplier.getContactPhoneNumber()).isEqualTo(UPDATED_CONTACT_PHONE_NUMBER);
        assertThat(testSupplier.getContactEmailAddress()).isEqualTo(UPDATED_CONTACT_EMAIL_ADDRESS);
        assertThat(testSupplier.getOperationSite()).isEqualTo(UPDATED_OPERATION_SITE);
        assertThat(testSupplier.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplier.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testSupplier.getRelationStartingYear()).isEqualTo(UPDATED_RELATION_STARTING_YEAR);
        assertThat(testSupplier.getBusinessLicense()).isEqualTo(UPDATED_BUSINESS_LICENSE);
        assertThat(testSupplier.getRexOriginStatus()).isEqualTo(UPDATED_REX_ORIGIN_STATUS);
        assertThat(testSupplier.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testSupplier.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testSupplier.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testSupplier.getSubItems()).isEqualTo(UPDATED_SUB_ITEMS);
        assertThat(testSupplier.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSupplier.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testSupplier.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testSupplier.getSupplierType()).isEqualTo(UPDATED_SUPPLIER_TYPE);
        assertThat(testSupplier.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSupplier.getFrenchName()).isEqualTo(UPDATED_FRENCH_NAME);
    }

    @Test
    @Transactional
    void putNonExistingSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();
        supplier.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplierMockMvc
            .perform(
                put(ENTITY_API_URL_ID, supplier.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplier))
            )
            .andExpect(status().isBadRequest());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();
        supplier.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplier))
            )
            .andExpect(status().isBadRequest());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();
        supplier.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(supplier)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSupplierWithPatch() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();

        // Update the supplier using partial update
        Supplier partialUpdatedSupplier = new Supplier();
        partialUpdatedSupplier.setId(supplier.getId());

        partialUpdatedSupplier
            .client(UPDATED_CLIENT)
            .category(UPDATED_CATEGORY)
            .subCategory(UPDATED_SUB_CATEGORY)
            .capStatus(UPDATED_CAP_STATUS)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .bopeScore(UPDATED_BOPE_SCORE)
            .internalSupplierId(UPDATED_INTERNAL_SUPPLIER_ID)
            .contact(UPDATED_CONTACT)
            .contactEmailAddress(UPDATED_CONTACT_EMAIL_ADDRESS)
            .address(UPDATED_ADDRESS)
            .relationStartingYear(UPDATED_RELATION_STARTING_YEAR)
            .updateDate(UPDATED_UPDATE_DATE)
            .item(UPDATED_ITEM)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .region(UPDATED_REGION)
            .remark(UPDATED_REMARK);

        restSupplierMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSupplier.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSupplier))
            )
            .andExpect(status().isOk());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
        Supplier testSupplier = supplierList.get(supplierList.size() - 1);
        assertThat(testSupplier.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testSupplier.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testSupplier.getSubCategory()).isEqualTo(UPDATED_SUB_CATEGORY);
        assertThat(testSupplier.getCapStatus()).isEqualTo(UPDATED_CAP_STATUS);
        assertThat(testSupplier.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplier.getQualificationScore()).isEqualTo(DEFAULT_QUALIFICATION_SCORE);
        assertThat(testSupplier.getBopeScore()).isEqualTo(UPDATED_BOPE_SCORE);
        assertThat(testSupplier.getInternalSupplierId()).isEqualTo(UPDATED_INTERNAL_SUPPLIER_ID);
        assertThat(testSupplier.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testSupplier.getContactPhoneNumber()).isEqualTo(DEFAULT_CONTACT_PHONE_NUMBER);
        assertThat(testSupplier.getContactEmailAddress()).isEqualTo(UPDATED_CONTACT_EMAIL_ADDRESS);
        assertThat(testSupplier.getOperationSite()).isEqualTo(DEFAULT_OPERATION_SITE);
        assertThat(testSupplier.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplier.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testSupplier.getRelationStartingYear()).isEqualTo(UPDATED_RELATION_STARTING_YEAR);
        assertThat(testSupplier.getBusinessLicense()).isEqualTo(DEFAULT_BUSINESS_LICENSE);
        assertThat(testSupplier.getRexOriginStatus()).isEqualTo(DEFAULT_REX_ORIGIN_STATUS);
        assertThat(testSupplier.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testSupplier.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testSupplier.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testSupplier.getSubItems()).isEqualTo(DEFAULT_SUB_ITEMS);
        assertThat(testSupplier.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSupplier.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testSupplier.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testSupplier.getSupplierType()).isEqualTo(DEFAULT_SUPPLIER_TYPE);
        assertThat(testSupplier.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSupplier.getFrenchName()).isEqualTo(DEFAULT_FRENCH_NAME);
    }

    @Test
    @Transactional
    void fullUpdateSupplierWithPatch() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();

        // Update the supplier using partial update
        Supplier partialUpdatedSupplier = new Supplier();
        partialUpdatedSupplier.setId(supplier.getId());

        partialUpdatedSupplier
            .client(UPDATED_CLIENT)
            .category(UPDATED_CATEGORY)
            .subCategory(UPDATED_SUB_CATEGORY)
            .capStatus(UPDATED_CAP_STATUS)
            .supplierStatus(UPDATED_SUPPLIER_STATUS)
            .qualificationScore(UPDATED_QUALIFICATION_SCORE)
            .bopeScore(UPDATED_BOPE_SCORE)
            .internalSupplierId(UPDATED_INTERNAL_SUPPLIER_ID)
            .contact(UPDATED_CONTACT)
            .contactPhoneNumber(UPDATED_CONTACT_PHONE_NUMBER)
            .contactEmailAddress(UPDATED_CONTACT_EMAIL_ADDRESS)
            .operationSite(UPDATED_OPERATION_SITE)
            .address(UPDATED_ADDRESS)
            .website(UPDATED_WEBSITE)
            .relationStartingYear(UPDATED_RELATION_STARTING_YEAR)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .rexOriginStatus(UPDATED_REX_ORIGIN_STATUS)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .item(UPDATED_ITEM)
            .subItems(UPDATED_SUB_ITEMS)
            .date(UPDATED_DATE)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .region(UPDATED_REGION)
            .supplierType(UPDATED_SUPPLIER_TYPE)
            .remark(UPDATED_REMARK)
            .frenchName(UPDATED_FRENCH_NAME);

        restSupplierMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSupplier.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSupplier))
            )
            .andExpect(status().isOk());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
        Supplier testSupplier = supplierList.get(supplierList.size() - 1);
        assertThat(testSupplier.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testSupplier.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testSupplier.getSubCategory()).isEqualTo(UPDATED_SUB_CATEGORY);
        assertThat(testSupplier.getCapStatus()).isEqualTo(UPDATED_CAP_STATUS);
        assertThat(testSupplier.getSupplierStatus()).isEqualTo(UPDATED_SUPPLIER_STATUS);
        assertThat(testSupplier.getQualificationScore()).isEqualTo(UPDATED_QUALIFICATION_SCORE);
        assertThat(testSupplier.getBopeScore()).isEqualTo(UPDATED_BOPE_SCORE);
        assertThat(testSupplier.getInternalSupplierId()).isEqualTo(UPDATED_INTERNAL_SUPPLIER_ID);
        assertThat(testSupplier.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testSupplier.getContactPhoneNumber()).isEqualTo(UPDATED_CONTACT_PHONE_NUMBER);
        assertThat(testSupplier.getContactEmailAddress()).isEqualTo(UPDATED_CONTACT_EMAIL_ADDRESS);
        assertThat(testSupplier.getOperationSite()).isEqualTo(UPDATED_OPERATION_SITE);
        assertThat(testSupplier.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplier.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testSupplier.getRelationStartingYear()).isEqualTo(UPDATED_RELATION_STARTING_YEAR);
        assertThat(testSupplier.getBusinessLicense()).isEqualTo(UPDATED_BUSINESS_LICENSE);
        assertThat(testSupplier.getRexOriginStatus()).isEqualTo(UPDATED_REX_ORIGIN_STATUS);
        assertThat(testSupplier.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testSupplier.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testSupplier.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testSupplier.getSubItems()).isEqualTo(UPDATED_SUB_ITEMS);
        assertThat(testSupplier.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSupplier.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testSupplier.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testSupplier.getSupplierType()).isEqualTo(UPDATED_SUPPLIER_TYPE);
        assertThat(testSupplier.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testSupplier.getFrenchName()).isEqualTo(UPDATED_FRENCH_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();
        supplier.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplierMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, supplier.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplier))
            )
            .andExpect(status().isBadRequest());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();
        supplier.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplier))
            )
            .andExpect(status().isBadRequest());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSupplier() throws Exception {
        int databaseSizeBeforeUpdate = supplierRepository.findAll().size();
        supplier.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(supplier)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Supplier in the database
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSupplier() throws Exception {
        // Initialize the database
        supplierRepository.saveAndFlush(supplier);

        int databaseSizeBeforeDelete = supplierRepository.findAll().size();

        // Delete the supplier
        restSupplierMockMvc
            .perform(delete(ENTITY_API_URL_ID, supplier.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Supplier> supplierList = supplierRepository.findAll();
        assertThat(supplierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
