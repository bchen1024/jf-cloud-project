<template>
    <JFGrid :gridOp="gridOp">
        <template slot="toolbar">
            <Button icon="md-add" type="primary">
                创建角色
            </Button>
            <Button icon="md-trash" type="error">
                删除角色
            </Button>
        </template>
        <template slot="operation">
            <Button type="primary" size="small" style="margin-right: 5px" @click="show(row,index,column)">View</Button>
        </template>
    </JFGrid>
</template>
<script>
export default {
    data(){
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/page',
                    advancedQueryFields:[
                        {key:'createDate',type:'datePicker'}
                    ]
                },
                table:{
                    columns:[
                        {key:'roleCode',width:120,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150},
                        {key:'applyStatus',width:120,condition:{
                           type:'radio',items:[{value:'Y',label:this.$t('canApply')},{value:'N',label:this.$t('noApply')}]
                        }},
                        {key:'roleDesc',condition:true},
                        {key:'operation',width:80,align:'center',fixed:'right',slot: 'action'}
                    ]
                }
            }
        }
    },
    methods:{
        show(row,index,column){
           console.info(index);
        }
    }
}
</script>