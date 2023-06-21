package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PurchaseReturn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseReturn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseReturnRepository extends JpaRepository<PurchaseReturn, Long> {}
