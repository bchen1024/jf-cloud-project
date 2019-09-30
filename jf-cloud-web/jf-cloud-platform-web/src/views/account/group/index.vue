<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()">
                {{$t('createGroup')}}
            </Button>
        </template>
        <Edit slot="grid-drawer" :formId="formId" :formKey="formKey"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="saveCallback"/>
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
            formKey:'groupId',
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/group/page',
                    defaultParams:{appCode:this.$store.state.app.appInfo.appCode}
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/security/group/delete',
                    defaultParams:{appCode:this.$store.state.app.appInfo.appCode}
                },
                table:{
                    pkId:'groupId',
                    logSetting:{module:'Group'},
                    buttons:[
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),gridDelete:true}
                    ],
                    columns:[
                        {key:'groupCode',width:150,condition:true},
                        {key:'groupName',width:250,condition:true},
                        {key:'groupOwner',width:150,format:'user'},
                        {key:'applyStatus',width:120,format:'applyStatus',condition:{
                           type:'radio',items:[{value:'Y',label:vm.$t('canApply')},{value:'N',label:vm.$t('noApply')}]
                        }},
                        {key:'groupDesc',condition:true}
                    ]
                }
            }
        }
    }
}
</script>