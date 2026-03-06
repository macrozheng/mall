package com.macro.mall.mapper;

import com.macro.mall.model.CmsCommentCotent;
import com.macro.mall.model.CmsCommentCotentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsCommentCotentMapper {
    long countByExample(CmsCommentCotentExample example);

    int deleteByExample(CmsCommentCotentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsCommentCotent row);

    int insertSelective(CmsCommentCotent row);

    List<CmsCommentCotent> selectByExample(CmsCommentCotentExample example);

    CmsCommentCotent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") CmsCommentCotent row, @Param("example") CmsCommentCotentExample example);

    int updateByExample(@Param("row") CmsCommentCotent row, @Param("example") CmsCommentCotentExample example);

    int updateByPrimaryKeySelective(CmsCommentCotent row);

    int updateByPrimaryKey(CmsCommentCotent row);
}