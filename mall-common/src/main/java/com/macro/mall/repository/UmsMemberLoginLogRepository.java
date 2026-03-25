package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberLoginLogRepository extends JpaRepository<UmsMemberLoginLog, Long>, JpaSpecificationExecutor<UmsMemberLoginLog> {
}
