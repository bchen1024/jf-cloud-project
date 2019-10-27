package com.btsoft.jf.cloud.platform.security.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.ValidateCodeDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.base.service.IValidateCodeService;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.ValidateCodeTypeEnum;
import com.btsoft.jf.cloud.core.exception.CommonException;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.DESEncryptUtils;
import com.btsoft.jf.cloud.platform.security.dto.auth.AccountLoginDTO;
import com.btsoft.jf.cloud.platform.security.entity.UserEntity;
import com.btsoft.jf.cloud.platform.security.enums.UserStatusEnum;
import com.btsoft.jf.cloud.platform.security.mapper.IUserMapper;
import com.btsoft.jf.cloud.platform.security.service.IAuthService;
import com.btsoft.jf.cloud.platform.security.vo.auth.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Calendar;

/**
 * 鉴权服务
 * @author jeo_cb
 * @date 2019/10/25 12:24
 **/
@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IValidateCodeService validateCodeService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /** 密码最大错误次数*/
    @Value("${jf.platform.auth.maxPasswordErrorNum:5}")
    private Integer maxPasswordErrorNum;

    /** 用户锁定时长，单位毫秒，默认是5分钟*/
    @Value("${jf.platform.auth.lockDuration:300000}")
    private Integer lockDuration;

    @Override
    public void generateValidateCode(ValidateCodeDTO dto, HttpServletResponse resp) {
        String code=validateCodeService.writeValidateCodeImage(resp,dto);
        if(StringUtils.isEmpty(dto.getKey())){
            dto.setKey(JfCloud.getCurrent().getCurrentUserId().toString());
        }
        String key= dto.getType()+"_"+dto.getKey();
        stringRedisTemplate.opsForValue().set(key,code, Duration.ofMinutes(3));
    }

    @Override
    public CommonResult<LoginVO> accountLogin(AccountLoginDTO dto) {
        String cacheKey= ValidateCodeTypeEnum.LoginValid.getKey()+"_"+dto.getUserAccount();
        String validateCode=stringRedisTemplate.opsForValue().get(cacheKey);
        if(!dto.getValidateCode().equals(validateCode)){
            throw new CommonException("validator.validateCodeError","验证码不正确");
        }
        //根据账号获取用户信息
        UserEntity userEntity=userMapper.findValidUser(dto.getUserAccount());

        //用户不存在或者被删除
        if(userEntity==null || UserStatusEnum.D.getKey().equals(userEntity.getUserStatus())){
            throw new CommonException("validator.userNotExists","该账号不存在");
        }
        UserEntity entity=new UserEntity();
        BeanUtils.copyProperties(userEntity,entity);
        //用户被锁定
        if(UserStatusEnum.L.getKey().equals(userEntity.getUserStatus())){
            long curTime=System.currentTimeMillis();
            long lockTime=userEntity.getLockTime().getTime();
            if(curTime-lockTime<=lockDuration && lockDuration>0){
                throw new CommonException("validator.userInvalid","该账号已被锁定，请稍后再试");
            }else{//超过锁定时长，解锁
                entity.setPasswordErrorNum(0);
                entity.setUserStatus(UserStatusEnum.Y.getKey());
                entity.setLockTime(null);
                userMapper.updateErrorNumAndStatus(entity);
            }
        }
        //密码错误
        String password= DESEncryptUtils.encrypt(dto.getPassword());
        if(!password.equals(userEntity.getPassword())){
            //更新密码错误次数
            if(entity.getPasswordErrorNum()==null){
                entity.setPasswordErrorNum(0);
            }
            entity.setPasswordErrorNum(entity.getPasswordErrorNum()+1);
            //超过密码最大错误次数，锁定用户
            if(entity.getPasswordErrorNum()>=maxPasswordErrorNum){
                entity.setUserStatus(UserStatusEnum.L.getKey());
                entity.setLockTime(Calendar.getInstance().getTime());
            }
            userMapper.updateErrorNumAndStatus(entity);
            throw new CommonException("validator.passwordError","登录密码不正确");
        }else{
            //解锁
            entity.setPasswordErrorNum(0);
            entity.setUserStatus(UserStatusEnum.Y.getKey());
            entity.setLockTime(null);
            userMapper.updateErrorNumAndStatus(entity);
        }
        String token=DESEncryptUtils.encrypt(userEntity.getUserId().toString());
        LoginVO loginVO=new LoginVO();
        loginVO.setToken(token);

        String userKey="UserEnv_"+userEntity.getUserId();
        redisTemplate.delete(cacheKey);
        return CommonResultUtils.success(loginVO);
    }

    @Override
    public Result logout() {
        Long userId=JfCloud.getCurrent().getCurrentUserId();
        String cacheKey="UserEnv_"+userId;
        redisTemplate.delete(cacheKey);
        return CommonResultUtils.success();
    }
}
