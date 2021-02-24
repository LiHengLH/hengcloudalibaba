package com.hengcloud.heng.api.feign.factory;

import com.hengcloud.heng.api.feign.RemoteUserService;
import com.hengcloud.heng.api.feign.fallback.RemoteUserFallBackImpl;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteUserFallBackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable cause) {
        RemoteUserFallBackImpl remoteUserFallBackImpl = new RemoteUserFallBackImpl();
        remoteUserFallBackImpl.setCause(cause);
        return remoteUserFallBackImpl;
    }
}
