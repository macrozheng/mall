package com.macro.mall.repository;

import com.macro.mall.model.SmsCouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCouponHistoryRepository extends JpaRepository<SmsCouponHistory, Long>, JpaSpecificationExecutor<SmsCouponHistory> {
}
