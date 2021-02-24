package com.hengcloud.heng.security.component;

import cn.hutool.core.util.ReUtil;
import com.hengcloud.heng.security.annotation.Expose;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Configuration
@ConditionalOnExpression("!'${security.oauth2.client.ignore-urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security.oauth2.client")
public class PermitAllUrlProperties implements InitializingBean {
   private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");
   @Autowired
   private WebApplicationContext applicationContext;
   @Getter
   @Setter
   private List<String> ignoreUrls = new ArrayList<>();
   @Override
   public void afterPropertiesSet() {
      RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
      Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
      map.keySet().forEach(info -> {
         HandlerMethod handlerMethod = map.get(info);
         // 获取方法上边的注解 替代path variable 为 *
         Expose method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Expose.class);
         Optional.ofNullable(method)
               .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                     .forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, "*"))));
         // 获取类上边的注解, 替代path variable 为 *
         Expose controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Expose.class);
         Optional.ofNullable(controller)
               .ifPresent(inner -> info.getPatternsCondition().getPatterns()
                     .forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, "*"))));
      });
   }
}