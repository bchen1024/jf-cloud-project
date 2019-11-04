package com.btsoft.jf.cloud.platform.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.constant.StringConstants;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.permission.PermissionSyncDTO;
import com.btsoft.jf.cloud.platform.security.entity.PermissionEntity;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import com.btsoft.jf.cloud.platform.security.entity.RolePermissionEntity;
import com.btsoft.jf.cloud.platform.security.enums.PermissionQueryTypeEnum;
import com.btsoft.jf.cloud.platform.security.mapper.IPermissionMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IRoleMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IRolePermissionMapper;
import com.btsoft.jf.cloud.platform.security.service.IPermissionService;
import com.btsoft.jf.cloud.platform.security.vo.permission.PermissionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    @Autowired
    private IRolePermissionMapper rolePermissionMapper;
    @Autowired
    private IRoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        //获取应用权限点
        String result=restTemplate.getForObject(url.toString(), String.class);
        CommonResult<List<PermissionEntity>> cr=JSON.parseObject(result,
                new TypeReference<CommonResult<List<PermissionEntity>>>() {
                });
        if(cr!=null && cr.getSuccess()){
            if(!CollectionUtils.isEmpty(cr.getData())) {
                BatchEntity<PermissionEntity> batchEntity=new BatchEntity<>();
                batchEntity.setAppCode(dto.getAppCode());
                batchEntity.setList(cr.getData());
                //批量插入权限点
                int rows=mapper.createMultiple(batchEntity);

                //失效已去掉注解的权限
                mapper.inValidPermission(batchEntity);

                //授权该应用新增权限给超级管理员
                RoleEntity superAdminRole = roleMapper.findRoleByCode(JfCloud.getCurrentAppCode(),"superAdmin");
                if(superAdminRole!=null){
                    int count=rolePermissionMapper.grantAdminPermission(dto.getAppCode(),batchEntity.getCurrentUserId(),superAdminRole.getRoleId());
                    rows+=count;
                }
                return CommonResultUtils.result(rows,OperationTypeEnum.Sync);
            }
        }
        return CommonResultUtils.fail(OperationTypeEnum.Sync);
    }

    @Override
    public CommonResult<List<PermissionVO>> findPermissionTree(PermissionQueryDTO dto) {
        PermissionEntity entity=new PermissionEntity();
        BeanUtils.copyProperties(dto,entity);


        //配置角色权限
        List<Long> permissionIds=new ArrayList<>();
        if(PermissionQueryTypeEnum.Role.getKey().equals(dto.getQueryType())){
            entity.setEnableFlag(StringConstants.Y);
            RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
            if(dto.getRoleId()==null){
                rolePermissionEntity.setRoleId(-1L);
            }else{
                rolePermissionEntity.setRoleId(dto.getRoleId());
            }
            List<RolePermissionEntity> rolePermissionEntities=rolePermissionMapper.findList(rolePermissionEntity);
            permissionIds.addAll(rolePermissionEntities.stream().map(RolePermissionEntity::getPermissionId).collect(Collectors.toList()));
        }
        List<PermissionEntity> list=mapper.findList(entity);
        String lang= JfCloud.getCurrent().getLanguage();
        List<PermissionVO> permissionList=list.stream().map(v->{
            PermissionVO vo=new PermissionVO();
            BeanUtils.copyProperties(v,vo);
            vo.setValue(vo.getPermissionId());
            if(LanguageEnum.EN.getKey().equals(lang)){
                vo.setTitle(vo.getPermissionDescEn());
            }else{
                vo.setTitle(vo.getPermissionDescCn());
            }
            if(permissionIds.contains(vo.getPermissionId())){
                vo.setChecked(true);
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
        PermissionEntity entity= EntityUtils.dtoToEntity(PermissionEntity.class,dto);
        int rows;
        if(dto.getPermissionId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            rows=mapper.createSingle(entity);
        }
        return CommonResultUtils.result(rows,OperationTypeEnum.Save);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletePermission(BaseIdDTO dto) {
        int rows=mapper.deleteSingleById(dto.getId());
        //删除角色关系关系
        rolePermissionMapper.deleteByPermissionId(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    private void recursePermission(List<PermissionVO> all,String parentCode,PermissionVO item){
        List<PermissionVO> children=all.stream().filter(v->parentCode.equals(v.getParentCode()))
                .peek(v->{
                    if(v.getParentIds()==null){
                        v.setParentIds(new ArrayList<>());
                    }
                    if(item.getParentIds()!=null){
                        v.getParentIds().addAll(item.getParentIds());
                    }
                    v.getParentIds().add(parentCode);
                }).collect(Collectors.toList());
        item.setChildren(children);
        children.forEach(v->{
            recursePermission(all,v.getPermissionCode(),v);
        });
    }
}
