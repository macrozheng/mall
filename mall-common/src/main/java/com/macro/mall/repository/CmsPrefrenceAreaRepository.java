package com.macro.mall.repository;

import com.macro.mall.model.CmsPrefrenceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsPrefrenceAreaRepository extends JpaRepository<CmsPrefrenceArea, Long>, JpaSpecificationExecutor<CmsPrefrenceArea> {
}
