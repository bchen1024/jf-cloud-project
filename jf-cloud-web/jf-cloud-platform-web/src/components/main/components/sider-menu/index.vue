<template>
  <div class="side-menu-wrapper">
    <slot></slot>
    <Menu ref="menu" width="auto" @on-select="handleSelect" theme="dark" 
      :active-name="$route.name" :open-names="openedNames" accordion>
      <template v-for="item in menuList">
            <SiderMenuItem v-if="item.children && item.children.length>0" :key="`menu-${item.name}`" :parent-item="item"></SiderMenuItem>
            <menu-item v-else :name="item.name" :key="`menu-${item.name}`"><Icon v-if="item.meta && item.meta.icon" :type="item.meta.icon"/><span>{{ $t($util.showTitle(item)) }}</span></menu-item>
      </template>
    </Menu>
  </div>
</template>
<script>
import SiderMenuItem from './sider-menu-item.vue'

export default {
  name: 'SiderMenu',
  components: {
    SiderMenuItem
  },
  props: {
    menuList: {
      type: Array,
      default () {
        return []
      }
    },
    collapsed: {
      type: Boolean
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
  computed: {
    textColor () {
      return this.theme === 'dark' ? '#fff' : '#495060'
    }
  },
  watch: {
    activeName (name) {
      this.openedNames = this.$route.matched.map(item => item.name).filter(item => item !== name);
      console.info("--"+this.openedNames);
    },
    openedNames () {
      this.$nextTick(() => {
        this.$refs.menu.updateOpened()
      })
    }
  },
  mounted () {
    this.openedNames = this.$route.matched.map(item => item.name).filter(item => item !== name);
    console.info("=="+this.openedNames);
  }
}
</script>
<style lang="less">
@import './index.less';
</style>
