<template>
    <Form v-if="items && items.length>0" :model="formData" label-position="right" :label-width="120" class="jf-detail-form">
        <Spin size="large" fix v-if="loading"></Spin>
        <div style="text-align:right;margin-bottom:12px;">
            <Button icon="md-create" v-if="detailOp.allowEdit" @click="edit()">
                {{$t('edit')}}
            </Button>
            <Button icon="md-refresh" v-if="detailOp.allowReresh" @click="load()">
                {{$t('refresh')}}
            </Button>
        </div>
        <div v-if="errorMsg" style="text-align:center;color:red;margin-bottom:12px;">
            {{errorMsg}}
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
                allowEdit:true,
                allowReresh:true,
                autoLoad:false,
                showAudit:true,
                items:[]
            };
            return Object.assign({},defaultOp,op);
        },
        items(){
            let vm=this,op=vm.detailOp;
            let items=op.items || [];
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
            loading:false,
            errorMsg:null
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
                vm.errorMsg=null;
                vm.$http({
                    method:searchOp.method || 'get',
                    url:searchOp.url,
                    params:{appCode:vm.$store.state.app.appInfo.appCode,id:vm.id}
                }).then(result=>{
                    vm.loading=false;
                    if(result && result.success){
                        vm.formData=result.data;
                        let userIds=[vm.formData['createBy'],vm.formData['lastUpdateBy']];
                        (vm.op.userFields || []).forEach(item => {
                            userIds.push(vm.formData[item]);
                        });
                        vm.$store.dispatch('loadUser',userIds);
                        vm.$emit("loadCallback",vm.formData);
                    }
                    
                }).catch(error=>{
                    vm.loading=false;
                    vm.errorMsg=vm.$util.handerError(error,vm);
                });
            }
        },
        edit(){
            this.$emit("detailEdit",Object.assign({},this.formData));
        }
    }
}
</script>