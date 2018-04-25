package com.macro.mall.service.impl;

import com.macro.mall.dao.PmsMemberPriceDao;
import com.macro.mall.dao.PmsProductFullReductionDao;
import com.macro.mall.dao.PmsProductLadderDao;
import com.macro.mall.dto.PmsProductParam;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.PmsMemberPrice;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductFullReduction;
import com.macro.mall.model.PmsProductLadder;
import com.macro.mall.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品管理Service实现类
 */
@Service
public class PmsProductServiceImpl implements PmsProductService{
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsMemberPriceDao memberPriceDao;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;
    @Override
    public int create(PmsProductParam productParam) {
        int count;
        //创建商品
        PmsProduct product = productParam.getProduct();
        product.setId(null);
        productMapper.insertSelective(product);
        //根据促销类型设置价格：、阶梯价格、满减价格
        Long productId = product.getId();
        if(product.getPromotionType()==2){
            //会员价格
            List<PmsMemberPrice> memberPriceList = productParam.getPmsMemberPriceList();
            if(!CollectionUtils.isEmpty(memberPriceList)){
                for(PmsMemberPrice pmsMemberPrice: memberPriceList){
                    pmsMemberPrice.setId(null);
                    pmsMemberPrice.setProductId(productId);
                }
                memberPriceDao.insertList(memberPriceList);
            }
        }else if(product.getPromotionType()==3){
            //阶梯价格
            List<PmsProductLadder> productLadderList = productParam.getProductLadderList();
            if(!CollectionUtils.isEmpty(productLadderList)){
                for(PmsProductLadder productLadder:productLadderList){
                    productLadder.setId(null);
                    productLadder.setProductId(productId);
                }
                productLadderDao.insertList(productLadderList);
            }
        }else if(product.getPromotionType()==4){
            //满减价格
            List<PmsProductFullReduction> productFullReductionList = productParam.getPmsProductFullReductionList();
            if(!CollectionUtils.isEmpty(productFullReductionList)){
                for (PmsProductFullReduction productFullReduction: productFullReductionList) {
                    productFullReduction.setId(null);
                    productFullReduction.setProductId(productId);
                }
                productFullReductionDao.insertList(productFullReductionList);
            }
        }
        //添加sku库存信息
        //添加商品参数
        //添加自定义商品规格
        //关联专题
        //关联优选
        count=1;
        return count;
    }
}
