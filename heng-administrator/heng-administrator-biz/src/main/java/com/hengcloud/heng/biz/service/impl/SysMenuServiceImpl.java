package com.hengcloud.heng.biz.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hengcloud.heng.api.dto.MenuTree;
import com.hengcloud.heng.api.entity.SysMenu;

import com.hengcloud.heng.api.entity.SysRoleMenu;
import com.hengcloud.heng.api.util.TreeUtils;
import com.hengcloud.heng.api.vo.MenuVO;
import com.hengcloud.heng.biz.mapper.SysMenuMapper;
import com.hengcloud.heng.biz.mapper.SysRoleMenuMapper;
import com.hengcloud.heng.biz.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hengcloud.heng.core.constant.CommonConstants;
import com.hengcloud.heng.core.constant.enums.MenuTypeEnum;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author li
 * @since 2021-02-27
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    //@Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public List<MenuVO> findMenuByRoleId(Integer roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }


    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return true成功,false失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    //@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public Boolean removeMenuById(Integer id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

        Assert.isTrue(CollUtil.isEmpty(menuList), "菜单含有下级不能删除");

        sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
        // 删除当前菜单及其子菜单
        return this.removeById(id);
    }

    @Override
    //@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }

    /**
     * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
     * @param lazy 是否是懒加载
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<MenuTree> treeMenu(boolean lazy, Integer parentId) {
        if (!lazy) {
            return buildTree(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)),
                    CommonConstants.MENU_TREE_ROOT_ID);
        }

        Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return buildTree(
                baseMapper.selectList(
                        Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSort)),
                parent);
    }

    /**
     * 查询菜单
     * @param all 全部菜单s
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<MenuTree> filterMenu(Set<MenuVO> all, Integer parentId) {
        List<MenuTree> menuTreeList = all.stream().filter(vo -> MenuTypeEnum.LEFT_MENU.getType().equals(vo.getType()))
                .map(MenuTree::new).sorted(Comparator.comparingInt(MenuTree::getSort)).collect(Collectors.toList());
        Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtils.build(menuTreeList, parent);
    }

    /**
     * 通过sysMenu创建树形节点
     * @param menus
     * @param root
     * @return
     */
    private List<MenuTree> buildTree(List<SysMenu> menus, int root) {
        List<MenuTree> trees = new ArrayList<>();
        MenuTree node;
        for (SysMenu menu : menus) {
            node = new MenuTree();
            node.setId(menu.getMenuId());
            node.setParentId(menu.getParentId());
            node.setName(menu.getName());
            node.setPath(menu.getPath());
            node.setPermission(menu.getPermission());
            node.setLabel(menu.getName());
            node.setIcon(menu.getIcon());
            node.setType(menu.getType());
            node.setSort(menu.getSort());
            node.setHasChildren(false);
            node.setKeepAlive(menu.getKeepAlive());
            trees.add(node);
        }
        return TreeUtils.build(trees, root);
    }

}
