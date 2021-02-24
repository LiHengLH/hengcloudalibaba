package com.hengcloud.heng.biz;


import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.hengcloud.heng.feign.HengFeignAutoConfiguration;
import com.hengcloud.heng.swagger.annotation.EnableHengSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author pig archetype
 * <p>
 * 项目启动
 */
@EnableHengSwagger2
@MapperScan("com.hengcloud.heng.biz.mapper")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
