import constant from './constant'

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
        sessionStorage.removeItem(constant.token);
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
    }
}