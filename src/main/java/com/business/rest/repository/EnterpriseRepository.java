package com.business.rest.repository;

import com.business.rest.db.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findEnterpriseById(Long id);
}
