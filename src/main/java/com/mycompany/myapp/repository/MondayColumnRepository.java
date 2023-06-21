package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MondayColumn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MondayColumn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MondayColumnRepository extends JpaRepository<MondayColumn, Long> {}
