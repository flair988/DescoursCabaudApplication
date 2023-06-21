package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CommercialInvoice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CommercialInvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommercialInvoiceRepository extends JpaRepository<CommercialInvoice, Long> {}
