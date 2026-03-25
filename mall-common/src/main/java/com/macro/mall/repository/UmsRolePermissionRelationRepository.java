package com.macro.mall.repository;

import com.macro.mall.model.UmsRolePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsRolePermissionRelationRepository extends JpaRepository<UmsRolePermissionRelation, Long>, JpaSpecificationExecutor<UmsRolePermissionRelation> {
}
