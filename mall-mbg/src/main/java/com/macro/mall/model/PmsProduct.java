package com.macro.mall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PmsProduct implements Serializable {
    private Long id;

    private Long brandId;

    private Long productCategoryId;

    private Long feightTemplateId;

    private Long productAttributeCategoryId;

    private String name;

    private String pic;

    private String productSn;

    private Integer deleteStatus;

    private Integer publishStatus;

    private Integer newStatus;

    private Integer recommandStatus;

    private Integer verifyStatus;

    private Integer sort;

    private Integer sale;

    private BigDecimal price;

    private BigDecimal promotionPrice;

    private Integer giftGrowth;

    private Integer giftPoint;

    private Integer usePointLimit;

    private String subTitle;

    private BigDecimal originalPrice;

    private Integer stock;

    private Integer lowStock;

    private String unit;

    private BigDecimal weight;

    private Integer previewStatus;

    private String serviceIds;

    private String keywords;

    private String note;

    private String albumPics;

    private String detailTitle;

    private Date promotionStartTime;

    private Date promotionEndTime;

    private Integer promotionPerLimit;

    private Integer promotionType;

    private String brandName;

    private String productCategoryName;

    private String description;

    private String detailDesc;

    private String detailHtml;

    private String detailMobileHtml;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(Integer recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(Integer giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public Integer getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(Integer giftPoint) {
        this.giftPoint = giftPoint;
    }

    public Integer getUsePointLimit() {
        return usePointLimit;
    }

    public void setUsePointLimit(Integer usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPreviewStatus() {
        return previewStatus;
    }

    public void setPreviewStatus(Integer previewStatus) {
        this.previewStatus = previewStatus;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public Date getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Date promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public Date getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(Date promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public Integer getPromotionPerLimit() {
        return promotionPerLimit;
    }

    public void setPromotionPerLimit(Integer promotionPerLimit) {
        this.promotionPerLimit = promotionPerLimit;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getDetailMobileHtml() {
        return detailMobileHtml;
    }

    public void setDetailMobileHtml(String detailMobileHtml) {
        this.detailMobileHtml = detailMobileHtml;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", brandId=").append(brandId);
        sb.append(", productCategoryId=").append(productCategoryId);
        sb.append(", feightTemplateId=").append(feightTemplateId);
        sb.append(", productAttributeCategoryId=").append(productAttributeCategoryId);
        sb.append(", name=").append(name);
        sb.append(", pic=").append(pic);
        sb.append(", productSn=").append(productSn);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", newStatus=").append(newStatus);
        sb.append(", recommandStatus=").append(recommandStatus);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", sort=").append(sort);
        sb.append(", sale=").append(sale);
        sb.append(", price=").append(price);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", giftGrowth=").append(giftGrowth);
        sb.append(", giftPoint=").append(giftPoint);
        sb.append(", usePointLimit=").append(usePointLimit);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", originalPrice=").append(originalPrice);
        sb.append(", stock=").append(stock);
        sb.append(", lowStock=").append(lowStock);
        sb.append(", unit=").append(unit);
        sb.append(", weight=").append(weight);
        sb.append(", previewStatus=").append(previewStatus);
        sb.append(", serviceIds=").append(serviceIds);
        sb.append(", keywords=").append(keywords);
        sb.append(", note=").append(note);
        sb.append(", albumPics=").append(albumPics);
        sb.append(", detailTitle=").append(detailTitle);
        sb.append(", promotionStartTime=").append(promotionStartTime);
        sb.append(", promotionEndTime=").append(promotionEndTime);
        sb.append(", promotionPerLimit=").append(promotionPerLimit);
        sb.append(", promotionType=").append(promotionType);
        sb.append(", brandName=").append(brandName);
        sb.append(", productCategoryName=").append(productCategoryName);
        sb.append(", description=").append(description);
        sb.append(", detailDesc=").append(detailDesc);
        sb.append(", detailHtml=").append(detailHtml);
        sb.append(", detailMobileHtml=").append(detailMobileHtml);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}