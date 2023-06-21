package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Uom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Uom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UomRepository extends JpaRepository<Uom, Long> {}
