package com.macro.mall.portal.service.impl;

import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.domain.MemberProductCollection;
import com.macro.mall.portal.repository.MemberProductCollectionRepository;
import com.macro.mall.portal.service.MemberCollectionService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 会员收藏Service实现类
 * Created by macro on 2018/8/2.
 */
@Service
public class MemberCollectionServiceImpl implements MemberCollectionService {
    @Value("${mongo.insert.sqlEnable}")
    private Boolean sqlEnable;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private MemberProductCollectionRepository productCollectionRepository;
    @Autowired
    private UmsMemberService memberService;

    @Override
    public int add(MemberProductCollection productCollection) {
        int count = 0;
        if (productCollection.getProductId() == null) {
            return 0;
        }
        UmsMember member = memberService.getCurrentMember();
        productCollection.setMemberId(member.getId());
        productCollection.setMemberNickname(member.getNickname());
        productCollection.setMemberIcon(member.getIcon());
        MemberProductCollection findCollection = productCollectionRepository.findByMemberIdAndProductId(productCollection.getMemberId(), productCollection.getProductId());
        if (findCollection == null) {
            if (sqlEnable) {
                PmsProduct product = productMapper.selectByPrimaryKey(productCollection.getProductId());
                if (product == null || product.getDeleteStatus() == 1) {
                    return 0;
                }
                productCollection.setProductName(product.getName());
                productCollection.setProductSubTitle(product.getSubTitle());
                productCollection.setProductPrice(product.getPrice() + "");
                productCollection.setProductPic(product.getPic());
            }
            productCollectionRepository.save(productCollection);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long productId) {
        UmsMember member = memberService.getCurrentMember();
        return productCollectionRepository.deleteByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public Page<MemberProductCollection> list(Integer pageNum, Integer pageSize) {
        UmsMember member = memberService.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return productCollectionRepository.findByMemberId(member.getId(), pageable);
    }

    @Override
    public MemberProductCollection detail(Long productId) {
        UmsMember member = memberService.getCurrentMember();
        return productCollectionRepository.findByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public void clear() {
        UmsMember member = memberService.getCurrentMember();
        productCollectionRepository.deleteAllByMemberId(member.getId());
    }
}
