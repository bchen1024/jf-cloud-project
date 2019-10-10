package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
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
import com.btsoft.jf.cloud.platform.security.mapper.IRoleMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IRolePermissionMapper;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 角色保存，存在角色id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 角色包存参数
     * @return 保存结果
     **/
    @Override
    public Result saveRole(RoleSaveDTO dto) {
        RoleEntity entity=new RoleEntity();
        BeanUtils.copyProperties(dto,entity);
        int rows=0;
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
    public Result deleteRole(BaseIdAppDTO dto) {
        RoleEntity entity=new RoleEntity();
        entity.setRoleId(dto.getId());
        entity.setAppCode(dto.getAppCode());
        int rows=mapper.deleteSingle(entity);
        //TODO 删除角色关联数据
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
        RoleEntity entity=new RoleEntity();
        entity.setRoleId(id);
        RoleEntity roleEntity=mapper.findSingle(entity);
        RoleVO roleVO=new RoleVO();
        BeanUtils.copyProperties(roleEntity,roleVO);
        return CommonResultUtils.success(roleVO);
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
        rolePermissionMapper.deleteRolePermission(dto);
        if(!CollectionUtils.isEmpty(dto.getPermissionIds())){
            List<RolePermissionEntity> list=new ArrayList<>();
            dto.getPermissionIds().forEach(v->{
                RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
                rolePermissionEntity.setRoleId(dto.getRoleId());
                rolePermissionEntity.setPermissionId(v);
                list.add(rolePermissionEntity);
            });
            BatchEntity batchEntity=new BatchEntity();
            batchEntity.setList(list);
            rolePermissionMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.success(OperationTypeEnum.Save);
    }
}
