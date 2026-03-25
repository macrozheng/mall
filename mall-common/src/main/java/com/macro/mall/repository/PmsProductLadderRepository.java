package com.macro.mall.repository;

import com.macro.mall.model.PmsProductLadder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsProductLadderRepository extends JpaRepository<PmsProductLadder, Long>, JpaSpecificationExecutor<PmsProductLadder> {
}
