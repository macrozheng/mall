package com.macro.mall.repository;

import com.macro.mall.model.PmsSkuStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsSkuStockRepository extends JpaRepository<PmsSkuStock, Long>, JpaSpecificationExecutor<PmsSkuStock> {
}
