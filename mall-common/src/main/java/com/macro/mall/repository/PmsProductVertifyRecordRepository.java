package com.macro.mall.repository;

import com.macro.mall.model.PmsProductVertifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductVertifyRecordRepository extends JpaRepository<PmsProductVertifyRecord, Long>, JpaSpecificationExecutor<PmsProductVertifyRecord> {
}
