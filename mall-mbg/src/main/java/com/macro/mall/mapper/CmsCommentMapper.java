package com.macro.mall.mapper;

import com.macro.mall.model.CmsComment;
import com.macro.mall.model.CmsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCommentMapper {
    long countByExample(CmsCommentExample example);

    int deleteByExample(CmsCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsComment row);

    int insertSelective(CmsComment row);

    List<CmsComment> selectByExample(CmsCommentExample example);

    CmsComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsComment row, @Param("example") CmsCommentExample example);

    int updateByExample(@Param("row") CmsComment row, @Param("example") CmsCommentExample example);

    int updateByPrimaryKeySelective(CmsComment row);

    int updateByPrimaryKey(CmsComment row);
}