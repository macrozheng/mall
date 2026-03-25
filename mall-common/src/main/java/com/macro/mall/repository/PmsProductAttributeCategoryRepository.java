package com.macro.mall.repository;

import com.macro.mall.model.PmsProductAttributeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductAttributeCategoryRepository extends JpaRepository<PmsProductAttributeCategory, Long>, JpaSpecificationExecutor<PmsProductAttributeCategory> {
}
