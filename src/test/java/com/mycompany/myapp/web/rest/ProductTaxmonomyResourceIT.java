package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ProductTaxmonomy;
import com.mycompany.myapp.repository.ProductTaxmonomyRepository;
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
 * Integration tests for the {@link ProductTaxmonomyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductTaxmonomyResourceIT {

    private static final String DEFAULT_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/product-taxmonomies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductTaxmonomyRepository productTaxmonomyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductTaxmonomyMockMvc;

    private ProductTaxmonomy productTaxmonomy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductTaxmonomy createEntity(EntityManager em) {
        ProductTaxmonomy productTaxmonomy = new ProductTaxmonomy()
            .itemId(DEFAULT_ITEM_ID)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .groupName(DEFAULT_GROUP_NAME)
            .parentGroupName(DEFAULT_PARENT_GROUP_NAME)
            .subGroupName(DEFAULT_SUB_GROUP_NAME)
            .description(DEFAULT_DESCRIPTION);
        return productTaxmonomy;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductTaxmonomy createUpdatedEntity(EntityManager em) {
        ProductTaxmonomy productTaxmonomy = new ProductTaxmonomy()
            .itemId(UPDATED_ITEM_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .itemName(UPDATED_ITEM_NAME)
            .groupName(UPDATED_GROUP_NAME)
            .parentGroupName(UPDATED_PARENT_GROUP_NAME)
            .subGroupName(UPDATED_SUB_GROUP_NAME)
            .description(UPDATED_DESCRIPTION);
        return productTaxmonomy;
    }

    @BeforeEach
    public void initTest() {
        productTaxmonomy = createEntity(em);
    }

    @Test
    @Transactional
    void createProductTaxmonomy() throws Exception {
        int databaseSizeBeforeCreate = productTaxmonomyRepository.findAll().size();
        // Create the ProductTaxmonomy
        restProductTaxmonomyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isCreated());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeCreate + 1);
        ProductTaxmonomy testProductTaxmonomy = productTaxmonomyList.get(productTaxmonomyList.size() - 1);
        assertThat(testProductTaxmonomy.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testProductTaxmonomy.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testProductTaxmonomy.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testProductTaxmonomy.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testProductTaxmonomy.getParentGroupName()).isEqualTo(DEFAULT_PARENT_GROUP_NAME);
        assertThat(testProductTaxmonomy.getSubGroupName()).isEqualTo(DEFAULT_SUB_GROUP_NAME);
        assertThat(testProductTaxmonomy.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void createProductTaxmonomyWithExistingId() throws Exception {
        // Create the ProductTaxmonomy with an existing ID
        productTaxmonomy.setId(1L);

        int databaseSizeBeforeCreate = productTaxmonomyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductTaxmonomyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductTaxmonomies() throws Exception {
        // Initialize the database
        productTaxmonomyRepository.saveAndFlush(productTaxmonomy);

        // Get all the productTaxmonomyList
        restProductTaxmonomyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productTaxmonomy.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].parentGroupName").value(hasItem(DEFAULT_PARENT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].subGroupName").value(hasItem(DEFAULT_SUB_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getProductTaxmonomy() throws Exception {
        // Initialize the database
        productTaxmonomyRepository.saveAndFlush(productTaxmonomy);

        // Get the productTaxmonomy
        restProductTaxmonomyMockMvc
            .perform(get(ENTITY_API_URL_ID, productTaxmonomy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productTaxmonomy.getId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
            .andExpect(jsonPath("$.parentGroupName").value(DEFAULT_PARENT_GROUP_NAME))
            .andExpect(jsonPath("$.subGroupName").value(DEFAULT_SUB_GROUP_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingProductTaxmonomy() throws Exception {
        // Get the productTaxmonomy
        restProductTaxmonomyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProductTaxmonomy() throws Exception {
        // Initialize the database
        productTaxmonomyRepository.saveAndFlush(productTaxmonomy);

        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();

        // Update the productTaxmonomy
        ProductTaxmonomy updatedProductTaxmonomy = productTaxmonomyRepository.findById(productTaxmonomy.getId()).get();
        // Disconnect from session so that the updates on updatedProductTaxmonomy are not directly saved in db
        em.detach(updatedProductTaxmonomy);
        updatedProductTaxmonomy
            .itemId(UPDATED_ITEM_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .itemName(UPDATED_ITEM_NAME)
            .groupName(UPDATED_GROUP_NAME)
            .parentGroupName(UPDATED_PARENT_GROUP_NAME)
            .subGroupName(UPDATED_SUB_GROUP_NAME)
            .description(UPDATED_DESCRIPTION);

        restProductTaxmonomyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProductTaxmonomy.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProductTaxmonomy))
            )
            .andExpect(status().isOk());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
        ProductTaxmonomy testProductTaxmonomy = productTaxmonomyList.get(productTaxmonomyList.size() - 1);
        assertThat(testProductTaxmonomy.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testProductTaxmonomy.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testProductTaxmonomy.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testProductTaxmonomy.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testProductTaxmonomy.getParentGroupName()).isEqualTo(UPDATED_PARENT_GROUP_NAME);
        assertThat(testProductTaxmonomy.getSubGroupName()).isEqualTo(UPDATED_SUB_GROUP_NAME);
        assertThat(testProductTaxmonomy.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingProductTaxmonomy() throws Exception {
        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();
        productTaxmonomy.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTaxmonomyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productTaxmonomy.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductTaxmonomy() throws Exception {
        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();
        productTaxmonomy.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTaxmonomyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductTaxmonomy() throws Exception {
        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();
        productTaxmonomy.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTaxmonomyMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductTaxmonomyWithPatch() throws Exception {
        // Initialize the database
        productTaxmonomyRepository.saveAndFlush(productTaxmonomy);

        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();

        // Update the productTaxmonomy using partial update
        ProductTaxmonomy partialUpdatedProductTaxmonomy = new ProductTaxmonomy();
        partialUpdatedProductTaxmonomy.setId(productTaxmonomy.getId());

        partialUpdatedProductTaxmonomy.itemId(UPDATED_ITEM_ID).description(UPDATED_DESCRIPTION);

        restProductTaxmonomyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductTaxmonomy.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductTaxmonomy))
            )
            .andExpect(status().isOk());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
        ProductTaxmonomy testProductTaxmonomy = productTaxmonomyList.get(productTaxmonomyList.size() - 1);
        assertThat(testProductTaxmonomy.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testProductTaxmonomy.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testProductTaxmonomy.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testProductTaxmonomy.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testProductTaxmonomy.getParentGroupName()).isEqualTo(DEFAULT_PARENT_GROUP_NAME);
        assertThat(testProductTaxmonomy.getSubGroupName()).isEqualTo(DEFAULT_SUB_GROUP_NAME);
        assertThat(testProductTaxmonomy.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateProductTaxmonomyWithPatch() throws Exception {
        // Initialize the database
        productTaxmonomyRepository.saveAndFlush(productTaxmonomy);

        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();

        // Update the productTaxmonomy using partial update
        ProductTaxmonomy partialUpdatedProductTaxmonomy = new ProductTaxmonomy();
        partialUpdatedProductTaxmonomy.setId(productTaxmonomy.getId());

        partialUpdatedProductTaxmonomy
            .itemId(UPDATED_ITEM_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .itemName(UPDATED_ITEM_NAME)
            .groupName(UPDATED_GROUP_NAME)
            .parentGroupName(UPDATED_PARENT_GROUP_NAME)
            .subGroupName(UPDATED_SUB_GROUP_NAME)
            .description(UPDATED_DESCRIPTION);

        restProductTaxmonomyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductTaxmonomy.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProductTaxmonomy))
            )
            .andExpect(status().isOk());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
        ProductTaxmonomy testProductTaxmonomy = productTaxmonomyList.get(productTaxmonomyList.size() - 1);
        assertThat(testProductTaxmonomy.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testProductTaxmonomy.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testProductTaxmonomy.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testProductTaxmonomy.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testProductTaxmonomy.getParentGroupName()).isEqualTo(UPDATED_PARENT_GROUP_NAME);
        assertThat(testProductTaxmonomy.getSubGroupName()).isEqualTo(UPDATED_SUB_GROUP_NAME);
        assertThat(testProductTaxmonomy.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingProductTaxmonomy() throws Exception {
        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();
        productTaxmonomy.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTaxmonomyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productTaxmonomy.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductTaxmonomy() throws Exception {
        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();
        productTaxmonomy.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTaxmonomyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductTaxmonomy() throws Exception {
        int databaseSizeBeforeUpdate = productTaxmonomyRepository.findAll().size();
        productTaxmonomy.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTaxmonomyMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(productTaxmonomy))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductTaxmonomy in the database
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductTaxmonomy() throws Exception {
        // Initialize the database
        productTaxmonomyRepository.saveAndFlush(productTaxmonomy);

        int databaseSizeBeforeDelete = productTaxmonomyRepository.findAll().size();

        // Delete the productTaxmonomy
        restProductTaxmonomyMockMvc
            .perform(delete(ENTITY_API_URL_ID, productTaxmonomy.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductTaxmonomy> productTaxmonomyList = productTaxmonomyRepository.findAll();
        assertThat(productTaxmonomyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
