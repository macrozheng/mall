package com.macro.mall.portal.service;

import com.macro.mall.model.UmsFeedBack;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UmsFeedBackService {
    /**
     * 添加意见反馈
     */
    int add(UmsFeedBack feedback);
}
