package com.hengcloud.heng.feign.handle;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.hengcloud.heng.core.util.R;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        R r =new R();
        if(e instanceof FlowException){
            r =R.failed("请求数量过大").setMsg("22").setCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }else{
            r = R.failed().setMsg("11111");
        }
        response.setContentType(ContentType.JSON.toString()+";charset=utf-8");
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setHeader("Content-Type","application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSONUtil.toJsonStr(r));
    }
}
