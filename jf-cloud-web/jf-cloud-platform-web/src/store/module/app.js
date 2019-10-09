import util from '@/libs/util';
export default {
    state: {
      appInfo: {},
      appList:[]
    },
    getters: {
      appInfo:state=>state.appInfo || {},
      appList:state=>state.appList
    },
    mutations:{
      setAppList(state,app){
        if(app && app.appList && app.appList.length>0){
          state.appList=app.appList;
          state.appInfo=app.appInfo || {};
          util.setApp(state.appInfo.appCode);
        }else{
          util.clearApp();
        }
      }
    },
    actions:{
      setAppList({commit},app){
        commit('setAppList',app);
      }
    }
  }