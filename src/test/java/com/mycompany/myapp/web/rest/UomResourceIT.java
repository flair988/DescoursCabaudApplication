package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Uom;
import com.mycompany.myapp.repository.UomRepository;
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
 * Integration tests for the {@link UomResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UomResourceIT {

    private static final String DEFAULT_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_UOM = "AAAAAAAAAA";
    private static final String UPDATED_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_UOM_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_UOM_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ITEMS = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ITEMS = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_BOARD_ID = "AAAAAAAAAA";
    private static final String UPDATED_BOARD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/uoms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UomRepository uomRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUomMockMvc;

    private Uom uom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uom createEntity(EntityManager em) {
        Uom uom = new Uom()
            .itemId(DEFAULT_ITEM_ID)
            .uom(DEFAULT_UOM)
            .uomGroup(DEFAULT_UOM_GROUP)
            .description(DEFAULT_DESCRIPTION)
            .subItems(DEFAULT_SUB_ITEMS)
            .parentItem(DEFAULT_PARENT_ITEM)
            .boardId(DEFAULT_BOARD_ID)
            .kingdeeId(DEFAULT_KINGDEE_ID);
        return uom;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uom createUpdatedEntity(EntityManager em) {
        Uom uom = new Uom()
            .itemId(UPDATED_ITEM_ID)
            .uom(UPDATED_UOM)
            .uomGroup(UPDATED_UOM_GROUP)
            .description(UPDATED_DESCRIPTION)
            .subItems(UPDATED_SUB_ITEMS)
            .parentItem(UPDATED_PARENT_ITEM)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID);
        return uom;
    }

    @BeforeEach
    public void initTest() {
        uom = createEntity(em);
    }

    @Test
    @Transactional
    void createUom() throws Exception {
        int databaseSizeBeforeCreate = uomRepository.findAll().size();
        // Create the Uom
        restUomMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uom)))
            .andExpect(status().isCreated());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeCreate + 1);
        Uom testUom = uomList.get(uomList.size() - 1);
        assertThat(testUom.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testUom.getUom()).isEqualTo(DEFAULT_UOM);
        assertThat(testUom.getUomGroup()).isEqualTo(DEFAULT_UOM_GROUP);
        assertThat(testUom.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testUom.getSubItems()).isEqualTo(DEFAULT_SUB_ITEMS);
        assertThat(testUom.getParentItem()).isEqualTo(DEFAULT_PARENT_ITEM);
        assertThat(testUom.getBoardId()).isEqualTo(DEFAULT_BOARD_ID);
        assertThat(testUom.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
    }

    @Test
    @Transactional
    void createUomWithExistingId() throws Exception {
        // Create the Uom with an existing ID
        uom.setId(1L);

        int databaseSizeBeforeCreate = uomRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUomMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uom)))
            .andExpect(status().isBadRequest());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUoms() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        // Get all the uomList
        restUomMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uom.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID)))
            .andExpect(jsonPath("$.[*].uom").value(hasItem(DEFAULT_UOM)))
            .andExpect(jsonPath("$.[*].uomGroup").value(hasItem(DEFAULT_UOM_GROUP)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].subItems").value(hasItem(DEFAULT_SUB_ITEMS)))
            .andExpect(jsonPath("$.[*].parentItem").value(hasItem(DEFAULT_PARENT_ITEM)))
            .andExpect(jsonPath("$.[*].boardId").value(hasItem(DEFAULT_BOARD_ID)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)));
    }

    @Test
    @Transactional
    void getUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        // Get the uom
        restUomMockMvc
            .perform(get(ENTITY_API_URL_ID, uom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uom.getId().intValue()))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID))
            .andExpect(jsonPath("$.uom").value(DEFAULT_UOM))
            .andExpect(jsonPath("$.uomGroup").value(DEFAULT_UOM_GROUP))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.subItems").value(DEFAULT_SUB_ITEMS))
            .andExpect(jsonPath("$.parentItem").value(DEFAULT_PARENT_ITEM))
            .andExpect(jsonPath("$.boardId").value(DEFAULT_BOARD_ID))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID));
    }

    @Test
    @Transactional
    void getNonExistingUom() throws Exception {
        // Get the uom
        restUomMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        int databaseSizeBeforeUpdate = uomRepository.findAll().size();

        // Update the uom
        Uom updatedUom = uomRepository.findById(uom.getId()).get();
        // Disconnect from session so that the updates on updatedUom are not directly saved in db
        em.detach(updatedUom);
        updatedUom
            .itemId(UPDATED_ITEM_ID)
            .uom(UPDATED_UOM)
            .uomGroup(UPDATED_UOM_GROUP)
            .description(UPDATED_DESCRIPTION)
            .subItems(UPDATED_SUB_ITEMS)
            .parentItem(UPDATED_PARENT_ITEM)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID);

        restUomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUom.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUom))
            )
            .andExpect(status().isOk());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
        Uom testUom = uomList.get(uomList.size() - 1);
        assertThat(testUom.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testUom.getUom()).isEqualTo(UPDATED_UOM);
        assertThat(testUom.getUomGroup()).isEqualTo(UPDATED_UOM_GROUP);
        assertThat(testUom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUom.getSubItems()).isEqualTo(UPDATED_SUB_ITEMS);
        assertThat(testUom.getParentItem()).isEqualTo(UPDATED_PARENT_ITEM);
        assertThat(testUom.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testUom.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
    }

    @Test
    @Transactional
    void putNonExistingUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();
        uom.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, uom.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uom))
            )
            .andExpect(status().isBadRequest());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();
        uom.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(uom))
            )
            .andExpect(status().isBadRequest());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();
        uom.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUomMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(uom)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUomWithPatch() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        int databaseSizeBeforeUpdate = uomRepository.findAll().size();

        // Update the uom using partial update
        Uom partialUpdatedUom = new Uom();
        partialUpdatedUom.setId(uom.getId());

        partialUpdatedUom.uom(UPDATED_UOM).uomGroup(UPDATED_UOM_GROUP).description(UPDATED_DESCRIPTION).boardId(UPDATED_BOARD_ID);

        restUomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUom.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUom))
            )
            .andExpect(status().isOk());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
        Uom testUom = uomList.get(uomList.size() - 1);
        assertThat(testUom.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testUom.getUom()).isEqualTo(UPDATED_UOM);
        assertThat(testUom.getUomGroup()).isEqualTo(UPDATED_UOM_GROUP);
        assertThat(testUom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUom.getSubItems()).isEqualTo(DEFAULT_SUB_ITEMS);
        assertThat(testUom.getParentItem()).isEqualTo(DEFAULT_PARENT_ITEM);
        assertThat(testUom.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testUom.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
    }

    @Test
    @Transactional
    void fullUpdateUomWithPatch() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        int databaseSizeBeforeUpdate = uomRepository.findAll().size();

        // Update the uom using partial update
        Uom partialUpdatedUom = new Uom();
        partialUpdatedUom.setId(uom.getId());

        partialUpdatedUom
            .itemId(UPDATED_ITEM_ID)
            .uom(UPDATED_UOM)
            .uomGroup(UPDATED_UOM_GROUP)
            .description(UPDATED_DESCRIPTION)
            .subItems(UPDATED_SUB_ITEMS)
            .parentItem(UPDATED_PARENT_ITEM)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID);

        restUomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUom.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUom))
            )
            .andExpect(status().isOk());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
        Uom testUom = uomList.get(uomList.size() - 1);
        assertThat(testUom.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testUom.getUom()).isEqualTo(UPDATED_UOM);
        assertThat(testUom.getUomGroup()).isEqualTo(UPDATED_UOM_GROUP);
        assertThat(testUom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUom.getSubItems()).isEqualTo(UPDATED_SUB_ITEMS);
        assertThat(testUom.getParentItem()).isEqualTo(UPDATED_PARENT_ITEM);
        assertThat(testUom.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testUom.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();
        uom.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, uom.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uom))
            )
            .andExpect(status().isBadRequest());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();
        uom.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(uom))
            )
            .andExpect(status().isBadRequest());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();
        uom.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUomMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(uom)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        int databaseSizeBeforeDelete = uomRepository.findAll().size();

        // Delete the uom
        restUomMockMvc.perform(delete(ENTITY_API_URL_ID, uom.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
