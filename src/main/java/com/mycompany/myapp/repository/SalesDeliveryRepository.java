package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SalesDelivery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SalesDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesDeliveryRepository extends JpaRepository<SalesDelivery, Long> {}
