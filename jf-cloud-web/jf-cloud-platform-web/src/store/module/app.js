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
      setAppList(state,appList){
        if(appList && appList.length>0){
          state.appList=appList;
          //获取浏览器缓存的应用
          let currentApp=util.getApp();
          if(currentApp){//浏览器存在缓存也再范围内，则默认浏览器缓存的应用，否则默认第一个
              let app=appList.filter(item=>{return item.appCode==currentApp});
              if(app && app.length>0){
                state.appInfo=app[0];
              }else{
                state.appInfo=appList[0];
              }
          }else{//浏览器不存在缓存即默认第一个
            state.appInfo=appList[0];
          }
          util.setApp(state.appInfo.appCode);
        }else{
          util.clearApp();
        }
      },
      swtichApp(state,appCode){
        state.appInfo=state.appList.filter(o=>{return o.appCode==appCode})[0];
        util.setApp(appCode);
        location.reload();
      }
    },
    actions:{
      setAppList({commit},appList){
        commit('setAppList',appList);
      },
      swtichApp({commit},appCode){
        commit('swtichApp',appCode);
      }
    }
  }