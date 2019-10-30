package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdAppDTO;
import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupUserSaveDTO;
import com.btsoft.jf.cloud.platform.security.entity.GroupEntity;
import com.btsoft.jf.cloud.platform.security.entity.GroupUserEntity;
import com.btsoft.jf.cloud.platform.security.mapper.IGroupMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IGroupUserMapper;
import com.btsoft.jf.cloud.platform.security.service.IGroupService;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupUserVO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 群组管理Service实现类
 * @author jeo_cb
 * @date 2019/8/28
 **/
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupMapper mapper;
    @Autowired
    private IGroupUserMapper groupUserMapper;

    /**
     * 分页查询群组列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 群组分页列表
     **/
    @Override
    public CommonResult<PageResult<GroupVO>> findGroupPage(GroupQueryDTO dto) {
        GroupEntity entity= EntityUtils.queryDtoToEntity(GroupEntity.class,dto);
        Page page=PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findList(entity);
        return CommonResultUtils.pageResult(GroupVO.class,page);
    }

    /**
     * 根据群组id获取单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 群组id
     * @return 群组信息
     **/
    @Override
    public CommonResult<GroupVO> findGroup(Long id) {
        GroupEntity entity=new GroupEntity();
        entity.setGroupId(id);
        GroupEntity groupEntity=mapper.findSingle(entity);
        return CommonResultUtils.result(GroupVO.class,groupEntity);
    }

    /**
     * 群组保存，存在群组id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 群组保存参数
     * @return 保存结果
     **/
    @Override
    public Result saveGroup(GroupSaveDTO dto) {
        GroupEntity entity=EntityUtils.dtoToEntity(GroupEntity.class,dto);
        int rows;
        if(dto.getGroupId()!=null){
            rows=mapper.updateSingle(entity);
        }else{
            rows=mapper.createSingle(entity);
        }
        return CommonResultUtils.result(rows,OperationTypeEnum.Save);
    }

    /**
     * 删除单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteGroup(BaseIdAppDTO dto) {
        GroupEntity entity=new GroupEntity();
        entity.setGroupId(dto.getId());
        entity.setAppCode(dto.getAppCode());
        int rows=mapper.deleteSingle(entity);
        //TODO 删除群组关联数据
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    @Override
    public CommonResult<PageResult<GroupUserVO>> findGroupUserList(GroupUserQueryDTO dto) {
        Page page=PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        groupUserMapper.findGroupUserList(dto);
        return CommonResultUtils.pageResult(GroupUserVO.class,page);
    }

    @Override
    public Result addGroupUser(GroupUserSaveDTO dto) {
        GroupUserEntity entity=new GroupUserEntity();
        BeanUtils.copyProperties(dto,entity);
        int rows=groupUserMapper.createSingle(entity);
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }

    @Override
    public Result deleteGroupUser(BaseIdDTO dto) {
        GroupUserEntity entity=new GroupUserEntity();
        entity.setId(dto.getId());
        int rows=groupUserMapper.deleteSingle(entity);
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }


}
