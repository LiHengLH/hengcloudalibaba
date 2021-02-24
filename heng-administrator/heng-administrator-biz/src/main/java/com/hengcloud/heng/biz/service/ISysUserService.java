package com.hengcloud.heng.biz.service;

import com.hengcloud.heng.api.dto.UserInfo;
import com.hengcloud.heng.api.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
public interface ISysUserService extends IService<SysUser> {

    /**
     * 查询用户信息
     * @param sysUser 用户
     * @return userInfo
     */
    UserInfo getUserInfo(SysUser sysUser);
}
