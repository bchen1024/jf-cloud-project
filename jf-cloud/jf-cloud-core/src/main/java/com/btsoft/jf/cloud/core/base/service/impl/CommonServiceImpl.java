package com.btsoft.jf.cloud.core.base.service.impl;

import com.btsoft.jf.cloud.core.annotation.JOperator;
import com.btsoft.jf.cloud.core.annotation.JResource;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.service.ICommonService;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.PermissionSourceEnum;
import com.btsoft.jf.cloud.core.enums.impl.PermissionTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 通用Service实现类
 * @author jeo_cb
 * @date 2019/10/5
 **/
@Service
public class CommonServiceImpl implements ICommonService {

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
                Map<String, String> resourceMap=new HashMap<>(6);
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
                        Map<String, String> methodMap=new HashMap<>(6);
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
}
