package com.hengcloud.heng.biz.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hengcloud.heng.api.entity.SysUser;
import com.hengcloud.heng.biz.service.ISysUserService;
import com.hengcloud.heng.core.util.R;
import com.hengcloud.heng.security.annotation.Expose;
import io.micrometer.core.instrument.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {
    //必须加上final 才会注入到容器中
    private final ISysUserService  userService;

    /**
     * 获取当前用户全部信息
     * @return 用户信息
     */
    @GetMapping(value = { "/info" })

    public R info() {
       return  R.ok().setData(userService.list());
    }

    //pms是自动以判断是否有权限的工具PermissionUtil
    @Expose
    @GetMapping(value = { "/test" })
    //@PreAuthorize("@pms.hasPermission('sys_log_del')")
    public R test() {
        return  R.ok().setData(userService.list());
    }

    @Expose
    @GetMapping("/info/{username}")
    public R info(@PathVariable String username) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.failed(String.format("用户信息为空 %s", username));
        }
        return R.ok(userService.getUserInfo(user));
    }

}
