package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Forwarder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Forwarder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ForwarderRepository extends JpaRepository<Forwarder, Long> {}
