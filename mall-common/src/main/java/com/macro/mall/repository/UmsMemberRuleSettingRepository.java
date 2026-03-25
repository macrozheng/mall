package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberRuleSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberRuleSettingRepository extends JpaRepository<UmsMemberRuleSetting, Long>, JpaSpecificationExecutor<UmsMemberRuleSetting> {
}
