export default{
    name:'permission',
    directive:{
        bind(el,binding,vm){
            if(!vm.context.$util.checkPermission(binding.value,vm.context.$store.state.permission.permissionList)){
                if(el.parentNode){
                    el.parentNode.removeChild(el);
                }else{
                    el.style.display='none';
                }
                
            }
        }
    }
}