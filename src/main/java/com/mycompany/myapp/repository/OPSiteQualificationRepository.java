package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OPSiteQualification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OPSiteQualification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OPSiteQualificationRepository extends JpaRepository<OPSiteQualification, Long> {}
