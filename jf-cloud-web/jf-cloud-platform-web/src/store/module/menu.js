export default {
    state: {
      breadCrumbList: [],
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
            })
            state.breadCrumbList = [homeRouter, ...res];
          }else{
            let res=[];
              let parent=route.meta.parent;
              parent.forEach(item => {
                res.push({
                  name:item
                });
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