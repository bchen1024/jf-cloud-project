<template>
    <div class="jf-login">
        <div class="jf-login-con">
            <Card class='login-card' icon="log-in" title="欢迎登录" :bordered="false">
                <Form ref="formInline" :model="formInline" :rules="ruleInline">
                    <div class='login-error-panel'>
                        {{loginMsg}}
                    </div>
                    <FormItem prop="userAccount">
                        <Input type="text" v-model="formInline.userAccount" :placeholder="$t('common.loginAccount')">
                            <Icon type="ios-person-outline" slot="prepend"></Icon>
                        </Input>
                    </FormItem>
                    <FormItem prop="password">
                        <Input type="password" v-model="formInline.password" :placeholder="$t('common.loginPassword')">
                            <Icon type="ios-lock-outline" slot="prepend"></Icon>
                        </Input>
                    </FormItem>
                    <div class='login-forget-panel'>
                        <a class='login-forget-pwd' href='javascript:void(0)'>{{$t('common.forgetPassword')}}</a>
                    </div>
                    <FormItem class='login-item'>
                        <Button long :loading='loginLoading' type="primary" @click="handleSubmit('formInline')">{{$t('common.login')}}</Button>
                    </FormItem>
                </Form>
            </Card>
        </div>
    </div>
</template>
<script>
    export default {
        data(){
            let vue=this;
            return {
                loginMsg:null,
                loginLoading:false,
                formInline: {
                    userAccount: 'chenbin',
                    password: 'chenbin1'
                },
                ruleInline: {
                    userAccount: [
                        { required: true, message: vue.$t('common.accountNotNull')}
                    ],
                    password: [
                        { required: true, message: vue.$t('common.passwordNotNull')}
                    ]
                }
            }
        },
        methods:{
            handleSubmit(name) {
                var vue=this;
                vue.loginMsg=null;
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        var params={
                            userAccount:this.formInline.userAccount,
                            password:this.formInline.password
                        };
                        this.loginLoading=true;
                        common.login(params).then(result => {
                            vue.loginLoading=false;
                            if(result){
                                sessionStorage.setItem("token",result.token);
                                if(vue.$route.query.redirectName){
                                    vue.$router.replace({name:vue.$route.query.redirectName});
                                }else{
                                    vue.$router.replace({name:'home'});
                                }
                            }else{
                                vue.loginMsg=vue.$t(result.errorMsg);
                            }
                        }).catch(error => {
                            vue.loginLoading=false;
                            vue.loginMsg=error.errorMsg || error.message;
                        });
                    }
                })
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
            height:320px;
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