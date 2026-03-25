package com.macro.mall.repository;

import com.macro.mall.model.CmsMemberReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsMemberReportRepository extends JpaRepository<CmsMemberReport, Long>, JpaSpecificationExecutor<CmsMemberReport> {
}
