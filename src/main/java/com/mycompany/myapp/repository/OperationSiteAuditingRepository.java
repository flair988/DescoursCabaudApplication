package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OperationSiteAuditing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OperationSiteAuditing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationSiteAuditingRepository extends JpaRepository<OperationSiteAuditing, Long> {}
