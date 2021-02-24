package com.hengcloud.heng.biz.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.hengcloud.heng.api.dto.UserInfo;
import com.hengcloud.heng.api.entity.SysRole;
import com.hengcloud.heng.api.entity.SysUser;
import com.hengcloud.heng.api.vo.MenuVO;
import com.hengcloud.heng.biz.mapper.SysUserMapper;
import com.hengcloud.heng.biz.service.ISysMenuService;
import com.hengcloud.heng.biz.service.ISysRoleService;
import com.hengcloud.heng.biz.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

     final ISysRoleService sysRoleService;

    final ISysMenuService sysMenuService;
    /**
     * 通过查用户的全部信息
     * @param sysUser 用户
     * @return
     */
    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        // 设置角色列表 （ID）
        List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId()).stream().map(SysRole::getRoleId)
                .collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));

        // 设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.findMenuByRoleId(roleId).stream()
                    .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission())).map(MenuVO::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }
}
