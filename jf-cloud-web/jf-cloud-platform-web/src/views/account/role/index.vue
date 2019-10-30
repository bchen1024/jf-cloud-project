<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()" v-permission="'role$save'">
                {{$t('createRole')}}
            </Button>
        </template>
        <Edit slot="grid-drawer" :formId="formId" formKey="roleId"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="gridSearch"/>
    </JFGrid>
</template>
<script>
import Edit from './edit.vue';
import editMixins from '@/mixins/editMixins';
export default {
    mixins:[editMixins],
    components:{
        Edit
    },
    data(){
        let vm=this;
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/page',
                    defaultParams:{appCode:vm.$store.state.app.appInfo.appCode}
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/security/role/delete',
                    defaultParams:{appCode:vm.$store.state.app.appInfo.appCode}
                },
                table:{
                    pkId:'roleId',
                    logSetting:{module:'Role'},
                    detail:{name:'roleDetail'},
                    buttons:[
                        {title:vm.$t('edit'),permissionCode:'role$save',click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),permissionCode:'role$delete',gridDelete:true,show:(row)=>{
                            if(row.roleCode!='superAdmin'){
                                return true;
                            }
                            return false;
                        }},
                        {title:vm.$t('detail'),permissionCode:'role$single',gridDetail:true},
                        {title:vm.$t('roleUsers'),permissionCode:'role$roleUsers',gridDetail:{tabId:'roleUsers'}},
                        {title:vm.$t('rolePermission'),permissionCode:'rolePermission$tree',gridDetail:{tabId:'rolePermission'}},
                        {title:vm.$t('roleGroups'),permissionCode:'role$roleGroups',gridDetail:{tabId:'roleGroups'}}
                    ],
                    columns:[
                        {key:'roleCode',width:150,condition:true},
                        {key:'roleName',width:200,condition:true},
                        {key:'roleOwner',width:200,format:'user',condition:{type:'userSelect'}},
                        {key:'applyStatus',width:130,format:'status',condition:{
                           type:'radio',items:[{value:'Y',label:vm.$t('status.applyStatus.Y')},{value:'N',label:vm.$t('status.applyStatus.N')}]
                        }},
                        {key:'roleDesc',condition:true}
                    ]
                }
            }
        }
    }
}
</script>