<template>
    <div>
        <div style="margin-bottom:12px;min-height: 32px;">
            <Form v-if="advancedQueryFields && advancedQueryFields.length>0" inline :model="searchOp.queryParams" 
                :label-width="100" v-show="(queryKeywordFields.length==0) || advancedFilterShow" class="jf-grid-advanced-filter-form">
                <FormItem v-for="item in advancedQueryFields" :label="$t(item.key)" :key="item.key" :prop="item.key">
                    <template v-if="item.type=='radio'">
                        <RadioGroup v-model="searchOp.queryParams[item.key]">
                            <Radio   label="">{{$t('all')}}</Radio>
                            <Radio v-for="radio in item.items" :key="radio.value"  :label="radio.value">{{radio.label}}</Radio>
                        </RadioGroup>
                    </template>
                    <template v-else-if="item.type=='datePicker'">
                        <DatePicker :type="item.dateType || 'datetimerange'" format="yyyy-MM-dd HH:mm:ss" 
                            v-model="searchOp.queryParams[item.key]" style="width: 280px"
                            :placeholder="$t('selectDateTimeRange')"></DatePicker>
                    </template>
                    <template v-else>
                        <Input v-model="searchOp.queryParams[item.key]" style="width: 150px;"/>
                    </template>
                </FormItem>
                <Button v-if="queryKeywordFields.length==0 && advancedQueryFields.length>0" type="primary" icon="ios-search" @click="search" :loading="loading">{{$t('search')}}</Button>
            </Form>

            <div class="jf-grid-search">
                <!--关键字搜索框-->
                <Input v-if="queryKeywordFields.length>0"
                    autofocus class="jf-grid-search-input"
                    :placeholder="searchOp.queryPlaceholder || $t('queryPlaceholder')"
                    :disabled="loading" 
                    v-model="searchOp.queryParams.keywordValue"
                    @on-enter="search">
                    <Select :disabled="loading" v-model="searchOp.queryParams.keywordField" slot="prepend">
                        <Option v-for="field in queryKeywordFields" :key="field.key" :value="field.key">
                            {{field.title?field.title:$t(field.key)}}
                        </Option>
                    </Select>
                </Input>
                <!--关键字筛选，存在关键字筛选框才显示-->
                <Button v-if="queryKeywordFields.length>0" type="primary" icon="ios-search" @click="search" :loading="loading">{{$t('search')}}</Button>
                <!--高级筛选，存在关键字筛选框和高级筛选条件才显示-->
                <Badge :count="advancedFilterCount" v-if="queryKeywordFields.length>0 && advancedQueryFields.length>0">
                    <Button @click="advancedFilterShow=!advancedFilterShow" :disabled="loading" >
                        {{$t('advancedFilter')}}
                        <Icon :type="advancedFilterShow?'ios-arrow-down':'ios-arrow-up'"></Icon>
                    </Button>
                </Badge>
            </div>
            <div style="float:right;margin-right:12px;">
                <slot name="grid-search-toolbar"></slot>
                <Tooltip v-if="tableOp.allowSetting" :content="$t('tableSetting')" placement="bottom">
                    <Icon type="ios-settings-outline" size="20" style="cursor: pointer;"/>
                </Tooltip>
            </div>
        </div>
        <div style="margin-bottom:12px;">
            <slot name="grid-toolbar"></slot>
        </div>
        <!--表格-->
        <Table stripe
            :columns="columns" :data="data||[]"  
            :loading="loading"
            :no-data-text="tableNoDataText">
        </Table>
        <!--分页-->
        <Page v-if='tableOp.showPager' @on-change="onPageChange"
            @on-page-size-change="onPageSizeChange"
            :total="pagerOp.total" 
            :page-size="pagerOp.pageSize" 
            :current="pagerOp.curPage" 
            :page-size-opts="pagerOp.pageList"
            placement="top" show-elevator show-sizer show-total transfer
            class="jf-grid-pager"
            :style="'text-align:'+(pagerOp.align || 'left')">
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
            //默认表格配置
            let defaultOp={
                //列配置
                columns:[],
                //是否允许设置表格
                allowSetting:true,
                //是否显示tip提示
                showTip:false,
                //文本超长是否显示省略号
                ellipsis:true,
                //是否显示序号列
                showIndex:true,
                //是否多选
                showSelection:true,
                //是否显示分页
                showPager:true,
                //是否默认显示高级筛选
                showAdvancedFilter:false,
                //序号列的宽度
                indexWidth:60,
                //选择列的宽度
                selectionWidth:60,
                //序号列的位置
                indexAlign:'center',
                //选择列列的位置
                selectionAlign:'center',
                //是否显示创建信息字段
                showAuditCreate:false,
                //是否显示更新信息字段
                showAuditUpdate:false,
                //列标题需要国际化
                titleI18n:true
            };
            let tableOp=Object.assign({},defaultOp,op.table);
            return tableOp;
        },
        /**
        * 分页配置
        */
        pagerOp(){
            let vm=this,op=vm.gridOp || {};
            //默认分页配置
            let defaultOp={
                total:0,
                curPage:1,
                pageSize:10,
                pageList:[10,15,30,50,100,200],
                align:'right'
            };
            let pagerOp=Object.assign({},defaultOp,op.pager);
            return pagerOp;
        },
        /**
         * 列配置
         */
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
                        //如果配置
                        if(!column.title){
                            column.title=column.key;
                        }
                        //列标题国际化
                        if(tableOp.titleI18n){
                            column.title=vm.$t(column.title);
                        }
                        //未指定宽度的列配置默认宽度
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
            var vm=this,gridOp=vm.gridOp || {};
            //默认的查询配置
            var defaultOp={
                //是否自动加载
                autoLoad:true,
                //查询参数
                queryParams:{},
                //请求方法
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
                    fields.push(Object.assign({},{key:field.key},field.condition));
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
        /**
         * 高级筛选数量
         */
        advancedFilterCount(){
            if(this.advancedFilterShow){
                return 0;
            }else{
                let count=0;
                let queryParams=this.searchOp.queryParams;
                for(let p in queryParams){
                    if(this.advancedFilterFields.includes(p)){
                        if(!(queryParams[p] instanceof Array) && queryParams[p]){
                            count++;
                        }else if(queryParams[p] instanceof Array){
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
                }
                return count;
            }
        },
        tableNoDataText(){
            if(this.loadError){
                return "<font color='red'>"+this.errorMsg+"</font>"
            }
        }
    },
    data(){
        return {
            //是否在加载
            loading:false,
            //是否显示高级筛选
            advancedFilterShow:false,
            //高级筛选字段
            advancedFilterFields:[],
            //表格数据
            data:[],
            //是否加载错误
            loadError:false,
            //错误信息
            errorMsg:null
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
         * 表格搜索
         */
        search(){
            let vm=this,tableOp=vm.tableOp,searchOp=vm.searchOp,pagerOp=vm.pagerOp;        
            //参数组装
            var params=Object.assign({},
                searchOp.defaultParams,
                searchOp.queryParams,{
                pageSize:pagerOp.pageSize,
                curPage:pagerOp.curPage
            });
            vm.loading=true;
            vm.errorMsg=null;
            vm.$http({
                method:searchOp.method,
                url:searchOp.url,
                data:params
            }).then(result=>{
                 vm.loading=false;
                //成功
                if(result && result.success){
                    vm.loadError=false;
                    let data=result.data;
                    //分页结果
                    if(tableOp.showPager){
                        if(data){
                            pagerOp.total=data.total;
                            vm.data=data.list;
                        }else{
                            pagerOp.total=0;
                            vm.data=[];
                        }
                    }else{
                        vm.data=data || [];
                    }
                }else{
                    vm.loadError=true;
                    vm.errorMsg=vm.$t(result.code) || result.msg;
                }
            }).catch(error=>{
                vm.loadError=true;
                vm.loading=false;
                vm.errorMsg=error;
            });
        }
    },
    created(){
        if(this.tableOp.showAdvancedFilter){
            this.advancedFilterShow=true;
        }
        if(this.searchOp.autoLoad){
            this.search();
        }
    }
}
</script>
<style lang="less" scoped>
    .jf-grid-pager{
        margin-top:6px;
        margin-bottom:6px;
    }
    .jf-grid-search{
        display: inline-block;
        min-width:600px;
    }
    .jf-grid-search-input{
        max-width:320px;
        display:inline-table;
    }
    .jf-grid-search-input .ivu-select{
        width:auto;
    }
    .jf-grid-advanced-filter-form{
        background-color: #fff;
        margin-bottom: 12px;
        box-shadow: 0 1px 5px rgba(0,0,0,.15);
        border-color: transparent;
        border-radius: 4px;
        border: 1px solid #E5F4F3;padding-top: 10px
    }
    .jf-grid-advanced-filter-form .ivu-form-item{
        margin-bottom: 10px;
    }
</style>