<template>
    <Layout class="jf-layout-full">
        <Sider class="jf-layout-sider" :width="200">
            <JFOrgTree ref="orgTree" @onSelectChange="onSelectChange"/>
        </Sider>
        <Content class="jf-layout-content">
            <JFGrid :ref="gridId" :gridOp="gridOp" @seachBefore="seachBefore">
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
                                let title=vm.$util.getOrgName(vm,params.row.employeeOrg,false);
                                if(title){
                                    return h('Tooltip',{props:{content:title,transfer:true,'max-width':400}},title);
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
    },
    methods:{
        seachBefore(params){
            let selected=this.$refs.orgTree.getSelectedNodes();
            if(selected && selected.length>0){
                params.employeeOrg=selected[0].orgId;
            }else{
                params.employeeOrg=null;
            }
        },
        onSelectChange(){
            this.$refs[this.gridId].search();
        }
    }
}
</script>