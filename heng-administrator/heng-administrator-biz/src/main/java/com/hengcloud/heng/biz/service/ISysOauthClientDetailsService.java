package com.hengcloud.heng.biz.service;

import com.hengcloud.heng.api.entity.SysOauthClientDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 终端信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
public interface ISysOauthClientDetailsService extends IService<SysOauthClientDetails> {
    /**
     * 通过ID删除客户端
     * @param id
     * @return
     */
    Boolean removeClientDetailsById(String id);

    /**
     * 根据客户端信息
     * @param sysOauthClientDetails
     * @return
     */
    Boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);
}
