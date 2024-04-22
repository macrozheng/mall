package com.macro.mall.mapper;

import com.macro.mall.model.CmsHelp;
import com.macro.mall.model.CmsHelpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsHelpMapper {
    long countByExample(CmsHelpExample example);

    int deleteByExample(CmsHelpExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsHelp row);

    int insertSelective(CmsHelp row);

    List<CmsHelp> selectByExampleWithBLOBs(CmsHelpExample example);

    List<CmsHelp> selectByExample(CmsHelpExample example);

    CmsHelp selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsHelp row, @Param("example") CmsHelpExample example);

    int updateByExampleWithBLOBs(@Param("row") CmsHelp row, @Param("example") CmsHelpExample example);

    int updateByExample(@Param("row") CmsHelp row, @Param("example") CmsHelpExample example);

    int updateByPrimaryKeySelective(CmsHelp row);

    int updateByPrimaryKeyWithBLOBs(CmsHelp row);

    int updateByPrimaryKey(CmsHelp row);
}