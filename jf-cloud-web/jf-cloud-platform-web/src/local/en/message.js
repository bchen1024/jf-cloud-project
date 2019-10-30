export default{
    validator:{
        //通用验证
        notEmpty:'该值不能为空',
        email:'请输入正确的邮箱地址',
        integer:'请输入有效的整数',
        tel:'请输入正确的手机号码',

        //注册验证
        telSame:'该手机号码已注册',
        emailSame:'该邮箱地址已注册',

        //登录验证
        accountNotNull:'账号不能为空',
        passwordNotNull:'密码不能为空',
        validateCodeNotNull:'验证码不能为空',
        validateCodeError:'验证码不正确',
        userNotExists:'该账号不存在',
        userInvalid:'该账号已被锁定，请稍后再试',
        passwordError:'登录密码错误'
    },
    error:{
        common:'请求异常，请联系管理员'
    }
}