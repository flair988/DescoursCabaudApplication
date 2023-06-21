package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.CommercialInvoice;
import com.mycompany.myapp.repository.CommercialInvoiceRepository;
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
 * Integration tests for the {@link CommercialInvoiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommercialInvoiceResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/commercial-invoices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CommercialInvoiceRepository commercialInvoiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommercialInvoiceMockMvc;

    private CommercialInvoice commercialInvoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommercialInvoice createEntity(EntityManager em) {
        CommercialInvoice commercialInvoice = new CommercialInvoice()
            .itemName(DEFAULT_ITEM_NAME)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .date(DEFAULT_DATE)
            .client(DEFAULT_CLIENT)
            .cateGory(DEFAULT_CATE_GORY)
            .totalPrice(DEFAULT_TOTAL_PRICE)
            .currency(DEFAULT_CURRENCY)
            .remarks(DEFAULT_REMARKS)
            .clientId(DEFAULT_CLIENT_ID)
            .docStatus(DEFAULT_DOC_STATUS);
        return commercialInvoice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommercialInvoice createUpdatedEntity(EntityManager em) {
        CommercialInvoice commercialInvoice = new CommercialInvoice()
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .date(UPDATED_DATE)
            .client(UPDATED_CLIENT)
            .cateGory(UPDATED_CATE_GORY)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .clientId(UPDATED_CLIENT_ID)
            .docStatus(UPDATED_DOC_STATUS);
        return commercialInvoice;
    }

    @BeforeEach
    public void initTest() {
        commercialInvoice = createEntity(em);
    }

    @Test
    @Transactional
    void createCommercialInvoice() throws Exception {
        int databaseSizeBeforeCreate = commercialInvoiceRepository.findAll().size();
        // Create the CommercialInvoice
        restCommercialInvoiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isCreated());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeCreate + 1);
        CommercialInvoice testCommercialInvoice = commercialInvoiceList.get(commercialInvoiceList.size() - 1);
        assertThat(testCommercialInvoice.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testCommercialInvoice.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testCommercialInvoice.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCommercialInvoice.getClient()).isEqualTo(DEFAULT_CLIENT);
        assertThat(testCommercialInvoice.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testCommercialInvoice.getTotalPrice()).isEqualTo(DEFAULT_TOTAL_PRICE);
        assertThat(testCommercialInvoice.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testCommercialInvoice.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testCommercialInvoice.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testCommercialInvoice.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
    }

    @Test
    @Transactional
    void createCommercialInvoiceWithExistingId() throws Exception {
        // Create the CommercialInvoice with an existing ID
        commercialInvoice.setId(1L);

        int databaseSizeBeforeCreate = commercialInvoiceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommercialInvoiceMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommercialInvoices() throws Exception {
        // Initialize the database
        commercialInvoiceRepository.saveAndFlush(commercialInvoice);

        // Get all the commercialInvoiceList
        restCommercialInvoiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commercialInvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].client").value(hasItem(DEFAULT_CLIENT)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)))
            .andExpect(jsonPath("$.[*].totalPrice").value(hasItem(DEFAULT_TOTAL_PRICE)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)));
    }

    @Test
    @Transactional
    void getCommercialInvoice() throws Exception {
        // Initialize the database
        commercialInvoiceRepository.saveAndFlush(commercialInvoice);

        // Get the commercialInvoice
        restCommercialInvoiceMockMvc
            .perform(get(ENTITY_API_URL_ID, commercialInvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commercialInvoice.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.client").value(DEFAULT_CLIENT))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY))
            .andExpect(jsonPath("$.totalPrice").value(DEFAULT_TOTAL_PRICE))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingCommercialInvoice() throws Exception {
        // Get the commercialInvoice
        restCommercialInvoiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommercialInvoice() throws Exception {
        // Initialize the database
        commercialInvoiceRepository.saveAndFlush(commercialInvoice);

        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();

        // Update the commercialInvoice
        CommercialInvoice updatedCommercialInvoice = commercialInvoiceRepository.findById(commercialInvoice.getId()).get();
        // Disconnect from session so that the updates on updatedCommercialInvoice are not directly saved in db
        em.detach(updatedCommercialInvoice);
        updatedCommercialInvoice
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .date(UPDATED_DATE)
            .client(UPDATED_CLIENT)
            .cateGory(UPDATED_CATE_GORY)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .clientId(UPDATED_CLIENT_ID)
            .docStatus(UPDATED_DOC_STATUS);

        restCommercialInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCommercialInvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCommercialInvoice))
            )
            .andExpect(status().isOk());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
        CommercialInvoice testCommercialInvoice = commercialInvoiceList.get(commercialInvoiceList.size() - 1);
        assertThat(testCommercialInvoice.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testCommercialInvoice.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testCommercialInvoice.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCommercialInvoice.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testCommercialInvoice.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testCommercialInvoice.getTotalPrice()).isEqualTo(UPDATED_TOTAL_PRICE);
        assertThat(testCommercialInvoice.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCommercialInvoice.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testCommercialInvoice.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testCommercialInvoice.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingCommercialInvoice() throws Exception {
        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();
        commercialInvoice.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommercialInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, commercialInvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommercialInvoice() throws Exception {
        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();
        commercialInvoice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommercialInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommercialInvoice() throws Exception {
        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();
        commercialInvoice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommercialInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommercialInvoiceWithPatch() throws Exception {
        // Initialize the database
        commercialInvoiceRepository.saveAndFlush(commercialInvoice);

        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();

        // Update the commercialInvoice using partial update
        CommercialInvoice partialUpdatedCommercialInvoice = new CommercialInvoice();
        partialUpdatedCommercialInvoice.setId(commercialInvoice.getId());

        partialUpdatedCommercialInvoice
            .client(UPDATED_CLIENT)
            .cateGory(UPDATED_CATE_GORY)
            .currency(UPDATED_CURRENCY)
            .clientId(UPDATED_CLIENT_ID);

        restCommercialInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommercialInvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCommercialInvoice))
            )
            .andExpect(status().isOk());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
        CommercialInvoice testCommercialInvoice = commercialInvoiceList.get(commercialInvoiceList.size() - 1);
        assertThat(testCommercialInvoice.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testCommercialInvoice.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testCommercialInvoice.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCommercialInvoice.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testCommercialInvoice.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testCommercialInvoice.getTotalPrice()).isEqualTo(DEFAULT_TOTAL_PRICE);
        assertThat(testCommercialInvoice.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCommercialInvoice.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testCommercialInvoice.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testCommercialInvoice.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateCommercialInvoiceWithPatch() throws Exception {
        // Initialize the database
        commercialInvoiceRepository.saveAndFlush(commercialInvoice);

        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();

        // Update the commercialInvoice using partial update
        CommercialInvoice partialUpdatedCommercialInvoice = new CommercialInvoice();
        partialUpdatedCommercialInvoice.setId(commercialInvoice.getId());

        partialUpdatedCommercialInvoice
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .date(UPDATED_DATE)
            .client(UPDATED_CLIENT)
            .cateGory(UPDATED_CATE_GORY)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .currency(UPDATED_CURRENCY)
            .remarks(UPDATED_REMARKS)
            .clientId(UPDATED_CLIENT_ID)
            .docStatus(UPDATED_DOC_STATUS);

        restCommercialInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommercialInvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCommercialInvoice))
            )
            .andExpect(status().isOk());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
        CommercialInvoice testCommercialInvoice = commercialInvoiceList.get(commercialInvoiceList.size() - 1);
        assertThat(testCommercialInvoice.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testCommercialInvoice.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testCommercialInvoice.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCommercialInvoice.getClient()).isEqualTo(UPDATED_CLIENT);
        assertThat(testCommercialInvoice.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testCommercialInvoice.getTotalPrice()).isEqualTo(UPDATED_TOTAL_PRICE);
        assertThat(testCommercialInvoice.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCommercialInvoice.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testCommercialInvoice.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testCommercialInvoice.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingCommercialInvoice() throws Exception {
        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();
        commercialInvoice.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommercialInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, commercialInvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommercialInvoice() throws Exception {
        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();
        commercialInvoice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommercialInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommercialInvoice() throws Exception {
        int databaseSizeBeforeUpdate = commercialInvoiceRepository.findAll().size();
        commercialInvoice.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommercialInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(commercialInvoice))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommercialInvoice in the database
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommercialInvoice() throws Exception {
        // Initialize the database
        commercialInvoiceRepository.saveAndFlush(commercialInvoice);

        int databaseSizeBeforeDelete = commercialInvoiceRepository.findAll().size();

        // Delete the commercialInvoice
        restCommercialInvoiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, commercialInvoice.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommercialInvoice> commercialInvoiceList = commercialInvoiceRepository.findAll();
        assertThat(commercialInvoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
