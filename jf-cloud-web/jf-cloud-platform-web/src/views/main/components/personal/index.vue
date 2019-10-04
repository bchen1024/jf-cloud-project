<template>
  <div v-if="userInfo && userInfo.userId" class="user-avatar-dropdown" style="margin-left:12px;">
    <Dropdown @on-click="handleClick">
      <Avatar icon="ios-person"/>
      <a>{{$util.getUserName(userInfo)}}</a>
      <Icon :size="18" type="md-arrow-dropdown"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="personal">{{$t('personal')}}</DropdownItem>
        <DropdownItem name="logout">{{$t('logout')}}</DropdownItem>
      </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
export default {
  name: 'Personal',
  computed:{
    userInfo(){
      return this.$store.state.user.userInfo || {}
    }
  },
  methods: {
    logout () {
      
    },
    handleClick (name) {
        if(name=='personal'){
           this.$router.push({
                name
            });
        }else if(name=='logout'){
            this.logout();
        }
    },
    logout(){
        this.$Modal.confirm({
            title: this.$t('logout'),
            content: this.$t('message.logoutConfirm'),
            onOk: () => {
               this.$util.clearToken();
               this.$router.replace({name:'login'});
            }
        });
    }
  }
}
</script>
<style lang="less">
.user{
  &-avatar-dropdown{
    cursor: pointer;
    display: inline-block;
    // height: 64px;
    vertical-align: middle;
    // line-height: 64px;
    .ivu-badge-dot{
      top: 16px;
    }
  }
}
</style>
