package com.hengcloud.heng.auth.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.hengcloud.heng.api.dto.UserInfo;
import com.hengcloud.heng.api.entity.SysUser;
import com.hengcloud.heng.api.feign.RemoteUserService;
import com.hengcloud.heng.core.constant.SecurityConstants;
import com.hengcloud.heng.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
 class MyUserDetailsServiceImpl implements UserDetailsService {

    private final  RemoteUserService remoteUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        R<UserInfo> result = remoteUserService.info(username,SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);

        return userDetails;
    }

    /**
     * 构建userdetails
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(R<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        // 构造security用户
        return new User( user.getUsername(),
                SecurityConstants.BCRYPT + user.getPassword(),
                StrUtil.equals(user.getLockFlag(), "0"), true, true, true, authorities);
    }
}
