package com.macro.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsOrderReturnApply implements Serializable {
    private Long id;

    private Long orderId;

    private Long companyAddressId;

    private String orderSn;

    private Date createTime;

    private String memberUsername;

    private BigDecimal returnAmount;

    private String returnName;

    private String returnPhone;

    private Integer status;

    private Date handleTime;

    private String productPic;

    private String productName;

    private String brandName;

    private String productAttr;

    private Integer productCount;

    private String reason;

    private String description;

    private String proofPics;

    private BigDecimal returnPostAmount;

    private Integer returnPostStatus;

    private BigDecimal confirmReturnAmount;

    private String handleNote;

    private String handleMan;

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