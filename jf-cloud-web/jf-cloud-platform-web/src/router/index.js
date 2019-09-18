import Vue from 'vue'
import VueRouter from 'vue-router'
import iView from 'iview';
import routes from './routes'
import util from '@/libs/util'


Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'hash',
  routes:routes
});
//路由前
router.beforeEach((to, from, next) => {
  if(from.name!=to.name){
    iView.LoadingBar.start();
    next();
  }
});

//路由后
router.afterEach((to) => {
  iView.LoadingBar.finish();
  util.setTitle(router,to);
});
export default router;
