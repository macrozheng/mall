package com.macro.mall.model;

import java.io.Serializable;

public class PmsProductAttributeValue implements Serializable {
    private Long id;

    private Long pmsProductId;

    private Long pmsProductAttributeId;

    private String value;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPmsProductId() {
        return pmsProductId;
    }

    public void setPmsProductId(Long pmsProductId) {
        this.pmsProductId = pmsProductId;
    }

    public Long getPmsProductAttributeId() {
        return pmsProductAttributeId;
    }

    public void setPmsProductAttributeId(Long pmsProductAttributeId) {
        this.pmsProductAttributeId = pmsProductAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pmsProductId=").append(pmsProductId);
        sb.append(", pmsProductAttributeId=").append(pmsProductAttributeId);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}