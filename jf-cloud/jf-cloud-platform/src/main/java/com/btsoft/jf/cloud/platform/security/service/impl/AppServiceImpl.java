package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppTokenSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import com.btsoft.jf.cloud.platform.security.entity.AppUserEntity;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import com.btsoft.jf.cloud.platform.security.enums.AppTypeEnum;
import com.btsoft.jf.cloud.platform.security.mapper.IAppMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IAppUserMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IRoleMapper;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.IAppVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppTokenVO;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
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
            if(Integer.valueOf(AppTypeEnum.Business.getValue().toString()).equals(entity.getAppType())) {
                //默认给应用责任人授予应用管理员角色
                RoleEntity appAdminRole = roleMapper.findRoleByCode(JfCloud.getCurrentAppCode(), "appAdmin");
                if (appAdminRole != null) {
                    AppUserEntity appUserEntity = new AppUserEntity();
                    appUserEntity.setAppId(entity.getAppId());
                    appUserEntity.setUserId(dto.getAppOwner());
                    appUserEntity.setRoleId(appAdminRole.getRoleId());
                    //默认半年有效期
                    Calendar cal = Calendar.getInstance();
                    appUserEntity.setBeginDate(cal.getTime());
                    cal.add(Calendar.MONTH, 6);
                    appUserEntity.setEndDate(cal.getTime());
                    appUserMapper.createSingle(appUserEntity);
                }
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

    @Override
    public void fillAppInfo(List<? extends IAppVO> list) {
        List<Long> ids=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(v->{
                ids.add(v.getAppId());
            });
        }
        if(!CollectionUtils.isEmpty(ids)){
            List<AppEntity> entityList=mapper.findListByIds(ids);
            Map<Long,AppEntity> entityMap=entityList.stream().collect(Collectors.toMap(AppEntity::getAppId, p->p));
            list.forEach(v->{
                AppEntity app=entityMap.get(v.getAppId());
                if(app!=null){
                    v.setAppCode(app.getAppCode());
                    v.setAppName(app.getAppName());
                    v.setAppDesc(app.getAppDesc());
                }
            });
        }
    }

    @Override
    public CommonResult<List<AppBaseVO>> findAppList(AppQueryDTO dto) {
        AppEntity entity= EntityUtils.queryDtoToEntity(AppEntity.class,dto);
        List<AppEntity> list=mapper.findList(entity);
        return CommonResultUtils.resultList(AppBaseVO.class,list);
    }
}
