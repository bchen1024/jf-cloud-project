<template>
    <Dropdown @on-click="selectTheme" style="margin-left:12px;">
      <a>
        {{ title }}
        <Icon :size="18" type="md-arrow-dropdown" />
      </a>
      <DropdownMenu slot="list">
        <DropdownItem v-for="(value, key) in themeList" :selected="key==theme" :name="key" :key="`theme-${key}`">{{ value }}</DropdownItem>
      </DropdownMenu>
    </Dropdown>
</template>

<script>
export default {
  name: 'Theme',
  data () {
    return {
      themeList: {
        'light': 'Light-高亮主题',
        'dark': 'Dark-黑暗主题'
      }
    }
  },
  computed: {
    theme(){
      return this.$store.state.menu.theme;
    },
    title () {
      return this.themeList[this.theme];
    }
  },
  methods: {
    selectTheme (name) {
      if(name==this.theme){
        return;
      }
      this.$store.dispatch('setTheme',name);
    }
  }
}
</script>
