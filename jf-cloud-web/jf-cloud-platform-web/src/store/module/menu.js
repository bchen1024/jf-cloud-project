export default {
    state: {
      breadCrumbList: [],
      activeName:'',
      openNames:[],
      collapsed:false
    },
    getters: {
      
    },
    mutations: {
      collapsedSider(state){
        state.collapsed=!state.collapsed;
      },
      setBreadCrumb (state, route) {
        let homeRouter={name:'home',path:'/home',meta:{icon:'md-home'}};
        if(route.name=='home'){
          state.breadCrumbList = [homeRouter];
        }else{
          if(!route.meta || !route.meta.parent){
            let routeMetched = route.matched;
            let res = routeMetched.filter(item => {
              return (item.meta === undefined || !item.meta.hideInBread) && item.name!='main';
            }).map(item => {
              let obj = {
                name: item.name,
                meta: item.meta
              }
              if(item.meta && item.meta.noLink){
                delete obj.path;
              }
              return obj
            });
            state.activeName=route.name;
            if(res.length>1){
              state.openNames=[res[res.length-2].name];
            }
            state.breadCrumbList = [homeRouter, ...res];
          }else{
            let res=[];
              let parent=route.meta.parent;
              parent.forEach(item => {
                if(typeof item=='string'){
                  res.push({
                    name:item
                  });
                }else{
                  res.push({
                    name:item.name,
                    meta:item.meta
                  });
                }
              });
              res.push({name:route.name,meta: route.meta});
              state.breadCrumbList = [homeRouter, ...res];
          }
        }
      }
    },
    actions: {
      
    }
  }