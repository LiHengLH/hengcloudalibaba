package com.hengcloud.heng.biz.service;

import com.hengcloud.heng.api.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
public interface ISysRoleService extends IService<SysRole> {
   List<SysRole> findRolesByUserId(Integer userId);

}
