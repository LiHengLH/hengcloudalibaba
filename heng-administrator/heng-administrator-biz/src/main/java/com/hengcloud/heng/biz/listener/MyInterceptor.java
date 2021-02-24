package com.hengcloud.heng.biz.listener;



import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需要config配置类配合，看config目录
 * 路径只有走DispatcherServlet，才会被拦截，默认静态资源不会被拦截
 * @author jared
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 拦截请求，在访问controller调用之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入到拦截器，被拦截。。");

        /**
         * false:请求被拦截，被驳回，验证出现问题
         * true: 请求在经过验证效验以后，是OK的,是可以放行
         */
        return true;
    }


    /**
     * 请求访问controller之后，渲染视图之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}