package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.entity.impl.BatchEntity;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserSaveDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserAppSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.AppUserEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IAppUserMapper;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.service.IAppUserService;
import com.btsoft.jf.cloud.platform.security.service.IRoleService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppUserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用用户Service接口实现
 * @author jeo_cb
 * @date 2019/11/5
 **/
@Service
public class AppUserServiceImpl implements IAppUserService {

    @Autowired
    private IAppUserMapper appUserMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAppService appService;

    @Override
    public CommonResult<PageResult<AppUserVO>> findAppUserPage(AppUserQueryDTO dto) {
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        appUserMapper.findAppUserList(dto);
        CommonResult<PageResult<AppUserVO>> result= CommonResultUtils.pageResult(AppUserVO.class,page);
        if(result.getData()!=null && !CollectionUtils.isEmpty(result.getData().getList())){
            roleService.fillRoleInfo(result.getData().getList());
            if(dto.getUserId()!=null){
                appService.fillAppInfo(result.getData().getList());
            }

        }
        return result;
    }

    @Override
    public Result deleteAppUser(BaseIdDTO dto) {
        int rows=appUserMapper.deleteSingleById(dto.getId());
        return CommonResultUtils.result(rows, OperationTypeEnum.Delete);
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
    public Result addUserApp(UserAppSaveDTO dto) {
        BatchEntity<AppUserEntity> batchEntity=new BatchEntity<>();
        int rows = 0;
        if(!CollectionUtils.isEmpty(dto.getAppIds())){
            List<AppUserEntity> list=new ArrayList<>();
            dto.getAppIds().forEach(appId->{
                AppUserEntity entity=new AppUserEntity();
                BeanUtils.copyProperties(dto,entity);
                entity.setBeginDate(dto.getDateRange().get(0));
                entity.setEndDate(dto.getDateRange().get(1));
                entity.setAppId(appId);
                list.add(entity);
            });
            batchEntity.setList(list);
            rows=appUserMapper.createMultiple(batchEntity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Create);
    }

}
