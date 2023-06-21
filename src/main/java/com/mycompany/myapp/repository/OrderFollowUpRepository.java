package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrderFollowUp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderFollowUp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderFollowUpRepository extends JpaRepository<OrderFollowUp, Long> {}
