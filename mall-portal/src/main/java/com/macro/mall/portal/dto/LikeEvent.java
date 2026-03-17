package com.macro.mall.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞事件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeEvent {
    private Long userId;        // 用户ID
    private Long commentId;     // 评论ID
    private Integer increment;  // 增量（+1点赞，-1取消点赞）
}
