package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.*;
import com.btsoft.jf.cloud.platform.security.dto.user.UserCreateDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.security.entity.UserDetailEntity;
import com.btsoft.jf.cloud.platform.security.entity.UserEntity;
import com.btsoft.jf.cloud.platform.security.enums.UserTypeEnum;
import com.btsoft.jf.cloud.platform.security.mapper.IUserDetailMapper;
import com.btsoft.jf.cloud.platform.security.mapper.IUserMapper;
import com.btsoft.jf.cloud.platform.security.service.IUserService;
import com.btsoft.jf.cloud.platform.security.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账号Service实现类
 * @author jeo_cb
 * @date 2019/9/28
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper mapper;
    @Autowired
    private IUserDetailMapper detailMapper;

    /**
     * 账号分页查询
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 查询参数
     * @return 账号分页结果
     **/
    @Override
    public CommonResult<PageResult<UserVO>> findUserPage(UserQueryDTO dto) {
        UserEntity entity= EntityUtils.queryDtoToEntity(UserEntity.class,dto);
        Page page= PageHelper.startPage(dto.getCurPage(),dto.getPageSize(),true);
        mapper.findList(entity);
        return CommonResultUtils.pageResult(UserVO.class,page);
    }

    /**
     * 创建用户
     * @author jeo_cb
     * @date 2019/9/28
     * @param  dto 创建参数
     * @return 创建结果
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result createUser(UserCreateDTO dto) {
        UserEntity entity= EntityUtils.baseDtoToEntity(UserEntity.class,dto);
        //校验userNo是否重复
        int userCount=mapper.findUserCountByUserCn(entity.getUserCn());
        //根据中文名称生成userNo
        String userNo=PinyinUtils.toPinyin(entity.getUserCn());
        if(userCount==0){
            entity.setUserNo(userNo);
        }else{
            entity.setUserNo(userNo + StringUtils.zeroFill(userCount));
        }
        //生成密码
        entity.setPassword(DESEncrypt.encrypt(entity.getUserNo()));

        //创建用户
        mapper.createSingle(entity);

        //创建用户详情
        UserDetailEntity detailEntity=new UserDetailEntity();
        BeanUtils.copyProperties(dto,detailEntity);
        detailEntity.setUserId(entity.getUserId());
        detailMapper.createSingle(detailEntity);

        if(UserTypeEnum.Employee.getValue().toString().equals(entity.getUserType())){
            System.out.println("创建员工信息:"+entity.getUserId());
        }
        return CommonResultUtils.success(OperationTypeEnum.Create);
    }

    @Override
    public CommonResult<List<UserBaseVO>> findUserBaseInfoList(BaseIdListDTO dto) {
        List<UserBaseVO> userBaseList=mapper.findUserListByIds(dto.getIdList());
        return CommonResultUtils.success(userBaseList);
    }

    @Override
    public CommonResult<Map<Long, UserBaseVO>> findUserBaseInfoMap(BaseIdListDTO dto) {
        List<UserBaseVO> userBaseList=mapper.findUserListByIds(dto.getIdList());
        Map<Long, UserBaseVO> userMap=userBaseList.stream().collect(Collectors.toMap(UserBaseVO::getUserId,u->u));
        return CommonResultUtils.success(userMap);
    }
}
