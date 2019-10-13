import constant from './constant'
import Vue from 'vue';

export default {
    /**
     * 获取Tokens
     */
    getToken:function(){
        return sessionStorage.getItem(constant.TOKEN);
    },

    /**
     * 设置token
     * @param {*} token token值
     */
    setToken:function(token){
        sessionStorage.setItem(constant.TOKEN,token);
    },

    /**
     * 清除token
     */
    clearToken:function(){
        sessionStorage.removeItem(constant.TOKEN);
    },

    /**
     * 设置浏览器标题
     * @param {*} router 路由对象
     * @param {*} to 目标路由
     */
    setTitle:function(router,to){
        if(!router || !to){
            return;
        }
        let title="";
        let vm=router.app;
        if(to.meta && to.meta.title){
            title=vm.$t(to.meta.title);
        }else if(to.name){
            title = vm.$t(to.name);
        }
        if(!title){
            title=vm.$t(constant.DEFAULT_TITLE);
        }
        document.title=title;
    },
    showTitle:function(item){
        let title="";
        if(item.meta && item.meta.title){
            title=to.meta.title;
        }else if(item.name){
            title = item.name;
        }
        return title;
    },
    /**
     * 获取当前语言
     */
    getLanguage:function(){
        const langs=[constant.ZH_CN,constant.EN_US];
        // 自动根据浏览器系统语言设置语言
        const navLang = navigator.language
        const localLang = (langs.indexOf(navLang)>-1) ? navLang : false
        let lang = localStorage.getItem(constant.LANGUAGE) || localLang ||  constant.ZH_CN;
        if(langs.indexOf(lang)<0){
            console.warn(`当前获取的语言为${lang},不符合规范，默认为zh-CN`);
            lang=constant.ZH_CN;
        }
        return lang;
    },
    /**
     * 设置语言
     * @param {*} lang 
     */
    setLanguage:function(lang){
        if(Vue.config.lang==lang){
            return;
        }
        localStorage.setItem(constant.LANGUAGE,lang);
        window.location.reload();
    },
    /**
     * 通用异常信息处理
     * @param {*} error 
     * @param {*} vm 
     */
    handerError(error,vm){
        let errorMsg='';
        if(typeof error=='string'){
            errorMsg=error;
        }else if(error.code){
            errorMsg=vm.$t(error.code);
        }else{
            errorMsg=error.msg || error.message;
        }
        return errorMsg;
    },
    /**
     * 用户状态
     * @param {*} value 
     * @param {*} vm 
     */
    getUserStatus(value,vm){
        let title='';
        if(value=='Y'){
            title=vm.$t('normal');
        }else if(value=='L'){
            title=vm.$t('lock');
        }else if(value=='D'){
            title=vm.$t('delete');
        }
        return title;
    },
    /**
     * 获取当前应用编码
     */
    getApp(){
        return localStorage.getItem(constant.APP_CODE);
    },
    /**
     * 设置app编码
     * @param {*} appCode 
     */
    setApp(appCode){
        localStorage.setItem(constant.APP_CODE,appCode);
    },
    clearApp(){
        localStorage.removeItem(constant.APP_CODE);
    },
    getUserName(userInfo){
        let names=[];
        if(userInfo){
            if(userInfo.userCn){
                names.push(userInfo.userCn);
            }
            if(userInfo.userEn){
                names.push(userInfo.userEn);
            }
        }
        if(names.length>0){
            return names.join('/');
        }
        return '';
    },
    checkPermission(permissionCode,vm){
        if(!permissionCode){
            return true;
        }else{
            let permissionList;
            if(vm instanceof Array){
                permissionList=vm;
            }else{
                permissionList=vm.$store.state.permission.permissionList;
            }
            
            if(permissionList || permissionList.length>0){
                let flag=false;
                if(permissionCode.indexOf('&')>-1){
                    let permissionCodes=permissionCode.split('&');
                    let filterList=permissionList.filter(v=>permissionCodes.includes(v));
                    flag=(filterList.length==permissionCodes.length);
                }else if(permissionCode.indexOf('|')>-1){
                    let permissionCodes=permissionCode.split('|');
                    flag=permissionList.some(v=>permissionCodes.includes(v));
                }else{
                    flag=permissionList.includes(permissionCode);
                }
                return flag;
            }
            return false;
        }
    }
}