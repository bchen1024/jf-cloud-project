package com.btsoft.jf.cloud.platform.account.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdListDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.*;
import com.btsoft.jf.cloud.platform.account.mapper.IEmployeeMapper;
import com.btsoft.jf.cloud.platform.account.mapper.IUserMapper;
import com.btsoft.jf.cloud.platform.security.dto.app.AppUserQueryDTO;
import com.btsoft.jf.cloud.platform.auth.dto.UpdatePasswordDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserSaveDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserSelectQueryDTO;
import com.btsoft.jf.cloud.platform.account.dto.user.UserStatusUpdateDTO;
import com.btsoft.jf.cloud.platform.security.entity.AppEntity;
import com.btsoft.jf.cloud.platform.security.entity.AppUserEntity;
import com.btsoft.jf.cloud.platform.account.entity.EmployeeEntity;
import com.btsoft.jf.cloud.platform.account.entity.UserEntity;
import com.btsoft.jf.cloud.platform.security.enums.GrantStatusEnum;
import com.btsoft.jf.cloud.platform.account.enums.UserTypeEnum;
import com.btsoft.jf.cloud.platform.security.mapper.*;
import com.btsoft.jf.cloud.platform.account.service.IUserService;
import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserBaseVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserEnvironmentVO;
import com.btsoft.jf.cloud.platform.account.vo.user.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Collections;
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
    private IAppUserMapper appUserMapper;
    @Autowired
    private IAppMapper appMapper;
    @Autowired
    private IEmployeeMapper employeeMapper;
    @Autowired
    private IPermissionMapper permissionMapper;
    @Autowired
    private ValueOperations<String,Object> valueOperations;

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
        CommonResult<PageResult<UserVO>> result=CommonResultUtils.pageResult(UserVO.class,page);
        if(result.getData() != null && !CollectionUtils.isEmpty(result.getData().getList())){
            result.getData().getList().forEach(v->{
                String cacheKey=RedisUtils.getkey("UserEnv",v.getUserId());
                if(valueOperations.get(cacheKey)!=null){
                    v.setUserEnvCache(true);
                }else{
                    v.setUserEnvCache(false);
                }
            });
        }
        return result;
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
        int rows;
        UserEntity entity= EntityUtils.dtoToEntity(UserEntity.class,dto);
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getMobile())){
            int count=mapper.validUserMobile(entity);
            if(count>0){
                return CommonResultUtils.fail("validator.telSame","该手机号码已注册");
            }
        }
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getEmail())){
            int count=mapper.validUserEmail(entity);
            if(count>0){
                return CommonResultUtils.fail("validator.emailSame","该邮箱地址已注册");
            }
        }
        if(entity.getUserId()!=null){
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
            entity.setPassword(DESEncryptUtils.encrypt(entity.getUserNo()));

            //创建用户
            rows=mapper.createSingle(entity);

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
        UserEntity userEntity=mapper.findSingleById(id);
        return CommonResultUtils.result(UserVO.class,userEntity);
    }

    @Override
    public CommonResult<List<UserBaseVO>> findUserBaseInfoList(BaseIdListDTO dto) {
        if(CollectionUtils.isEmpty(dto.getIdList())){
            return CommonResultUtils.success(Collections.emptyList());
        }
        List<UserEntity> userList=mapper.findListByIds(dto.getIdList());
        List<UserBaseVO> userBaseList=userList.stream().map(v->{
            UserBaseVO userBaseVO=new UserBaseVO();
            BeanUtils.copyProperties(v,userBaseVO);
            return userBaseVO;
        }).collect(Collectors.toList());
        return CommonResultUtils.success(userBaseList);
    }



    @Override
    public CommonResult<Map<Long, UserBaseVO>> findUserBaseInfoMap(BaseIdListDTO dto) {
        List<UserBaseVO> userBaseList= findUserBaseInfoList(dto).getData();
        Map<Long, UserBaseVO> userMap=userBaseList.stream().collect(Collectors.toMap(UserBaseVO::getUserId,u->u));
        return CommonResultUtils.success(userMap);
    }

    @Override
    public CommonResult<UserEnvironmentVO> findUserEnvironment() {
        Long userId= JfCloud.getCurrent().getCurrentUserId();
        String cacheKey=RedisUtils.getkey("UserEnv",userId);
        UserEnvironmentVO result=new UserEnvironmentVO();
        Object cacheValue=valueOperations.get(cacheKey);
        if(cacheValue!=null){
            BeanUtils.copyProperties(cacheValue,result);
            return CommonResultUtils.success(result);
        }
        //获取用户基本信息
        BaseIdListDTO dto=new BaseIdListDTO();
        dto.setIdList(Collections.singletonList(userId));
        List<UserBaseVO> userBaseList=findUserBaseInfoList(dto).getData();
        if(!CollectionUtils.isEmpty(userBaseList)){
            result.setUser(userBaseList.get(0));
        }

        //查询用户拥有的应用
        AppUserQueryDTO appUserQueryDTO=new AppUserQueryDTO();
        appUserQueryDTO.setUserId(userId);
        appUserQueryDTO.setGrantStatus(GrantStatusEnum.Normal.getValue());
        List<AppUserEntity> appUserEntityList=appUserMapper.findAppUserList(appUserQueryDTO);
        if(!CollectionUtils.isEmpty(appUserEntityList)){

            //根据应用id获取应用信息
            List<Long> appIds=appUserEntityList.stream().map(AppUserEntity::getAppId).collect(Collectors.toList());
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
        valueOperations.set(cacheKey,result, Duration.ofMinutes(30));
        return CommonResultUtils.success(result);
    }

    @Override
    public Result updateUserStatus(UserStatusUpdateDTO dto) {
        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(dto,userEntity);
        int rows=mapper.updateUserStatus(userEntity);
        return CommonResultUtils.result(rows,OperationTypeEnum.Update);
    }

    @Override
    public Result updatePassword(UpdatePasswordDTO dto) {
        if(!dto.getConfirmPassword().equals(dto.getNewPassword())){
            return CommonResultUtils.failResult("validator.passwordAtypism","确认密码与新密码不一致");
        }
        Long userId=JfCloud.getCurrent().getCurrentUserId();

        return CommonResultUtils.success();
    }

    @Override
    public CommonResult<List<UserBaseVO>> findSelectUserList(UserSelectQueryDTO dto) {
        List<UserEntity> userList=mapper.findSelectUserList(dto);
        return CommonResultUtils.resultList(UserBaseVO.class,userList);
    }

    @Override
    public Result clearUserEnvCache(Long userId) {
        String cacheKey=RedisUtils.getkey("UserEnv",userId);
        Boolean deleteResult=valueOperations.getOperations().delete(cacheKey);
        if(deleteResult==null){
            deleteResult=false;
        }
        return deleteResult?CommonResultUtils.success():CommonResultUtils.fail(OperationTypeEnum.Operate);
    }
}
