package com.macro.mall.mapper;

import com.macro.mall.model.SmsFlashPromotion;
import com.macro.mall.model.SmsFlashPromotionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsFlashPromotionMapper {
    int countByExample(SmsFlashPromotionExample example);

    int deleteByExample(SmsFlashPromotionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsFlashPromotion record);

    int insertSelective(SmsFlashPromotion record);

    List<SmsFlashPromotion> selectByExample(SmsFlashPromotionExample example);

    SmsFlashPromotion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsFlashPromotion record, @Param("example") SmsFlashPromotionExample example);

    int updateByExample(@Param("record") SmsFlashPromotion record, @Param("example") SmsFlashPromotionExample example);

    int updateByPrimaryKeySelective(SmsFlashPromotion record);

    int updateByPrimaryKey(SmsFlashPromotion record);
}