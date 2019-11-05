package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.role.RolePermissionSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.role.RoleSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import com.btsoft.jf.cloud.platform.security.entity.RolePermissionEntity;
import com.btsoft.jf.cloud.platform.security.mapper.*;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.IRoleVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色管理Service实现类
 * @author jeo_cb
 * @date 2019/8/28
 **/
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleMapper mapper;
    @Autowired
    private IRolePermissionMapper rolePermissionMapper;
    @Autowired
    private IAppUserMapper appUserMapper;
    @Autowired
    private IGroupRoleMapper groupRoleMapper;
    @Autowired
    private IRoleUserMapper roleUserMapper;
    /**
     * 角色保存，存在角色id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 角色包存参数
     * @return 保存结果
     **/
    @Override
    public Result saveRole(RoleSaveDTO dto) {
        RoleEntity entity=EntityUtils.dtoToEntity(RoleEntity.class,dto);
        int rows;
        if(dto.getRoleId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            rows=mapper.createSingle(entity);
        }
        return CommonResultUtils.result(rows,OperationTypeEnum.Save);
    }

    /**
     * 删除单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteRole(BaseIdDTO dto) {
        int rows=mapper.deleteSingleById(dto.getId());
        //删除应用用户角色关系表
        appUserMapper.deleteByRoleId(dto.getId());
        //删除群组角色关系表
        groupRoleMapper.deleteByRoleId(dto.getId());
        //删除角色权限关系
        rolePermissionMapper.deleteByRoleId(dto.getId());
        //删除角色用户关系
        roleUserMapper.deleteByRoleId(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    /**
     * 根据角色id获取单个角色
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 角色
     * @return 角色信息
     **/
    @Override
    public CommonResult<RoleVO> findRole(Long id) {
        RoleEntity roleEntity=mapper.findSingleById(id);
        return CommonResultUtils.result(RoleVO.class,roleEntity);
    }

    /**
     * 分页查询角色列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 角色分页列表
     **/
    @Override
    public CommonResult<PageResult<RoleVO>> findRolePage(RoleQueryDTO dto) {
        RoleEntity entity= EntityUtils.queryDtoToEntity(RoleEntity.class,dto);
        Page page=PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findList(entity);
        return CommonResultUtils.pageResult(RoleVO.class,page);
    }

    @Override
    public CommonResult<List<RoleBaseVO>> findRoleList(RoleQueryDTO dto) {
        RoleEntity entity= EntityUtils.queryDtoToEntity(RoleEntity.class,dto);
        List<RoleEntity> roleList=mapper.findList(entity);
        return CommonResultUtils.success(EntityUtils.entityToList(RoleBaseVO.class,roleList));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveRolePermission(RolePermissionSaveDTO dto) {
        int rows=rolePermissionMapper.deleteRolePermission(dto);
        if(!CollectionUtils.isEmpty(dto.getPermissionIds())){
            List<RolePermissionEntity> list=new ArrayList<>();
            dto.getPermissionIds().forEach(v->{
                RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
                rolePermissionEntity.setRoleId(dto.getRoleId());
                rolePermissionEntity.setPermissionId(v);
                list.add(rolePermissionEntity);
            });
            BatchEntity<RolePermissionEntity> batchEntity=new BatchEntity<>();
            batchEntity.setList(list);
            int count=rolePermissionMapper.createMultiple(batchEntity);
            rows+=count;
        }
        return CommonResultUtils.result(rows,OperationTypeEnum.Save);
    }

    @Override
    public void fillRoleInfo(List<? extends IRoleVO> list) {
        List<Long> roleIds=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(v->{
                roleIds.add(v.getRoleId());
            });
        }
        if(!CollectionUtils.isEmpty(roleIds)){
            List<RoleEntity> roleList=mapper.findListByIds(roleIds);
            Map<Long,RoleEntity> roleMap=roleList.stream().collect(Collectors.toMap(RoleEntity::getRoleId, p->p));
            list.forEach(v->{
                RoleEntity role=roleMap.get(v.getRoleId());
                if(role!=null){
                    v.setRoleName(role.getRoleName());
                    v.setRoleDesc(role.getRoleDesc());
                    v.setRoleCode(role.getRoleCode());
                }
            });
        }
    }
}
