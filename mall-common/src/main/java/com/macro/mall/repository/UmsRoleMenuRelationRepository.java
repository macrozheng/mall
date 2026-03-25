package com.macro.mall.repository;

import com.macro.mall.model.UmsRoleMenuRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsRoleMenuRelationRepository extends JpaRepository<UmsRoleMenuRelation, Long>, JpaSpecificationExecutor<UmsRoleMenuRelation> {
}
