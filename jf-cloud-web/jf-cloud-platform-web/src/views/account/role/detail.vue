<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" name="detail">
            <Spin size="large" fix v-if="loading"></Spin>
            <Form :model="roleData" label-position="right" :label-width="120" class="jf-detail-form">
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem :label="$t('roleCode')">
                            {{roleData.roleCode}}
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem :label="$t('roleName')">
                            {{roleData.roleName}}
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem :label="$t('roleOwner')">
                            <JFUser :userId="roleData.roleOwner"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem :label="$t('applyStatus')">
                            <JFStatus :value="roleData.applyStatus" type="applyStatus"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem :label="$t('createBy')">
                            <JFUser :userId="roleData.createBy"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem :label="$t('createDate')">
                            {{roleData.createDate}}
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col span="12">
                        <FormItem :label="$t('lastUpdateBy')">
                            <JFUser :userId="roleData.lastUpdateBy"/>
                        </FormItem>
                    </Col>
                    <Col span="12">
                        <FormItem :label="$t('lastUpdateDate')">
                            {{roleData.lastUpdateDate}}
                        </FormItem>
                    </Col>
                </Row>
                <FormItem :label="$t('roleDesc')" class="jf-form-detail-desc">
                    {{roleData.roleDesc}}
                </FormItem>
                <div style="text-align:right;">
                    <Button icon="md-create"  @click="openEdit(roleData)">
                        {{$t('edit')}}
                    </Button>
                    <Button icon="md-refresh"  @click="loadDetail(true)">
                        {{$t('refresh')}}
                    </Button>
                </div>
            </Form>
            <EditRole :formId="formId" :formKey="formKey"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail(true)"/>
        </TabPane>
        <TabPane :label="$t('roleUsers')" name="roleUsers">标签二的内容</TabPane>
        <TabPane :label="$t('rolePermission')" name="rolePermission">标签三的内容</TabPane>
        <TabPane :label="$t('roleGroups')" name="roleGroups">标签三的内容</TabPane>
    </Tabs>
</template>
<script>
import EditRole from './edit.vue';
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    components:{
        EditRole
    },
    data(){
        return {
            formKey:'roleId',
            tabId:'detail',
            id:null,
            loadMap:{},
            roleData:{},
            loading:false
        }
    },
    created(){
        let vm=this,query=vm.$route.query;
        vm.id=query.id;
        if(query.tabId){
            vm.tabId=query.tabId;
        }
        vm.load();
    },
    methods:{
        load(name){
            let vm=this;
            if(name){
                vm.tabId=name;
            }
            if(vm.tabId=='detail'){
                vm.loadDetail();
            }
        },
        loadDetail(reload){
            let vm=this;
            if(vm.id && (!vm.loadMap['detail'] || reload)){
                vm.loading=true;
                vm.$http({
                    method:'get',
                    url:'jfcloud/jf-cloud-platform/security/role/single',
                    params:{appCode:vm.$store.state.app.appInfo.appCode,id:vm.id}
                }).then(result=>{
                    vm.loading=false;
                    vm.loadMap['detail']=true;
                    if(result && result.success){
                        vm.roleData=result.data;
                        let userIds=[vm.roleData.roleOwner,vm.roleData.createBy,vm.roleData.lastUpdateBy];
                        vm.$store.dispatch('loadUser',userIds);
                    }
                    
                });
            }
        }
    }
}
</script>