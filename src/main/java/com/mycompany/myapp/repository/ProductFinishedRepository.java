package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ProductFinished;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProductFinished entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductFinishedRepository extends JpaRepository<ProductFinished, Long> {}
