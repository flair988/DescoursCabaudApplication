package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MondayUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MondayUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MondayUserRepository extends JpaRepository<MondayUser, Long> {}
