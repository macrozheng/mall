package com.macro.mall.repository;

import com.macro.mall.model.CmsSubjectComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsSubjectCommentRepository extends JpaRepository<CmsSubjectComment, Long>, JpaSpecificationExecutor<CmsSubjectComment> {
}
