package com.macro.mall.repository;

import com.macro.mall.model.UmsResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsResourceRepository extends JpaRepository<UmsResource, Long>, JpaSpecificationExecutor<UmsResource> {
}
