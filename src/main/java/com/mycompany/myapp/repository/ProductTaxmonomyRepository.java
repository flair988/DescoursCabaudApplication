package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ProductTaxmonomy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProductTaxmonomy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductTaxmonomyRepository extends JpaRepository<ProductTaxmonomy, Long> {}
