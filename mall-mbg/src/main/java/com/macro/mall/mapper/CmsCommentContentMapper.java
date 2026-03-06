package com.macro.mall.mapper;

import com.macro.mall.model.CmsCommentContent;
import com.macro.mall.model.CmsCommentContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCommentContentMapper {
    long countByExample(CmsCommentContentExample example);

    int deleteByExample(CmsCommentContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsCommentContent row);

    int insertSelective(CmsCommentContent row);

    List<CmsCommentContent> selectByExample(CmsCommentContentExample example);

    CmsCommentContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsCommentContent row, @Param("example") CmsCommentContentExample example);

    int updateByExample(@Param("row") CmsCommentContent row, @Param("example") CmsCommentContentExample example);

    int updateByPrimaryKeySelective(CmsCommentContent row);

    int updateByPrimaryKey(CmsCommentContent row);
}