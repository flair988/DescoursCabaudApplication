package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ProductFinished;
import com.mycompany.myapp.repository.ProductFinishedRepository;
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
 * Integration tests for the {@link ProductFinishedResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductFinishedResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_CATE_GORY = "AAAAAAAAAA";
    private static final String UPDATED_CATE_GORY = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL_RECEIPT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_RECEIPT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/product-finisheds";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductFinishedRepository productFinishedRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductFinishedMockMvc;

    private ProductFinished productFinished;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductFinished createEntity(EntityManager em) {
        ProductFinished productFinished = new ProductFinished()
            .itemName(DEFAULT_ITEM_NAME)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .supplier(DEFAULT_SUPPLIER)
            .supplierEmail(DEFAULT_SUPPLIER_EMAIL)
            .orderDate(DEFAULT_ORDER_DATE)
            .cateGory(DEFAULT_CATE_GORY)
            .remark(DEFAULT_REMARK)
            .materialReceiptDate(DEFAULT_MATERIAL_RECEIPT_DATE)
            .docStatus(DEFAULT_DOC_STATUS)
            .supplierName(DEFAULT_SUPPLIER_NAME);
        return productFinished;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductFinished createUpdatedEntity(EntityManager em) {
        ProductFinished productFinished = new ProductFinished()
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .orderDate(UPDATED_ORDER_DATE)
            .cateGory(UPDATED_CATE_GORY)
            .remark(UPDATED_REMARK)
            .materialReceiptDate(UPDATED_MATERIAL_RECEIPT_DATE)
            .docStatus(UPDATED_DOC_STATUS)
            .supplierName(UPDATED_SUPPLIER_NAME);
        return productFinished;
    }

    @BeforeEach
    public void initTest() {
        productFinished = createEntity(em);
    }

    @Test
    @Transactional
    void createProductFinished() throws Exception {
        int databaseSizeBeforeCreate = productFinishedRepository.findAll().size();
        // Create the ProductFinished
        restProductFinishedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isCreated());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeCreate + 1);
        ProductFinished testProductFinished = productFinishedList.get(productFinishedList.size() - 1);
        assertThat(testProductFinished.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testProductFinished.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testProductFinished.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testProductFinished.getSupplierEmail()).isEqualTo(DEFAULT_SUPPLIER_EMAIL);
        assertThat(testProductFinished.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testProductFinished.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testProductFinished.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testProductFinished.getMaterialReceiptDate()).isEqualTo(DEFAULT_MATERIAL_RECEIPT_DATE);
        assertThat(testProductFinished.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
        assertThat(testProductFinished.getSupplierName()).isEqualTo(DEFAULT_SUPPLIER_NAME);
    }

    @Test
    @Transactional
    void createProductFinishedWithExistingId() throws Exception {
        // Create the ProductFinished with an existing ID
        productFinished.setId(1L);

        int databaseSizeBeforeCreate = productFinishedRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductFinishedMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductFinisheds() throws Exception {
        // Initialize the database
        productFinishedRepository.saveAndFlush(productFinished);

        // Get all the productFinishedList
        restProductFinishedMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productFinished.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].supplierEmail").value(hasItem(DEFAULT_SUPPLIER_EMAIL)))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(DEFAULT_ORDER_DATE)))
            .andExpect(jsonPath("$.[*].cateGory").value(hasItem(DEFAULT_CATE_GORY)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].materialReceiptDate").value(hasItem(DEFAULT_MATERIAL_RECEIPT_DATE)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)))
            .andExpect(jsonPath("$.[*].supplierName").value(hasItem(DEFAULT_SUPPLIER_NAME)));
    }

    @Test
    @Transactional
    void getProductFinished() throws Exception {
        // Initialize the database
        productFinishedRepository.saveAndFlush(productFinished);

        // Get the productFinished
        restProductFinishedMockMvc
            .perform(get(ENTITY_API_URL_ID, productFinished.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productFinished.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.supplierEmail").value(DEFAULT_SUPPLIER_EMAIL))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE))
            .andExpect(jsonPath("$.cateGory").value(DEFAULT_CATE_GORY))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.materialReceiptDate").value(DEFAULT_MATERIAL_RECEIPT_DATE))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS))
            .andExpect(jsonPath("$.supplierName").value(DEFAULT_SUPPLIER_NAME));
    }

    @Test
    @Transactional
    void getNonExistingProductFinished() throws Exception {
        // Get the productFinished
        restProductFinishedMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProductFinished() throws Exception {
        // Initialize the database
        productFinishedRepository.saveAndFlush(productFinished);

        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();

        // Update the productFinished
        ProductFinished updatedProductFinished = productFinishedRepository.findById(productFinished.getId()).get();
        // Disconnect from session so that the updates on updatedProductFinished are not directly saved in db
        em.detach(updatedProductFinished);
        updatedProductFinished
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .orderDate(UPDATED_ORDER_DATE)
            .cateGory(UPDATED_CATE_GORY)
            .remark(UPDATED_REMARK)
            .materialReceiptDate(UPDATED_MATERIAL_RECEIPT_DATE)
            .docStatus(UPDATED_DOC_STATUS)
            .supplierName(UPDATED_SUPPLIER_NAME);

        restProductFinishedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProductFinished.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProductFinished))
            )
            .andExpect(status().isOk());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
        ProductFinished testProductFinished = productFinishedList.get(productFinishedList.size() - 1);
        assertThat(testProductFinished.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testProductFinished.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testProductFinished.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testProductFinished.getSupplierEmail()).isEqualTo(UPDATED_SUPPLIER_EMAIL);
        assertThat(testProductFinished.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testProductFinished.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testProductFinished.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testProductFinished.getMaterialReceiptDate()).isEqualTo(UPDATED_MATERIAL_RECEIPT_DATE);
        assertThat(testProductFinished.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testProductFinished.getSupplierName()).isEqualTo(UPDATED_SUPPLIER_NAME);
    }

    @Test
    @Transactional
    void putNonExistingProductFinished() throws Exception {
        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();
        productFinished.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductFinishedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productFinished.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductFinished() throws Exception {
        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();
        productFinished.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductFinishedMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductFinished() throws Exception {
        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();
        productFinished.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductFinishedMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductFinishedWithPatch() throws Exception {
        // Initialize the database
        productFinishedRepository.saveAndFlush(productFinished);

        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();

        // Update the productFinished using partial update
        ProductFinished partialUpdatedProductFinished = new ProductFinished();
        partialUpdatedProductFinished.setId(productFinished.getId());

        partialUpdatedProductFinished.orderDate(UPDATED_ORDER_DATE).remark(UPDATED_REMARK).docStatus(UPDATED_DOC_STATUS);

        restProductFinishedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductFinished.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductFinished))
            )
            .andExpect(status().isOk());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
        ProductFinished testProductFinished = productFinishedList.get(productFinishedList.size() - 1);
        assertThat(testProductFinished.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testProductFinished.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testProductFinished.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testProductFinished.getSupplierEmail()).isEqualTo(DEFAULT_SUPPLIER_EMAIL);
        assertThat(testProductFinished.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testProductFinished.getCateGory()).isEqualTo(DEFAULT_CATE_GORY);
        assertThat(testProductFinished.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testProductFinished.getMaterialReceiptDate()).isEqualTo(DEFAULT_MATERIAL_RECEIPT_DATE);
        assertThat(testProductFinished.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testProductFinished.getSupplierName()).isEqualTo(DEFAULT_SUPPLIER_NAME);
    }

    @Test
    @Transactional
    void fullUpdateProductFinishedWithPatch() throws Exception {
        // Initialize the database
        productFinishedRepository.saveAndFlush(productFinished);

        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();

        // Update the productFinished using partial update
        ProductFinished partialUpdatedProductFinished = new ProductFinished();
        partialUpdatedProductFinished.setId(productFinished.getId());

        partialUpdatedProductFinished
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .orderDate(UPDATED_ORDER_DATE)
            .cateGory(UPDATED_CATE_GORY)
            .remark(UPDATED_REMARK)
            .materialReceiptDate(UPDATED_MATERIAL_RECEIPT_DATE)
            .docStatus(UPDATED_DOC_STATUS)
            .supplierName(UPDATED_SUPPLIER_NAME);

        restProductFinishedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductFinished.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductFinished))
            )
            .andExpect(status().isOk());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
        ProductFinished testProductFinished = productFinishedList.get(productFinishedList.size() - 1);
        assertThat(testProductFinished.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testProductFinished.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testProductFinished.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testProductFinished.getSupplierEmail()).isEqualTo(UPDATED_SUPPLIER_EMAIL);
        assertThat(testProductFinished.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testProductFinished.getCateGory()).isEqualTo(UPDATED_CATE_GORY);
        assertThat(testProductFinished.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testProductFinished.getMaterialReceiptDate()).isEqualTo(UPDATED_MATERIAL_RECEIPT_DATE);
        assertThat(testProductFinished.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testProductFinished.getSupplierName()).isEqualTo(UPDATED_SUPPLIER_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingProductFinished() throws Exception {
        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();
        productFinished.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductFinishedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productFinished.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductFinished() throws Exception {
        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();
        productFinished.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductFinishedMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductFinished() throws Exception {
        int databaseSizeBeforeUpdate = productFinishedRepository.findAll().size();
        productFinished.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductFinishedMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productFinished))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductFinished in the database
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductFinished() throws Exception {
        // Initialize the database
        productFinishedRepository.saveAndFlush(productFinished);

        int databaseSizeBeforeDelete = productFinishedRepository.findAll().size();

        // Delete the productFinished
        restProductFinishedMockMvc
            .perform(delete(ENTITY_API_URL_ID, productFinished.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductFinished> productFinishedList = productFinishedRepository.findAll();
        assertThat(productFinishedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
