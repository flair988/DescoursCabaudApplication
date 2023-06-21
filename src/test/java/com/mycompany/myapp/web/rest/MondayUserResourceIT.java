package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MondayUser;
import com.mycompany.myapp.repository.MondayUserRepository;
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
 * Integration tests for the {@link MondayUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MondayUserResourceIT {

    private static final Long DEFAULT_MONDAY_ID = 1L;
    private static final Long UPDATED_MONDAY_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/monday-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MondayUserRepository mondayUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMondayUserMockMvc;

    private MondayUser mondayUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MondayUser createEntity(EntityManager em) {
        MondayUser mondayUser = new MondayUser().mondayId(DEFAULT_MONDAY_ID).name(DEFAULT_NAME).email(DEFAULT_EMAIL).url(DEFAULT_URL);
        return mondayUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MondayUser createUpdatedEntity(EntityManager em) {
        MondayUser mondayUser = new MondayUser().mondayId(UPDATED_MONDAY_ID).name(UPDATED_NAME).email(UPDATED_EMAIL).url(UPDATED_URL);
        return mondayUser;
    }

    @BeforeEach
    public void initTest() {
        mondayUser = createEntity(em);
    }

    @Test
    @Transactional
    void createMondayUser() throws Exception {
        int databaseSizeBeforeCreate = mondayUserRepository.findAll().size();
        // Create the MondayUser
        restMondayUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mondayUser)))
            .andExpect(status().isCreated());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeCreate + 1);
        MondayUser testMondayUser = mondayUserList.get(mondayUserList.size() - 1);
        assertThat(testMondayUser.getMondayId()).isEqualTo(DEFAULT_MONDAY_ID);
        assertThat(testMondayUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMondayUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMondayUser.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    void createMondayUserWithExistingId() throws Exception {
        // Create the MondayUser with an existing ID
        mondayUser.setId(1L);

        int databaseSizeBeforeCreate = mondayUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMondayUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mondayUser)))
            .andExpect(status().isBadRequest());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMondayUsers() throws Exception {
        // Initialize the database
        mondayUserRepository.saveAndFlush(mondayUser);

        // Get all the mondayUserList
        restMondayUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mondayUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].mondayId").value(hasItem(DEFAULT_MONDAY_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }

    @Test
    @Transactional
    void getMondayUser() throws Exception {
        // Initialize the database
        mondayUserRepository.saveAndFlush(mondayUser);

        // Get the mondayUser
        restMondayUserMockMvc
            .perform(get(ENTITY_API_URL_ID, mondayUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mondayUser.getId().intValue()))
            .andExpect(jsonPath("$.mondayId").value(DEFAULT_MONDAY_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }

    @Test
    @Transactional
    void getNonExistingMondayUser() throws Exception {
        // Get the mondayUser
        restMondayUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMondayUser() throws Exception {
        // Initialize the database
        mondayUserRepository.saveAndFlush(mondayUser);

        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();

        // Update the mondayUser
        MondayUser updatedMondayUser = mondayUserRepository.findById(mondayUser.getId()).get();
        // Disconnect from session so that the updates on updatedMondayUser are not directly saved in db
        em.detach(updatedMondayUser);
        updatedMondayUser.mondayId(UPDATED_MONDAY_ID).name(UPDATED_NAME).email(UPDATED_EMAIL).url(UPDATED_URL);

        restMondayUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMondayUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMondayUser))
            )
            .andExpect(status().isOk());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
        MondayUser testMondayUser = mondayUserList.get(mondayUserList.size() - 1);
        assertThat(testMondayUser.getMondayId()).isEqualTo(UPDATED_MONDAY_ID);
        assertThat(testMondayUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMondayUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMondayUser.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    void putNonExistingMondayUser() throws Exception {
        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();
        mondayUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMondayUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mondayUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mondayUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMondayUser() throws Exception {
        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();
        mondayUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mondayUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMondayUser() throws Exception {
        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();
        mondayUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mondayUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMondayUserWithPatch() throws Exception {
        // Initialize the database
        mondayUserRepository.saveAndFlush(mondayUser);

        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();

        // Update the mondayUser using partial update
        MondayUser partialUpdatedMondayUser = new MondayUser();
        partialUpdatedMondayUser.setId(mondayUser.getId());

        partialUpdatedMondayUser.mondayId(UPDATED_MONDAY_ID).name(UPDATED_NAME).email(UPDATED_EMAIL).url(UPDATED_URL);

        restMondayUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMondayUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMondayUser))
            )
            .andExpect(status().isOk());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
        MondayUser testMondayUser = mondayUserList.get(mondayUserList.size() - 1);
        assertThat(testMondayUser.getMondayId()).isEqualTo(UPDATED_MONDAY_ID);
        assertThat(testMondayUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMondayUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMondayUser.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    void fullUpdateMondayUserWithPatch() throws Exception {
        // Initialize the database
        mondayUserRepository.saveAndFlush(mondayUser);

        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();

        // Update the mondayUser using partial update
        MondayUser partialUpdatedMondayUser = new MondayUser();
        partialUpdatedMondayUser.setId(mondayUser.getId());

        partialUpdatedMondayUser.mondayId(UPDATED_MONDAY_ID).name(UPDATED_NAME).email(UPDATED_EMAIL).url(UPDATED_URL);

        restMondayUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMondayUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMondayUser))
            )
            .andExpect(status().isOk());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
        MondayUser testMondayUser = mondayUserList.get(mondayUserList.size() - 1);
        assertThat(testMondayUser.getMondayId()).isEqualTo(UPDATED_MONDAY_ID);
        assertThat(testMondayUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMondayUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMondayUser.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    void patchNonExistingMondayUser() throws Exception {
        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();
        mondayUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMondayUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mondayUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mondayUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMondayUser() throws Exception {
        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();
        mondayUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mondayUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMondayUser() throws Exception {
        int databaseSizeBeforeUpdate = mondayUserRepository.findAll().size();
        mondayUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMondayUserMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(mondayUser))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MondayUser in the database
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMondayUser() throws Exception {
        // Initialize the database
        mondayUserRepository.saveAndFlush(mondayUser);

        int databaseSizeBeforeDelete = mondayUserRepository.findAll().size();

        // Delete the mondayUser
        restMondayUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, mondayUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MondayUser> mondayUserList = mondayUserRepository.findAll();
        assertThat(mondayUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
