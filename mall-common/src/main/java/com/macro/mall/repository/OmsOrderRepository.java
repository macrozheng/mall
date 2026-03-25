package com.macro.mall.repository;

import com.macro.mall.model.OmsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsOrderRepository extends JpaRepository<OmsOrder, Long>, JpaSpecificationExecutor<OmsOrder> {
}
