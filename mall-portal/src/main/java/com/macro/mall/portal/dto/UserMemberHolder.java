package com.macro.mall.portal.dto;

import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.domain.MemberDetails;
import com.macro.mall.security.component.UserHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserMemberHolder {

    public static MemberDetails get() {
        return (MemberDetails)UserHolder.get();
    }

    public static void set(MemberDetails user) {
        UserHolder.set(user);
    }
}
