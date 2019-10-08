<template>
  <div class="side-menu-wrapper">
    <slot></slot>
    <Menu ref="menu" width="auto" v-show="!collapsed" @on-select="handleSelect" :theme="theme" 
      :active-name="activeName" :open-names="openedNames" accordion>
      <template v-for="item in menuList">
            <SiderMenuItem v-if="checkMenu(item.children)" :key="`menu-${item.name}`" :parent-item="item"></SiderMenuItem>
            <menu-item v-else-if="!item.children" :name="item.name" :key="`menu-${item.name}`">
              <Icon v-if="item.meta && item.meta.icon" :type="item.meta.icon"/>
              <span>{{ $t($util.showTitle(item)) }}</span>
            </menu-item>
      </template>
    </Menu>
    <div class="menu-collapsed" v-show="collapsed">
      <template v-for="item in menuList">
        <CollapsedMenu v-if="checkMenu(item.children)" @on-click="handleSelect" hide-title  :parentItem="item" :key="`drop-menu-${item.name}`"></CollapsedMenu>
        <Tooltip transfer v-else-if="!item.children" :content="$t($util.showTitle(item.children && item.children[0] ? item.children[0] : item))" placement="right" :key="`drop-menu-${item.name}`">
          <a @click="handleSelect(item.name)" class="drop-menu-a" :style="($store.state.menu.openNames || []).includes(item.name)?'color: #2d8cf0; font-size: larger;':''">{{ $t($util.showTitle(item)) }}</a>
        </Tooltip>
      </template>
    </div>
  </div>
</template>
<script>
import SiderMenuItem from './sider-menu-item.vue'
import CollapsedMenu from './collapsed-menu.vue'
import routes from '@/router/routes';
import util from '@/libs/util';
export default {
  name: 'SiderMenu',
  components: {
    SiderMenuItem,
    CollapsedMenu
  },
  computed: {
      collapsed(){
          return this.$store.state.menu.collapsed;
      },
      openedNames(){
        return this.$store.state.menu.openedNames;
      },
      activeName(){
        return this.$store.state.menu.activeName;
      },
      theme(){
        return this.$store.state.menu.theme;
      },
      menuList () {
          let router=routes.filter(item=>{
              return item.name=='main';
          });
          let menus=router[0].children.filter(item=>{
              return !item.meta || !item.meta.hideMenu;
          });
          return menus;
      }
  },
  methods: {
    handleSelect (route) {
      if(route==this.$route.name || route.name==this.$route.name){
          return;
      }
      let { name, params, query } = {}
      if (typeof route === 'string') {
          name = route
      }else {
          name = route.name
          params = route.params
          query = route.query
      }
      if (name.indexOf('isTurnByHref_') > -1) {
          window.open(name.split('_')[1])
          return
      }
      this.$router.push({
          name,
          params,
          query
      });
    },
    checkMenu(children){
      if(!children || children.length==0){
        return false;
      }else{
        let filterMenu=children.filter(v=>util.checkPermission((v.meta && v.meta.permissionCode),this.$store.state.permission.permissionList));
        if(filterMenu.length>0){
          return true;
        }
        return false;
      }
    }
  },
  watch: {
    openedNames () {
      this.$nextTick(() => {
        this.$refs.menu.updateOpened()
      })
    }
  }
}
</script>
<style lang="less">
.side-menu-wrapper{
  user-select: none;
  .menu-collapsed{
    padding-top: 10px;

    .ivu-dropdown{
      width: 100%;
      .ivu-dropdown-rel a{
        width: 100%;
      }
    }
    .ivu-tooltip{
      width: 100%;
      .ivu-tooltip-rel{
        width: 100%;
      }
      .ivu-tooltip-popper .ivu-tooltip-content{
        .ivu-tooltip-arrow{
          border-right-color: #fff;
        }
        .ivu-tooltip-inner{
          background: #fff;
          color: #495060;
        }
      }
    }


  }
  a.drop-menu-a{
    display: inline-block;
    padding: 6px 15px;
    width: 100%;
    text-align: center;
    color: #ffffff;
  }
}
.left-sider-light .drop-menu-a{
    color:#495060 !important;
  }
.menu-title{
  padding-left: 6px;
}

</style>
