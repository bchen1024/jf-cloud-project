<template>
    <div>
        <div class="jf-margin-bottom12">
            <Form v-if="advancedQueryFields && advancedQueryFields.length>0"  inline :model="searchOp.queryParams" 
                :label-width="120" v-show="(queryKeywordFields.length==0) || advancedFilterShow" class="jf-grid-advanced-filter-form">
                <FormItem v-for="item in advancedQueryFields" :label-width="item.labelWidth || 120" :label="$t(item.title) || $t(item.key)" :key="item.key" :prop="item.key">
                    <template v-if="item.type=='radio'">
                        <RadioGroup v-model="searchOp.queryParams[item.key]">
                            <Radio   label="">{{$t('all')}}</Radio>
                            <Radio v-for="radio in item.items" :key="radio.value"  :label="radio.value">{{radio.label}}</Radio>
                        </RadioGroup>
                    </template>
                    <template v-else-if="item.type=='datePicker'">
                        <DatePicker :type="item.dateType || 'datetimerange'" :format="item.format || 'yyyy-MM-dd HH:mm:ss'" 
                            v-model="searchOp.queryParams[item.key]" :style="{'width':(item.width || 320)+'px'}"
                            :placeholder="$t('selectDateTimeRange')" @on-change="searchOp.queryParams[item.key]=$event" >
                        </DatePicker>
                    </template>
                    <template v-else-if="item.type=='userSelect'">
                        <JFUserSelect v-model="searchOp.queryParams[item.key]"/>
                    </template>
                    <template v-else>
                        <Input v-model="searchOp.queryParams[item.key]" style="width: 150px;"/>
                    </template>
                </FormItem>
                <Button v-if="queryKeywordFields.length==0 && advancedQueryFields.length>0" type="primary" icon="ios-search" @click="search()" :loading="loading">{{$t('search')}}</Button>
            </Form>
            <div  class="jf-grid-search">
                <div>
                    <!--关键字搜索框-->
                    <Input v-if="queryKeywordFields.length>0"
                        autofocus clearable class="jf-grid-search-input"
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
                    <Button v-if="queryKeywordFields.length>0" type="primary" icon="ios-search" @click="search()" :loading="loading">{{$t('search')}}</Button>
                    <!--高级筛选，存在关键字筛选框和高级筛选条件才显示-->
                    <Badge :count="advancedFilterCount" v-if="queryKeywordFields.length>0 && advancedQueryFields.length>0">
                        <Button @click="advancedFilterShow=!advancedFilterShow" :disabled="loading" >
                            {{$t('advancedFilter')}}
                            <Icon :type="advancedFilterShow?'ios-arrow-down':'ios-arrow-up'"></Icon>
                        </Button>
                    </Badge>
                </div>
                <div class="jf-search-toolbar-right">
                    <slot name="grid-search-toolbar"></slot>
                    <Button v-permission="'auditLog$page'" v-if="tableOp.logSetting && tableOp.logSetting.module" @click="viewLog">
                        {{$t('operationLog')}}
                    </Button>
                    <Tooltip v-if="tableOp.allowSetting" :content="$t('tableSetting')" placement="bottom">
                        <Icon type="ios-settings-outline" size="20" style="cursor: pointer;"/>
                    </Tooltip>
                </div>
            </div>
        </div>
        <!--操作按钮区-->
        <div style="jf-margin-bottom12">
            <slot name="grid-toolbar"></slot>
        </div>
        <!--表格-->
        <Table stripe
            :columns="columns" :data="data||[]"  
            :loading="loading"
            :no-data-text="tableNoDataText"
            :max-height="540"
        >
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
        <slot name="grid-drawer"></slot>
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
                allowSetting:false,
                //是否显示tip提示
                showTip:true,
                //文本超长是否显示省略号
                ellipsis:true,
                //允许调整列宽
                resizable:true,
                //是否显示序号列
                showIndex:true,
                //是否多选
                showSelection:false,
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
                titleI18n:true,
                //是否显示操作日志按钮
                logSetting:{}
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
                        if(!column.width && !column.minWidth){
                            column.tooltip=true;
                            column.minWidth=200;
                        }
                        if(column.format=='user'){
                            column.render=(h,params)=>{
                                return h('JFUser',{props:{userId:params.row[column.key]}});
                            }
                        }else if(column.format=='status'){
                            column.render=(h,params)=>{
                                let value=params.row[column.key];
                                return h('JFStatus',{props:{value:value,type:column.key}});
                            }
                        }else if(column.format=='type'){
                            column.render=(h,params)=>{
                                let value=params.row[column.key];
                                return h('JFType',{props:{value:value,type:column.key}});
                            }
                        }
                        columns.push(column);
                    }
                });
            }

            //显示审计列
            if(tableOp.showAuditCreate){
                columns.push(
                    {key:'createBy',title:vm.$t('createBy'),width:150,format:'user',render:(h,params)=>{
                         return h('JFUser',{props:{userId:params.row['createBy']}});
                    }},
                    {key:'createDate',title:vm.$t('createDate'),width:180,align: 'center'}
                );
            }
            if(tableOp.showAuditUpdate){
                columns.push(
                    {key:'lastUpdateBy',title:vm.$t('lastUpdateBy'),width:150,format:'user',render:(h,params)=>{
                         return h('JFUser',{props:{userId:params.row['lastUpdateBy']}});
                    }},
                    {key:'lastUpdateDate',title:vm.$t('lastUpdateDate'),width:180,align: 'center'}
                );
            }
            if(tableOp.buttons && tableOp.buttons.length>0){
                let btns=[];
                (tableOp.buttons || []).forEach(btn=>{
                    //校验权限
                    if(vm.$util.checkPermission(btn.permissionCode,vm)){
                        btns.push(btn);
                    }            
                });
                if(btns.length>0){
                     columns.push(
                        {key:'operation',title:vm.$t('operation'),width:110,align:'center',fixed:'right',render: (h, params) => {
                            let menuItems=[];
                            btns.forEach(btn=>{
                                if(btn.show && !btn.show(params.row)){
                                    return false;
                                }
                                let title;
                                if(typeof btn.title =='function'){
                                    title=btn.title(params.row);
                                }else{
                                    title=btn.title;
                                }
                                menuItems.push(h('DropdownItem',{nativeOn:{
                                    click:(name)=>{
                                        if(btn.gridDelete){
                                            vm.gridDelete(params.row);
                                        }else if(btn.gridDetail){
                                            vm.gridDetail(btn.gridDetail.tabId,params.row);
                                        }else if(btn.router){
                                            vm.gridRouter(btn.router,params.row);
                                        }else if(btn.click){
                                            btn.click(params);
                                        }
                                        
                                    }
                                }},title));
                            })
                            if(menuItems.length>0){
                                return h('Dropdown',{props:{transfer:true}}, [
                                    h('Icon',{props:{type:'ios-more',size:20}}),
                                    h('DropdownMenu',{slot:'list'},menuItems)
                                ]);
                            }
                            return '';
                        }}
                    );
                }
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
         * 删除配置
         */
        deleteOp(){
            var vm=this,gridOp=vm.gridOp || {};
            //默认的查询配置
            var defaultOp={
                //请求方法
                method:"delete",
                confirmMsg:vm.$t('deleteConfirm')
            };
            var deleteOp=Object.assign({},defaultOp,gridOp.delete);
            return deleteOp;
        },
        /**
         * 关键字查询字段
         */
        queryKeywordFields(){
            var vm=this,tableOp=vm.tableOp || {},searchOp=vm.searchOp || {};
            //查询
            var fields=[];
            (vm.columns || []).forEach(field => {
                let condition=field.condition;
                if(condition && (typeof condition=='boolean' || condition.type=='string')){
                    if(condition.key){
                        fields.push({key:condition.key});
                    }else{
                        fields.push({key:field.key});
                    } 
                }
            });
            if(searchOp.queryKeywordFields && searchOp.queryKeywordFields.length>0){
                fields=fields.concat(searchOp.queryKeywordFields);
            }
            if(fields.length>0 && !searchOp.queryParams.keywordField){
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
                if(field.condition && typeof field.condition=='object' && field.condition.type!='string'){
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
                //如果高级筛选区隐藏，计算存在高级筛选条件个数
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
        /**
         * 如果加载错误，红色标记错误信息
         */
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
            vm.$emit('seachBefore',params);
            vm.loading=true;
            vm.errorMsg=null;
             vm.data=[];
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
                    vm._loadUserMap();
                }
            }).catch(error=>{
                vm.loadError=true;
                vm.loading=false;
                vm.errorMsg=vm.$util.handerError(error,vm);
            });
        },
        _loadUserMap(){
            let vm=this;
            let userColumns=[];
            vm.columns.forEach(v=>{
                if(!v.hidden && v.format=='user'){
                    userColumns.push(v.key);
                }
            });
            let userIds=[];
            (vm.data || []).forEach(rowData=>{
                userColumns.forEach(col=>{
                    if(rowData[col] && !userIds.includes(rowData[col])){
                        userIds.push(rowData[col]);
                    }
                });
            });
            if(userIds.length>0){
                vm.$store.dispatch('loadUser',userIds);
            }
            
        },
        /**
         * 表格数据删除方法
         */
        gridDelete(data){
            let deleteOp=this.deleteOp;
            this.$Modal.confirm({
                title: this.$t('confirm'),
                content: deleteOp.confirmMsg,
                onOk: () => {
                    this.$Message.loading({
                        content: this.$t('deleteing'),
                        duration: 0
                    });
                    let deleteOption={
                        method:deleteOp.method,
                        url:deleteOp.url,
                        data:Object.assign({},deleteOp.defaultParams,{id:data[this.tableOp.pkId]})
                    };
                    this.$http(deleteOption).then(result=>{
                        this.$Message.destroy();
                        //成功
                        if(result && result.success){
                            this.$Message.success(this.$t('deleteSuccessful'));
                            this.search();
                        }
                    }).catch(error=>{
                        this.$Message.destroy();
                        this.$Message.error(vm.$util.handerError(error,vm));
                    });
                }
            });
        },
        /**
         * 详情查看
         */
        gridDetail(tabId,row){
            let vm=this,tableOp=vm.tableOp;
            if(tableOp.detail){
                let routeData = vm.$router.resolve({
                    name: tableOp.detail.name,
                    query: {
                        id:row[tableOp.pkId],
                        tabId:tabId
                    }
                });
                window.open(routeData.href, '_blank');
            }
        },
        /**
         * 路由跳转
         */
        gridRouter(router,row){
            let vm=this;
            let query={};
            if(router.query){
                query=router.query(row);
            }
            let routeData = vm.$router.resolve({
                name: router.name,
                query:query
            });
            window.open(routeData.href, '_blank');
        },
        /**
         * 查看操作日志
         */
        viewLog(){
            let vm=this,tableOp=vm.tableOp;
            if(tableOp.logSetting && tableOp.logSetting.module){
                let routeData = vm.$router.resolve({
                    name: "auditLog",
                    query: {
                        logModule:tableOp.logSetting.module
                    }
                });
                window.open(routeData.href, '_blank');
            }
        }
    },
    created(){
        //默认显示高级筛选区域
        if(this.tableOp.showAdvancedFilter){
            this.advancedFilterShow=true;
        }
        //字段加载数据
        if(this.searchOp.autoLoad){
            this.search();
        }
    }
}
</script>
<style lang="less">
    .jf-margin-bottom12{
        margin-bottom: 12px;
    }
    .jf-grid-pager{
        margin-top:6px;
        margin-bottom:6px;
    }
    .jf-grid-search{
        display: flex;
    }
    .jf-grid-search-input{
        width:auto;
        display:inline-table;
    }
    .jf-grid-search-input .ivu-select{
        width:auto;
    }
    .jf-search-toolbar-right{
        flex:1;
        text-align: right;
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
    .jf-grid-advanced-filter-form .ivu-form-item-label{
        font-weight: bold;
    }
</style>