package com.macro.mall.repository;

import com.macro.mall.model.PmsMemberPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsMemberPriceRepository extends JpaRepository<PmsMemberPrice, Long>, JpaSpecificationExecutor<PmsMemberPrice> {
}
