import Vue from 'vue';
export default {
    state: {
      userInfo: null,
      userCache:{},
      userSelectInit:false
    },
    mutations:{
      setUserInfo(state,userInfo){
        state.userInfo=userInfo;
      },
      loadUser(state,userIds){
        if(userIds){
          let userIdList=[];
          if(typeof userIds=='number'){
            userIdList.push(userIds);
          }else{
            userIdList=userIdList.concat(userIds);
          }
          let userIdsList=Array.from(new Set(userIdList)).filter(v=>!state.userCache[v]);
          
          if(userIdsList && userIdsList.length>0){
            this._vm.$http({
              method:'post',
              url:'jfcloud/jf-cloud-platform/security/user/userMapByIds',
              data:{idList:userIdsList}
            }).then(result=>{
              //成功
              if(result && result.success){
                state.userCache=Object.assign({},state.userCache,result.data);
              }
            });
          }
        }
      },
      setUserCache(state,userList){
        if(userList && userList.length){
          let userCache={};
          userList.forEach(user => {
            userCache[user.userId]=user;
          });
          state.userCache=Object.assign({},state.userCache,userCache);
        }
        
      },
      setUserSelectInit(state,initFlag){
        state.userSelectInit=initFlag;
      }
    },
    actions:{
      setUserInfo({commit},userInfo){
        commit('setUserInfo',userInfo);
      },
      loadUser({commit},userIds){
        commit('loadUser',userIds);
      },
      setUserCache({commit},userList){
        commit('setUserCache',userList);
      },
      setUserSelectInit({commit},initFlag){
        commit('setUserSelectInit',initFlag);
      }
    },
    getters: {
      userInfo:state=>state.userInfo
    }
  }