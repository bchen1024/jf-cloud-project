<template>
    <JFGrid :gridOp="gridOp"/>
</template>
<script>
export default {
    data(){
        return {
            gridOp:{
                search:{
                    url:'/security/role/page'
                },
                table:{
                    columns:[
                        {key:'roleCode',width:120,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150},
                        {key:'applyStatus',width:120,condition:{
                           type:'radio',items:[{value:'Y',label:this.$t('canApply')},{value:'N',label:this.$t('noApply')}]
                        }},
                        {key:'roleDesc',condition:true}
                    ]
                }
            }
        }
    }
}
</script>