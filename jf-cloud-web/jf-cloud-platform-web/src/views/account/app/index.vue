<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()">
                {{$t('createApp')}}
            </Button>
        </template>
        <EditApp slot="grid-drawer" :formId="formId" :formKey="formKey"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="saveCallback"/>
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
            formKey:'appId',
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
                    buttons:[
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),gridDelete:true}
                    ],
                    columns:[
                        {key:'appCode',width:150,condition:true},
                        {key:'appName',width:250,condition:true},
                        {key:'appOwner',width:150,format:'user'},
                        {key:'contextPath',width:120},
                        {key:'appDesc',condition:true}
                    ]
                }
            }
        }
    }
}
</script>