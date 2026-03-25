package com.macro.mall.repository;

import com.macro.mall.model.SmsFlashPromotionSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsFlashPromotionSessionRepository extends JpaRepository<SmsFlashPromotionSession, Long>, JpaSpecificationExecutor<SmsFlashPromotionSession> {
}
