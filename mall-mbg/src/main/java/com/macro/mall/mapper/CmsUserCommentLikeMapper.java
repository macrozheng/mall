package com.macro.mall.mapper;

import com.macro.mall.model.CmsUserCommentLike;
import com.macro.mall.model.CmsUserCommentLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsUserCommentLikeMapper {
    long countByExample(CmsUserCommentLikeExample example);

    int deleteByExample(CmsUserCommentLikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsUserCommentLike row);

    int insertSelective(CmsUserCommentLike row);

    List<CmsUserCommentLike> selectByExample(CmsUserCommentLikeExample example);

    CmsUserCommentLike selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsUserCommentLike row, @Param("example") CmsUserCommentLikeExample example);

    int updateByExample(@Param("row") CmsUserCommentLike row, @Param("example") CmsUserCommentLikeExample example);

    int updateByPrimaryKeySelective(CmsUserCommentLike row);

    int updateByPrimaryKey(CmsUserCommentLike row);
}