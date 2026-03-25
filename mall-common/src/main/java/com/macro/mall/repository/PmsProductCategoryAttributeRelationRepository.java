package com.macro.mall.repository;

import com.macro.mall.model.PmsProductCategoryAttributeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductCategoryAttributeRelationRepository extends JpaRepository<PmsProductCategoryAttributeRelation, Long>, JpaSpecificationExecutor<PmsProductCategoryAttributeRelation> {
}
