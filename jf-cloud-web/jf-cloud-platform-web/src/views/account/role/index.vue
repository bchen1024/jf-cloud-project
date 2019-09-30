<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()">
                {{$t('createRole')}}
            </Button>
        </template>
        <EditRole slot="grid-drawer" :formId="formId" :formKey="formKey"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="saveCallback"/>
    </JFGrid>
</template>
<script>
import EditRole from './edit.vue';
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    components:{
        EditRole
    },
    data(){
        let vm=this;
        return {
            formKey:'roleId',
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
                    pkId:'roleId',
                    logSetting:{module:'Role'},
                    detail:{name:'roleDetail'},
                    buttons:[
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),gridDelete:true},
                        {title:vm.$t('detail'),gridDetail:true},
                        {title:vm.$t('roleUsers'),gridDetail:{tabId:'roleUsers'}},
                        {title:vm.$t('rolePermission'),gridDetail:{tabId:'rolePermission'}},
                        {title:vm.$t('roleGroups'),gridDetail:{tabId:'roleGroups'}}
                    ],
                    columns:[
                        {key:'roleCode',width:120,condition:true},
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