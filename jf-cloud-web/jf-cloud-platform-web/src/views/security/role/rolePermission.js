export default{
    data(){
        return {
            loading:false,
            saveLoading:false,
            treeData:[],
            syncLoading:false
        }
    },
    methods:{
        loadPermission(){
            let vm=this;
            vm.loading=true;
            vm.$http({
                method:'post',
                url:'jfcloud/jf-cloud-platform/security/role/permission/tree',
                data:{appCode:vm.$store.state.app.appInfo.appCode,roleId:vm.id,queryType:'rolePermission'}
            }).then(result=>{
                vm.loading=false;
                if(result && result.success){
                    vm.treeData=result.data;
                }
                
            }).catch(error=>{
                vm.loading=false;
            });
        },
        saveRolePermission(){
            let vm=this;
            let permissionTree=vm.$refs.permissionTree;
            if(permissionTree){
                let checkedNodes=permissionTree.getCheckedNodes();
                let permissionIds=[];
                checkedNodes.forEach(element => {
                    if(element.permissionType=='method'){
                        permissionIds.push(element.permissionId);
                    }
                });
                vm.saveLoading=true;
                vm.$http({
                    method:'put',
                    url:'jfcloud/jf-cloud-platform/security/role/permission/save',
                    data:{roleId:vm.id,permissionIds:permissionIds}
                }).then(result=>{
                    vm.saveLoading=false;
                    if(result && result.success){
                         vm.$Message.success(vm.$t('saveSuccessful'));
                    }
                }).catch(error=>{
                    vm.saveLoading=false;
                });
            }
        },
        /**
         * 同步权限
         */
        syncPermission(){
            let vm=this;
            vm.syncLoading=true;
            let appInfo=vm.$store.state.app.appInfo;
            vm.$http.post('jfcloud/jf-cloud-platform/security/permission/sync',{
                appCode:appInfo.appCode,contextPath:appInfo.contextPath
            },{
                headers:{op:'sync'}
            }).then(result=>{
                vm.loadPermission();
            }).catch(error=>{}).then(()=>{
                vm.syncLoading=false;
            });
        }
    }
}