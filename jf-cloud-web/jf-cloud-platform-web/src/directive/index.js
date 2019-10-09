import vPermssion from './v-permission';

const importDirective = Vue => {
  /**
   * 权限点指令 v-permission="app$page"
   * 通过权限码判断是否显示
   */
  Vue.directive(vPermssion.name, vPermssion.directive)
}

export default importDirective
