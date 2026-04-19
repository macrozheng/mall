package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.dao.SmsGroupBuyActivityDao;
import com.macro.mall.mapper.SmsGroupBuyTeamMapper;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.service.SmsGroupBuyTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 拼团团管理Service实现 (后台)
 */
@Service
public class SmsGroupBuyTeamServiceImpl implements SmsGroupBuyTeamService {

    @Autowired
    private SmsGroupBuyTeamMapper teamMapper;

    @Autowired
    private SmsGroupBuyActivityDao activityDao;

    @Override
    public List<SmsGroupBuyTeam> list(Long activityId, Integer status, String teamNo,
                                      Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return activityDao.listTeam(activityId, status, teamNo);
    }

    @Override
    public SmsGroupBuyTeam getItem(Long id) {
        return teamMapper.selectByPrimaryKey(id);
    }

    @Override
    public int forceClose(Long id) {
        SmsGroupBuyTeam team = teamMapper.selectByPrimaryKey(id);
        if (team == null) {
            Asserts.fail("团不存在");
        }
        if (!Integer.valueOf(0).equals(team.getStatus())) {
            Asserts.fail("仅进行中的团可强制关闭");
        }
        SmsGroupBuyTeam update = new SmsGroupBuyTeam();
        update.setId(id);
        update.setStatus(3);
        update.setCloseTime(new Date());
        // 注:此处仅修改状态,真正的库存释放/退款由 mall-portal 的团异常处理逻辑完成
        return teamMapper.updateByPrimaryKeySelective(update);
    }
}
