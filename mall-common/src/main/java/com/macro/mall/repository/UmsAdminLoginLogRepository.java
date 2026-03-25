package com.macro.mall.repository;

import com.macro.mall.model.UmsAdminLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsAdminLoginLogRepository extends JpaRepository<UmsAdminLoginLog, Long>, JpaSpecificationExecutor<UmsAdminLoginLog> {
}
