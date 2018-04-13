package com.macro.mall.model;

import java.io.Serializable;

public class OmsOrderSetting implements Serializable {
    private Long id;

    private Integer flashOrderOvertime;

    private Integer normalOrderOvertime;

    private Integer confirmOvertime;

    private Integer finishOvertime;

    private Integer commentOvertime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlashOrderOvertime() {
        return flashOrderOvertime;
    }

    public void setFlashOrderOvertime(Integer flashOrderOvertime) {
        this.flashOrderOvertime = flashOrderOvertime;
    }

    public Integer getNormalOrderOvertime() {
        return normalOrderOvertime;
    }

    public void setNormalOrderOvertime(Integer normalOrderOvertime) {
        this.normalOrderOvertime = normalOrderOvertime;
    }

    public Integer getConfirmOvertime() {
        return confirmOvertime;
    }

    public void setConfirmOvertime(Integer confirmOvertime) {
        this.confirmOvertime = confirmOvertime;
    }

    public Integer getFinishOvertime() {
        return finishOvertime;
    }

    public void setFinishOvertime(Integer finishOvertime) {
        this.finishOvertime = finishOvertime;
    }

    public Integer getCommentOvertime() {
        return commentOvertime;
    }

    public void setCommentOvertime(Integer commentOvertime) {
        this.commentOvertime = commentOvertime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", flashOrderOvertime=").append(flashOrderOvertime);
        sb.append(", normalOrderOvertime=").append(normalOrderOvertime);
        sb.append(", confirmOvertime=").append(confirmOvertime);
        sb.append(", finishOvertime=").append(finishOvertime);
        sb.append(", commentOvertime=").append(commentOvertime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}