package com.macro.mall.repository;

import com.macro.mall.model.UmsIntegrationConsumeSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsIntegrationConsumeSettingRepository extends JpaRepository<UmsIntegrationConsumeSetting, Long>, JpaSpecificationExecutor<UmsIntegrationConsumeSetting> {
}
