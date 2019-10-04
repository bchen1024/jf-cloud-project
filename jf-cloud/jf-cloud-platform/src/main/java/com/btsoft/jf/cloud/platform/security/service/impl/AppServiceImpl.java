package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import com.btsoft.jf.cloud.platform.security.entity.AppRoleUserEntity;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IAppMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IAppRoleUserMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IRoleMapper;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppRoleUserVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 应用Service实现类
 * @author jeo_cb
 * @date 2019/9/7
 **/
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    private IAppMapper mapper;

    @Autowired
    private IAppRoleUserMapper appUserMapper;

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public Result saveApp(AppSaveDTO dto) {
        AppEntity entity=new AppEntity();
        BeanUtils.copyProperties(dto,entity);
        int rows=0;
        if(dto.getAppId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            rows=mapper.createSingle(entity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }

    @Override
    public Result deleteApp(BaseIdAppDTO dto) {
        AppEntity entity=new AppEntity();
        entity.setAppId(dto.getId());
        int rows=mapper.deleteSingle(entity);
        //TODO 删除应用关联数据
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public CommonResult<AppVO> findApp(Long id) {
        AppEntity entity=new AppEntity();
        entity.setAppId(id);
        AppEntity appEntity=mapper.findSingle(entity);
        AppVO vo=new AppVO();
        BeanUtils.copyProperties(appEntity,vo);
        return CommonResultUtils.success(vo);
    }

    @Override
    public CommonResult<PageResult<AppVO>> findAppPage(AppQueryDTO dto) {
        AppEntity entity= EntityUtils.queryDtoToEntity(AppEntity.class,dto);
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findList(entity);
        return CommonResultUtils.pageResult(AppVO.class,page);
    }

    @Override
    public CommonResult<PageResult<AppRoleUserVO>> findAppUserPage(AppUserQueryDTO dto) {
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        appUserMapper.findAppUserList(dto);
        List<Long> roleIds=new ArrayList<>();
        CommonResult<PageResult<AppRoleUserVO>> result=CommonResultUtils.pageResult(AppRoleUserVO.class,page);
        if(result.getData()!=null && !CollectionUtils.isEmpty(result.getData().getList())){
            result.getData().getList().forEach(v->{
                roleIds.add(v.getRoleId());
            });
        }
        if(!CollectionUtils.isEmpty(roleIds)){
            List<RoleEntity> roleList=roleMapper.findListByIds(roleIds);
            Map<Long,RoleEntity> roleMap=roleList.stream().collect(Collectors.toMap(RoleEntity::getRoleId,p->p));
            result.getData().getList().forEach(v->{
                RoleEntity role=roleMap.get(v.getRoleId());
                if(role!=null){
                    v.setRoleName(role.getRoleName());
                }
            });
        }
        return result;
    }

    @Override
    public Result deleteAppUser(BaseIdDTO dto) {
        AppRoleUserEntity entity=new AppRoleUserEntity();
        entity.setId(dto.getId());
        int rows=appUserMapper.deleteSingle(entity);
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public Result addAppUser(AppUserSaveDTO dto) {
        AppRoleUserEntity entity=new AppRoleUserEntity();
        BeanUtils.copyProperties(dto,entity);
        int rows=appUserMapper.createSingle(entity);
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }
}
