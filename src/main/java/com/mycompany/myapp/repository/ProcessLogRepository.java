package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ProcessLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProcessLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcessLogRepository extends JpaRepository<ProcessLog, Long> {}
