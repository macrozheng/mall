package com.macro.mall.repository;

import com.macro.mall.model.OmsOrderSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsOrderSettingRepository extends JpaRepository<OmsOrderSetting, Long>, JpaSpecificationExecutor<OmsOrderSetting> {
}
