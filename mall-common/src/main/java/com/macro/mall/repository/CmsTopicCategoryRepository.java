package com.macro.mall.repository;

import com.macro.mall.model.CmsTopicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsTopicCategoryRepository extends JpaRepository<CmsTopicCategory, Long>, JpaSpecificationExecutor<CmsTopicCategory> {
}
