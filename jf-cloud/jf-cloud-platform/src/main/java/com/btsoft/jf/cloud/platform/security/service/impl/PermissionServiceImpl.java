package com.btsoft.jf.cloud.platform.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.StringConstants;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;
import com.btsoft.jf.cloud.platform.security.entity.PermissionEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IPermissionMapper;
import com.btsoft.jf.cloud.platform.security.service.IPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 权限Service实现类
 * @author jeo_cb
 * @date 2019/10/6
 **/
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionMapper mapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Result syncPermission(PermissionSyncDTO dto) {
        StringBuilder url=new StringBuilder("http://");
        url.append(dto.getAppCode());
        if(StringUtils.isNotEmpty(dto.getContextPath())){
            url.append(dto.getContextPath());
        }
        if(!url.toString().endsWith(StringConstants.SLASH)){
            url.append(StringConstants.SLASH);
        }
        url.append("common/permission/list");
        String result=restTemplate.getForObject(url.toString(), String.class);
        CommonResult<List<PermissionEntity>> cr=JSON.parseObject(result,
                new TypeReference<CommonResult<List<PermissionEntity>>>() {
                });
        if(!CollectionUtils.isEmpty(cr.getData())) {
            BatchEntity<PermissionEntity> batchEntity=new BatchEntity<>();
            batchEntity.setList(cr.getData());
            return CommonResultUtils.success(mapper.createMultiple(batchEntity));
        }
        return CommonResultUtils.success(OperationTypeEnum.Sync);
    }
}
