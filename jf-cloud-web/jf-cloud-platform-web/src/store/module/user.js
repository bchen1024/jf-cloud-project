import Vue from 'vue';
export default {
    state: {
      userInfo: {
        userId:1,
        userName:'陈斌'
      },
      userCache:{
      }
    },
    mutations:{
      loadUser(state,userIds){
        if(userIds){
          let userIdList=[];
          if(typeof userIds=='number'){
            userIdList.push(userIds);
          }else{
            userIdList=userIdList.concat(userIds);
          }
          let userIdsList=userIdList.filter(v=>!state.userCache[v]);
          
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
          // userIdList.forEach(v=>{
          //   Vue.set(state.userCache,v,{userCn:'userCn'+v,userEn:'userEn'+v});
          // });
        }
        
      }
    },
    actions:{
      loadUser({commit},userIds){
        commit('loadUser',userIds);
      }
    },
    getters: {
      userInfo:state=>state.userInfo
    }
  }