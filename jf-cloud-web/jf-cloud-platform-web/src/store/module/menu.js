export default {
    state: {
      breadCrumbList: []
    },
    getters: {
      
    },
    mutations: {
      setBreadCrumb (state, route) {
        let homeRouter={name:'home',path:'/home',meta:{icon:'md-home'}};
        if(route.name=='home'){
          state.breadCrumbList = [homeRouter];
        }else{
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
        }
      }
    },
    actions: {
      
    }
  }