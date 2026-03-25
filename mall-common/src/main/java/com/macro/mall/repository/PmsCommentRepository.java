package com.macro.mall.repository;

import com.macro.mall.model.PmsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsCommentRepository extends JpaRepository<PmsComment, Long>, JpaSpecificationExecutor<PmsComment> {
}
