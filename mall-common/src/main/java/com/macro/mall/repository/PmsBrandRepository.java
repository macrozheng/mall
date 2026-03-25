package com.macro.mall.repository;

import com.macro.mall.model.PmsBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsBrandRepository extends JpaRepository<PmsBrand, Long>, JpaSpecificationExecutor<PmsBrand> {
}
