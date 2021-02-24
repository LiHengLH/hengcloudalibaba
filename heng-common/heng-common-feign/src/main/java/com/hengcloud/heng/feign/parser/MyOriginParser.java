package com.hengcloud.heng.feign.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class MyOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        return request.getHeader("ALLOW");
    }
}
