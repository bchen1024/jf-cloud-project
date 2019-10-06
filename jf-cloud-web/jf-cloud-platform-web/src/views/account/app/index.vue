<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()">
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
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
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
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),gridDelete:true},
                        {title:vm.$t('detail'),gridDetail:true},
                        {title:vm.$t('appUsers'),gridDetail:{tabId:'appUsers'},show:(row)=>{
                            if(row.appType=='2'){
                                return true;
                            }
                            return false;
                        }},
                        {title:vm.$t('appToken'),gridDetail:{tabId:'appToken'},show:(row)=>{
                            if(row.appType=='2'){
                                return true;
                            }
                            return false;
                        }},
                        {title:vm.$t('syncPermission'),show:(row)=>{
                            if(row.appType=='2'){
                                return true;
                            }
                            return false;
                        },click:(params)=>{
                            vm.syncPermission(params.row);
                        }}
                    ],
                    columns:[
                        {key:'appCode',width:180,condition:true},
                        {key:'appName',width:200,condition:true},
                        {key:'appOwner',width:150,format:'user'},
                        {key:'appType',width:100,format:'type',condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('type.appType.1')},
                               {value:'2',label:vm.$t('type.appType.2')}
                            ]
                        }},
                        {key:'appPort',width:100},
                        {key:'contextPath',width:120},
                        {key:'appDesc',condition:true}
                    ]
                }
            }
        }
    },
    methods:{
        syncPermission(rows){
            let vm=this;
            vm.$Message.loading({
                content: this.$t('synching'),
                duration: 0
            });
            vm.$http({
                method:'post',
                url:'jfcloud/jf-cloud-platform/security/permission/sync',
                data:{appCode:rows.appCode,contextPath:rows.contextPath}
            }).then(result=>{
                this.$Message.destroy();
                //成功
                if(result && result.success){
                    vm.$Message.success(vm.$t('syncSuccessful'));
                }
            }).catch(error=>{
                vm.$Message.destroy();
                vm.$Message.error(vm.$util.handerError(error,vm));
            });
        }
    }
}
</script>