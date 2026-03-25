package com.macro.mall.repository;

import com.macro.mall.model.PmsFeightTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsFeightTemplateRepository extends JpaRepository<PmsFeightTemplate, Long>, JpaSpecificationExecutor<PmsFeightTemplate> {
}
