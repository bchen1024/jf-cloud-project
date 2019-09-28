<template>
    <div>
        <JFGrid ref="roleGrid" :gridOp="gridOp">
            <template slot="grid-search-toolbar">
                <Button icon="md-add" type="primary" @click="roleEdit()">
                    {{$t('createRole')}}
                </Button>
            </template>
            <EditRole slot="grid-drawer" formId="roleForm"   formKey="roleId"
                :visible.sync="showEditRole" 
                :formData="formData"
                @saveCallback="saveCallback"/>
        </JFGrid>
    </div>
</template>
<script>
import EditRole from './edit.vue';
export default {
    components:{
        EditRole
    },
    data(){
        let vm=this;
        return {
            showEditRole:false,
            formData:{},
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/page',
                    defaultParams:{appCode:this.$store.state.app.appInfo.appCode}
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/security/role/delete',
                    defaultParams:{appCode:this.$store.state.app.appInfo.appCode}
                },
                table:{
                    logSetting:{module:'Role'},
                    columns:[
                        {key:'roleCode',width:120,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150},
                        {key:'applyStatus',width:120,condition:{
                           type:'radio',items:[{value:'Y',label:vm.$t('canApply')},{value:'N',label:vm.$t('noApply')}]
                        }},
                        {key:'roleDesc',condition:true},
                        {key:'operation',width:100,align:'center',fixed:'right', render: (h, params) => {
                            return h('Dropdown',{props:{transfer:true}}, [
                                h('Icon',{props:{type:'ios-more',size:20}}),
                                h('DropdownMenu',{slot:'list'},[
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.roleEdit(params.row);
                                        }
                                    }},vm.$t('edit')),
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.roleDelete(params.row);
                                        }
                                    }},vm.$t('delete')),
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.roleDetail(params.row);
                                        }
                                    }},vm.$t('detail')),
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.roleDetail(params.row,'roleUsers');
                                        }
                                    }},vm.$t('roleUsers')),
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.roleDetail(params.row,'rolePermission');
                                        }
                                    }},vm.$t('rolePermission')),
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.roleDetail(params.row,'roleGroups');
                                        }
                                    }},vm.$t('roleGroups'))
                                    
                                ])
                            ]);
                        }}
                    ]
                }
            }
        }
    },
    methods:{
        //创建、编辑角色
        roleEdit(data){
            if(data){
                this.formData=data;
            }else{
                this.formData={};
            }
            this.showEditRole=true;
        },
        /**
         * 角色删除
         */
        roleDelete(data){
            if(data.roleCode=='admin'){
                this.$Message.warning(this.$t('role.adminNotDelete'));
                return;
            }
            this.$refs.roleGrid.gridDelete({id:data.roleId});
        },
        /**
         * 查看角色详情
         */
        roleDetail(data,tabId){
            let vm=this;
            let routeData = vm.$router.resolve({
                name: "roleDetail",
                query: {
                    id:data.roleId,
                    tabId:tabId
                }
            });
            window.open(routeData.href, '_blank');
        },
        /**
         * 保存回调刷新表格
         */
        saveCallback(formData){
            this.$refs.roleGrid.search();
        }
    }
}
</script>