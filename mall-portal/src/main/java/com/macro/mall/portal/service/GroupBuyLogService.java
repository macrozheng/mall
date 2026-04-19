package com.macro.mall.portal.service;

import com.macro.mall.model.SmsGroupBuyLog;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;

/**
 * 拼团操作日志Service
 * 同步写库,内部自行吞异常,不影响主业务
 */
public interface GroupBuyLogService {

    /**
     * 操作类型枚举
     */
    int OP_OPEN_GROUP = 1;        // 开团
    int OP_JOIN_GROUP = 2;        // 参团
    int OP_PAY_SUCCESS = 3;       // 支付成功
    int OP_CANCEL = 4;            // 取消参团
    int OP_GROUP_SUCCESS = 5;     // 成团
    int OP_GROUP_FAIL = 6;        // 成团失败
    int OP_REFUND_FINISH = 7;     // 退款完成
    int OP_SHARE = 8;             // 分享
    int OP_VIRTUAL_FILL = 9;      // 系统补团
    int OP_FORCE_CLOSE = 10;      // 管理员强制关闭

    /**
     * 来源
     */
    int SRC_MEMBER = 0;
    int SRC_SYSTEM = 1;
    int SRC_PAY_CALLBACK = 2;
    int SRC_ADMIN = 3;

    /**
     * 记录一条操作日志 - 失败吞异常,不抛给调用方
     */
    void record(SmsGroupBuyLog log);

    /**
     * 便捷方法: 基于团信息记录日志
     */
    void record(int operateType, int operateSource, SmsGroupBuyTeam team,
                SmsGroupBuyRecord record, Integer beforeStatus, Integer afterStatus,
                String detail);
}
