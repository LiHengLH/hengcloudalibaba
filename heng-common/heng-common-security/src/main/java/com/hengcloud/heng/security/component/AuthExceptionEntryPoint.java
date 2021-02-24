package com.hengcloud.heng.security.component;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hengcloud.heng.core.constant.CommonConstants;
import com.hengcloud.heng.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *根据认证服务器返回异常，自定义处理
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        R<String> result = new R<>();
        Throwable cause = authException.getCause();
        result.setCode(cn.hutool.http.HttpStatus.HTTP_UNAUTHORIZED);
        if (authException != null) {
            result.setMsg("error");
            result.setData(authException.getMessage());
        }

        if(cause instanceof InvalidTokenException) {
            result.setMsg("无效的token");

        }else{
            result.setMsg("访问此资源需要完全的身份验证");

        }
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }


}