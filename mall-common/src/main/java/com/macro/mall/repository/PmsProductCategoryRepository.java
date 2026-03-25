package com.macro.mall.repository;

import com.macro.mall.model.PmsProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductCategoryRepository extends JpaRepository<PmsProductCategory, Long>, JpaSpecificationExecutor<PmsProductCategory> {
}
