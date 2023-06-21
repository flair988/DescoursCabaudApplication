package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OperationSite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OperationSite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationSiteRepository extends JpaRepository<OperationSite, Long> {}
