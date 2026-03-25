package com.macro.mall.repository;

import com.macro.mall.model.UmsResourceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsResourceCategoryRepository extends JpaRepository<UmsResourceCategory, Long>, JpaSpecificationExecutor<UmsResourceCategory> {
}
