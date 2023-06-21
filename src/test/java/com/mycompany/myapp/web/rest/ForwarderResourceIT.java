package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Forwarder;
import com.mycompany.myapp.repository.ForwarderRepository;
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
 * Integration tests for the {@link ForwarderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ForwarderResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BOARD_ID = "AAAAAAAAAA";
    private static final String UPDATED_BOARD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/forwarders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ForwarderRepository forwarderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restForwarderMockMvc;

    private Forwarder forwarder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Forwarder createEntity(EntityManager em) {
        Forwarder forwarder = new Forwarder()
            .itemName(DEFAULT_ITEM_NAME)
            .itemId(DEFAULT_ITEM_ID)
            .boardId(DEFAULT_BOARD_ID)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .contact(DEFAULT_CONTACT)
            .email(DEFAULT_EMAIL)
            .telephone(DEFAULT_TELEPHONE);
        return forwarder;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Forwarder createUpdatedEntity(EntityManager em) {
        Forwarder forwarder = new Forwarder()
            .itemName(UPDATED_ITEM_NAME)
            .itemId(UPDATED_ITEM_ID)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE);
        return forwarder;
    }

    @BeforeEach
    public void initTest() {
        forwarder = createEntity(em);
    }

    @Test
    @Transactional
    void createForwarder() throws Exception {
        int databaseSizeBeforeCreate = forwarderRepository.findAll().size();
        // Create the Forwarder
        restForwarderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(forwarder)))
            .andExpect(status().isCreated());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeCreate + 1);
        Forwarder testForwarder = forwarderList.get(forwarderList.size() - 1);
        assertThat(testForwarder.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testForwarder.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testForwarder.getBoardId()).isEqualTo(DEFAULT_BOARD_ID);
        assertThat(testForwarder.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testForwarder.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testForwarder.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testForwarder.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
    }

    @Test
    @Transactional
    void createForwarderWithExistingId() throws Exception {
        // Create the Forwarder with an existing ID
        forwarder.setId(1L);

        int databaseSizeBeforeCreate = forwarderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restForwarderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(forwarder)))
            .andExpect(status().isBadRequest());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllForwarders() throws Exception {
        // Initialize the database
        forwarderRepository.saveAndFlush(forwarder);

        // Get all the forwarderList
        restForwarderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(forwarder.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID)))
            .andExpect(jsonPath("$.[*].boardId").value(hasItem(DEFAULT_BOARD_ID)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)));
    }

    @Test
    @Transactional
    void getForwarder() throws Exception {
        // Initialize the database
        forwarderRepository.saveAndFlush(forwarder);

        // Get the forwarder
        restForwarderMockMvc
            .perform(get(ENTITY_API_URL_ID, forwarder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(forwarder.getId().intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID))
            .andExpect(jsonPath("$.boardId").value(DEFAULT_BOARD_ID))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE));
    }

    @Test
    @Transactional
    void getNonExistingForwarder() throws Exception {
        // Get the forwarder
        restForwarderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingForwarder() throws Exception {
        // Initialize the database
        forwarderRepository.saveAndFlush(forwarder);

        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();

        // Update the forwarder
        Forwarder updatedForwarder = forwarderRepository.findById(forwarder.getId()).get();
        // Disconnect from session so that the updates on updatedForwarder are not directly saved in db
        em.detach(updatedForwarder);
        updatedForwarder
            .itemName(UPDATED_ITEM_NAME)
            .itemId(UPDATED_ITEM_ID)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE);

        restForwarderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedForwarder.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedForwarder))
            )
            .andExpect(status().isOk());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
        Forwarder testForwarder = forwarderList.get(forwarderList.size() - 1);
        assertThat(testForwarder.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testForwarder.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testForwarder.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testForwarder.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testForwarder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testForwarder.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testForwarder.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    void putNonExistingForwarder() throws Exception {
        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();
        forwarder.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restForwarderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, forwarder.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(forwarder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchForwarder() throws Exception {
        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();
        forwarder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(forwarder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamForwarder() throws Exception {
        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();
        forwarder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(forwarder)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateForwarderWithPatch() throws Exception {
        // Initialize the database
        forwarderRepository.saveAndFlush(forwarder);

        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();

        // Update the forwarder using partial update
        Forwarder partialUpdatedForwarder = new Forwarder();
        partialUpdatedForwarder.setId(forwarder.getId());

        partialUpdatedForwarder
            .itemName(UPDATED_ITEM_NAME)
            .itemId(UPDATED_ITEM_ID)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE);

        restForwarderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedForwarder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedForwarder))
            )
            .andExpect(status().isOk());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
        Forwarder testForwarder = forwarderList.get(forwarderList.size() - 1);
        assertThat(testForwarder.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testForwarder.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testForwarder.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testForwarder.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testForwarder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testForwarder.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testForwarder.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    void fullUpdateForwarderWithPatch() throws Exception {
        // Initialize the database
        forwarderRepository.saveAndFlush(forwarder);

        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();

        // Update the forwarder using partial update
        Forwarder partialUpdatedForwarder = new Forwarder();
        partialUpdatedForwarder.setId(forwarder.getId());

        partialUpdatedForwarder
            .itemName(UPDATED_ITEM_NAME)
            .itemId(UPDATED_ITEM_ID)
            .boardId(UPDATED_BOARD_ID)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .contact(UPDATED_CONTACT)
            .email(UPDATED_EMAIL)
            .telephone(UPDATED_TELEPHONE);

        restForwarderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedForwarder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedForwarder))
            )
            .andExpect(status().isOk());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
        Forwarder testForwarder = forwarderList.get(forwarderList.size() - 1);
        assertThat(testForwarder.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testForwarder.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testForwarder.getBoardId()).isEqualTo(UPDATED_BOARD_ID);
        assertThat(testForwarder.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testForwarder.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testForwarder.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testForwarder.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    void patchNonExistingForwarder() throws Exception {
        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();
        forwarder.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restForwarderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, forwarder.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(forwarder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchForwarder() throws Exception {
        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();
        forwarder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(forwarder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamForwarder() throws Exception {
        int databaseSizeBeforeUpdate = forwarderRepository.findAll().size();
        forwarder.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restForwarderMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(forwarder))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Forwarder in the database
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteForwarder() throws Exception {
        // Initialize the database
        forwarderRepository.saveAndFlush(forwarder);

        int databaseSizeBeforeDelete = forwarderRepository.findAll().size();

        // Delete the forwarder
        restForwarderMockMvc
            .perform(delete(ENTITY_API_URL_ID, forwarder.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Forwarder> forwarderList = forwarderRepository.findAll();
        assertThat(forwarderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
