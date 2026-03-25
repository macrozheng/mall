package com.macro.mall.repository;

import com.macro.mall.model.PmsProductFullReduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductFullReductionRepository extends JpaRepository<PmsProductFullReduction, Long>, JpaSpecificationExecutor<PmsProductFullReduction> {
}
