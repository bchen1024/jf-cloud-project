import http from '@/libs/request';
export default {
    state: {
      orgCacheList: [],
      orgInit:false,
      orgMap:{}
    },
    getters: {
      orgCacheList:state=>state.orgCacheList || []
    },
    mutations:{
      setOrgCacheList(state,orgCacheList){
        state.orgCacheList=orgCacheList || []
      },
      loadOrgList(state,reload=false){
        if(reload){
          state.orgInit=false;
        }
        if(state.orgInit){
          return;
        }
        let loadParentIds=function(list,orgMap){
            (list || []).forEach(item=>{
                orgMap[item.orgId]=item;
                if(item.children && item.children.length>0){
                    loadParentIds(item.children,orgMap);
                }
            });
        };
        state.orgInit=true;
        http.post('jfcloud/jf-cloud-platform/config/organization/tree',{}).then(result=>{
          state.orgInit=true;
          state.orgCacheList=result.data;
          let orgMap={};
          loadParentIds(result.data,orgMap);
          state.orgMap=orgMap;
      });
      }
    },
    actions:{
      setOrgCacheList({commit},orgCacheList){
        commit('setOrgCacheList',orgCacheList);
      },
      loadOrgList({commit},relaod){
        commit('loadOrgList',relaod);
      }
    }
  }