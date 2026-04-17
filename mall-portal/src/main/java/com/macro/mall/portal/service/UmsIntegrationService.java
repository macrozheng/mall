package com.macro.mall.portal.service;

import com.macro.mall.model.UmsIntegrationChangeHistory;
import com.macro.mall.model.UmsIntegrationLevel;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UmsIntegrationService {

    @Transactional
    int addIntegration(Long memberId, Integer amount, String sourceType, String note, Long orderId);

    @Transactional
    int consumeIntegration(Long memberId, Integer amount, String note, Long orderId);

    List<UmsIntegrationChangeHistory> getIntegrationHistory(Integer pageNum, Integer pageSize, Integer changeType);

    UmsIntegrationLevel getCurrentLevel();

    List<UmsIntegrationLevel> getAllLevels();

    Map<String, Object> getIntegrationStats();

    Integer calculateConsumeIntegration(BigDecimal amount);

    BigDecimal calculateIntegrationAmount(Integer integration);

    @Transactional
    void grantCommentIntegration(Long orderId, boolean hasImage);

    @Transactional
    void grantShareIntegration(Long memberId);

    @Transactional
    void grantRegisterIntegration(Long memberId);

    @Transactional
    void grantBirthdayIntegration(Long memberId);
}
