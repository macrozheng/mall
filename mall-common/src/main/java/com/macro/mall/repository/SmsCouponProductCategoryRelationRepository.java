package com.macro.mall.repository;

import com.macro.mall.model.SmsCouponProductCategoryRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCouponProductCategoryRelationRepository extends JpaRepository<SmsCouponProductCategoryRelation, Long>, JpaSpecificationExecutor<SmsCouponProductCategoryRelation> {
}
