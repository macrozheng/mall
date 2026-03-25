package com.macro.mall.repository;

import com.macro.mall.model.OmsCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsCartItemRepository extends JpaRepository<OmsCartItem, Long>, JpaSpecificationExecutor<OmsCartItem> {
}
