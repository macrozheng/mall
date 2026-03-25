package com.macro.mall.repository;

import com.macro.mall.model.OmsOrderOperateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OmsOrderOperateHistoryRepository extends JpaRepository<OmsOrderOperateHistory, Long>, JpaSpecificationExecutor<OmsOrderOperateHistory> {
}
