import Vue from 'vue';
import VueI18n from 'vue-i18n';
import util from '@/libs/util'
import customZhCn from './cn';
import customEnUs from './en';
import zhCnLocale from 'iview/src/locale/lang/zh-CN';
import enUsLocale from 'iview/src/locale/lang/en-US';
Vue.use(VueI18n);
let lang = util.getLanguage();
//设置vue语言
Vue.config.lang = lang

// vue-i18n 6.x+写法
Vue.locale = () => {}
const messages = {
  'zh-CN': Object.assign(zhCnLocale, customZhCn),
  'en-US': Object.assign(enUsLocale, customEnUs)
}
const i18n = new VueI18n({
  locale: lang,
  messages
});
window.i18n=i18n;
export default i18n;