package com.macro.mall.repository;

import com.macro.mall.model.CmsHelpCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsHelpCategoryRepository extends JpaRepository<CmsHelpCategory, Long>, JpaSpecificationExecutor<CmsHelpCategory> {
}
