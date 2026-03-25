package com.macro.mall.repository;

import com.macro.mall.model.CmsTopicComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsTopicCommentRepository extends JpaRepository<CmsTopicComment, Long>, JpaSpecificationExecutor<CmsTopicComment> {
}
