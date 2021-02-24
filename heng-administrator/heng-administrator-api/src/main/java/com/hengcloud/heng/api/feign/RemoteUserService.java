

package com.hengcloud.heng.api.feign;


import com.hengcloud.heng.api.dto.UserInfo;
import com.hengcloud.heng.api.feign.config.FeignConfiguration;
import com.hengcloud.heng.api.feign.factory.RemoteUserFallBackFactory;
import com.hengcloud.heng.api.feign.fallback.RemoteUserFallBackImpl;
import com.hengcloud.heng.core.constant.SecurityConstants;
import com.hengcloud.heng.core.constant.ServiceNameConstants;
import com.hengcloud.heng.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author liheng
 * @date 2021/1/29
 */
//@FeignClient(value = ServiceNameConstants.UMPS_SERVICE,fallback = RemoteUserFallBackImpl.class,configuration = FeignConfiguration.class)
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE,fallbackFactory = RemoteUserFallBackFactory.class,configuration = FeignConfiguration.class)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr);

}
