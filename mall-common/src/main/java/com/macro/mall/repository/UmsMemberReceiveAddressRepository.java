package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberReceiveAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberReceiveAddressRepository extends JpaRepository<UmsMemberReceiveAddress, Long>, JpaSpecificationExecutor<UmsMemberReceiveAddress> {
}
