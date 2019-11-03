<template>
    <Layout class="jf-layout-full">
        <Sider class="jf-layout-sider" :width="200">
            <JFOrgTree ref="orgTree"/>
        </Sider>
        <Content class="jf-layout-content">
            <JFGrid :ref="gridId" :gridOp="gridOp">
                <Edit slot="grid-drawer" :formId="formId" formKey="userId"
                    :visible.sync="showEdit" 
                    :formData="formData"
                    @saveCallback="gridSearch"/>
            </JFGrid>
        </Content>
    </Layout>
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
                    url:'jfcloud/jf-cloud-platform/security/employee/page'
                },
                table:{
                    pkId:'userId',
                    logSetting:{module:'Employee'},
                    detail:{name:'employeeDetail'},
                    buttons:[
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('detail'),gridDetail:true},
                    ],
                    columns:[
                        {key:'userId',title:'userName',width:150,format:'user',condition:{type:'string',key:'userName'}},
                        {key:'employeeNo',width:130},
                        {key:'employeeType',width:140,format:'type',condition:{
                            type:'radio',labelWidth:150,items:[
                               {value:1,label:vm.$t('type.employeeType.1')},
                               {value:2,label:vm.$t('type.employeeType.2')},
                               {value:3,label:vm.$t('type.employeeType.3')}
                            ]
                        }},
                        {key:'employeeStatus',width:150,format:'status',condition:{
                            type:'radio',labelWidth:150,items:[
                               {value:1,label:vm.$t('status.employeeStatus.1')},
                               {value:2,label:vm.$t('status.employeeStatus.2')}
                            ]
                        }},
                        {key:'entryDate',width:120},
                        {key:'employeeOrg',render: (h, params) => {
                            if(params.row.employeeOrg){
                                let org=vm.$store.state.cache.orgMap[params.row.employeeOrg];
                                if(org){
                                    return h('div',{},org.title);
                                }
                            }
                            return '';
                        }},
                        {key:'employeeJob'},
                        {key:'parentId',title:'employeeSuperior',width:140,format:'user'}
                    ]
                }
            }
        }
    }
}
</script>