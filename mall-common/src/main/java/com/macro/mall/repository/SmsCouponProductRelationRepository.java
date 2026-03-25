package com.macro.mall.repository;

import com.macro.mall.model.SmsCouponProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCouponProductRelationRepository extends JpaRepository<SmsCouponProductRelation, Long>, JpaSpecificationExecutor<SmsCouponProductRelation> {
}
