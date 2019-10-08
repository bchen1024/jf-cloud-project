export default {
    state: {
      permissionList: []
    },
    getters: {
      permissionList:state=>state.permissionList || []
    },
    mutations:{
      setPermissionList(state,permissionList){
        state.permissionList=permissionList || []
      }
    },
    actions:{
      setPermissionList({commit},permissionList){
        commit('setPermissionList',permissionList);
      }
    }
  }