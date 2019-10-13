package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.*;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserSaveDTO;
import com.btsoft.jf.cloud.platform.security.dto.user.UserStatusUpdateDTO;
import com.btsoft.jf.cloud.platform.security.entity.*;
import com.btsoft.jf.cloud.platform.security.enums.UserTypeEnum;
import com.btsoft.jf.cloud.platform.security.mapper.*;
import com.btsoft.jf.cloud.platform.security.service.IUserService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserEnvironmentVO;
import com.btsoft.jf.cloud.platform.security.vo.user.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
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
    @Autowired
    private IAppUserMapper appUserMapper;
    @Autowired
    private IAppMapper appMapper;
    @Autowired
    private IEmployeeMapper employeeMapper;
    @Autowired
    private IPermissionMapper permissionMapper;

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
    public Result saveUser(UserSaveDTO dto) {
        int rows=0;
        UserEntity entity= EntityUtils.dtoToEntity(UserEntity.class,dto);
        if(dto.getUserId()!=null){
            rows=mapper.updateUserBaseInfo(entity);
        }else{
            //校验userNo是否重复
            int userCount=mapper.findUserCountByUserCn(entity.getUserCn());
            //根据中文名称生成userNo
            String userNo= PinyinUtils.toPinyin(entity.getUserCn());
            if(userCount==0){
                entity.setUserNo(userNo);
            }else{
                entity.setUserNo(userNo + StringUtils.zeroFill(userCount));
            }
            //生成密码
            entity.setPassword(DESEncrypt.encrypt(entity.getUserNo()));

            //创建用户
            rows=mapper.createSingle(entity);

            //创建用户详情
            UserDetailEntity detailEntity=new UserDetailEntity();
            BeanUtils.copyProperties(dto,detailEntity);
            detailEntity.setUserId(entity.getUserId());
            detailMapper.createSingle(detailEntity);

            //创建员工信息
            if(UserTypeEnum.Employee.getValue().toString().equals(entity.getUserType())){
                EmployeeEntity employeeEntity=new EmployeeEntity();
                BeanUtils.copyProperties(dto,employeeEntity);
                employeeEntity.setUserId(entity.getUserId());
                employeeMapper.createSingle(employeeEntity);
            }
        }
        return CommonResultUtils.result(rows,OperationTypeEnum.Save);
    }

    @Override
    public CommonResult<UserVO> findUser(Long id) {
        UserEntity entity=new UserEntity();
        entity.setUserId(id);
        UserEntity userEntity=mapper.findSingle(entity);
        UserVO vo=new UserVO();
        BeanUtils.copyProperties(userEntity,vo);
        return CommonResultUtils.success(vo);
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

    @Override
    public CommonResult<UserEnvironmentVO> findUserEnvironment() {
        UserEnvironmentVO result=new UserEnvironmentVO();
        Long userId= JfCloud.getCurrent().getCurrentUserId();

        //获取用户基本信息
        List<UserBaseVO> userBaseList=mapper.findUserListByIds(Arrays.asList(userId));
        if(!CollectionUtils.isEmpty(userBaseList)){
            result.setUser(userBaseList.get(0));
        }

        //查询用户拥有的应用
        AppUserQueryDTO appUserEntity=new AppUserQueryDTO();
        appUserEntity.setUserId(userId);
        List<AppUserEntity> appUserEntityList=appUserMapper.findAppUserList(appUserEntity);
        if(!CollectionUtils.isEmpty(appUserEntityList)){

            //根据应用id获取应用信息
            List<Long> appIds=appUserEntityList.stream().map(v->v.getAppId()).collect(Collectors.toList());
            List<AppEntity> appList=appMapper.findListByIds(appIds);
            if(!CollectionUtils.isEmpty(appList)){

                //设置拥有拥有的应用列表
                List<AppBaseVO> appBaseList=EntityUtils.entityToList(AppBaseVO.class,appList);
                result.setAppList(appBaseList);

                //设置用户当前应用
                String appCode=JfCloud.getCurrent().getAppCode();
                List<AppBaseVO> currentAppList=appBaseList.stream().filter(v->v.getAppCode().equals(appCode))
                        .collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(currentAppList)){
                    result.setAppInfo(currentAppList.get(0));
                }else{
                    result.setAppInfo(appBaseList.get(0));
                }

                //筛选出当前应用拥有的角色id
                List<Long> roleIds=appUserEntityList.stream()
                        .filter(v->v.getAppId().equals(result.getAppInfo().getAppId()))
                        .map(AppUserEntity::getRoleId).collect(Collectors.toList());
                result.setPermissionList(permissionMapper.findPermissionCodeList(roleIds));
            }
        }

        return CommonResultUtils.success(result);
    }

    @Override
    public Result updateUserStatus(UserStatusUpdateDTO dto) {
        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(dto,userEntity);
        int rows=mapper.updateUserStatus(userEntity);
        return CommonResultUtils.result(rows,OperationTypeEnum.Update);
    }
}
