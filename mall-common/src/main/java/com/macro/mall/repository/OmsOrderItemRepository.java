package com.macro.mall.repository;

import com.macro.mall.model.OmsOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsOrderItemRepository extends JpaRepository<OmsOrderItem, Long>, JpaSpecificationExecutor<OmsOrderItem> {
}
