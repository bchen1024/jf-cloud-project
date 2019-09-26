export default {
    state: {
      app: {
        appId:1,
        appCode:'jf-cloud-platform'
      },
      appList:[
        {appId:1,appCode:'jf-cloud-platform'}
      ]
    },
    getters: {
      app:state=>state.app,
      appList:state=>state.appList
    }
  }