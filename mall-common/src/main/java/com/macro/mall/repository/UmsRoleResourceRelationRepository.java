package com.macro.mall.repository;

import com.macro.mall.model.UmsRoleResourceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsRoleResourceRelationRepository extends JpaRepository<UmsRoleResourceRelation, Long>, JpaSpecificationExecutor<UmsRoleResourceRelation> {
}
