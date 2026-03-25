package com.macro.mall.repository;

import com.macro.mall.model.SmsHomeRecommendSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHomeRecommendSubjectRepository extends JpaRepository<SmsHomeRecommendSubject, Long>, JpaSpecificationExecutor<SmsHomeRecommendSubject> {
}
