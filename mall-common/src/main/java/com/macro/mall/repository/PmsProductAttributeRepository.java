package com.macro.mall.repository;

import com.macro.mall.model.PmsProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductAttributeRepository extends JpaRepository<PmsProductAttribute, Long>, JpaSpecificationExecutor<PmsProductAttribute> {
}
