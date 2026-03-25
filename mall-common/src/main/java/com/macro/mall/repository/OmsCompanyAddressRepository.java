package com.macro.mall.repository;

import com.macro.mall.model.OmsCompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsCompanyAddressRepository extends JpaRepository<OmsCompanyAddress, Long>, JpaSpecificationExecutor<OmsCompanyAddress> {
}
