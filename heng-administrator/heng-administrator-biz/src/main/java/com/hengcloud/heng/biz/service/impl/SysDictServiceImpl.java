package com.hengcloud.heng.biz.service.impl;

import com.hengcloud.heng.api.entity.SysDict;
import com.hengcloud.heng.biz.mapper.SysDictMapper;
import com.hengcloud.heng.biz.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

}
