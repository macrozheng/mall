package com.macro.mall.repository;

import com.macro.mall.model.PmsProductOperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductOperateLogRepository extends JpaRepository<PmsProductOperateLog, Long>, JpaSpecificationExecutor<PmsProductOperateLog> {
}
