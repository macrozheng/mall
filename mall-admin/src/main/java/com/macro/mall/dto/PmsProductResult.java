package com.macro.mall.dto;

import com.macro.mall.model.CmsPrefrenceArea;
import com.macro.mall.model.CmsSubject;

import java.util.List;

/**
 * 查询单个产品进行修改时返回的结果
 * Created by macro on 2018/4/26.
 */
public class PmsProductResult extends PmsProductParam{
    private String feightTemplateName;
    private String productAttributeCategoryName;
    private List<CmsSubject> subjectList;
    private List<CmsPrefrenceArea> prefrenceAreaList;

    public String getFeightTemplateName() {
        return feightTemplateName;
    }

    public void setFeightTemplateName(String feightTemplateName) {
        this.feightTemplateName = feightTemplateName;
    }

    public String getProductAttributeCategoryName() {
        return productAttributeCategoryName;
    }

    public void setProductAttributeCategoryName(String productAttributeCategoryName) {
        this.productAttributeCategoryName = productAttributeCategoryName;
    }

    public List<CmsSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<CmsSubject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<CmsPrefrenceArea> getPrefrenceAreaList() {
        return prefrenceAreaList;
    }

    public void setPrefrenceAreaList(List<CmsPrefrenceArea> prefrenceAreaList) {
        this.prefrenceAreaList = prefrenceAreaList;
    }
}
