
package com.hengcloud.heng.swagger.annotation;


import com.hengcloud.heng.swagger.config.GatewaySwaggerAutoConfiguration;
import com.hengcloud.heng.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * @author lengleng
 * @date 2020/10/2 开启pig swagger
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableSwagger2
@Import({ SwaggerAutoConfiguration.class, GatewaySwaggerAutoConfiguration.class })
public @interface EnableHengSwagger2 {

}
