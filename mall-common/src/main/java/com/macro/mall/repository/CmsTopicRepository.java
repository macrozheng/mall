package com.macro.mall.repository;

import com.macro.mall.model.CmsTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsTopicRepository extends JpaRepository<CmsTopic, Long>, JpaSpecificationExecutor<CmsTopic> {
}
