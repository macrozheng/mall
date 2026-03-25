package com.macro.mall.repository;

import com.macro.mall.model.SmsFlashPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsFlashPromotionRepository extends JpaRepository<SmsFlashPromotion, Long>, JpaSpecificationExecutor<SmsFlashPromotion> {
}
