<template>
  <div class="side-menu-wrapper">
    <slot></slot>
    <Menu ref="menu" width="auto" v-show="!collapsed" @on-select="handleSelect" theme="dark" 
      :active-name="$route.name" :open-names="openedNames" accordion>
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
import { mapMutations, mapActions, mapGetters } from 'vuex'

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
        }
    },
  data () {
    return {
      openedNames: []
    }
  },
  methods: {
    handleSelect (name) {
      this.$emit('on-select', name)
    }
  },
  watch: {
    activeName (name) {
      this.openedNames = this.$route.matched.map(item => item.name).filter(item => item !== name);
    },
    openedNames () {
      this.$nextTick(() => {
        this.$refs.menu.updateOpened()
      })
    }
  },
  mounted () {
    this.openedNames = this.$route.matched.map(item => item.name).filter(item => item !== name);
  }
}
</script>
<style lang="less">
@import './index.less';
</style>
