package com.macro.mall.repository;

import com.macro.mall.model.UmsAdminPermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsAdminPermissionRelationRepository extends JpaRepository<UmsAdminPermissionRelation, Long>, JpaSpecificationExecutor<UmsAdminPermissionRelation> {
}
