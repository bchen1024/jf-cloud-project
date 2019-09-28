export default {
    state: {
      appInfo: {
        appId:1,
        appCode:'jf-cloud-platform'
      },
      appList:[
        {appId:1,appCode:'jf-cloud-platform'},
        {appId:2,appCode:'jf-cloud-report'}
      ]
    },
    getters: {
      appInfo:state=>state.appInfo,
      appList:state=>state.appList
    },
    mutations:{
      swtichApp(state,appCode){
        state.appInfo=state.appList.filter(o=>{return o.appCode==appCode});
      }
    }
  }