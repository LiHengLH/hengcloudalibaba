package com.hengcloud.heng.security.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

@Component("pms")
public class PermissionUtil {

    public boolean hasPermission(String per){
        if(per.isEmpty()){
            return false;
        }

        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();

        if(ObjectUtils.isEmpty(authentication)){
            return false;
        }

       return  authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).
                filter(StringUtils::hasText).anyMatch(i-> PatternMatchUtils.simpleMatch(per, i));

    }
}
