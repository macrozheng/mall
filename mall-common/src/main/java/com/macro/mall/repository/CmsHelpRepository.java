package com.macro.mall.repository;

import com.macro.mall.model.CmsHelp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsHelpRepository extends JpaRepository<CmsHelp, Long>, JpaSpecificationExecutor<CmsHelp> {
}
