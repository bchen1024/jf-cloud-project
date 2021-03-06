import Vue from 'vue'
import VueRouter from 'vue-router'
import iView from 'iview';
import routes from './routes'
import util from '@/libs/util'


Vue.use(VueRouter);

const router = new VueRouter({
  //mode: 'hash',
  routes:routes
});
//路由前
router.beforeEach((to, from, next) => {
  if(from.name!=to.name){
    //判断是否需要登录
    let isNeedLogin=true;
    if(to.meta && to.meta.noNeedLogin){
      isNeedLogin=false;
    }
    iView.LoadingBar.start();
    if(isNeedLogin){
      let token=util.getToken();
      if(!token){//需要登录，如果没有token,跳转到登录页
        next({name:'login',query:{routerName:to.name}});
      }else{
        let userInfo=router.app.$store.state.user.userInfo;
        if (userInfo && userInfo.userId) {//如果已经获取到登录信息，则直接跳转
          if(util.checkPermission((to.meta && to.meta.permissionCode),router.app)){
            next();
          }else{
            next({name:'noPermission'});
          }
        }else{
          router.app.$http.get('jfcloud/jf-cloud-platform/security/user/environment').then(result=>{
            if(result && result.success){
              router.app.$store.dispatch('setUserInfo',result.data.user);
              router.app.$store.dispatch('setPermissionList',result.data.permissionList);
              router.app.$store.dispatch('setAppList',{appList:result.data.appList,appInfo:result.data.appInfo});
              if(util.checkPermission((to.meta && to.meta.permissionCode),router.app)){
                next();
              }else{
                next({name:'noPermission'});
              }
            }
          }).catch(error=>{
            console.error(error);
            next({name:'badGateway'});
          });
        }
      }
    }else{//不需要登录
      next();
    }
  }else{
    iView.LoadingBar.finish();
  }
});

//路由后
router.afterEach((to) => {
  iView.LoadingBar.finish();
  util.setTitle(router,to);
  router.app.$store.dispatch('setBreadCrumb',to);
});
export default router;
