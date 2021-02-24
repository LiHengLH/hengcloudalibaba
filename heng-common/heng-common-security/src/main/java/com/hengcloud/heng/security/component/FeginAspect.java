package com.hengcloud.heng.security.component;

import com.hengcloud.heng.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class FeginAspect {
    @Autowired
    private  HttpServletRequest request;

    @Pointcut("@annotation(com.hengcloud.heng.security.annotation.Expose)")
    public void exposeAspect(){

    }

//    @Around(value = "exposeAspect()")
//    public Object doAround(ProceedingJoinPoint point){
//        try{
//            String headFrom = request.getHeader(SecurityConstants.FROM);
//            if(SecurityConstants.FROM_IN.equals(headFrom)){
//                return point.proceed();
//            }else{
//                log.warn("访问接口 {} 没有权限", point.getSignature().getName());
//                throw new AccessDeniedException("Access is denied");
//            }
//        }catch (Throwable e){
//            log.error("异常",e);
//        }
//       return null;
//    }

}
