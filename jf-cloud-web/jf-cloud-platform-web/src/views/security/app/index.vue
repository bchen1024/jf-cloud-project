<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()" v-permission="'app$save'">
                {{$t('createApp')}}
            </Button>
        </template>
        <EditApp slot="grid-drawer" :formId="formId" formKey="appId"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="gridSearch"/>
    </JFGrid>
</template>
<script>
import EditApp from './edit.vue';
import editMixins from '@/mixins/editMixins';
export default {
    mixins:[editMixins],
    components:{
        EditApp
    },
    data(){
        let vm=this;
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/app/page'
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/security/app/delete'
                },
                table:{
                    pkId:'appId',
                    logSetting:{module:'App'},
                    detail:{name:'appDetail'},
                    buttons:[
                        {title:vm.$t('edit'),permissionCode:'app$save',click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),permissionCode:'app$delete',gridDelete:true},
                        {title:vm.$t('detail'),permissionCode:'app$single',gridDetail:true},
                        {title:vm.$t('syncPermission'),permissionCode:'permission$sync',click:(params)=>{
                            vm.syncPermission(params.row);
                        }},
                        {title:vm.$t('appUsers'),permissionCode:'appUsers$page',gridDetail:{tabId:'appUsers'},show:(row)=>{
                            if(row.appType==2){
                                return true;
                            }
                            return false;
                        }},
                        {title:vm.$t('appToken'),permissionCode:'appToken$single',gridDetail:{tabId:'appToken'},show:(row)=>{
                            if(row.appType==2){
                                return true;
                            }
                            return false;
                        }}
                    ],
                    columns:[
                        {key:'appCode',width:180,condition:true},
                        {key:'appName',width:200,condition:true},
                        {key:'appOwner',width:150,format:'user',condition:{type:'userSelect'}},
                        {key:'appType',width:100,format:'type',condition:{
                            type:'radio',items:[
                               {value:1,label:vm.$t('type.appType.1')},
                               {value:2,label:vm.$t('type.appType.2')}
                            ]
                        }},
                        {key:'contextPath',width:150},
                        {key:'appDesc',condition:true}
                    ]
                }
            }
        }
    },
    methods:{
        syncPermission(rows){
            let vm=this;
            vm.$http.post('jfcloud/jf-cloud-platform/security/permission/sync',{
                appCode:rows.appCode,contextPath:rows.contextPath
            },{
                headers:{op:'sync'}
            });
        }
    }
}
</script>