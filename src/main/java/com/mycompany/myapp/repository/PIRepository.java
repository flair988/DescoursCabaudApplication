package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PI;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PI entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PIRepository extends JpaRepository<PI, Long> {}
