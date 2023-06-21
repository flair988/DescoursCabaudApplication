package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SupplierQualification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SupplierQualification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierQualificationRepository extends JpaRepository<SupplierQualification, Long> {}
