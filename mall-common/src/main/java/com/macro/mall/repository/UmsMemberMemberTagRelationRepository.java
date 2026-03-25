package com.macro.mall.repository;

import com.macro.mall.model.UmsMemberMemberTagRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberMemberTagRelationRepository extends JpaRepository<UmsMemberMemberTagRelation, Long>, JpaSpecificationExecutor<UmsMemberMemberTagRelation> {
}
