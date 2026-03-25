package com.macro.mall.repository;

import com.macro.mall.model.SmsHomeAdvertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHomeAdvertiseRepository extends JpaRepository<SmsHomeAdvertise, Long>, JpaSpecificationExecutor<SmsHomeAdvertise> {
}
