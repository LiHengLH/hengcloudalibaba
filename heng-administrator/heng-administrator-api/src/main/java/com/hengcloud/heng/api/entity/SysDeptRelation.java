package com.hengcloud.heng.api.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门关系表
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDeptRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    private Integer ancestor;

    /**
     * 后代节点
     */
    private Integer descendant;


}
