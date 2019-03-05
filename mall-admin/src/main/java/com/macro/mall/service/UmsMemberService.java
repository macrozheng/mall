package com.macro.mall.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.macro.mall.dto.UmsMemberQueryParam;
import com.macro.mall.model.UmsMember;

/**
 * 会员管理Service
 * Created by pray on 2019/3/6.
 */
public interface UmsMemberService {
	
	 /**
     * 创建会员账户
     */
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(UmsMember memberParam);

    /**
     * 更新会员信息
     */
    @Transactional
    int update(Long id, UmsMember memberParam);

    /**
     * 分页查询商品
     */
    List<UmsMember> list(UmsMemberQueryParam memberQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 修改状态
     * @param id 会员id
     * @param Status 审核状态
     */
    @Transactional
    int updateVerifyStatus(Long id, Integer status);


    /**
     * 批量删除商品
     */
    int deleteMember(Long id);

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<UmsMember> list(String keyword);
}
