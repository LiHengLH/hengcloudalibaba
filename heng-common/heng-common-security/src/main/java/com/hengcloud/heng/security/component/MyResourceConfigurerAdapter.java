package com.hengcloud.heng.security.component;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.access.prepost.PrePostAdviceReactiveMethodInterceptor;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;


@Order(90)
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass = true,jsr250Enabled = true)
public class MyResourceConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    private RemoteTokenServices remoteTokenServices;

    @Autowired
    private PermitAllUrlProperties permitAllUrl;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);  不能写，重写父类不得有
        //为请求授权

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests();

        permitAllUrl.getIgnoreUrls() .forEach(url -> registry.antMatchers(url).permitAll());
        registry.antMatchers(
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/v2/api-docs").permitAll();
        registry.anyRequest().authenticated().and().csrf().disable();

    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }




    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);

        restTemplate().setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            // Ignore 400
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
        //remoteTokenServices中本身有restTemplate,但是不具备负载均衡功能。
        if (Objects.nonNull(remoteTokenServices)) {
            remoteTokenServices.setRestTemplate(restTemplate());
            resources.tokenServices(remoteTokenServices);
        }
    }


}

