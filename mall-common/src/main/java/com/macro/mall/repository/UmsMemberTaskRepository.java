package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberTaskRepository extends JpaRepository<UmsMemberTask, Long>, JpaSpecificationExecutor<UmsMemberTask> {
}
