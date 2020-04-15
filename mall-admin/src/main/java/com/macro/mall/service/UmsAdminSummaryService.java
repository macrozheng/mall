package com.macro.mall.service;

import com.macro.mall.dto.UmsAdminSummaryResult;

/**
 * @author shiyue
 * @version 1.0 4/15/20
 */
public interface UmsAdminSummaryService {
    /**
     * 获取首页数据统计
     * @return
     */
    UmsAdminSummaryResult getAdminSummary();
}
