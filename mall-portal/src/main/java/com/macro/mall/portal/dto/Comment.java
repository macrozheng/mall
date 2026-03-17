package com.macro.mall.portal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode
public class Comment {
    private Long id;
    private String content;
    private String picUrl;
    private int likeNum;
    private int repostNum;
    private int parentId;
    private String createTime;
    private Long score;
    private String userName;
    private boolean isLiked;
    private Long goodId;
}
