package com.hengcloud.heng.api.feign.fallback;

import com.hengcloud.heng.api.dto.UserInfo;
import com.hengcloud.heng.api.feign.RemoteUserService;
import com.hengcloud.heng.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RemoteUserFallBackImpl implements RemoteUserService {
    @Setter
    private Throwable cause;


    @Override
    public R<UserInfo> info(String username, String from) {
        log.error("xxxx{}",cause);
        return R.failed("熔断。。。");
    }

    @Override
    public R<UserInfo> social(String inStr) {
        return null;
    }


}


