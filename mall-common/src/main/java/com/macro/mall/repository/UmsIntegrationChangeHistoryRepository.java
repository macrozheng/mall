package com.macro.mall.repository;

import com.macro.mall.model.UmsIntegrationChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsIntegrationChangeHistoryRepository extends JpaRepository<UmsIntegrationChangeHistory, Long>, JpaSpecificationExecutor<UmsIntegrationChangeHistory> {
}
