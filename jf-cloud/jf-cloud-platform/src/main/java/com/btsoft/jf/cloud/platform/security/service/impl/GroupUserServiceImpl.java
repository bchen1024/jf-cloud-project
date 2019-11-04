package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserGroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.GroupUserEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IGroupRoleMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IGroupUserMapper;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.service.IGroupUserService;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 群组用户Service实现类
 * @author jeo_cb
 * @date 2019/11/4 16:30
 **/
@Service
public class GroupUserServiceImpl implements IGroupUserService {

    @Autowired
    private IGroupRoleMapper groupRoleMapper;
    @Autowired
    private IGroupUserMapper groupUserMapper;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IGroupService groupService;

    @Override
    public CommonResult<PageResult<GroupUserVO>> findGroupUserList(GroupUserQueryDTO dto) {
        Page page=PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        groupUserMapper.findGroupUserList(dto);
        CommonResult<PageResult<GroupUserVO>> result=CommonResultUtils.pageResult(GroupUserVO.class,page);
        if(result.getData()!=null && !CollectionUtils.isEmpty(result.getData().getList())){
            if(dto.getUserId()!=null){
                groupService.fillGroupInfo(result.getData().getList());
            }
        }
        return result;
    }

    @Override
    public Result addGroupUser(GroupUserSaveDTO dto) {
        BatchEntity<GroupUserEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getUserIds())){
            List<GroupUserEntity> list=new ArrayList<>();
            dto.getUserIds().forEach(userId->{
                GroupUserEntity entity=new GroupUserEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setBeginDate(dto.getDateRange().get(0));
                entity.setEndDate(dto.getDateRange().get(1));
                entity.setUserId(userId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=groupUserMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }

    @Override
    public Result deleteGroupUser(BaseIdDTO dto) {
        int rows=groupUserMapper.deleteSingleById(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public Result addUserGroup(UserGroupSaveDTO dto) {
        BatchEntity<GroupUserEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getGroupIds())){
            List<GroupUserEntity> list=new ArrayList<>();
            dto.getGroupIds().forEach(groupId->{
                GroupUserEntity entity=new GroupUserEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setBeginDate(dto.getDateRange().get(0));
                entity.setEndDate(dto.getDateRange().get(1));
                entity.setGroupId(groupId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=groupUserMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }
}
