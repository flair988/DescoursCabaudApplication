package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ProcessLog;
import com.mycompany.myapp.repository.ProcessLogRepository;
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
 * Integration tests for the {@link ProcessLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProcessLogResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROCESS_START_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROCESS_START_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PROCESS_END_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROCESS_END_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/process-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProcessLogRepository processLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProcessLogMockMvc;

    private ProcessLog processLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcessLog createEntity(EntityManager em) {
        ProcessLog processLog = new ProcessLog()
            .type(DEFAULT_TYPE)
            .request(DEFAULT_REQUEST)
            .response(DEFAULT_RESPONSE)
            .status(DEFAULT_STATUS)
            .reason(DEFAULT_REASON)
            .processStartTime(DEFAULT_PROCESS_START_TIME)
            .processEndTime(DEFAULT_PROCESS_END_TIME);
        return processLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcessLog createUpdatedEntity(EntityManager em) {
        ProcessLog processLog = new ProcessLog()
            .type(UPDATED_TYPE)
            .request(UPDATED_REQUEST)
            .response(UPDATED_RESPONSE)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .processEndTime(UPDATED_PROCESS_END_TIME);
        return processLog;
    }

    @BeforeEach
    public void initTest() {
        processLog = createEntity(em);
    }

    @Test
    @Transactional
    void createProcessLog() throws Exception {
        int databaseSizeBeforeCreate = processLogRepository.findAll().size();
        // Create the ProcessLog
        restProcessLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(processLog)))
            .andExpect(status().isCreated());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeCreate + 1);
        ProcessLog testProcessLog = processLogList.get(processLogList.size() - 1);
        assertThat(testProcessLog.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProcessLog.getRequest()).isEqualTo(DEFAULT_REQUEST);
        assertThat(testProcessLog.getResponse()).isEqualTo(DEFAULT_RESPONSE);
        assertThat(testProcessLog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProcessLog.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testProcessLog.getProcessStartTime()).isEqualTo(DEFAULT_PROCESS_START_TIME);
        assertThat(testProcessLog.getProcessEndTime()).isEqualTo(DEFAULT_PROCESS_END_TIME);
    }

    @Test
    @Transactional
    void createProcessLogWithExistingId() throws Exception {
        // Create the ProcessLog with an existing ID
        processLog.setId(1L);

        int databaseSizeBeforeCreate = processLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcessLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(processLog)))
            .andExpect(status().isBadRequest());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProcessLogs() throws Exception {
        // Initialize the database
        processLogRepository.saveAndFlush(processLog);

        // Get all the processLogList
        restProcessLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(processLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].request").value(hasItem(DEFAULT_REQUEST)))
            .andExpect(jsonPath("$.[*].response").value(hasItem(DEFAULT_RESPONSE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].processStartTime").value(hasItem(DEFAULT_PROCESS_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].processEndTime").value(hasItem(DEFAULT_PROCESS_END_TIME.toString())));
    }

    @Test
    @Transactional
    void getProcessLog() throws Exception {
        // Initialize the database
        processLogRepository.saveAndFlush(processLog);

        // Get the processLog
        restProcessLogMockMvc
            .perform(get(ENTITY_API_URL_ID, processLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(processLog.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.request").value(DEFAULT_REQUEST))
            .andExpect(jsonPath("$.response").value(DEFAULT_RESPONSE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.processStartTime").value(DEFAULT_PROCESS_START_TIME.toString()))
            .andExpect(jsonPath("$.processEndTime").value(DEFAULT_PROCESS_END_TIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProcessLog() throws Exception {
        // Get the processLog
        restProcessLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProcessLog() throws Exception {
        // Initialize the database
        processLogRepository.saveAndFlush(processLog);

        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();

        // Update the processLog
        ProcessLog updatedProcessLog = processLogRepository.findById(processLog.getId()).get();
        // Disconnect from session so that the updates on updatedProcessLog are not directly saved in db
        em.detach(updatedProcessLog);
        updatedProcessLog
            .type(UPDATED_TYPE)
            .request(UPDATED_REQUEST)
            .response(UPDATED_RESPONSE)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .processEndTime(UPDATED_PROCESS_END_TIME);

        restProcessLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProcessLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProcessLog))
            )
            .andExpect(status().isOk());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
        ProcessLog testProcessLog = processLogList.get(processLogList.size() - 1);
        assertThat(testProcessLog.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProcessLog.getRequest()).isEqualTo(UPDATED_REQUEST);
        assertThat(testProcessLog.getResponse()).isEqualTo(UPDATED_RESPONSE);
        assertThat(testProcessLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProcessLog.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testProcessLog.getProcessStartTime()).isEqualTo(UPDATED_PROCESS_START_TIME);
        assertThat(testProcessLog.getProcessEndTime()).isEqualTo(UPDATED_PROCESS_END_TIME);
    }

    @Test
    @Transactional
    void putNonExistingProcessLog() throws Exception {
        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();
        processLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcessLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, processLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(processLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProcessLog() throws Exception {
        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();
        processLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcessLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(processLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProcessLog() throws Exception {
        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();
        processLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcessLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(processLog)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProcessLogWithPatch() throws Exception {
        // Initialize the database
        processLogRepository.saveAndFlush(processLog);

        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();

        // Update the processLog using partial update
        ProcessLog partialUpdatedProcessLog = new ProcessLog();
        partialUpdatedProcessLog.setId(processLog.getId());

        partialUpdatedProcessLog.request(UPDATED_REQUEST).status(UPDATED_STATUS).processEndTime(UPDATED_PROCESS_END_TIME);

        restProcessLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProcessLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProcessLog))
            )
            .andExpect(status().isOk());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
        ProcessLog testProcessLog = processLogList.get(processLogList.size() - 1);
        assertThat(testProcessLog.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProcessLog.getRequest()).isEqualTo(UPDATED_REQUEST);
        assertThat(testProcessLog.getResponse()).isEqualTo(DEFAULT_RESPONSE);
        assertThat(testProcessLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProcessLog.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testProcessLog.getProcessStartTime()).isEqualTo(DEFAULT_PROCESS_START_TIME);
        assertThat(testProcessLog.getProcessEndTime()).isEqualTo(UPDATED_PROCESS_END_TIME);
    }

    @Test
    @Transactional
    void fullUpdateProcessLogWithPatch() throws Exception {
        // Initialize the database
        processLogRepository.saveAndFlush(processLog);

        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();

        // Update the processLog using partial update
        ProcessLog partialUpdatedProcessLog = new ProcessLog();
        partialUpdatedProcessLog.setId(processLog.getId());

        partialUpdatedProcessLog
            .type(UPDATED_TYPE)
            .request(UPDATED_REQUEST)
            .response(UPDATED_RESPONSE)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON)
            .processStartTime(UPDATED_PROCESS_START_TIME)
            .processEndTime(UPDATED_PROCESS_END_TIME);

        restProcessLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProcessLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProcessLog))
            )
            .andExpect(status().isOk());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
        ProcessLog testProcessLog = processLogList.get(processLogList.size() - 1);
        assertThat(testProcessLog.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProcessLog.getRequest()).isEqualTo(UPDATED_REQUEST);
        assertThat(testProcessLog.getResponse()).isEqualTo(UPDATED_RESPONSE);
        assertThat(testProcessLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProcessLog.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testProcessLog.getProcessStartTime()).isEqualTo(UPDATED_PROCESS_START_TIME);
        assertThat(testProcessLog.getProcessEndTime()).isEqualTo(UPDATED_PROCESS_END_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingProcessLog() throws Exception {
        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();
        processLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcessLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, processLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(processLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProcessLog() throws Exception {
        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();
        processLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcessLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(processLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProcessLog() throws Exception {
        int databaseSizeBeforeUpdate = processLogRepository.findAll().size();
        processLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProcessLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(processLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProcessLog in the database
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProcessLog() throws Exception {
        // Initialize the database
        processLogRepository.saveAndFlush(processLog);

        int databaseSizeBeforeDelete = processLogRepository.findAll().size();

        // Delete the processLog
        restProcessLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, processLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProcessLog> processLogList = processLogRepository.findAll();
        assertThat(processLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
