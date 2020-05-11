package com.macro.mall.dao;

import com.macro.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义会员阶梯价格Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsProductLadderDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
