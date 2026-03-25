package com.macro.mall.repository;

import com.macro.mall.model.UmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsRoleRepository extends JpaRepository<UmsRole, Long>, JpaSpecificationExecutor<UmsRole> {
}
