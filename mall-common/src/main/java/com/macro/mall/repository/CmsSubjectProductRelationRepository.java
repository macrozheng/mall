package com.macro.mall.repository;

import com.macro.mall.model.CmsSubjectProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsSubjectProductRelationRepository extends JpaRepository<CmsSubjectProductRelation, Long>, JpaSpecificationExecutor<CmsSubjectProductRelation> {
}
