<template>
    <Table stripe
        :columns="columns" :data="data||[]"  
        :loading="tableOp.loading"
        :no-data-text="tableOp.noDataText">
    </Table>
</template>
<script>
export default {
    computed:{
        /**
         * 表格配置
         */
        tableOp(){
            let vm=this,op=vm.gridOp || {};
            let defaultOp={
                columns:[],
                loading:false,
                allowSetting:true,
                showAuditCreate:false,
                showAuditUpdate:true,
                showTip:false,
                ellipsis:true,
                showIndex:true,
                showSelection:true,
                showPager:true,
                userColumns:[],
                noDataText:'<font color=red>no data</font>',
                defaultUserColumns:['createBy','lastUpdateBy']
            };
            return Object.assign({},defaultOp,op.table)
        },
        columns(){
            let vm=this,tableOp=vm.tableOp || {},columns=[];
            //显示序号列
            if(tableOp.showIndex){
                columns.push(
                    {type: 'index',width:tableOp.indexWidth || 60,align:tableOp.indexAlign || 'center',fixed:'left'}
                );
            }
            //显示选择框
            if(tableOp.showSelection){
                columns.push(
                    {type:'selection',width:tableOp.selectionWidth || 60,align:tableOp.selectionAlign || 'center',fixed:'left'}
                );
            }
            //表格列配置
            if(tableOp.columns && tableOp.columns.length>0){
                tableOp.columns.forEach(column=>{
                    //检查该列是否有权限显示
                    if(!column.permission){
                        //配置所有列支持ellipsis
                        if(tableOp.ellipsis){
                            column.ellipsis=true;
                        }
                        //配置所有列显示tip提示
                        if(tableOp.showTip){
                            column.tooltip=true;
                        }
                        if(!column.title){
                            column.title=column.key;
                        }
                        if(typeof column.title==='string'){
                            column.title=vm.$t(column.title);
                        }
                        //配置默认宽度
                        if(!column.width){
                            column.tooltip=true;
                            column.minWidth=200;
                        }
                        columns.push(column);
                    }
                });
            }
            return columns;
        }
    },
    data(){
        return {
            gridOp:{
                table:{
                    columns:[
                        {key:'roleCode',width:120},
                        {key:'roleName',width:150},
                        {key:'roleOwner',width:150},
                        {key:'applyStatus',width:120},
                        {key:'roleDesc'}
                    ]
                }
            },
            data:[{roleCode:'admin',roleName:'管理员',roleOwner:1,applyStatus:'N',roleDesc:'管理员角色，拥有系统全部权限'}]
        }
    },
    methods:{

    }
}
</script>