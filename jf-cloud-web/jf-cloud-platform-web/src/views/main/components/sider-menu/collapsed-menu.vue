<template>
  <Dropdown ref="dropdown" @on-click="handleClick" :class="hideTitle ? '' : 'collased-menu-dropdown'" :transfer="true" :placement="placement">
    <a class="drop-menu-a" :style="($store.state.menu.openNames || []).includes(parentItem.name)?'color: #2d8cf0; font-size: larger;':''" @mouseover="handleMousemove($event, children)"><span>{{ $t($util.showTitle(parentItem)) }}</span></a>
    <DropdownMenu ref="dropdown" slot="list">
      <template v-for="child in children">
        <CollapsedMenu v-if="child.children && child.children.length>0"  :parentItem="child" :key="`drop-${child.name}`"/>
        <DropdownItem :selected="child.name==$store.state.menu.activeName" v-else :key="`drop-${child.name}`" :name="child.name"><Icon :type="child.icon"/><span class="menu-title">{{ $t($util.showTitle(child))  }}</span></DropdownItem>
      </template>
    </DropdownMenu>
  </Dropdown>
</template>
<script>

export default {
  name: 'CollapsedMenu',
  props: {
    hideTitle: {
      type: Boolean,
      default: false
    },
    rootIconSize: {
      type: Number,
      default: 16
    },
    parentItem: {
      type: Object,
      default: () => {}
    }
  },
  computed: {
      parentName () {
          return this.parentItem.name
      },
      children () {
          return this.parentItem.children
      }
  },
  data () {
    return {
      placement: 'right-end'
    }
  },
  methods: {
    handleClick (name) {
      this.$emit('on-click', name)
    },
    handleMousemove (event, children) {
      const { pageY } = event
      const height = children.length * 38
      const isOverflow = pageY + height < window.innerHeight
      this.placement = isOverflow ? 'right-start' : 'right-end'
    }
  },
  mounted () {
    
  }
}
</script>
