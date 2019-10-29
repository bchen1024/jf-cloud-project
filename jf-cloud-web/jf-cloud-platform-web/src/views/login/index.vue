<template>
    <div class="jf-login">
        <div class="jf-login-con">
            <Tabs>
                <TabPane :label="$t('passwordLogin')" name="passwordLogin">
                    <Form ref="formInline" :model="formInline" :rules="ruleInline">
                        <FormItem prop="userAccount">
                            <Input type="text" v-model="formInline.userAccount" :placeholder="$t('accontOrMobileOrEmail')">
                                <Icon type="ios-person-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="formInline.password" :placeholder="$t('password')">
                                <Icon type="ios-lock-outline" slot="prepend"></Icon>
                            </Input>
                        </FormItem>
                        <FormItem v-if="formInline.userAccount && formInline.password" prop="validateCode">
                            <Row>
                                <Col span="14">
                                    <Input type="text" v-model="formInline.validateCode" :placeholder="$t('validateCode')" @on-focus="refreshCode()">
                                        <Icon type="ios-lock-outline" slot="prepend"></Icon>
                                    </Input>
                                </Col>
                                <Col span="10">
                                    <img  v-if="imgSrc && formInline.userAccount" :src="imgSrc" @click="refreshCode(true)" style="margin-left:8px;cursor: pointer;" :title="$t('validCodeTip')"/>
                                </Col>
                            </Row>
                        </FormItem>
                        <div class='login-forget-panel'>
                            <a class='login-forget-pwd' href='javascript:void(0)'>{{$t('forgetPassword')}}</a>
                        </div>
                        <FormItem class='login-item'>
                            <Button long :loading='loginLoading' type="primary" @click="handleSubmit()">{{$t('login')}}</Button>
                        </FormItem>
                    </Form>
                </TabPane>
                <TabPane :label="$t('mobileLogin')" name="mobileLogin">
                </TabPane>
            </Tabs>
        </div>
    </div>
</template>
<script>
    import util from '@/libs/util';
    export default {
        data(){
            let vue=this;
            return {
                loginLoading:false,
                imgSrc:null,
                validateKey:null,
                formInline: {
                   
                },
                ruleInline: {
                    userAccount: [
                        { required: true, message: vue.$t('validator.accountNotNull')}
                    ],
                    password: [
                        { required: true, message: vue.$t('validator.passwordNotNull')}
                    ],
                    validateCode: [
                        { required: true, message: vue.$t('validator.validateCodeNotNull')}
                    ]
                }
            }
        },
        mounted() {
            let vm=this;
            //三分钟刷新一次验证码
            vm.timer = setInterval(function(){
                vm.refreshCode(true);
            }, 3*60*1000);
        },
        beforeDestroy() {
            clearInterval(this.timer);
        },
        methods:{
            handleSubmit() {
                let vm=this;
                vm.$refs['formInline'].validate((valid) => {
                    if (valid) {
                        var data={
                            userAccount:vm.formInline.userAccount,
                            password:vm.formInline.password,
                            validateCode:vm.formInline.validateCode
                        };
                        vm.loginLoading=true;
                        vm.$http.post('jfcloud/jf-cloud-platform/auth/account/login',data).then(result=>{
                            util.setToken(result.data.token);
                            //跳转到指定页面或者首页
                            let routerName=vm.$route.query.routerName
                            if(routerName){
                                vm.$router.replace({name:routerName});
                            }else{
                                vm.$router.replace({name:'home'});
                            }
                        }).catch(error => {
                            if(error.code=='validator.validateCodeError'){
                                vm.refreshCode(true);
                            }
                        }).then(()=>{
                            vm.loginLoading=false;
                        });
                    }
                })
            },
            refreshCode(refresh){
                let vm=this;
                let userAccount=vm.formInline.userAccount;
                if(userAccount){
                    if(userAccount!=vm.validateKey || refresh){
                        vm.validateKey=userAccount;
                        vm.imgSrc='jfcloud/jf-cloud-platform/auth/validateCode?type=LoginValid&key='+userAccount+'&random='+new Date().getTime();
                    }
                }else{
                    vm.imgSrc=null;
                }
            }
        }
        
    };
</script>
<style lang="less">
    .jf-login{
        width: 100%;
        height: 100%;
        background-image: url('../../assets/images/login-bg.jpg');
        background-size: cover;
        background-position: center;
        position: relative;
        &-con{
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%,-50%);
            width: 400px;
            
            background-color: #ffffff;
            padding: 8px 16px;
            &-header{
                font-size: 16px;
                font-weight: 300;
                text-align: center;
                padding: 30px 0;
            }
            .form-con{
                padding: 10px 0 0;
            }
            .login-tip{
                font-size: 10px;
                text-align: center;
                color: #c3c3c3;
            }
        }
    }
    .login-error-panel{
        white-space: normal;
        color:red;
        margin-bottom: 8px;
        word-break: break-all;
    }
    .login-forget-panel{
        height:26px;
        line-height: 6px;
    }
    .login-forget-pwd{
        float:right;
    }
    .login-item{
        margin-bottom: 6px;
    }
</style>