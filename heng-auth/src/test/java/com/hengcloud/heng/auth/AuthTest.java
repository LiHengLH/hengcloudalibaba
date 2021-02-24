package com.hengcloud.heng.auth;


import com.hengcloud.heng.api.feign.RemoteUserService;
import com.hengcloud.heng.core.constant.SecurityConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
class TmallApplicationTests {
    @Autowired
 private RemoteUserService remoteUserService;
    @Test
    public void a(){
        remoteUserService.info("admin", SecurityConstants.FROM_IN);
    }
}
