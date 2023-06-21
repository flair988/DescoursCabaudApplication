package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.PurchaseReturn;
import com.mycompany.myapp.repository.PurchaseReturnRepository;
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
 * Integration tests for the {@link PurchaseReturnResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseReturnResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOC_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL_RETURN_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_RETURN_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_REASON_FOR_RETURN = "AAAAAAAAAA";
    private static final String UPDATED_REASON_FOR_RETURN = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHASE_DEPT = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASE_DEPT = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DOC_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUPPLIER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUPPLIER_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/purchase-returns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseReturnRepository purchaseReturnRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseReturnMockMvc;

    private PurchaseReturn purchaseReturn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseReturn createEntity(EntityManager em) {
        PurchaseReturn purchaseReturn = new PurchaseReturn()
            .itemName(DEFAULT_ITEM_NAME)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .docNumber(DEFAULT_DOC_NUMBER)
            .supplier(DEFAULT_SUPPLIER)
            .supplierEmail(DEFAULT_SUPPLIER_EMAIL)
            .materialReturnDate(DEFAULT_MATERIAL_RETURN_DATE)
            .reasonForReturn(DEFAULT_REASON_FOR_RETURN)
            .purchaseDept(DEFAULT_PURCHASE_DEPT)
            .remarks(DEFAULT_REMARKS)
            .docStatus(DEFAULT_DOC_STATUS)
            .itemCode(DEFAULT_ITEM_CODE)
            .supplierCode(DEFAULT_SUPPLIER_CODE);
        return purchaseReturn;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseReturn createUpdatedEntity(EntityManager em) {
        PurchaseReturn purchaseReturn = new PurchaseReturn()
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .docNumber(UPDATED_DOC_NUMBER)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .materialReturnDate(UPDATED_MATERIAL_RETURN_DATE)
            .reasonForReturn(UPDATED_REASON_FOR_RETURN)
            .purchaseDept(UPDATED_PURCHASE_DEPT)
            .remarks(UPDATED_REMARKS)
            .docStatus(UPDATED_DOC_STATUS)
            .itemCode(UPDATED_ITEM_CODE)
            .supplierCode(UPDATED_SUPPLIER_CODE);
        return purchaseReturn;
    }

    @BeforeEach
    public void initTest() {
        purchaseReturn = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseReturn() throws Exception {
        int databaseSizeBeforeCreate = purchaseReturnRepository.findAll().size();
        // Create the PurchaseReturn
        restPurchaseReturnMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isCreated());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseReturn testPurchaseReturn = purchaseReturnList.get(purchaseReturnList.size() - 1);
        assertThat(testPurchaseReturn.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPurchaseReturn.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testPurchaseReturn.getDocNumber()).isEqualTo(DEFAULT_DOC_NUMBER);
        assertThat(testPurchaseReturn.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testPurchaseReturn.getSupplierEmail()).isEqualTo(DEFAULT_SUPPLIER_EMAIL);
        assertThat(testPurchaseReturn.getMaterialReturnDate()).isEqualTo(DEFAULT_MATERIAL_RETURN_DATE);
        assertThat(testPurchaseReturn.getReasonForReturn()).isEqualTo(DEFAULT_REASON_FOR_RETURN);
        assertThat(testPurchaseReturn.getPurchaseDept()).isEqualTo(DEFAULT_PURCHASE_DEPT);
        assertThat(testPurchaseReturn.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPurchaseReturn.getDocStatus()).isEqualTo(DEFAULT_DOC_STATUS);
        assertThat(testPurchaseReturn.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testPurchaseReturn.getSupplierCode()).isEqualTo(DEFAULT_SUPPLIER_CODE);
    }

    @Test
    @Transactional
    void createPurchaseReturnWithExistingId() throws Exception {
        // Create the PurchaseReturn with an existing ID
        purchaseReturn.setId(1L);

        int databaseSizeBeforeCreate = purchaseReturnRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseReturnMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseReturns() throws Exception {
        // Initialize the database
        purchaseReturnRepository.saveAndFlush(purchaseReturn);

        // Get all the purchaseReturnList
        restPurchaseReturnMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseReturn.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].docNumber").value(hasItem(DEFAULT_DOC_NUMBER)))
            .andExpect(jsonPath("$.[*].supplier").value(hasItem(DEFAULT_SUPPLIER)))
            .andExpect(jsonPath("$.[*].supplierEmail").value(hasItem(DEFAULT_SUPPLIER_EMAIL)))
            .andExpect(jsonPath("$.[*].materialReturnDate").value(hasItem(DEFAULT_MATERIAL_RETURN_DATE)))
            .andExpect(jsonPath("$.[*].reasonForReturn").value(hasItem(DEFAULT_REASON_FOR_RETURN)))
            .andExpect(jsonPath("$.[*].purchaseDept").value(hasItem(DEFAULT_PURCHASE_DEPT)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].docStatus").value(hasItem(DEFAULT_DOC_STATUS)))
            .andExpect(jsonPath("$.[*].itemCode").value(hasItem(DEFAULT_ITEM_CODE)))
            .andExpect(jsonPath("$.[*].supplierCode").value(hasItem(DEFAULT_SUPPLIER_CODE)));
    }

    @Test
    @Transactional
    void getPurchaseReturn() throws Exception {
        // Initialize the database
        purchaseReturnRepository.saveAndFlush(purchaseReturn);

        // Get the purchaseReturn
        restPurchaseReturnMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseReturn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseReturn.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.docNumber").value(DEFAULT_DOC_NUMBER))
            .andExpect(jsonPath("$.supplier").value(DEFAULT_SUPPLIER))
            .andExpect(jsonPath("$.supplierEmail").value(DEFAULT_SUPPLIER_EMAIL))
            .andExpect(jsonPath("$.materialReturnDate").value(DEFAULT_MATERIAL_RETURN_DATE))
            .andExpect(jsonPath("$.reasonForReturn").value(DEFAULT_REASON_FOR_RETURN))
            .andExpect(jsonPath("$.purchaseDept").value(DEFAULT_PURCHASE_DEPT))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.docStatus").value(DEFAULT_DOC_STATUS))
            .andExpect(jsonPath("$.itemCode").value(DEFAULT_ITEM_CODE))
            .andExpect(jsonPath("$.supplierCode").value(DEFAULT_SUPPLIER_CODE));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseReturn() throws Exception {
        // Get the purchaseReturn
        restPurchaseReturnMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPurchaseReturn() throws Exception {
        // Initialize the database
        purchaseReturnRepository.saveAndFlush(purchaseReturn);

        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();

        // Update the purchaseReturn
        PurchaseReturn updatedPurchaseReturn = purchaseReturnRepository.findById(purchaseReturn.getId()).get();
        // Disconnect from session so that the updates on updatedPurchaseReturn are not directly saved in db
        em.detach(updatedPurchaseReturn);
        updatedPurchaseReturn
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .docNumber(UPDATED_DOC_NUMBER)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .materialReturnDate(UPDATED_MATERIAL_RETURN_DATE)
            .reasonForReturn(UPDATED_REASON_FOR_RETURN)
            .purchaseDept(UPDATED_PURCHASE_DEPT)
            .remarks(UPDATED_REMARKS)
            .docStatus(UPDATED_DOC_STATUS)
            .itemCode(UPDATED_ITEM_CODE)
            .supplierCode(UPDATED_SUPPLIER_CODE);

        restPurchaseReturnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPurchaseReturn.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPurchaseReturn))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
        PurchaseReturn testPurchaseReturn = purchaseReturnList.get(purchaseReturnList.size() - 1);
        assertThat(testPurchaseReturn.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPurchaseReturn.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testPurchaseReturn.getDocNumber()).isEqualTo(UPDATED_DOC_NUMBER);
        assertThat(testPurchaseReturn.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testPurchaseReturn.getSupplierEmail()).isEqualTo(UPDATED_SUPPLIER_EMAIL);
        assertThat(testPurchaseReturn.getMaterialReturnDate()).isEqualTo(UPDATED_MATERIAL_RETURN_DATE);
        assertThat(testPurchaseReturn.getReasonForReturn()).isEqualTo(UPDATED_REASON_FOR_RETURN);
        assertThat(testPurchaseReturn.getPurchaseDept()).isEqualTo(UPDATED_PURCHASE_DEPT);
        assertThat(testPurchaseReturn.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPurchaseReturn.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testPurchaseReturn.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testPurchaseReturn.getSupplierCode()).isEqualTo(UPDATED_SUPPLIER_CODE);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseReturn() throws Exception {
        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();
        purchaseReturn.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseReturnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseReturn.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseReturn() throws Exception {
        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();
        purchaseReturn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseReturnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseReturn() throws Exception {
        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();
        purchaseReturn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseReturnMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(purchaseReturn)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseReturnWithPatch() throws Exception {
        // Initialize the database
        purchaseReturnRepository.saveAndFlush(purchaseReturn);

        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();

        // Update the purchaseReturn using partial update
        PurchaseReturn partialUpdatedPurchaseReturn = new PurchaseReturn();
        partialUpdatedPurchaseReturn.setId(purchaseReturn.getId());

        partialUpdatedPurchaseReturn
            .docNumber(UPDATED_DOC_NUMBER)
            .materialReturnDate(UPDATED_MATERIAL_RETURN_DATE)
            .purchaseDept(UPDATED_PURCHASE_DEPT)
            .remarks(UPDATED_REMARKS)
            .docStatus(UPDATED_DOC_STATUS)
            .itemCode(UPDATED_ITEM_CODE)
            .supplierCode(UPDATED_SUPPLIER_CODE);

        restPurchaseReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseReturn.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseReturn))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
        PurchaseReturn testPurchaseReturn = purchaseReturnList.get(purchaseReturnList.size() - 1);
        assertThat(testPurchaseReturn.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testPurchaseReturn.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testPurchaseReturn.getDocNumber()).isEqualTo(UPDATED_DOC_NUMBER);
        assertThat(testPurchaseReturn.getSupplier()).isEqualTo(DEFAULT_SUPPLIER);
        assertThat(testPurchaseReturn.getSupplierEmail()).isEqualTo(DEFAULT_SUPPLIER_EMAIL);
        assertThat(testPurchaseReturn.getMaterialReturnDate()).isEqualTo(UPDATED_MATERIAL_RETURN_DATE);
        assertThat(testPurchaseReturn.getReasonForReturn()).isEqualTo(DEFAULT_REASON_FOR_RETURN);
        assertThat(testPurchaseReturn.getPurchaseDept()).isEqualTo(UPDATED_PURCHASE_DEPT);
        assertThat(testPurchaseReturn.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPurchaseReturn.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testPurchaseReturn.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testPurchaseReturn.getSupplierCode()).isEqualTo(UPDATED_SUPPLIER_CODE);
    }

    @Test
    @Transactional
    void fullUpdatePurchaseReturnWithPatch() throws Exception {
        // Initialize the database
        purchaseReturnRepository.saveAndFlush(purchaseReturn);

        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();

        // Update the purchaseReturn using partial update
        PurchaseReturn partialUpdatedPurchaseReturn = new PurchaseReturn();
        partialUpdatedPurchaseReturn.setId(purchaseReturn.getId());

        partialUpdatedPurchaseReturn
            .itemName(UPDATED_ITEM_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .docNumber(UPDATED_DOC_NUMBER)
            .supplier(UPDATED_SUPPLIER)
            .supplierEmail(UPDATED_SUPPLIER_EMAIL)
            .materialReturnDate(UPDATED_MATERIAL_RETURN_DATE)
            .reasonForReturn(UPDATED_REASON_FOR_RETURN)
            .purchaseDept(UPDATED_PURCHASE_DEPT)
            .remarks(UPDATED_REMARKS)
            .docStatus(UPDATED_DOC_STATUS)
            .itemCode(UPDATED_ITEM_CODE)
            .supplierCode(UPDATED_SUPPLIER_CODE);

        restPurchaseReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseReturn.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPurchaseReturn))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
        PurchaseReturn testPurchaseReturn = purchaseReturnList.get(purchaseReturnList.size() - 1);
        assertThat(testPurchaseReturn.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testPurchaseReturn.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testPurchaseReturn.getDocNumber()).isEqualTo(UPDATED_DOC_NUMBER);
        assertThat(testPurchaseReturn.getSupplier()).isEqualTo(UPDATED_SUPPLIER);
        assertThat(testPurchaseReturn.getSupplierEmail()).isEqualTo(UPDATED_SUPPLIER_EMAIL);
        assertThat(testPurchaseReturn.getMaterialReturnDate()).isEqualTo(UPDATED_MATERIAL_RETURN_DATE);
        assertThat(testPurchaseReturn.getReasonForReturn()).isEqualTo(UPDATED_REASON_FOR_RETURN);
        assertThat(testPurchaseReturn.getPurchaseDept()).isEqualTo(UPDATED_PURCHASE_DEPT);
        assertThat(testPurchaseReturn.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPurchaseReturn.getDocStatus()).isEqualTo(UPDATED_DOC_STATUS);
        assertThat(testPurchaseReturn.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testPurchaseReturn.getSupplierCode()).isEqualTo(UPDATED_SUPPLIER_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseReturn() throws Exception {
        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();
        purchaseReturn.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseReturn.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseReturn() throws Exception {
        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();
        purchaseReturn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseReturn() throws Exception {
        int databaseSizeBeforeUpdate = purchaseReturnRepository.findAll().size();
        purchaseReturn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseReturnMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(purchaseReturn))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseReturn in the database
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseReturn() throws Exception {
        // Initialize the database
        purchaseReturnRepository.saveAndFlush(purchaseReturn);

        int databaseSizeBeforeDelete = purchaseReturnRepository.findAll().size();

        // Delete the purchaseReturn
        restPurchaseReturnMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseReturn.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseReturn> purchaseReturnList = purchaseReturnRepository.findAll();
        assertThat(purchaseReturnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
