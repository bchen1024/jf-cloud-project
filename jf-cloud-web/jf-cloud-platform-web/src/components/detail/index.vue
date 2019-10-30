<template>
    <Form v-if="items && items.length>0" :model="formData" label-position="right" :label-width="150" class="jf-detail-form">
        <!--loading-->
        <Spin size="large" fix v-if="loading"></Spin>
        <div style="text-align:right;margin-bottom:12px;">
            <Button icon="md-create" v-if="detailOp.allowEdit" @click="edit()" v-permission="op.editPermission">
                {{$t('edit')}}
            </Button>
            <Button icon="md-refresh" v-if="detailOp.allowReresh" @click="load()">
                {{$t('refresh')}}
            </Button>
        </div>
        <template v-for="(item,index) in items">
            <template v-if="item.cols && item.cols.length>0">
                <Row :gutter="32" :key="'row-'+index">
                    <Col v-for="col in item.cols" :span="24/item.cols.length" :key="'col-'+col.key">
                        <DetailItem :item="col" :formData="formData" :key="'item-'+col.key"/>
                    </Col>
                </Row>
            </template>
            <DetailItem v-else :item="item" :formData="formData" :key="'item-'+index"/>
        </template>
    </Form>
</template>
<script>
import DetailItem from './item.vue';
export default {
    name:'JFDetail',
    components:{
        DetailItem
    },
    props:{
        op:{
            type:Object,
            default:{}
        },
        id:{
            type:[Number,String]
        }
    },
    computed:{
        detailOp(){
            let vm=this,op=vm.op;
            let defaultOp={
                //显示编辑按钮
                allowEdit:true,
                //显示刷新按钮
                allowReresh:true,
                //自动加载数据
                autoLoad:false,
                //显示审计字段
                showAudit:true,
                items:[]
            };
            return Object.assign({},defaultOp,op);
        },
        items(){
            let vm=this,op=vm.detailOp,items=op.items || [];
            if(op.showAudit){
                items=items.concat([
                    {cols:[
                        {key:'createBy',type:'user'},
                        {key:'createDate'}
                    ]},
                    {cols:[
                        {key:'lastUpdateBy',type:'user'},
                        {key:'lastUpdateDate'}
                    ]}
                ])
            }
            return items;
        }
    },
    data(){
        return {
            formData:{},
            loading:false
        }
    },
    created(){
        if(this.detailOp.autoLoad){
            this.load();
        }
    },
    methods:{
        load(){
            let vm=this,op=vm.detailOp||{},searchOp=op.search;
            if(searchOp){
                vm.loading=true;
                vm.formData={};
                let config={
                    method:searchOp.method || 'get',
                    url:searchOp.url
                };
                if(config.method=='get'){
                    config.params={id:vm.id};
                }else{
                    config.data={id:vm.id};
                }
                vm.$http(config).then(result=>{
                    vm.formData=result.data || {};
                    vm.$util.dispatchUser(vm,vm.formData,vm.op.userFields);
                    vm.$emit("loadCallback",vm.formData);
                }).then(()=>{
                    vm.loading=false;
                });
            }
        },
        edit(){
            this.$emit("detailEdit",Object.assign({},this.formData));
        }
    }
}
</script>