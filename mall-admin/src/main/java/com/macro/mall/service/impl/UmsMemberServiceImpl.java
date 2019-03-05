package com.macro.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.UmsMemberQueryParam;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberExample;
import com.macro.mall.service.UmsMemberService;
/**
 *会员管理实现类
 * @author Administrator
 *
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService{
	@Autowired
	private UmsMemberMapper memberMapper;
	@Override
	public int create(UmsMember memberParam) {
		
		UmsMember member = memberParam;
		member.setId(null);
		int count =memberMapper.insertSelective(member);
		return count;
	}

	@Override
	public int update(Long id, UmsMember memberParam) {
		// TODO Auto-generated method stub
		UmsMember member = memberParam;
		member.setId(id);
		return memberMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public List<UmsMember> list(UmsMemberQueryParam memberQueryParam, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		UmsMemberExample memberExample = new UmsMemberExample();
		UmsMemberExample.Criteria criteria =memberExample.createCriteria();
		if (memberQueryParam.getStatus() !=null) {
			criteria.andStatusEqualTo(memberQueryParam.getStatus());
		}
		if (!StringUtils.isEmpty(memberQueryParam.getKeyword())) {
			criteria.andUsernameLike(memberQueryParam.getKeyword());
			criteria.andNicknameLike(memberQueryParam.getKeyword());
		}
		return memberMapper.selectByExample(memberExample);
	}

	@Override
	public int updateVerifyStatus(Long id, Integer status) {
		// TODO Auto-generated method stub
		UmsMember member = new UmsMember();
		member.setId(id);
		member.setStatus(status);
		return memberMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public int deleteMember(Long id) {
		// TODO Auto-generated method stub
		return memberMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<UmsMember> list(String keyword) {
		UmsMemberExample memberExample = new UmsMemberExample();
		UmsMemberExample.Criteria criteria =memberExample.createCriteria();
		if (!StringUtils.isEmpty(keyword)) {
			criteria.andUsernameLike("%" + keyword + "%");
			memberExample.or().andNicknameLike("%" + keyword + "%");
		}
		return memberMapper.selectByExample(memberExample);
	}

}
