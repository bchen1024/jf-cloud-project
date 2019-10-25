<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" name="detail" v-if="$util.checkPermission('role$single',$store.state.permission.permissionList)">
            <JFDetail ref="roleDetail" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <EditRole :formId="formId" formKey="roleId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
        <TabPane :label="$t('roleUsers')" name="roleUsers" v-if="$util.checkPermission('role$roleUsers',$store.state.permission.permissionList)">标签二的内容</TabPane>
        <TabPane :label="$t('rolePermission')" name="rolePermission" v-if="$util.checkPermission('rolePermission$tree',$store.state.permission.permissionList)">
            <Spin size="large" fix v-if="loading"></Spin>
            <div>
                <Button icon="md-checkmark" :loading="saveLoading" type="primary" @click="saveRolePermission()" v-permission="'rolePermission$save'">
                    {{$t('save')}}
                </Button>
                <Button  icon="md-refresh" @click="loadPermission()">
                    {{$t('refresh')}}
                </Button>
            </div>
            <Tree ref="permissionTree" :empty-text="$t('noPermissionData')" :data="treeData" multiple show-checkbox></Tree>
        </TabPane>
        <TabPane :label="$t('roleGroups')" name="roleGroups" v-if="$util.checkPermission('role$roleGroups',$store.state.permission.permissionList)">标签三的内容</TabPane>
    </Tabs>
</template>
<script>
import EditRole from './edit.vue';
import editMixins from '@/mixins/editMixins';
export default {
    mixins:[editMixins],
    components:{
        EditRole
    },
    data(){
        return {
            tabId:'detail',
            id:null,
            loadMap:{},
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/single'
                },
                editPermission:'role$save',
                autoLoad:!this.$route.query.tabId,
                userFields:['roleOwner'],
                items:[
                    {cols:[
                        {key:'roleCode'},
                        {key:'roleName'},
                    ]},
                    {cols:[
                        {key:'roleOwner',type:'user'},
                        {key:'applyStatus',type:'status'}
                    ]},
                    
                    {key:'roleDesc',desc:true}
                ]
            },

            loading:false,
            saveLoading:false,
            treeData:[]
        }
    },
    created(){
        let vm=this,query=vm.$route.query;
        vm.id=query.id;
        if(query.tabId){
            vm.tabId=query.tabId;
        }
        vm.load();
    },
    methods:{
        load(name){
            let vm=this;
            if(name){
                vm.tabId=name;
            }
            if(!vm.loadMap[vm.tabId]){
                if(vm.tabId=='detail'){
                    vm.loadDetail();
                }else if(vm.tabId=='rolePermission'){
                    vm.loadPermission();
                }
                vm.loadMap[vm.tabId]=true;
            }
        },
        loadDetail(){
            let vm=this;
            let roleDetail=vm.$refs.roleDetail;
            if(roleDetail){
                roleDetail.load();
            }
        },
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
        }
    }
}
</script>