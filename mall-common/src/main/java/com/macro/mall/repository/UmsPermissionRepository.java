package com.macro.mall.repository;

import com.macro.mall.model.UmsPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsPermissionRepository extends JpaRepository<UmsPermission, Long>, JpaSpecificationExecutor<UmsPermission> {
}
