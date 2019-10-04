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
      if(!token){
        next({name:'login',query:{routerName:to.name}});
      }else{
        if (router.app.$store.state.user.userInfo) {
          next();
        }else{
          router.app.$http.get('jfcloud/jf-cloud-platform/security/user/environment').then(result=>{
            if(result && result.success){
              router.app.$store.dispatch('setUserInfo',result.data.user);
              router.app.$store.dispatch('setAppList',result.data.appList);
              next();
            }
          }).catch(error=>{
            debugger;
          });
        }
      }
    }else{
      next();
    }
  }
});

//路由后
router.afterEach((to) => {
  iView.LoadingBar.finish();
  util.setTitle(router,to);
});
export default router;
