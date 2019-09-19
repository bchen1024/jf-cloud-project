<template>
    <Dropdown trigger="click" @on-click="selectLang">
      <a href="javascript:void(0)">
        {{ title }}
        <Icon :size="18" type="md-arrow-dropdown" />
      </a>
      <DropdownMenu slot="list">
        <DropdownItem v-for="(value, key) in localList" :name="key" :key="`lang-${key}`">{{ value }}</DropdownItem>
      </DropdownMenu>
    </Dropdown>
</template>

<script>
export default {
  name: 'Language',
  props: {
    lang: String
  },
  data () {
    return {
      localList: {
        'zh-CN': '中文简体',
        'en-US': 'English'
      }
    }
  },
  computed: {
    title () {
      return this.localList[this.lang]
    }
  },
  methods: {
    selectLang (name) {
        this.$util.setLanguage(name);
    }
  }
}
</script>
