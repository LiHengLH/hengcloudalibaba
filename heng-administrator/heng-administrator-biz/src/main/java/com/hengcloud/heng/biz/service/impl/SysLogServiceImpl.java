package com.hengcloud.heng.biz.service.impl;

import com.hengcloud.heng.api.entity.SysLog;
import com.hengcloud.heng.biz.mapper.SysLogMapper;
import com.hengcloud.heng.biz.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
