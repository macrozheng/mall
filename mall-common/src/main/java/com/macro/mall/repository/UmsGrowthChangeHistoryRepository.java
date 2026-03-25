package com.macro.mall.repository;

import com.macro.mall.model.UmsGrowthChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsGrowthChangeHistoryRepository extends JpaRepository<UmsGrowthChangeHistory, Long>, JpaSpecificationExecutor<UmsGrowthChangeHistory> {
}
