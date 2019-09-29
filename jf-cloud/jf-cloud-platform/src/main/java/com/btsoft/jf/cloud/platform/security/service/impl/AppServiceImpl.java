package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.app.AppQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.app.AppSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import com.btsoft.jf.cloud.platform.security.entity.RoleEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IAppMapper;
import com.btsoft.jf.cloud.platform.security.service.IAppService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppVO;
import com.btsoft.jf.cloud.platform.security.vo.role.RoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 应用Service实现类
 * @author jeo_cb
 * @date 2019/9/7
 **/
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    private IAppMapper mapper;

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
}
