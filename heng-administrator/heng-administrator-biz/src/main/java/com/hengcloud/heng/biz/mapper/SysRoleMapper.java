package com.hengcloud.heng.biz.mapper;

import com.hengcloud.heng.api.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户ID，查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);
}
