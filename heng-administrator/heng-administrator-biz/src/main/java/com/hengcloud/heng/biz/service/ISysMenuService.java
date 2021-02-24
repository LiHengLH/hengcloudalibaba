package com.hengcloud.heng.biz.service;


import com.hengcloud.heng.api.dto.MenuTree;
import com.hengcloud.heng.api.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hengcloud.heng.api.vo.MenuVO;
import com.hengcloud.heng.core.util.R;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.Set;


/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<MenuVO> findMenuByRoleId(Integer roleId);

    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return true成功,false失败
     */
    Boolean removeMenuById(Integer id);

    /**
     * 更新菜单信息
     * @param sysMenu 菜单信息
     * @return 成功、失败
     */
    Boolean updateMenuById(SysMenu sysMenu);

    /**
     * 构建树
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    List<MenuTree> treeMenu(boolean lazy, Integer parentId);

    /**
     * 查询菜单
     * @param menuSet
     * @param parentId
     * @return
     */
    List<MenuTree> filterMenu(Set<MenuVO> menuSet, Integer parentId);

    /**
     * 返回树形菜单集合
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    default R getTree(boolean lazy, Integer parentId) {
        return R.ok(treeMenu(lazy, parentId));
    }

}
