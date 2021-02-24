package com.hengcloud.heng.api.feign.config;

import com.hengcloud.heng.api.feign.factory.RemoteUserFallBackFactory;
import com.hengcloud.heng.api.feign.fallback.RemoteUserFallBackImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
  @Bean
  public RemoteUserFallBackFactory echoServiceFallback() {
    return new RemoteUserFallBackFactory();
  }


}