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
                    columns:[
                        {key:'logTime',width:170},
                        {key:'userId',title:'userName',width:150,format:'user',condition:{type:'string',key:'userName'}},
                        {key:'logModule',width:120,condition:true},
                        {key:'logMethod',width:120,condition:true},
                        {key:'logStartTime',width:200},
                        {key:'logEndTime',width:200},
                        {key:'logCost',width:100},
                        {key:'logIp',width:120,condition:true},
                        {key:'logType',width:120,format:'type'},
                        {key:'logMessage'}
                    ]
                }
            }
        }
    }
}
</script>