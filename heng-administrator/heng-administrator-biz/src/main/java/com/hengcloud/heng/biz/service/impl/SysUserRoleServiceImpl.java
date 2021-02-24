package com.hengcloud.heng.biz.service.impl;

import com.hengcloud.heng.api.entity.SysUserRole;
import com.hengcloud.heng.biz.mapper.SysUserRoleMapper;
import com.hengcloud.heng.biz.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
