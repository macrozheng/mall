package com.macro.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsOrderReturnApply implements Serializable {
    private Long id;

    /**
     * 订单id
     *
     * @mbggenerated
     */
    private Long orderId;

    private Long companyAddressId;

    /**
     * 订单编号
     *
     * @mbggenerated
     */
    private String orderSn;

    private Date createTime;

    private String memberUsername;

    /**
     * 退款金额
     *
     * @mbggenerated
     */
    private BigDecimal returnAmount;

    private String returnName;

    /**
     * 退货人电话
     *
     * @mbggenerated
     */
    private String returnPhone;

    /**
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 处理时间
     *
     * @mbggenerated
     */
    private Date handleTime;

    private String productPic;

    private String productName;

    private String brandName;

    /**
     * 商品销售属性：颜色：红色；尺码：xl;
     *
     * @mbggenerated
     */
    private String productAttr;

    /**
     * 退货数量
     *
     * @mbggenerated
     */
    private Integer productCount;

    /**
     * 原因
     *
     * @mbggenerated
     */
    private String reason;

    /**
     * 描述
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 凭证图片，以逗号隔开
     *
     * @mbggenerated
     */
    private String proofPics;

    /**
     * 要退的邮费
     *
     * @mbggenerated
     */
    private BigDecimal returnPostAmount;

    /**
     * 是否退邮费：0->不退；1->退
     *
     * @mbggenerated
     */
    private Integer returnPostStatus;

    /**
     * 确认退款总金额
     *
     * @mbggenerated
     */
    private BigDecimal confirmReturnAmount;

    /**
     * 处理备注
     *
     * @mbggenerated
     */
    private String handleNote;

    /**
     * 处理人员
     *
     * @mbggenerated
     */
    private String handleMan;

    /**
     * 收货人
     *
     * @mbggenerated
     */
    private String receiveMan;

    private Date receiveTime;

    private String receiveNote;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }

    public BigDecimal getReturnPostAmount() {
        return returnPostAmount;
    }

    public void setReturnPostAmount(BigDecimal returnPostAmount) {
        this.returnPostAmount = returnPostAmount;
    }

    public Integer getReturnPostStatus() {
        return returnPostStatus;
    }

    public void setReturnPostStatus(Integer returnPostStatus) {
        this.returnPostStatus = returnPostStatus;
    }

    public BigDecimal getConfirmReturnAmount() {
        return confirmReturnAmount;
    }

    public void setConfirmReturnAmount(BigDecimal confirmReturnAmount) {
        this.confirmReturnAmount = confirmReturnAmount;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleMan() {
        return handleMan;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveNote() {
        return receiveNote;
    }

    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", companyAddressId=").append(companyAddressId);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", createTime=").append(createTime);
        sb.append(", memberUsername=").append(memberUsername);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", returnName=").append(returnName);
        sb.append(", returnPhone=").append(returnPhone);
        sb.append(", status=").append(status);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", brandName=").append(brandName);
        sb.append(", productAttr=").append(productAttr);
        sb.append(", productCount=").append(productCount);
        sb.append(", reason=").append(reason);
        sb.append(", description=").append(description);
        sb.append(", proofPics=").append(proofPics);
        sb.append(", returnPostAmount=").append(returnPostAmount);
        sb.append(", returnPostStatus=").append(returnPostStatus);
        sb.append(", confirmReturnAmount=").append(confirmReturnAmount);
        sb.append(", handleNote=").append(handleNote);
        sb.append(", handleMan=").append(handleMan);
        sb.append(", receiveMan=").append(receiveMan);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", receiveNote=").append(receiveNote);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}