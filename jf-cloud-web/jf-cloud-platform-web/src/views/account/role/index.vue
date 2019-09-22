<template>
    <div>
        <div style="margin-bottom:12px;">
            <!--搜索框-->
            <Input v-if="queryFields.length>0"
                autofocus search enter-button style="max-width:350px;display:inline-table;"
                class="jf-toolbar-item jf-input-search"
                :placeholder="queryPlaceholder"
                :disabled="loading"
                v-model="searchOp.queryParams.keywordValue"
                @on-search="search">
                <Select v-model="searchOp.queryParams.keywordField" slot="prepend" style="width:auto;">
                    <Option v-for="field in queryFields" :key="field.value" :value="field.value">
                        {{field.label}}
                    </Option>
                </Select>
                <!--Select v-model="searchOp.queryParams.keywordType" slot="prepend" style="width:auto;">
                    <Option v-for="field in queryTypes" :key="field.value" :value="field.value">
                        {{field.label}}
                    </Option>
                </Select-->
            </Input>
            <Button @click="showAdvancedFilter">
                {{$t('advancedFilter')}}
                <Icon :type="advancedFilter?'ios-arrow-up':'ios-arrow-down'"></Icon>
            </Button>
            <Tooltip :content="$t('tableSetting')" placement="bottom" style="float:right;margin-top: 4px;margin-right:12px;">
                <Icon type="ios-settings-outline" size="24" style="cursor: pointer;"/>
            </Tooltip>
            <!-- <Form :model="searchOp.queryParams" :label-width="80" v-show="advancedFilter" style="background-color: #fff;
    margin-top: 6px;
    box-shadow: 0 2px 7px rgba(0,0,0,.15);
    border-color: transparent;
    border-radius: 4px;">
                <FormItem label="applyStatus">
                    <RadioGroup v-model="searchOp.queryParams.applyStatus" type="button">
                        <Radio label="male">Y</Radio>
                        <Radio label="female">N</Radio>
                    </RadioGroup>
                </FormItem>
            </Form> -->
        </div>
        <Table stripe
            :columns="columns" :data="data||[]"  
            :loading="loading"
            :no-data-text="tableOp.noDataText">
        </Table>
        <!--分页-->
        <Page v-if='tableOp.showPager' @on-change="onPageChange"
            @on-page-size-change="onPageSizeChange"
            :total="pagerOp.total" 
            :page-size="pagerOp.pageSize" 
            :current="pagerOp.curPage" 
            :page-size-opts="pagerOp.pageList"
            placement="top" show-elevator show-sizer show-total transfer
            style="margin-top:6px;margin-bottom:6px;">
        </Page>
    </div>
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
                allowSetting:true,
                showTip:false,
                ellipsis:true,
                showIndex:true,
                showSelection:true,
                showPager:true,
                indexWidth:60,
                selectionWidth:60,
                indexAlign:'center',
                selectionAlign:'center'
            };
            let tableOp=Object.assign({},defaultOp,op.table);
            return tableOp;
        },
        /**
        * 分页配置
        */
        pagerOp(){
            let vm=this,op=vm.gridOp || {};
            let defaultOp={
                total:0,
                curPage:1,
                pageSize:10,
                pageList:[10,15,30,50,100,200]
            };
            let pagerOp=Object.assign({},defaultOp,op.pagerOp);
            return pagerOp;
        },
        columns(){
            let vm=this,tableOp=vm.tableOp || {},columns=[];
            //显示序号列
            if(tableOp.showIndex){
                columns.push(
                    {type: 'index',width:tableOp.indexWidth,align:tableOp.indexAlign,fixed:'left'}
                );
            }
            //显示选择框
            if(tableOp.showSelection){
                columns.push(
                    {type:'selection',width:tableOp.selectionWidth,align:tableOp.selectionAlign,fixed:'left'}
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
        },
        /**
         * 查询配置
         */
        searchOp(){
            var vm=this,gridOp=vm.gridOp || {};;
            var defaultOp={
                autoLoad:true,
                queryParams:{},
                method:"post"
            };
            var searchOp=Object.assign({},defaultOp,gridOp.search);
            return searchOp;
        },
        queryFields(){
            var vm=this,tableOp=vm.tableOp || {},searchOp=vm.searchOp || {};
            //查询
            var fields=[];
            (vm.columns || []).forEach(field => {
                if(field.condition){
                    fields.push({label:field.title,value:field.key});
                }
            });
            if(tableOp.queryFields && tableOp.queryFields.length>0){
                fields=fields.concat(tableOp.queryFields);
            }
            if(fields.length>0){
                searchOp.queryParams.keywordField=fields[0].value;
                searchOp.queryParams.keywordType=vm.queryTypes[0].value;
            }
            return fields;
        },
        queryPlaceholder(){
            let searchOp=this.searchOp;
            if(searchOp.queryParams.keywordField){
                return this.$t('pleaseEnter')+this.$t(searchOp.queryParams.keywordField);
            }
            return this.$t('pleaseEnterCondition');
        }
    },
    data(){
        return {
            loading:false,
            advancedFilter:false,
            data:[],
            queryTypes:[
                {value:'equls',label:'等于'},
                {value:'like',label:'包含'}
            ],
            gridOp:{
                search:{
                    url:'api/security/role/page'
                },
                table:{
                    columns:[
                        {key:'roleCode',width:120,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150},
                        {key:'applyStatus',width:120},
                        {key:'roleDesc',condition:true}
                    ]
                }
            }
        }
    },
    methods:{
        /**
         * 页数变化
         */
        onPageChange(curPage){
            this.pagerOp.curPage=curPage;
            this.search();
        },
        /**
         * 每页大小变化
         */
        onPageSizeChange(pageSize){
            this.pagerOp.pageSize=pageSize;
            this.search();
        },
        /**
         * 表格选中改变事件
         */
        onSelectionChange(selection){
            this.selectItems=selection;
        },
        showAdvancedFilter(){
            this.advancedFilter=!this.advancedFilter;
        },
        search(){
            var vm=this;
            vm.loading=true;
            var params=Object.assign({},vm.searchOp.queryParams,{
                pageSize:vm.pagerOp.pageSize,
                curPage:vm.pagerOp.curPage,
                appCode:'jf-cloud-platform'
            });
            vm.$http({
                method:vm.searchOp.method,
                url:vm.searchOp.url,
                data:params
            }).then(result=>{
                vm.pagerOp.total=result.data.total;
                vm.data=result.data.list;
                vm.loading=false;
            });
        }
    },
    created(){
        if(this.searchOp.autoLoad){
            this.search();
        }
    }
}
</script>