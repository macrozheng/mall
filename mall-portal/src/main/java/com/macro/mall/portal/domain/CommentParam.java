package com.macro.mall.portal.domain;

import lombok.Data;

@Data
public class CommentParam {
    private Long userId;
    private String content;
    private Long goodId;
    private String picUrl;
    private int parentId;
}
