package com.hengcloud.heng.biz.mapper;

import com.hengcloud.heng.api.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hengcloud.heng.api.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过角色编号查询菜单
     * @param roleId 角色ID
     * @return
     */
    List<MenuVO> listMenusByRoleId(Integer roleId);

    /**
     * 通过角色ID查询权限
     * @param roleIds Ids
     * @return
     */
    List<String> listPermissionsByRoleIds(String roleIds);
}
