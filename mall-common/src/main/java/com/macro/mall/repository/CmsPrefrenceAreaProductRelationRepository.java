package com.macro.mall.repository;

import com.macro.mall.model.CmsPrefrenceAreaProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsPrefrenceAreaProductRelationRepository extends JpaRepository<CmsPrefrenceAreaProductRelation, Long>, JpaSpecificationExecutor<CmsPrefrenceAreaProductRelation> {
}
