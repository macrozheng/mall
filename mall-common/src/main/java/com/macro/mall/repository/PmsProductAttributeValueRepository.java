package com.macro.mall.repository;

import com.macro.mall.model.PmsProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductAttributeValueRepository extends JpaRepository<PmsProductAttributeValue, Long>, JpaSpecificationExecutor<PmsProductAttributeValue> {
}
