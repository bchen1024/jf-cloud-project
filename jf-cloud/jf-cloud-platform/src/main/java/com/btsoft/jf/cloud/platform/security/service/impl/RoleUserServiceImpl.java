package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserRoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.RoleUserEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IRoleUserMapper;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.service.IRoleUserService;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleUserVO;
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
public class RoleUserServiceImpl implements IRoleUserService {

    @Autowired
    private IRoleUserMapper roleUserMapper;
    @Autowired
    private IRoleService roleService;

    @Override
    public CommonResult<PageResult<RoleUserVO>> findRoleUserList(RoleUserQueryDTO dto) {
        Page page=PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        roleUserMapper.findRoleUserList(dto);
        CommonResult<PageResult<RoleUserVO>> result=CommonResultUtils.pageResult(RoleUserVO.class,page);
        if(result.getData()!=null && !CollectionUtils.isEmpty(result.getData().getList())){
            if(dto.getUserId()!=null){
                roleService.fillRoleInfo(result.getData().getList());
            }
        }
        return result;
    }

    @Override
    public Result addRoleUser(RoleUserSaveDTO dto) {
        BatchEntity<RoleUserEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getUserIds())){
            List<RoleUserEntity> list=new ArrayList<>();
            dto.getUserIds().forEach(userId->{
                RoleUserEntity entity=new RoleUserEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setBeginDate(dto.getDateRange().get(0));
                entity.setEndDate(dto.getDateRange().get(1));
                entity.setUserId(userId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=roleUserMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }

    @Override
    public Result deleteRoleUser(BaseIdDTO dto) {
        int rows=roleUserMapper.deleteSingleById(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public Result addUserRole(UserRoleSaveDTO dto) {
        BatchEntity<RoleUserEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getRoleIds())){
            List<RoleUserEntity> list=new ArrayList<>();
            dto.getRoleIds().forEach(roleId->{
                RoleUserEntity entity=new RoleUserEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setBeginDate(dto.getDateRange().get(0));
                entity.setEndDate(dto.getDateRange().get(1));
                entity.setRoleId(roleId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=roleUserMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }
}
