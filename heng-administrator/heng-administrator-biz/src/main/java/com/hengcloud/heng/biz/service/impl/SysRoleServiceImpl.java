package com.hengcloud.heng.biz.service.impl;

import com.hengcloud.heng.api.entity.SysRole;
import com.hengcloud.heng.biz.mapper.SysRoleMapper;
import com.hengcloud.heng.biz.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {

        return baseMapper.listRolesByUserId(userId);
    }
}
