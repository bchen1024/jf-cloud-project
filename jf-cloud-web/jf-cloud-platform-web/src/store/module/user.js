export default {
    state: {
      userInfo: {
        userId:1,
        userName:'陈斌'
      }
    },
    getters: {
      userInfo:state=>state.userInfo
    }
  }