// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import iView from 'iview';
import './index.less'
import App from './App'
import i18n from '@/local'
import store from '@/store'
import router from '@/router'
import http from '@/libs/request'
import util from '@/libs/util'


Vue.use(iView,{
  i18n: (key, value) => i18n.t(key, value)
});
Vue.config.productionTip = false

Vue.prototype.$http=http;
Vue.prototype.$util=util;
if (process.env.NODE_ENV !== 'production') require('@/mock')
http.get("/jf-cloud-platform/config/menus",{}).then(res=>{
  debugger;
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  store,
  render: h => h(App)
})
