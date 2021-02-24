package com.hengcloud.heng.api.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 终端信息表
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;


}
