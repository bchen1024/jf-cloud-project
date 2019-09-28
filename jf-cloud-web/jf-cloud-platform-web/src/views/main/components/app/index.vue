<template>
    <Dropdown @on-click="selectApp">
      <a href="javascript:void(0)">
        {{ appCode }}
        <Icon :size="18" type="md-arrow-dropdown" />
      </a>
      <DropdownMenu slot="list">
        <DropdownItem v-for="app in appList" :selected="app.appCode==appCode" :name="app.appCode" :key="`app-${app.appCode}`">{{ app.appCode }}</DropdownItem>
      </DropdownMenu>
    </Dropdown>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
     
    }
  },
  computed: {
    appList(){
      return this.$store.state.app.appList;
    },
    appCode () {
       return this.$store.state.app.appInfo.appCode;
    }
  },
  methods: {
    selectApp (name) {
      if(name==this.appCode){
        return;
      }
      this.$Modal.confirm({
          title: this.$t('confirm'),
          content: this.$t('message.switchAppConfirm'),
          onOk: () => {
              this.$store.commit('swtichApp',name);
          }
      });
    }
  }
}
</script>
