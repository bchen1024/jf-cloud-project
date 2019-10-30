<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <!--表格工具栏-->
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()" v-permission="'group$save'">
                {{$t('createGroup')}}
            </Button>
        </template>
        <!--群组编辑-->
        <Edit slot="grid-drawer" :formId="formId" formKey="groupId"
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
                    detail:{name:'groupDetail'},
                    buttons:[
                        {title:vm.$t('edit'),permissionCode:'group$save',click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),permissionCode:'group$delete',gridDelete:true},
                        {title:vm.$t('detail'),permissionCode:'group$single',gridDetail:true},
                    ],
                    columns:[
                        {key:'groupCode',width:150,condition:true},
                        {key:'groupName',width:250,condition:true},
                        {key:'groupOwner',width:150,format:'user',condition:{type:'userSelect'}},
                        {key:'applyStatus',width:130,format:'status',condition:{
                           type:'radio',items:[
                               {value:'Y',label:vm.$t('status.applyStatus.Y')},
                               {value:'N',label:vm.$t('status.applyStatus.N')}
                            ]
                        }},
                        {key:'groupDesc',condition:true}
                    ]
                }
            }
        }
    }
}
</script>