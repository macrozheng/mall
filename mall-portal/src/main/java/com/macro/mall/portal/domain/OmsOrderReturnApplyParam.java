package com.macro.mall.portal.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 退货申请请求参数
 * Created by macro on 2018/10/17.
 */
@Getter
@Setter
public class OmsOrderReturnApplyParam {
    private Long orderId;
    private Long productId;
    private String orderSn;
    private String memberUsername;
    private String returnName;
    private String returnPhone;
    private String productPic;
    private String productName;
    private String productBrand;
    private String productAttr;
    private Integer productCount;
    private BigDecimal productPrice;
    private BigDecimal productRealPrice;
    private String reason;
    private String description;
    private String proofPics;

}
