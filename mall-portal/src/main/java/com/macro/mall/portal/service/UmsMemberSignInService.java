package com.macro.mall.portal.service;

import com.macro.mall.model.UmsMemberSignIn;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UmsMemberSignInService {

    @Transactional
    UmsMemberSignIn signIn();

    boolean isSignedInToday();

    Integer getContinueDays();

    List<UmsMemberSignIn> getSignInHistory(Integer pageNum, Integer pageSize);
}
