package com.macro.mall.repository;

import com.macro.mall.model.UmsMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMenuRepository extends JpaRepository<UmsMenu, Long>, JpaSpecificationExecutor<UmsMenu> {
}
