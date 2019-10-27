package com.btsoft.jf.cloud.core.aspect;

import com.alibaba.fastjson.JSON;
import com.btsoft.jf.cloud.core.annotation.JAuditLog;
import com.btsoft.jf.cloud.core.annotation.JAuditModule;
import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.base.service.ICommonService;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.context.impl.RequestContext;
import com.btsoft.jf.cloud.core.enums.impl.AuditTypeEnum;
import com.btsoft.jf.cloud.core.util.RequestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jeo_cb
 * @desc 控制器Controller拦截器
 * @date 2019/10/23 10:21
 **/
@Component
@Aspect
@Order(10)
public class ControllerAspect {

    private final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private final static String CONTROLLER="Controller";

    private static final ThreadLocal<Long> LOCAL_AUDIT = new ThreadLocal<>();

    private static final Long PERFORMANCE_THRESHOLD=1000*3L;

    @Autowired
    private ICommonService service;

    @Pointcut("execution(* com.btsoft.jf.cloud..controller.*Controller.*(..))")
    public void jAuditLog() {
    }

    @Before("jAuditLog()")
    public void doBefore(JoinPoint joinPoint) {
        LOCAL_AUDIT.set(System.currentTimeMillis());
    }

    @After("jAuditLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        auditLog(joinPoint);
    }

    private void auditLog(JoinPoint joinPoint){
        try {
            // 获取拦截的方法
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            Class<?> clazz = joinPoint.getTarget().getClass();
            Long startTime=LOCAL_AUDIT.get();
            LOCAL_AUDIT.remove();
            Long endTime=System.currentTimeMillis();
            Long cost=endTime-startTime;
            AuditTypeEnum auditTypeEnum=AuditTypeEnum.Audit;
            JAuditLog ja = targetMethod.getAnnotation(JAuditLog.class);
            if(ja==null && cost<PERFORMANCE_THRESHOLD){
                return;
            }
            if(ja==null && cost>PERFORMANCE_THRESHOLD){
                auditTypeEnum=AuditTypeEnum.Performance;
            }
            logger.debug("Audit log start...");
            Map<String,Object> log=new HashMap<>(10);
            //审计模块
            buildAuditLogModule(log,clazz);
            //审计方法
            buildAuditLogMethod(log,targetMethod);
            //审计时间
            buildAuditLogTime(log, startTime, endTime);
            //审计消息
            buildAuditLogMessage(log,targetMethod,joinPoint);
            //审计人信息
            buildAuditLogContext(log,auditTypeEnum);


            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if(requestAttributes!=null){
                HttpServletRequest request = requestAttributes.getRequest();
                log.put("logIp", RequestUtils.getIpAddr(request));
            }
            service.auditLog(log);
        } catch (Exception e) {
            logger.error("audit log is error:"+e.getMessage(),e);
        }
    }

    /**
     * 构建审计模块
     * @author jeo_cb
     * @date 2019/10/12
     * @param  log 审计map
     * @param clazz class对象
     **/
    private void buildAuditLogModule(Map<String, Object> log,Class<?> clazz){
        // 如果存在JAuditModule注解，从注解中获取模块名
        String module = clazz.getSimpleName();
        if (clazz.isAnnotationPresent(JAuditModule.class)) {
            // 获取模块
            JAuditModule jm = clazz.getAnnotation(JAuditModule.class);
            if (!StringUtils.isEmpty(jm.value())) {
                module = jm.value();
            }
        }else{
            module=module.replaceAll(CONTROLLER,"");
        }
        log.put("logModule", module);
    }

    /**
     * 构建审计模块
     * @author jeo_cb
     * @date 2019/10/12
     * @param  log 审计map
     * @param targetMethod 目标方法
     **/
    private void buildAuditLogMethod(Map<String, Object> log,Method targetMethod){
        String method = targetMethod.getName();
        JAuditLog ja = targetMethod.getAnnotation(JAuditLog.class);
        if (ja!=null && !StringUtils.isEmpty(ja.type())) {
            method = ja.type();
        }else{
            JOperator jo=targetMethod.getAnnotation(JOperator.class);
            if(jo!=null && !StringUtils.isEmpty(jo.code())){
                method=jo.code();
            }
        }
        log.put("logMethod", method);
    }

    /**
     * 构建审计模块
     * @author jeo_cb
     * @date 2019/10/12
     * @param  log 审计map
     * @param targetMethod 目标方法
     **/
    private void buildAuditLogMessage(Map<String, Object> log,Method targetMethod,JoinPoint joinPoint){
        JAuditLog ja = targetMethod.getAnnotation(JAuditLog.class);
        String message=ja.message();
        if(StringUtils.isEmpty(message)){
            log.put("logMessage", JSON.toJSONString(joinPoint.getArgs()));
        }else{
            log.put("logMessage", message);
        }
    }

    /**
     * 构建审计日期
     * @param log  审计map
     * @param startTime 请求开始时间
     * @param endTime 请求结束时间
     * @author jeo_cb
     * @date 2018-12-09 19:18
     */
    private void buildAuditLogTime(Map<String, Object> log,Long startTime,Long endTime) {
        //审计日期
        log.put("logTime", new Date(startTime));
        log.put("logStartTime", new Date(startTime));
        log.put("logEndTime", new Date(endTime));
        log.put("logCost", endTime-startTime);
    }

    /**
     * 构建审计模块
     * @author jeo_cb
     * @date 2019/10/12
     * @param  log 审计map
     **/
    private void buildAuditLogContext(Map<String, Object> log, AuditTypeEnum auditTypeEnum){
        RequestContext rc= JfCloud.getCurrent();
        if(rc!=null){
            log.put("appCode", rc.getRequestApp());
            log.put("userId", rc.getCurrentUserId());
        }
        log.put("logType", auditTypeEnum.getKey());
    }
}
