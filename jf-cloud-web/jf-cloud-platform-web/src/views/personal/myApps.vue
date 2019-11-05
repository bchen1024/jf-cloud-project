<template>
    <JFGrid ref="userAppGrid" :gridOp="userAppGrid"></JFGrid>
</template>
<script>

export default {
    data(){
        let vm=this;
        return {
            userAppGrid:{
                search:{
                    url:'jfcloud/jf-cloud-platform/personal/myApps'
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/personal/role/delete'
                },
                table:{
                    pkId:'id',
                    buttons:[
                        {title:vm.$t('delete'),gridDelete:true}
                    ],
                    showAuditUpdate:true,
                    columns:[
                        {key:'grantStatus',width:150,render:(h,params)=>{
                            return vm.$util.grantStatus(vm,h,params.row.beginDate,params.row.endDate);
                        },condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('status.grantStatus.1')},
                               {value:'2',label:vm.$t('status.grantStatus.2')},
                               {value:'3',label:vm.$t('status.grantStatus.3')},
                               {value:'4',label:vm.$t('status.grantStatus.4')}
                            ]
                        }},
                        {key:'appCode',width:150,condition:true},
                        {key:'appName',width:200,condition:true},
                        {key:'roleCode',width:150,condition:true},
                        {key:'roleName',width:200,condition:true},
                        {key:'beginDate',width:150,condition:{type:'datePicker',format:'yyyy-MM-dd',width:200}},
                        {key:'endDate',width:150,condition:{type:'datePicker',format:'yyyy-MM-dd',width:200}}
                    ]
                }
            }
        }
    }
}
</script>