<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()">
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
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
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
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),gridDelete:true,show:(row)=>{
                            if(row.roleCode!='superAdmin'){
                                return true;
                            }
                            return false;
                        }},
                        {title:vm.$t('detail'),gridDetail:true},
                        {title:vm.$t('roleUsers'),gridDetail:{tabId:'roleUsers'}},
                        {title:vm.$t('rolePermission'),gridDetail:{tabId:'rolePermission'}},
                        {title:vm.$t('roleGroups'),gridDetail:{tabId:'roleGroups'}}
                    ],
                    columns:[
                        {key:'roleCode',width:150,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150,format:'user'},
                        {key:'applyStatus',width:120,format:'applyStatus',condition:{
                           type:'radio',items:[{value:'Y',label:vm.$t('canApply')},{value:'N',label:vm.$t('noApply')}]
                        }},
                        {key:'roleDesc',condition:true}
                    ]
                }
            }
        }
    }
}
</script>