package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberProductCategoryRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberProductCategoryRelationRepository extends JpaRepository<UmsMemberProductCategoryRelation, Long>, JpaSpecificationExecutor<UmsMemberProductCategoryRelation> {
}
