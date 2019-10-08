<template>
    <Dropdown v-if="appInfo && appInfo.appCode" @on-click="selectApp" style="margin-left:12px;">
      <a>
        {{ appInfo.appCode }}
        <Icon :size="18" type="md-arrow-dropdown" />
      </a>
      <DropdownMenu slot="list">
        <DropdownItem v-for="app in appList" :selected="app.appCode==appInfo.appCode" :name="app.appCode" :key="`app-${app.appCode}`">{{ app.appCode }}</DropdownItem>
      </DropdownMenu>
    </Dropdown>
</template>

<script>
import util from '@/libs/util';
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
    appInfo () {
       return this.$store.state.app.appInfo;
    }
  },
  methods: {
    selectApp (name) {
      if(name==this.appInfo.appCode){
        return;
      }
      this.$Modal.confirm({
          title: this.$t('confirm'),
          content: this.$t('message.switchAppConfirm'),
          onOk: () => {
              util.setApp(name);
              location.reload();
          }
      });
    }
  }
}
</script>
