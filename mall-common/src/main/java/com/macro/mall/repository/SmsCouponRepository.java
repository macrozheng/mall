package com.macro.mall.repository;

import com.macro.mall.model.SmsCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCouponRepository extends JpaRepository<SmsCoupon, Long>, JpaSpecificationExecutor<SmsCoupon> {
}
