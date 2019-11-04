package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupRoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleGroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.GroupRoleEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IGroupRoleMapper;
import com.btsoft.jf.cloud.platform.security.service.IGroupRoleService;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupRoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 群组角色Service实现类
 * @author jeo_cb
 * @date 2019/11/4 16:30
 **/
@Service
public class GroupRoleServiceImpl implements IGroupRoleService {

    @Autowired
    private IGroupRoleMapper groupRoleMapper;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IGroupService groupService;

    @Override
    public CommonResult<PageResult<GroupRoleVO>> findGroupRoleList(GroupRoleQueryDTO dto) {
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        groupRoleMapper.findGroupRoleList(dto);
        CommonResult<PageResult<GroupRoleVO>> result=CommonResultUtils.pageResult(GroupRoleVO.class,page);
        if(result.getData()!=null && !CollectionUtils.isEmpty(result.getData().getList())){
            if(dto.getGroupId()!=null){
                roleService.fillRoleInfo(result.getData().getList());
            }
            if(dto.getRoleId()!=null){
                groupService.fillGroupInfo(result.getData().getList());
            }
        }
        return result;
    }

    @Override
    public Result addGroupRole(GroupRoleSaveDTO dto) {
        BatchEntity<GroupRoleEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getRoleIds())){
            List<GroupRoleEntity> list=new ArrayList<>();
            dto.getRoleIds().forEach(roleId->{
                GroupRoleEntity entity=new GroupRoleEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setRoleId(roleId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=groupRoleMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }

    @Override
    public Result deleteGroupRole(BaseIdDTO dto) {
        int rows=groupRoleMapper.deleteSingleById(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public Result addRoleGroup(RoleGroupSaveDTO dto) {
        BatchEntity<GroupRoleEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getGroupIds())){
            List<GroupRoleEntity> list=new ArrayList<>();
            dto.getGroupIds().forEach(groupId->{
                GroupRoleEntity entity=new GroupRoleEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setGroupId(groupId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=groupRoleMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }
}
