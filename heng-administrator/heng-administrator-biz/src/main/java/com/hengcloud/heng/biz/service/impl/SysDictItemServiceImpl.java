package com.hengcloud.heng.biz.service.impl;

import com.hengcloud.heng.api.entity.SysDictItem;
import com.hengcloud.heng.biz.mapper.SysDictItemMapper;
import com.hengcloud.heng.biz.service.ISysDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

}
