package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberTagRepository extends JpaRepository<UmsMemberTag, Long>, JpaSpecificationExecutor<UmsMemberTag> {
}
