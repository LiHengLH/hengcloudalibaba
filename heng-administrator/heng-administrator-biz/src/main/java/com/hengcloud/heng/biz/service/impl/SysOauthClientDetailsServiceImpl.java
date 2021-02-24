package com.hengcloud.heng.biz.service.impl;

import com.hengcloud.heng.api.entity.SysOauthClientDetails;
import com.hengcloud.heng.biz.mapper.SysOauthClientDetailsMapper;
import com.hengcloud.heng.biz.service.ISysOauthClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 终端信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
@RequiredArgsConstructor
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements ISysOauthClientDetailsService {

    /**
     * 通过ID删除客户端
     * @param id
     * @return
     */
    @Override
    //@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#id")
    public Boolean removeClientDetailsById(String id) {
        return this.removeById(id);
    }

    /**
     * 根据客户端信息
     * @param clientDetails
     * @return
     */
    @Override
    //@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
    public Boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
        return this.updateById(clientDetails);
    }
}
