package com.btsoft.jf.cloud.core.base.service.impl;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.base.service.ICommonService;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.PermissionSourceEnum;
import com.btsoft.jf.cloud.core.enums.impl.PermissionTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 通用Service实现类
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Service
public class CommonServiceImpl implements ICommonService {

    private final static Logger logger= LoggerFactory.getLogger(CommonServiceImpl.class);

    @Value("${jf.cloud.cloudMonitorUrl:http://jf-cloud-monitor}")
    private String cloudMonitorUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CommonResult<List<Map<String, String>>> listPermission() {
        Map<String, Object> resourcesMap = JfCloud.getContext().getBeansWithAnnotation(JResource.class);
        List<Map<String,String>> resourcesList=new ArrayList<>();
        if(resourcesMap!=null){
            List<Map<String,String>> methodList=new ArrayList<>();
            for (Iterator<Object> iterator = resourcesMap.values().iterator(); iterator.hasNext();) {
                Object obj = iterator.next();
                Class<?> clazz= AopUtils.getTargetClass(obj);
                if (!clazz.isAnnotationPresent(JResource.class)) {
                    continue;
                }
                JResource jr = clazz.getAnnotation(JResource.class);

                //构造Resources对象
                Map<String, String> resourceMap=new HashMap<>(8);
                resourceMap.put("permissionCode", jr.code());
                resourceMap.put("permissionDescCN", jr.descCN());
                resourceMap.put("permissionDescEN", jr.descEN());
                resourceMap.put("permissionType", PermissionTypeEnum.Resources.getKey());
                resourceMap.put("permissionSource", PermissionSourceEnum.Annotation.getKey());
                resourceMap.put("parentCode", jr.parentCode());
                resourceMap.put("permissionSort",String.valueOf(jr.sort()));
                String appCode=jr.appCode();
                if(StringUtils.isEmpty(appCode)) {
                    appCode=JfCloud.getCurrentAppCode();
                }
                resourceMap.put("appCode", appCode);
                resourcesList.add(resourceMap);

                //获取方法级权限配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(JOperator.class)) {
                        JOperator jo = method.getAnnotation(JOperator.class);
                        Map<String, String> methodMap=new HashMap<>(8);
                        methodMap.put("permissionCode", jo.code());
                        methodMap.put("permissionDescCN", jo.descCN());
                        methodMap.put("permissionDescEN", jo.descEN());
                        methodMap.put("parentCode", jr.code());
                        methodMap.put("permissionType", PermissionTypeEnum.Method.getKey());
                        methodMap.put("permissionSource", PermissionSourceEnum.Annotation.getKey());
                        methodMap.put("appCode", appCode);
                        methodMap.put("permissionSort",String.valueOf(jo.sort()));
                        methodList.add(methodMap);
                    }
                }
            }

            //合并资源和方法权限点
            resourcesList.addAll(methodList);
        }
        return CommonResultUtils.success(resourcesList);
    }

    @Async
    @Override
    public void auditLog(Map<String, Object> log) {
        try {
            Result result=restTemplate.postForObject(cloudMonitorUrl+"/monitor/auditLog/create", log, Result.class);
        } catch (Exception e) {
            logger.error("Audit log is error:"+e.getMessage(),e);
        }
    }
}
