
package com.hengcloud.heng.feign;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;

import com.hengcloud.heng.feign.ext.HengSentinelFeign;
import com.hengcloud.heng.feign.handle.MyUrlBlockHandler;
import com.hengcloud.heng.feign.parser.MyOriginParser;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * sentinel 配置
 *
 * @author heng
 * @date 2021-02-12
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class HengFeignAutoConfiguration {

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder() {
		return HengSentinelFeign.builder();
	}


	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	public MyUrlBlockHandler myHandle() {
		return new MyUrlBlockHandler();
	}

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	public MyOriginParser myParser() {
		return new MyOriginParser();
	}


}
