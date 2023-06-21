package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MondayColumn;
import com.mycompany.myapp.repository.MondayColumnRepository;
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
 * Integration tests for the {@link MondayColumnResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MondayColumnResourceIT {

    private static final String DEFAULT_BOARD_ID = "AAAAAAAAAA";
    private static final String UPDATED_BOARD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_ID = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/monday-columns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MondayColumnRepository mondayColumnRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMondayColumnMockMvc;

    private MondayColumn mondayColumn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MondayColumn createEntity(EntityManager em) {
        MondayColumn mondayColumn = new MondayColumn()
            .boardId(DEFAULT_BOARD_ID)
            .columnId(DEFAULT_COLUMN_ID)
            .title(DEFAULT_TITLE)
            .type(DEFAULT_TYPE);
        return mondayColumn;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MondayColumn createUpdatedEntity(EntityManager em) {
        MondayColumn mondayColumn = new MondayColumn()
            .boardId(UPDATED_BOARD_ID)
            .columnId(UPDATED_COLUMN_ID)
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE);
        return mondayColumn;
    }

    @BeforeEach
    public void initTest() {
        mondayColumn = createEntity(em);
    }

    @Test
    @Transactional
    void createMondayColumn() throws Exception {
        int databaseSizeBeforeCreate = mondayColumnRepository.findAll().size();
        // Create the MondayColumn
        restMondayColumnMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mondayColumn)))
            .andExpect(status().isCreated());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeCreate + 1);
        MondayColumn testMondayColumn = mondayColumnList.get(mondayColumnList.size() - 1);
        assertThat(testMondayColumn.getBoardId()).isEqualTo(DEFAULT_BOARD_ID);
        assertThat(testMondayColumn.getColumnId()).isEqualTo(DEFAULT_COLUMN_ID);
        assertThat(testMondayColumn.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMondayColumn.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    void createMondayColumnWithExistingId() throws Exception {
        // Create the MondayColumn with an existing ID
        mondayColumn.setId(1L);

        int databaseSizeBeforeCreate = mondayColumnRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMondayColumnMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mondayColumn)))
            .andExpect(status().isBadRequest());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMondayColumns() throws Exception {
        // Initialize the database
        mondayColumnRepository.saveAndFlush(mondayColumn);

        // Get all the mondayColumnList
        restMondayColumnMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mondayColumn.getId().intValue())))
            .andExpect(jsonPath("$.[*].boardId").value(hasItem(DEFAULT_BOARD_ID)))
            .andExpect(jsonPath("$.[*].columnId").value(hasItem(DEFAULT_COLUMN_ID)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }

    @Test
    @Transactional
    void getMondayColumn() throws Exception {
        // Initialize the database
        mondayColumnRepository.saveAndFlush(mondayColumn);

        // Get the mondayColumn
        restMondayColumnMockMvc
            .perform(get(ENTITY_API_URL_ID, mondayColumn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mondayColumn.getId().intValue()))
            .andExpect(jsonPath("$.boardId").value(DEFAULT_BOARD_ID))
            .andExpect(jsonPath("$.columnId").value(DEFAULT_COLUMN_ID))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingMondayColumn() throws Exception {
        // Get the mondayColumn
        restMondayColumnMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMondayColumn() throws Exception {
        // Initialize the database
        mondayColumnRepository.saveAndFlush(mondayColumn);

        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();

        // Update the mondayColumn
        MondayColumn updatedMondayColumn = mondayColumnRepository.findById(mondayColumn.getId()).get();
        // Disconnect from session so that the updates on updatedMondayColumn are not directly saved in db
        em.detach(updatedMondayColumn);
        updatedMondayColumn.boardId(UPDATED_BOARD_ID).columnId(UPDATED_COLUMN_ID).title(UPDATED_TITLE).type(UPDATED_TYPE);

        restMondayColumnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMondayColumn.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMondayColumn))
            )
            .andExpect(status().isOk());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
        MondayColumn testMondayColumn = mondayColumnList.get(mondayColumnList.size() - 1);
        assertThat(testMondayColumn.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testMondayColumn.getColumnId()).isEqualTo(UPDATED_COLUMN_ID);
        assertThat(testMondayColumn.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMondayColumn.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingMondayColumn() throws Exception {
        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();
        mondayColumn.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMondayColumnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mondayColumn.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mondayColumn))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMondayColumn() throws Exception {
        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();
        mondayColumn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayColumnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mondayColumn))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMondayColumn() throws Exception {
        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();
        mondayColumn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayColumnMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mondayColumn)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMondayColumnWithPatch() throws Exception {
        // Initialize the database
        mondayColumnRepository.saveAndFlush(mondayColumn);

        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();

        // Update the mondayColumn using partial update
        MondayColumn partialUpdatedMondayColumn = new MondayColumn();
        partialUpdatedMondayColumn.setId(mondayColumn.getId());

        partialUpdatedMondayColumn.type(UPDATED_TYPE);

        restMondayColumnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMondayColumn.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMondayColumn))
            )
            .andExpect(status().isOk());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
        MondayColumn testMondayColumn = mondayColumnList.get(mondayColumnList.size() - 1);
        assertThat(testMondayColumn.getBoardId()).isEqualTo(DEFAULT_BOARD_ID);
        assertThat(testMondayColumn.getColumnId()).isEqualTo(DEFAULT_COLUMN_ID);
        assertThat(testMondayColumn.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMondayColumn.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateMondayColumnWithPatch() throws Exception {
        // Initialize the database
        mondayColumnRepository.saveAndFlush(mondayColumn);

        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();

        // Update the mondayColumn using partial update
        MondayColumn partialUpdatedMondayColumn = new MondayColumn();
        partialUpdatedMondayColumn.setId(mondayColumn.getId());

        partialUpdatedMondayColumn.boardId(UPDATED_BOARD_ID).columnId(UPDATED_COLUMN_ID).title(UPDATED_TITLE).type(UPDATED_TYPE);

        restMondayColumnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMondayColumn.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMondayColumn))
            )
            .andExpect(status().isOk());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
        MondayColumn testMondayColumn = mondayColumnList.get(mondayColumnList.size() - 1);
        assertThat(testMondayColumn.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testMondayColumn.getColumnId()).isEqualTo(UPDATED_COLUMN_ID);
        assertThat(testMondayColumn.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMondayColumn.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingMondayColumn() throws Exception {
        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();
        mondayColumn.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMondayColumnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mondayColumn.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mondayColumn))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMondayColumn() throws Exception {
        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();
        mondayColumn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayColumnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mondayColumn))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMondayColumn() throws Exception {
        int databaseSizeBeforeUpdate = mondayColumnRepository.findAll().size();
        mondayColumn.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayColumnMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(mondayColumn))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MondayColumn in the database
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMondayColumn() throws Exception {
        // Initialize the database
        mondayColumnRepository.saveAndFlush(mondayColumn);

        int databaseSizeBeforeDelete = mondayColumnRepository.findAll().size();

        // Delete the mondayColumn
        restMondayColumnMockMvc
            .perform(delete(ENTITY_API_URL_ID, mondayColumn.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MondayColumn> mondayColumnList = mondayColumnRepository.findAll();
        assertThat(mondayColumnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
