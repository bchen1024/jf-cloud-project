<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
    </JFGrid>
</template>
<script>
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    data(){
        let vm=this;
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/employee/page'
                },
                table:{
                    pkId:'userId',
                    //logSetting:{module:'Employee'},
                    detail:{name:'employeeDetail'},
                    buttons:[
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('detail'),gridDetail:true},
                    ],
                    columns:[
                        {key:'userId',title:'userName',width:200,format:'user',condition:{type:'string',key:'userName'}},
                        {key:'employeeNo',width:120},
                        {key:'employeeType',width:120,format:'type',condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('type.employeeType.1')},
                               {value:'2',label:vm.$t('type.employeeType.2')},
                               {value:'3',label:vm.$t('type.employeeType.3')}
                            ]
                        }},
                        {key:'employeeStatus',width:120,format:'status',condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('status.employeeStatus.1')},
                               {value:'2',label:vm.$t('status.employeeStatus.2')}
                            ]
                        }},
                        {key:'employeeJob'},
                        {key:'parentId',title:'employeeParent',width:150,format:'user'}
                    ]
                }
            }
        }
    }
}
</script>