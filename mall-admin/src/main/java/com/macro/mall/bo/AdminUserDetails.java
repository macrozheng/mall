package com.macro.mall.bo;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * Created by macro on 2018/4/26.
 */
public class AdminUserDetails implements UserDetails {
    //后台用户
    private UmsAdmin umsAdmin;
    //拥有资源列表
    private List<UmsResource> resourceList;
    public AdminUserDetails(UmsAdmin umsAdmin,List<UmsResource> resourceList) {
        this.umsAdmin = umsAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的资源id和资源名称
        return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
// mall
// ├── mall-common -- 工具类及通用代码
// ├── mall-mbg -- MyBatisGenerator生成的数据库操作代码
// ├── mall-security -- SpringSecurity封装公用模块
// ├── mall-admin -- 后台商城管理系统接口
// ├── mall-search -- 基于Elasticsearch的商品搜索系统
// ├── mall-portal -- 前台商城系统接口
// └── mall-demo -- 框架搭建时的测试代码
