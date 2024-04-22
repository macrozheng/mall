package com.macro.mall.mapper;

import com.macro.mall.model.SmsFlashPromotionLog;
import com.macro.mall.model.SmsFlashPromotionLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsFlashPromotionLogMapper {
    long countByExample(SmsFlashPromotionLogExample example);

    int deleteByExample(SmsFlashPromotionLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsFlashPromotionLog row);

    int insertSelective(SmsFlashPromotionLog row);

    List<SmsFlashPromotionLog> selectByExample(SmsFlashPromotionLogExample example);

    SmsFlashPromotionLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") SmsFlashPromotionLog row, @Param("example") SmsFlashPromotionLogExample example);

    int updateByExample(@Param("row") SmsFlashPromotionLog row, @Param("example") SmsFlashPromotionLogExample example);

    int updateByPrimaryKeySelective(SmsFlashPromotionLog row);

    int updateByPrimaryKey(SmsFlashPromotionLog row);
}