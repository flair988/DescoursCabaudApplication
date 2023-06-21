package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OperationSite;
import com.mycompany.myapp.repository.OperationSiteRepository;
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
 * Integration tests for the {@link OperationSiteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OperationSiteResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_SUPPLIER_FACTORY = "AAAAAAAAAA";
    private static final String UPDATED_LINK_SUPPLIER_FACTORY = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_SITE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SITE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LICENSE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SAS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SAS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ISO_900_VALID_UTIL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISO_900_VALID_UTIL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ISO_14001_VALID_UTIL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ISO_14001_VALID_UTIL = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ATTRIBUTE_LOR = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_LOR = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_QUALIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_SITE_QUALIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_QUALIFICATION_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFICATION_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_PQV_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_PQV_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_PQV_DATE = "AAAAAAAAAA";
    private static final String UPDATED_PQV_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PQV_DECISION = "AAAAAAAAAA";
    private static final String UPDATED_PQV_DECISION = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICAL_AUDIT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICAL_AUDIT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICAL_AUDIT_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICAL_AUDIT_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_THIRD_RD_PARTY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_THIRD_RD_PARTY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_THIRD_RD_PARTY_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_THIRD_RD_PARTY_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_BOPE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_BOPE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_BOPE_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_BOPE_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_CAP_REQUIRED = "AAAAAAAAAA";
    private static final String UPDATED_CAP_REQUIRED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/operation-sites";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OperationSiteRepository operationSiteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOperationSiteMockMvc;

    private OperationSite operationSite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperationSite createEntity(EntityManager em) {
        OperationSite operationSite = new OperationSite()
            .itemName(DEFAULT_ITEM_NAME)
            .linkSupplierFactory(DEFAULT_LINK_SUPPLIER_FACTORY)
            .typeOfSite(DEFAULT_TYPE_OF_SITE)
            .contact(DEFAULT_CONTACT)
            .siteAddress(DEFAULT_SITE_ADDRESS)
            .cateGory(DEFAULT_CATE_GORY)
            .country(DEFAULT_COUNTRY)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .businessLicense(DEFAULT_BUSINESS_LICENSE)
            .sasDate(DEFAULT_SAS_DATE)
            .iso900ValidUtil(DEFAULT_ISO_900_VALID_UTIL)
            .iso14001ValidUtil(DEFAULT_ISO_14001_VALID_UTIL)
            .attributeLor(DEFAULT_ATTRIBUTE_LOR)
            .siteQualification(DEFAULT_SITE_QUALIFICATION)
            .qualificationScore(DEFAULT_QUALIFICATION_SCORE)
            .pqvScore(DEFAULT_PQV_SCORE)
            .pqvDate(DEFAULT_PQV_DATE)
            .pqvDecision(DEFAULT_PQV_DECISION)
            .technicalAuditDate(DEFAULT_TECHNICAL_AUDIT_DATE)
            .technicalAuditScore(DEFAULT_TECHNICAL_AUDIT_SCORE)
            .thirdRdPartyDate(DEFAULT_THIRD_RD_PARTY_DATE)
            .thirdRdPartyScore(DEFAULT_THIRD_RD_PARTY_SCORE)
            .bopeDate(DEFAULT_BOPE_DATE)
            .bopeScore(DEFAULT_BOPE_SCORE)
            .capRequired(DEFAULT_CAP_REQUIRED);
        return operationSite;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperationSite createUpdatedEntity(EntityManager em) {
        OperationSite operationSite = new OperationSite()
            .itemName(UPDATED_ITEM_NAME)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .contact(UPDATED_CONTACT)
            .siteAddress(UPDATED_SITE_ADDRESS)
            .cateGory(UPDATED_CATE_GORY)
            .country(UPDATED_COUNTRY)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .sasDate(UPDATED_SAS_DATE)
            .iso900ValidUtil(UPDATED_ISO_900_VALID_UTIL)
            .iso14001ValidUtil(UPDATED_ISO_14001_VALID_UTIL)
            .attributeLor(UPDATED_ATTRIBUTE_LOR)
            .siteQualification(UPDATED_SITE_QUALIFICATION)
            .qualificationScore(UPDATED_QUALIFICATION_SCORE)
            .pqvScore(UPDATED_PQV_SCORE)
            .pqvDate(UPDATED_PQV_DATE)
            .pqvDecision(UPDATED_PQV_DECISION)
            .technicalAuditDate(UPDATED_TECHNICAL_AUDIT_DATE)
            .technicalAuditScore(UPDATED_TECHNICAL_AUDIT_SCORE)
            .thirdRdPartyDate(UPDATED_THIRD_RD_PARTY_DATE)
            .thirdRdPartyScore(UPDATED_THIRD_RD_PARTY_SCORE)
            .bopeDate(UPDATED_BOPE_DATE)
            .bopeScore(UPDATED_BOPE_SCORE)
            .capRequired(UPDATED_CAP_REQUIRED);
        return operationSite;
    }

    @BeforeEach
    public void initTest() {
        operationSite = createEntity(em);
    }

    @Test
    @Transactional
    void createOperationSite() throws Exception {
        int databaseSizeBeforeCreate = operationSiteRepository.findAll().size();
        // Create the OperationSite
        restOperationSiteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operationSite)))
            .andExpect(status().isCreated());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeCreate + 1);
        OperationSite testOperationSite = operationSiteList.get(operationSiteList.size() - 1);
        assertThat(testOperationSite.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testOperationSite.getLinkSupplierFactory()).isEqualTo(DEFAULT_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSite.getTypeOfSite()).isEqualTo(DEFAULT_TYPE_OF_SITE);
        assertThat(testOperationSite.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testOperationSite.getSiteAddress()).isEqualTo(DEFAULT_SITE_ADDRESS);
        assertThat(testOperationSite.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testOperationSite.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testOperationSite.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testOperationSite.getBusinessLicense()).isEqualTo(DEFAULT_BUSINESS_LICENSE);
        assertThat(testOperationSite.getSasDate()).isEqualTo(DEFAULT_SAS_DATE);
        assertThat(testOperationSite.getIso900ValidUtil()).isEqualTo(DEFAULT_ISO_900_VALID_UTIL);
        assertThat(testOperationSite.getIso14001ValidUtil()).isEqualTo(DEFAULT_ISO_14001_VALID_UTIL);
        assertThat(testOperationSite.getAttributeLor()).isEqualTo(DEFAULT_ATTRIBUTE_LOR);
        assertThat(testOperationSite.getSiteQualification()).isEqualTo(DEFAULT_SITE_QUALIFICATION);
        assertThat(testOperationSite.getQualificationScore()).isEqualTo(DEFAULT_QUALIFICATION_SCORE);
        assertThat(testOperationSite.getPqvScore()).isEqualTo(DEFAULT_PQV_SCORE);
        assertThat(testOperationSite.getPqvDate()).isEqualTo(DEFAULT_PQV_DATE);
        assertThat(testOperationSite.getPqvDecision()).isEqualTo(DEFAULT_PQV_DECISION);
        assertThat(testOperationSite.getTechnicalAuditDate()).isEqualTo(DEFAULT_TECHNICAL_AUDIT_DATE);
        assertThat(testOperationSite.getTechnicalAuditScore()).isEqualTo(DEFAULT_TECHNICAL_AUDIT_SCORE);
        assertThat(testOperationSite.getThirdRdPartyDate()).isEqualTo(DEFAULT_THIRD_RD_PARTY_DATE);
        assertThat(testOperationSite.getThirdRdPartyScore()).isEqualTo(DEFAULT_THIRD_RD_PARTY_SCORE);
        assertThat(testOperationSite.getBopeDate()).isEqualTo(DEFAULT_BOPE_DATE);
        assertThat(testOperationSite.getBopeScore()).isEqualTo(DEFAULT_BOPE_SCORE);
        assertThat(testOperationSite.getCapRequired()).isEqualTo(DEFAULT_CAP_REQUIRED);
    }

    @Test
    @Transactional
    void createOperationSiteWithExistingId() throws Exception {
        // Create the OperationSite with an existing ID
        operationSite.setId(1L);

        int databaseSizeBeforeCreate = operationSiteRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperationSiteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operationSite)))
            .andExpect(status().isBadRequest());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOperationSites() throws Exception {
        // Initialize the database
        operationSiteRepository.saveAndFlush(operationSite);

        // Get all the operationSiteList
        restOperationSiteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operationSite.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].linkSupplierFactory").value(hasItem(DEFAULT_LINK_SUPPLIER_FACTORY)))
            .andExpect(jsonPath("$.[*].typeOfSite").value(hasItem(DEFAULT_TYPE_OF_SITE)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].siteAddress").value(hasItem(DEFAULT_SITE_ADDRESS)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].businessLicense").value(hasItem(DEFAULT_BUSINESS_LICENSE)))
            .andExpect(jsonPath("$.[*].sasDate").value(hasItem(DEFAULT_SAS_DATE.toString())))
            .andExpect(jsonPath("$.[*].iso900ValidUtil").value(hasItem(DEFAULT_ISO_900_VALID_UTIL.toString())))
            .andExpect(jsonPath("$.[*].iso14001ValidUtil").value(hasItem(DEFAULT_ISO_14001_VALID_UTIL.toString())))
            .andExpect(jsonPath("$.[*].attributeLor").value(hasItem(DEFAULT_ATTRIBUTE_LOR)))
            .andExpect(jsonPath("$.[*].siteQualification").value(hasItem(DEFAULT_SITE_QUALIFICATION)))
            .andExpect(jsonPath("$.[*].qualificationScore").value(hasItem(DEFAULT_QUALIFICATION_SCORE)))
            .andExpect(jsonPath("$.[*].pqvScore").value(hasItem(DEFAULT_PQV_SCORE)))
            .andExpect(jsonPath("$.[*].pqvDate").value(hasItem(DEFAULT_PQV_DATE)))
            .andExpect(jsonPath("$.[*].pqvDecision").value(hasItem(DEFAULT_PQV_DECISION)))
            .andExpect(jsonPath("$.[*].technicalAuditDate").value(hasItem(DEFAULT_TECHNICAL_AUDIT_DATE)))
            .andExpect(jsonPath("$.[*].technicalAuditScore").value(hasItem(DEFAULT_TECHNICAL_AUDIT_SCORE)))
            .andExpect(jsonPath("$.[*].thirdRdPartyDate").value(hasItem(DEFAULT_THIRD_RD_PARTY_DATE)))
            .andExpect(jsonPath("$.[*].thirdRdPartyScore").value(hasItem(DEFAULT_THIRD_RD_PARTY_SCORE)))
            .andExpect(jsonPath("$.[*].bopeDate").value(hasItem(DEFAULT_BOPE_DATE)))
            .andExpect(jsonPath("$.[*].bopeScore").value(hasItem(DEFAULT_BOPE_SCORE)))
            .andExpect(jsonPath("$.[*].capRequired").value(hasItem(DEFAULT_CAP_REQUIRED)));
    }

    @Test
    @Transactional
    void getOperationSite() throws Exception {
        // Initialize the database
        operationSiteRepository.saveAndFlush(operationSite);

        // Get the operationSite
        restOperationSiteMockMvc
            .perform(get(ENTITY_API_URL_ID, operationSite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operationSite.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.linkSupplierFactory").value(DEFAULT_LINK_SUPPLIER_FACTORY))
            .andExpect(jsonPath("$.typeOfSite").value(DEFAULT_TYPE_OF_SITE))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.siteAddress").value(DEFAULT_SITE_ADDRESS))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.businessLicense").value(DEFAULT_BUSINESS_LICENSE))
            .andExpect(jsonPath("$.sasDate").value(DEFAULT_SAS_DATE.toString()))
            .andExpect(jsonPath("$.iso900ValidUtil").value(DEFAULT_ISO_900_VALID_UTIL.toString()))
            .andExpect(jsonPath("$.iso14001ValidUtil").value(DEFAULT_ISO_14001_VALID_UTIL.toString()))
            .andExpect(jsonPath("$.attributeLor").value(DEFAULT_ATTRIBUTE_LOR))
            .andExpect(jsonPath("$.siteQualification").value(DEFAULT_SITE_QUALIFICATION))
            .andExpect(jsonPath("$.qualificationScore").value(DEFAULT_QUALIFICATION_SCORE))
            .andExpect(jsonPath("$.pqvScore").value(DEFAULT_PQV_SCORE))
            .andExpect(jsonPath("$.pqvDate").value(DEFAULT_PQV_DATE))
            .andExpect(jsonPath("$.pqvDecision").value(DEFAULT_PQV_DECISION))
            .andExpect(jsonPath("$.technicalAuditDate").value(DEFAULT_TECHNICAL_AUDIT_DATE))
            .andExpect(jsonPath("$.technicalAuditScore").value(DEFAULT_TECHNICAL_AUDIT_SCORE))
            .andExpect(jsonPath("$.thirdRdPartyDate").value(DEFAULT_THIRD_RD_PARTY_DATE))
            .andExpect(jsonPath("$.thirdRdPartyScore").value(DEFAULT_THIRD_RD_PARTY_SCORE))
            .andExpect(jsonPath("$.bopeDate").value(DEFAULT_BOPE_DATE))
            .andExpect(jsonPath("$.bopeScore").value(DEFAULT_BOPE_SCORE))
            .andExpect(jsonPath("$.capRequired").value(DEFAULT_CAP_REQUIRED));
    }

    @Test
    @Transactional
    void getNonExistingOperationSite() throws Exception {
        // Get the operationSite
        restOperationSiteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOperationSite() throws Exception {
        // Initialize the database
        operationSiteRepository.saveAndFlush(operationSite);

        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();

        // Update the operationSite
        OperationSite updatedOperationSite = operationSiteRepository.findById(operationSite.getId()).get();
        // Disconnect from session so that the updates on updatedOperationSite are not directly saved in db
        em.detach(updatedOperationSite);
        updatedOperationSite
            .itemName(UPDATED_ITEM_NAME)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .contact(UPDATED_CONTACT)
            .siteAddress(UPDATED_SITE_ADDRESS)
            .cateGory(UPDATED_CATE_GORY)
            .country(UPDATED_COUNTRY)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .sasDate(UPDATED_SAS_DATE)
            .iso900ValidUtil(UPDATED_ISO_900_VALID_UTIL)
            .iso14001ValidUtil(UPDATED_ISO_14001_VALID_UTIL)
            .attributeLor(UPDATED_ATTRIBUTE_LOR)
            .siteQualification(UPDATED_SITE_QUALIFICATION)
            .qualificationScore(UPDATED_QUALIFICATION_SCORE)
            .pqvScore(UPDATED_PQV_SCORE)
            .pqvDate(UPDATED_PQV_DATE)
            .pqvDecision(UPDATED_PQV_DECISION)
            .technicalAuditDate(UPDATED_TECHNICAL_AUDIT_DATE)
            .technicalAuditScore(UPDATED_TECHNICAL_AUDIT_SCORE)
            .thirdRdPartyDate(UPDATED_THIRD_RD_PARTY_DATE)
            .thirdRdPartyScore(UPDATED_THIRD_RD_PARTY_SCORE)
            .bopeDate(UPDATED_BOPE_DATE)
            .bopeScore(UPDATED_BOPE_SCORE)
            .capRequired(UPDATED_CAP_REQUIRED);

        restOperationSiteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOperationSite.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOperationSite))
            )
            .andExpect(status().isOk());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
        OperationSite testOperationSite = operationSiteList.get(operationSiteList.size() - 1);
        assertThat(testOperationSite.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOperationSite.getLinkSupplierFactory()).isEqualTo(UPDATED_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSite.getTypeOfSite()).isEqualTo(UPDATED_TYPE_OF_SITE);
        assertThat(testOperationSite.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testOperationSite.getSiteAddress()).isEqualTo(UPDATED_SITE_ADDRESS);
        assertThat(testOperationSite.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOperationSite.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testOperationSite.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testOperationSite.getBusinessLicense()).isEqualTo(UPDATED_BUSINESS_LICENSE);
        assertThat(testOperationSite.getSasDate()).isEqualTo(UPDATED_SAS_DATE);
        assertThat(testOperationSite.getIso900ValidUtil()).isEqualTo(UPDATED_ISO_900_VALID_UTIL);
        assertThat(testOperationSite.getIso14001ValidUtil()).isEqualTo(UPDATED_ISO_14001_VALID_UTIL);
        assertThat(testOperationSite.getAttributeLor()).isEqualTo(UPDATED_ATTRIBUTE_LOR);
        assertThat(testOperationSite.getSiteQualification()).isEqualTo(UPDATED_SITE_QUALIFICATION);
        assertThat(testOperationSite.getQualificationScore()).isEqualTo(UPDATED_QUALIFICATION_SCORE);
        assertThat(testOperationSite.getPqvScore()).isEqualTo(UPDATED_PQV_SCORE);
        assertThat(testOperationSite.getPqvDate()).isEqualTo(UPDATED_PQV_DATE);
        assertThat(testOperationSite.getPqvDecision()).isEqualTo(UPDATED_PQV_DECISION);
        assertThat(testOperationSite.getTechnicalAuditDate()).isEqualTo(UPDATED_TECHNICAL_AUDIT_DATE);
        assertThat(testOperationSite.getTechnicalAuditScore()).isEqualTo(UPDATED_TECHNICAL_AUDIT_SCORE);
        assertThat(testOperationSite.getThirdRdPartyDate()).isEqualTo(UPDATED_THIRD_RD_PARTY_DATE);
        assertThat(testOperationSite.getThirdRdPartyScore()).isEqualTo(UPDATED_THIRD_RD_PARTY_SCORE);
        assertThat(testOperationSite.getBopeDate()).isEqualTo(UPDATED_BOPE_DATE);
        assertThat(testOperationSite.getBopeScore()).isEqualTo(UPDATED_BOPE_SCORE);
        assertThat(testOperationSite.getCapRequired()).isEqualTo(UPDATED_CAP_REQUIRED);
    }

    @Test
    @Transactional
    void putNonExistingOperationSite() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();
        operationSite.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperationSiteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, operationSite.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSite))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOperationSite() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();
        operationSite.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(operationSite))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOperationSite() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();
        operationSite.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(operationSite)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOperationSiteWithPatch() throws Exception {
        // Initialize the database
        operationSiteRepository.saveAndFlush(operationSite);

        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();

        // Update the operationSite using partial update
        OperationSite partialUpdatedOperationSite = new OperationSite();
        partialUpdatedOperationSite.setId(operationSite.getId());

        partialUpdatedOperationSite
            .itemName(UPDATED_ITEM_NAME)
            .contact(UPDATED_CONTACT)
            .siteAddress(UPDATED_SITE_ADDRESS)
            .country(UPDATED_COUNTRY)
            .sasDate(UPDATED_SAS_DATE)
            .iso900ValidUtil(UPDATED_ISO_900_VALID_UTIL)
            .pqvDate(UPDATED_PQV_DATE)
            .technicalAuditDate(UPDATED_TECHNICAL_AUDIT_DATE)
            .technicalAuditScore(UPDATED_TECHNICAL_AUDIT_SCORE)
            .thirdRdPartyDate(UPDATED_THIRD_RD_PARTY_DATE)
            .bopeScore(UPDATED_BOPE_SCORE);

        restOperationSiteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOperationSite.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOperationSite))
            )
            .andExpect(status().isOk());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
        OperationSite testOperationSite = operationSiteList.get(operationSiteList.size() - 1);
        assertThat(testOperationSite.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOperationSite.getLinkSupplierFactory()).isEqualTo(DEFAULT_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSite.getTypeOfSite()).isEqualTo(DEFAULT_TYPE_OF_SITE);
        assertThat(testOperationSite.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testOperationSite.getSiteAddress()).isEqualTo(UPDATED_SITE_ADDRESS);
        assertThat(testOperationSite.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testOperationSite.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testOperationSite.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testOperationSite.getBusinessLicense()).isEqualTo(DEFAULT_BUSINESS_LICENSE);
        assertThat(testOperationSite.getSasDate()).isEqualTo(UPDATED_SAS_DATE);
        assertThat(testOperationSite.getIso900ValidUtil()).isEqualTo(UPDATED_ISO_900_VALID_UTIL);
        assertThat(testOperationSite.getIso14001ValidUtil()).isEqualTo(DEFAULT_ISO_14001_VALID_UTIL);
        assertThat(testOperationSite.getAttributeLor()).isEqualTo(DEFAULT_ATTRIBUTE_LOR);
        assertThat(testOperationSite.getSiteQualification()).isEqualTo(DEFAULT_SITE_QUALIFICATION);
        assertThat(testOperationSite.getQualificationScore()).isEqualTo(DEFAULT_QUALIFICATION_SCORE);
        assertThat(testOperationSite.getPqvScore()).isEqualTo(DEFAULT_PQV_SCORE);
        assertThat(testOperationSite.getPqvDate()).isEqualTo(UPDATED_PQV_DATE);
        assertThat(testOperationSite.getPqvDecision()).isEqualTo(DEFAULT_PQV_DECISION);
        assertThat(testOperationSite.getTechnicalAuditDate()).isEqualTo(UPDATED_TECHNICAL_AUDIT_DATE);
        assertThat(testOperationSite.getTechnicalAuditScore()).isEqualTo(UPDATED_TECHNICAL_AUDIT_SCORE);
        assertThat(testOperationSite.getThirdRdPartyDate()).isEqualTo(UPDATED_THIRD_RD_PARTY_DATE);
        assertThat(testOperationSite.getThirdRdPartyScore()).isEqualTo(DEFAULT_THIRD_RD_PARTY_SCORE);
        assertThat(testOperationSite.getBopeDate()).isEqualTo(DEFAULT_BOPE_DATE);
        assertThat(testOperationSite.getBopeScore()).isEqualTo(UPDATED_BOPE_SCORE);
        assertThat(testOperationSite.getCapRequired()).isEqualTo(DEFAULT_CAP_REQUIRED);
    }

    @Test
    @Transactional
    void fullUpdateOperationSiteWithPatch() throws Exception {
        // Initialize the database
        operationSiteRepository.saveAndFlush(operationSite);

        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();

        // Update the operationSite using partial update
        OperationSite partialUpdatedOperationSite = new OperationSite();
        partialUpdatedOperationSite.setId(operationSite.getId());

        partialUpdatedOperationSite
            .itemName(UPDATED_ITEM_NAME)
            .linkSupplierFactory(UPDATED_LINK_SUPPLIER_FACTORY)
            .typeOfSite(UPDATED_TYPE_OF_SITE)
            .contact(UPDATED_CONTACT)
            .siteAddress(UPDATED_SITE_ADDRESS)
            .cateGory(UPDATED_CATE_GORY)
            .country(UPDATED_COUNTRY)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .sasDate(UPDATED_SAS_DATE)
            .iso900ValidUtil(UPDATED_ISO_900_VALID_UTIL)
            .iso14001ValidUtil(UPDATED_ISO_14001_VALID_UTIL)
            .attributeLor(UPDATED_ATTRIBUTE_LOR)
            .siteQualification(UPDATED_SITE_QUALIFICATION)
            .qualificationScore(UPDATED_QUALIFICATION_SCORE)
            .pqvScore(UPDATED_PQV_SCORE)
            .pqvDate(UPDATED_PQV_DATE)
            .pqvDecision(UPDATED_PQV_DECISION)
            .technicalAuditDate(UPDATED_TECHNICAL_AUDIT_DATE)
            .technicalAuditScore(UPDATED_TECHNICAL_AUDIT_SCORE)
            .thirdRdPartyDate(UPDATED_THIRD_RD_PARTY_DATE)
            .thirdRdPartyScore(UPDATED_THIRD_RD_PARTY_SCORE)
            .bopeDate(UPDATED_BOPE_DATE)
            .bopeScore(UPDATED_BOPE_SCORE)
            .capRequired(UPDATED_CAP_REQUIRED);

        restOperationSiteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOperationSite.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOperationSite))
            )
            .andExpect(status().isOk());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
        OperationSite testOperationSite = operationSiteList.get(operationSiteList.size() - 1);
        assertThat(testOperationSite.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testOperationSite.getLinkSupplierFactory()).isEqualTo(UPDATED_LINK_SUPPLIER_FACTORY);
        assertThat(testOperationSite.getTypeOfSite()).isEqualTo(UPDATED_TYPE_OF_SITE);
        assertThat(testOperationSite.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testOperationSite.getSiteAddress()).isEqualTo(UPDATED_SITE_ADDRESS);
        assertThat(testOperationSite.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testOperationSite.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testOperationSite.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testOperationSite.getBusinessLicense()).isEqualTo(UPDATED_BUSINESS_LICENSE);
        assertThat(testOperationSite.getSasDate()).isEqualTo(UPDATED_SAS_DATE);
        assertThat(testOperationSite.getIso900ValidUtil()).isEqualTo(UPDATED_ISO_900_VALID_UTIL);
        assertThat(testOperationSite.getIso14001ValidUtil()).isEqualTo(UPDATED_ISO_14001_VALID_UTIL);
        assertThat(testOperationSite.getAttributeLor()).isEqualTo(UPDATED_ATTRIBUTE_LOR);
        assertThat(testOperationSite.getSiteQualification()).isEqualTo(UPDATED_SITE_QUALIFICATION);
        assertThat(testOperationSite.getQualificationScore()).isEqualTo(UPDATED_QUALIFICATION_SCORE);
        assertThat(testOperationSite.getPqvScore()).isEqualTo(UPDATED_PQV_SCORE);
        assertThat(testOperationSite.getPqvDate()).isEqualTo(UPDATED_PQV_DATE);
        assertThat(testOperationSite.getPqvDecision()).isEqualTo(UPDATED_PQV_DECISION);
        assertThat(testOperationSite.getTechnicalAuditDate()).isEqualTo(UPDATED_TECHNICAL_AUDIT_DATE);
        assertThat(testOperationSite.getTechnicalAuditScore()).isEqualTo(UPDATED_TECHNICAL_AUDIT_SCORE);
        assertThat(testOperationSite.getThirdRdPartyDate()).isEqualTo(UPDATED_THIRD_RD_PARTY_DATE);
        assertThat(testOperationSite.getThirdRdPartyScore()).isEqualTo(UPDATED_THIRD_RD_PARTY_SCORE);
        assertThat(testOperationSite.getBopeDate()).isEqualTo(UPDATED_BOPE_DATE);
        assertThat(testOperationSite.getBopeScore()).isEqualTo(UPDATED_BOPE_SCORE);
        assertThat(testOperationSite.getCapRequired()).isEqualTo(UPDATED_CAP_REQUIRED);
    }

    @Test
    @Transactional
    void patchNonExistingOperationSite() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();
        operationSite.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperationSiteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, operationSite.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operationSite))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOperationSite() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();
        operationSite.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(operationSite))
            )
            .andExpect(status().isBadRequest());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOperationSite() throws Exception {
        int databaseSizeBeforeUpdate = operationSiteRepository.findAll().size();
        operationSite.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOperationSiteMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(operationSite))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OperationSite in the database
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOperationSite() throws Exception {
        // Initialize the database
        operationSiteRepository.saveAndFlush(operationSite);

        int databaseSizeBeforeDelete = operationSiteRepository.findAll().size();

        // Delete the operationSite
        restOperationSiteMockMvc
            .perform(delete(ENTITY_API_URL_ID, operationSite.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperationSite> operationSiteList = operationSiteRepository.findAll();
        assertThat(operationSiteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
