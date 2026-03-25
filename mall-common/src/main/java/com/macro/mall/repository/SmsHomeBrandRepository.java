package com.macro.mall.repository;

import com.macro.mall.model.SmsHomeBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHomeBrandRepository extends JpaRepository<SmsHomeBrand, Long>, JpaSpecificationExecutor<SmsHomeBrand> {
}
