<template>
  <div class="side-menu-wrapper">
    <slot></slot>
    <Menu ref="menu" width="auto" v-show="!collapsed" @on-select="handleSelect" theme="dark" 
      :active-name="activeName" :open-names="openedNames" accordion>
      <template v-for="item in menuList">
            <SiderMenuItem v-if="item.children && item.children.length>0" :key="`menu-${item.name}`" :parent-item="item"></SiderMenuItem>
            <menu-item v-else :name="item.name" :key="`menu-${item.name}`"><Icon v-if="item.meta && item.meta.icon" :type="item.meta.icon"/><span>{{ $t($util.showTitle(item)) }}</span></menu-item>
      </template>
    </Menu>
    <div class="menu-collapsed" v-show="collapsed">
      <template v-for="item in menuList">
        <CollapsedMenu v-if="item.children && item.children.length > 1" @on-click="handleSelect" hide-title  :parentItem="item" :key="`drop-menu-${item.name}`"></CollapsedMenu>
        <Tooltip transfer v-else :content="$t($util.showTitle(item.children && item.children[0] ? item.children[0] : item))" placement="right" :key="`drop-menu-${item.name}`">
          <a @click="handleSelect(item.name)" class="drop-menu-a" :style="{textAlign: 'center'}">{{ $t($util.showTitle(item)) }}</a>
        </Tooltip>
      </template>
    </div>
  </div>
</template>
<script>
import SiderMenuItem from './sider-menu-item.vue'
import CollapsedMenu from './collapsed-menu.vue'

export default {
  name: 'SiderMenu',
  components: {
    SiderMenuItem,
    CollapsedMenu
  },
  props: {
    menuList: {
      type: Array,
      default () {
        return []
      }
    }
  },
  computed: {
        collapsed(){
            return this.$store.state.menu.collapsed;
        },
        openedNames(){
          if(this.$route.meta && this.$route.meta.parent){
            return [this.$route.meta.parent[0]];
          }else{
            let openedNames = this.$route.matched.map(item => item.name).filter(item => item !== name);
            return openedNames;
          }
        },
        activeName(){
          if(this.$route.meta && this.$route.meta.parent){
            let parent=this.$route.meta.parent;
            return parent[parent.length-1];
          }else{
            return this.$route.name;
          }
        }
    },
  data () {
    return {
      
    }
  },
  methods: {
    handleSelect (name) {
      this.$emit('on-select', name)
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
@import './index.less';
</style>
