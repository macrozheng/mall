package com.macro.mall.repository;

import com.macro.mall.model.SmsFlashPromotionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsFlashPromotionLogRepository extends JpaRepository<SmsFlashPromotionLog, Long>, JpaSpecificationExecutor<SmsFlashPromotionLog> {
}
