/**
 * 组件权限控制
 */
export default{
    name:'permission',
    directive:{
        bind(el,binding,vm){
            if(!vm.context.$util.checkPermission(binding.value,vm.context)){
                if(el.parentNode){
                    el.parentNode.removeChild(el);
                }else{
                    el.style.display='none';
                }
                
            }
        }
    }
}