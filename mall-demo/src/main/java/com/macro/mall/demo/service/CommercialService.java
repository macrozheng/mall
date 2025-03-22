package com.macro.mall.demo.service;

import com.macro.mall.demo.dto.CommercialDto;
import com.macro.mall.model.Commercial;
import java.util.List;

public interface CommercialService {
    /**
     * 获取所有商业列表
     */
    List<Commercial> listAllCommercial();

    /**
     * 创建商业
     */
    int createCommercial(CommercialDto commercialDto);

    /**
     * 更新商业信息
     */
    int updateCommercial(Long id, CommercialDto commercialDto);

    /**
     * 删除商业
     */
    int deleteCommercial(Long id);

    /**
     * 分页获取商业列表
     */
    List<Commercial> listCommercial(Integer pageNum, Integer pageSize);

    /**
     * 获取商业详情
     */
    Commercial getCommercial(Long id);
} 