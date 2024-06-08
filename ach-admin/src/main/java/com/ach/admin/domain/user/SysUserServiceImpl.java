package com.ach.admin.domain.user;


import com.ach.domain.system.user.SysUserService;
import com.ach.infrastructure.user.AuthenticationUtils;
import org.springframework.stereotype.Component;

@Component("DomainUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {
    @Override
    public Boolean matchesPassword(String rawPassword, String encodedPassword) {
        return AuthenticationUtils.matchesPassword(rawPassword, encodedPassword);
    }
}
