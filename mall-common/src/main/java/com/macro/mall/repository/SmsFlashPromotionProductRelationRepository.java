package com.macro.mall.repository;

import com.macro.mall.model.SmsFlashPromotionProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsFlashPromotionProductRelationRepository extends JpaRepository<SmsFlashPromotionProductRelation, Long>, JpaSpecificationExecutor<SmsFlashPromotionProductRelation> {
}
