package com.macro.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OmsCartItem implements Serializable {
    private Long id;

    private Long productId;

    private Long productSkuId;

    private Long memberId;

    private Integer quantity;

    private BigDecimal price;

    private String productPic;

    private String productName;

    private String productSubTitle;

    private String productSkuCode;

    private String memberNickname;

    private Date createDate;

    private Date modifyDate;

    private Integer deleteStatus;

    private Long productCategoryId;

    private String productBrand;

    private String productSn;

    private String productAttr;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getProductSubTitle() {
        return productSubTitle;
    }

    public void setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }

    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", productSkuId=").append(productSkuId);
        sb.append(", memberId=").append(memberId);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", productPic=").append(productPic);
        sb.append(", productName=").append(productName);
        sb.append(", productSubTitle=").append(productSubTitle);
        sb.append(", productSkuCode=").append(productSkuCode);
        sb.append(", memberNickname=").append(memberNickname);
        sb.append(", createDate=").append(createDate);
        sb.append(", modifyDate=").append(modifyDate);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", productCategoryId=").append(productCategoryId);
        sb.append(", productBrand=").append(productBrand);
        sb.append(", productSn=").append(productSn);
        sb.append(", productAttr=").append(productAttr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}