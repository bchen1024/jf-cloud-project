<template>
    <div>
        <div style="margin-bottom:12px;">
            <!--搜索框-->
            <Input v-if="queryKeywordFields.length>0"
                autofocus search enter-button style="max-width:350px;display:inline-table;"
                class="jf-toolbar-item jf-input-search"
                :placeholder="searchOp.queryPlaceholder || $t('queryPlaceholder')"
                :disabled="loading"
                v-model="searchOp.queryParams.keywordValue"
                @on-search="search">
                <Select v-model="searchOp.queryParams.keywordField" slot="prepend" style="width:auto;">
                    <Option v-for="field in queryKeywordFields" :key="field.key" :value="field.key">
                        {{field.title?field.title:$t(field.key)}}
                    </Option>
                </Select>
            </Input>
            <Badge :count="advancedFilterCount">
                <Button @click="showAdvancedFilter" v-if="advancedQueryFields && advancedQueryFields.length>0">
                    {{$t('advancedFilter')}}
                    <Icon :type="advancedFilter?'ios-arrow-up':'ios-arrow-down'"></Icon>
                </Button>
            </Badge>
            <Tooltip :content="$t('tableSetting')" placement="bottom" style="float:right;margin-top: 4px;margin-right:12px;">
                <Icon type="ios-settings-outline" size="24" style="cursor: pointer;"/>
            </Tooltip>
            <Form v-if="advancedQueryFields && advancedQueryFields.length>0" :model="searchOp.queryParams" 
                :label-width="100" v-show="advancedFilter" style="background-color: #fff;
    margin-top: 6px;
    box-shadow: 0 2px 7px rgba(0,0,0,.15);
    border-color: transparent;
    border-radius: 4px;border: 1px solid #E5F4F3;padding-top: 10px">
                <FormItem style="margin-bottom:10px;" v-for="item in advancedQueryFields" :label="$t(item.key)" :key="item.key" :prop="item.key">
                    <template v-if="item.type=='radio'">
                        <RadioGroup v-model="searchOp.queryParams[item.key]">
                            <Radio   label="">{{$t('all')}}</Radio>
                            <Radio v-for="radio in item.items" :key="radio.value"  :label="radio.value">{{radio.label}}</Radio>
                        </RadioGroup>
                    </template>
                    <template v-if="item.type=='datePicker'">
                        <DatePicker :type="item.dateType || 'datetimerange'" format="yyyy-MM-dd HH:mm:ss" 
                            v-model="searchOp.queryParams[item.key]" style="width: 280px"
                            :placeholder="$t('selectDateTimeRange')"></DatePicker>
                    </template>
                </FormItem>
            </Form>
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
    name:'JFGrid',
    props:['gridOp'],
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
                selectionAlign:'center',
                showAuditCreate:false,
                showAuditUpdate:true
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
            let pagerOp=Object.assign({},defaultOp,op.pager);
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

            //显示审计列
            if(tableOp.showAuditCreate){
                columns.push(
                    {key:'createBy',title:vm.$t('createBy'),width:150},
                    {key:'createDate',title:vm.$t('createDate'),width:155,align: 'center'}
                );
            }
            if(tableOp.showAuditUpdate){
                columns.push(
                    {key:'lastUpdateBy',title:vm.$t('lastUpdateBy'),width:150},
                    {key:'lastUpdateDate',title:vm.$t('lastUpdateDate'),width:155,align: 'center'}
                );
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
        /**
         * 关键字查询字段
         */
        queryKeywordFields(){
            var vm=this,tableOp=vm.tableOp || {},searchOp=vm.searchOp || {};
            //查询
            var fields=[];
            (vm.columns || []).forEach(field => {
                if(field.condition && typeof field.condition=='boolean'){
                    fields.push({key:field.key});
                }
            });
            if(searchOp.queryKeywordFields && searchOp.queryKeywordFields.length>0){
                fields=fields.concat(searchOp.queryKeywordFields);
            }
            if(fields.length>0){
                searchOp.queryParams.keywordField=fields[0].key;
            }
            return fields;
        },
        /**
         * 高级筛选字段
         */
        advancedQueryFields(){
            var vm=this,tableOp=vm.tableOp || {},searchOp=vm.searchOp || {};
            //查询
            var fields=[];
            (vm.columns || []).forEach(field => {
                if(field.condition && typeof field.condition=='object'){
                    let condition=field.condition;
                    fields.push(Object.assign({},{key:field.key},condition));
                }
            });
            if(searchOp.advancedQueryFields && searchOp.advancedQueryFields.length>0){
                fields=fields.concat(searchOp.advancedQueryFields);
            }
            fields.forEach(v=>{
                vm.advancedFilterFields.push(v.key);
            });
            return fields;
        },
        advancedFilterCount(){
            if(this.advancedFilter){
                return 0;
            }else{
                let count=0;
                let queryParams=this.searchOp.queryParams;
                for(let p in queryParams){
                    if(!(queryParams[p] instanceof Array) && queryParams[p] && this.advancedFilterFields.includes(p)){
                        count++;
                    }else if(queryParams[p] instanceof Array && this.advancedFilterFields.includes(p)){
                        if(queryParams[p].length>0){
                            var flag=false;
                            queryParams[p].forEach(v=>{
                                if(v){
                                    flag=true;
                                }
                            });
                            if(flag){
                                count++;
                            }
                        }
                    }
                }
                return count;
            }
        },
        queryPlaceholder(){
            // let searchOp=this.searchOp;
            // if(searchOp.queryParams.keywordField){
            //     return this.$t('pleaseEnter')+this.$t(searchOp.queryParams.keywordField);
            // }
            // return this.$t('pleaseEnterCondition');
        }
    },
    data(){
        return {
            loading:false,
            advancedFilter:false,
            advancedFilterFields:[],
            data:[]
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
        /**
         * 是否显示高级筛选
         */
        showAdvancedFilter(){
            this.advancedFilter=!this.advancedFilter;
        },
        search(){
            var vm=this;
            vm.loading=true;
            var params=Object.assign({},vm.searchOp.queryParams,{
                pageSize:vm.pagerOp.pageSize,
                curPage:vm.pagerOp.curPage,
                //TODO
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
            }).catch(error=>{
                vm.loading=false;
                vm.tableOp.noDataText=error;
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