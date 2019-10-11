// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import iView from 'view-design';
import 'view-design/dist/styles/iview.css';
import '@/assets/style/common.less'
import App from './App'
import i18n from '@/local'
import store from '@/store'
import router from '@/router'
import libs from '@/libs'
import JFView from '@/components';
import importDirective from '@/directive';

//注册iview
Vue.use(iView,{
  i18n: (key, value) => i18n.t(key, value)
});
//注册JFView
Vue.use(JFView);
//注册工具
Vue.use(libs);
//注册指令
importDirective(Vue);

//if (process.env.NODE_ENV !== 'production') require('@/mock')

new Vue({
  el: '#app',
  router,
  i18n,
  store,
  render: h => h(App)
})
