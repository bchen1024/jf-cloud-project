package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.app.*;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import com.btsoft.jf.cloud.platform.security.entity.AppUserEntity;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IAppMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IAppUserMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IRoleMapper;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppTokenVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppUserVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
    private IAppUserMapper appUserMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveApp(AppSaveDTO dto) {
        AppEntity entity=EntityUtils.dtoToEntity(AppEntity.class,dto);
        int rows;
        if(dto.getAppId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            entity.setAppToken(UUID.randomUUID().toString());
            rows=mapper.createSingle(entity);
        }
        if(rows>0){
            //默认给应用责任人授予应用管理员角色
            RoleEntity appAdminRole = roleMapper.findRoleByCode(JfCloud.getCurrentAppCode(),"appAdmin");
            if(appAdminRole!=null){
                AppUserEntity appUserEntity=new AppUserEntity();
                appUserEntity.setAppId(entity.getAppId());
                appUserEntity.setUserId(dto.getAppOwner());
                appUserEntity.setRoleId(appAdminRole.getRoleId());
                //默认半年有效期
                Calendar cal=Calendar.getInstance();
                appUserEntity.setBeginDate(cal.getTime());
                cal.add(Calendar.MONTH,6);
                appUserEntity.setEndDate(cal.getTime());
                appUserMapper.createSingle(appUserEntity);
            }

        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteApp(BaseIdDTO dto) {
        int rows=mapper.deleteSingleById(dto.getId());
        //TODO 删除应用关联数据
        //删除应用用户角色关系
        appUserMapper.deleteByAppId(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public CommonResult<AppVO> findApp(Long id) {
        AppEntity appEntity=mapper.findSingleById(id);
        return CommonResultUtils.result(AppVO.class,appEntity);
    }

    @Override
    public CommonResult<PageResult<AppVO>> findAppPage(AppQueryDTO dto) {
        AppEntity entity= EntityUtils.queryDtoToEntity(AppEntity.class,dto);
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findList(entity);
        return CommonResultUtils.pageResult(AppVO.class,page);
    }

    @Override
    public CommonResult<PageResult<AppUserVO>> findAppUserPage(AppUserQueryDTO dto) {
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        appUserMapper.findAppUserList(dto);
        CommonResult<PageResult<AppUserVO>> result=CommonResultUtils.pageResult(AppUserVO.class,page);
        if(result.getData()!=null && !CollectionUtils.isEmpty(result.getData().getList())){
            roleService.fillRoleInfo(result.getData().getList());
        }
        return result;
    }

    @Override
    public Result deleteAppUser(BaseIdDTO dto) {
        int rows=appUserMapper.deleteSingleById(dto.getId());
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public Result addAppUser(AppUserSaveDTO dto) {
        BatchEntity<AppUserEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getUserIds())){
            List<AppUserEntity> list=new ArrayList<>();
            dto.getUserIds().forEach(userId->{
                AppUserEntity entity=new AppUserEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setBeginDate(dto.getDateRange().get(0));
                entity.setEndDate(dto.getDateRange().get(1));
                entity.setUserId(userId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=appUserMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }

    @Override
    public CommonResult<AppTokenVO> findAppToken(Long id) {
        AppEntity appEntity=mapper.findSingleById(id);
        return CommonResultUtils.result(AppTokenVO.class,appEntity);
    }

    @Override
    public Result saveAppToken(AppTokenSaveDTO dto) {
        AppEntity entity=EntityUtils.dtoToEntity(AppEntity.class,dto);
        int rows=mapper.updateAppToken(entity);
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }
}
