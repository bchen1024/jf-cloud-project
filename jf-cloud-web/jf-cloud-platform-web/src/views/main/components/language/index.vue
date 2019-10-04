<template>
    <Dropdown @on-click="selectLang" style="margin-left:12px;">
      <a>
        {{ title }}
        <Icon :size="18" type="md-arrow-dropdown" />
      </a>
      <DropdownMenu slot="list">
        <DropdownItem v-for="(value, key) in localList" :selected="key==lang" :name="key" :key="`lang-${key}`">{{ value }}</DropdownItem>
      </DropdownMenu>
    </Dropdown>
</template>

<script>
export default {
  name: 'Language',
  data () {
    return {
      localList: {
        'zh-CN': '中文简体',
        'en-US': 'English'
      }
    }
  },
  computed: {
    lang(){
      return this.$util.getLanguage();
    },
    title () {
      return this.localList[this.lang];
    }
  },
  methods: {
    selectLang (name) {
      if(name==this.lang){
        return;
      }
      this.$Modal.confirm({
          title: this.$t('confirm'),
          content: this.$t('message.switchLangConfirm'),
          onOk: () => {
              this.$util.setLanguage(name);
          }
      });
    }
  }
}
</script>
