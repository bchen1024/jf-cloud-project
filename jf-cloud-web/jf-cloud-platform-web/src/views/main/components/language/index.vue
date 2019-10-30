<template>
    <Dropdown @on-click="selectLang" style="margin-left:12px;">
        <img  :src="local.imgSrc" width="48" height="24" style="vertical-align: middle;"/>
        <Icon :size="18" type="md-arrow-dropdown" />
      <DropdownMenu slot="list">
        <DropdownItem v-for="(value, key) in localList" :selected="key==lang" :name="key" :key="`lang-${key}`">
          <img  :src="value.imgSrc" width="48" height="24" style="margin-right:8px;vertical-align: middle;"/>{{value.title}}
        </DropdownItem>
      </DropdownMenu>
    </Dropdown>
</template>

<script>
import cn from '@/assets/images/cn.png';
import en from '@/assets/images/en.png';
export default {
  name: 'Language',
  data () {
    return {
      localList: {
        'zh-CN':{title:'简体中文',imgSrc:cn},
        'en-US':{title:'English',imgSrc:en},
      }
    }
  },
  computed: {
    lang(){
      return this.$util.getLanguage();
    },
    local () {
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
          content: this.$t('switchLangConfirm'),
          onOk: () => {
              this.$util.setLanguage(name);
          }
      });
    }
  }
}
</script>
