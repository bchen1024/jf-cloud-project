<template>
    <JFGrid :gridOp="gridOp">
    </JFGrid>
</template>
<script>
export default {
    data(){
        let vm=this,query=vm.$route.query;
        let queryParams={appCode:vm.$store.state.app.appInfo.appCode};
        if(query.logModule){
            queryParams.keywordField='logModule';
            queryParams.keywordValue=query.logModule;
        }
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-monitor/monitor/auditLog/page',
                    queryParams:queryParams
                },
                table:{
                    showAdvancedFilter:true,
                    columns:[
                        {key:'logTime',width:200,condition:{
                            type:'datePicker'
                        }},
                        {key:'userId',title:'userName',width:150,format:'user',condition:{type:'userSelect',title:'userName'}},
                        {key:'logModule',width:120,condition:true},
                        {key:'logMethod',width:120,condition:true},
                        {key:'logStartTime',width:200,condition:{
                            type:'datePicker'
                        }},
                        {key:'logEndTime',width:200,condition:{
                            type:'datePicker'
                        }},
                        {key:'logCost',width:100},
                        {key:'logIp',width:150,condition:true},
                        {key:'logType',width:120,format:'type',condition:{
                            type:'radio',items:[
                               {value:'audit',label:vm.$t('type.logType.audit')},
                               {value:'performance',label:vm.$t('type.logType.performance')},
                               {value:'login',label:vm.$t('type.logType.login')}
                            ]
                        }},
                        {key:'logMessage',minWidth:300,condition:true},
                    ]
                }
            }
        }
    }
}
</script>