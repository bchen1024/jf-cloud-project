package com.btsoft.jf.cloud.platform.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.StringConstants;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;
import com.btsoft.jf.cloud.platform.security.entity.PermissionEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IPermissionMapper;
import com.btsoft.jf.cloud.platform.security.service.IPermissionService;
import com.btsoft.jf.cloud.platform.security.vo.permission.PermissionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public CommonResult<List<PermissionVO>> findPermissionTree(PermissionQueryDTO dto) {
        PermissionEntity entity=new PermissionEntity();
        BeanUtils.copyProperties(dto,entity);
        List<PermissionEntity> list=mapper.findList(entity);
        String lang= JfCloud.getCurrent().getLanguage();
        List<PermissionVO> permissionList=list.stream().map(v->{
            PermissionVO vo=new PermissionVO();
            BeanUtils.copyProperties(v,vo);
            if(LanguageEnum.EN.getKey().equals(lang)){
                vo.setTitle(vo.getPermissionDescEn()+"-"+v.getPermissionCode());
            }else{
                vo.setTitle(vo.getPermissionDescCn()+"-"+v.getPermissionCode());
            }
            return vo;
        }).collect(Collectors.toList());

        List<PermissionVO> result=permissionList.stream().filter(v->StringUtils.isEmpty(v.getParentCode()))
                .collect(Collectors.toList());
        result.forEach(v->{
            recursePermission(permissionList,v.getPermissionCode(),v);
        });
        return CommonResultUtils.success(result);
    }

    @Override
    public Result savePermission(PermissionSaveDTO dto) {
        int rows;
        PermissionEntity entity=new PermissionEntity();
        BeanUtils.copyProperties(dto,entity);
        if(dto.getPermissionId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            rows=mapper.createSingle(entity);
        }
        return CommonResultUtils.result(rows,OperationTypeEnum.Save);
    }

    private void recursePermission(List<PermissionVO> all,String parentCode,PermissionVO item){
        List<PermissionVO> children=all.stream().filter(v->parentCode.equals(v.getParentCode()))
                .collect(Collectors.toList());
        item.setChildren(children);
        children.forEach(v->{
            recursePermission(all,v.getPermissionCode(),v);
        });
    }
}
