<template>
    <Layout :style="{height:'100%'}">
        <Sider style="background:#ffffff;padding:4px;border-right: 1px solid #e8eaec;overflow: auto;width:auto;min-width:200px">
            <Spin size="large" fix v-if="loading"></Spin>
            <Tree ref="organizationTree" :empty-text="$t('noOrganizationData')" :render="renderContent" :data="treeData" @on-select-change="onSelectChange"></Tree>
        </Sider>
        <Content :style="{background: '#fff',padding:'12px'}">
            <div style="margin-bottom: 12px;">
                <Button icon="md-add" type="primary" @click="addOrganization()" v-permission="'organization$save'">
                    {{$t('addOrganization')}}
                </Button>
                <Button  icon="md-refresh" @click="loadOrganization()">
                    {{$t('refresh')}}
                </Button>
                <Button type="error" icon="md-trash" @click="deleteOrganization()" v-permission="'organization$delete'" :disabled="!data.orgId">
                    {{$t('delete')}}
                </Button>
            </div>
            <Form ref='formOrganization' :model="data" :rules="formRules" label-position="top">
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('orgCn')" prop="orgCn">
                            <Input  v-model="data.orgCn" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('orgEn')" prop="orgEn">
                            <Input v-model="data.orgEn" />
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('parentId')" prop="parentId">
                            <Input v-model="data.parentId" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('orgDesc')" prop="orgDesc">
                            <Input v-model="data.orgDesc" />
                        </FormItem>
                    </Col>
                </Row>

                <div style="text-align:right;" v-permission="'organization$save'">
                    <Button icon="md-checkmark" :loading="saveLoading" type="primary" @click="saveOrganization()">
                        {{$t('save')}}
                    </Button>
                </div>
            </Form>
        </Content>
    </Layout>
</template>
<script>
export default {
    data(){
        let vm=this;
        return {
            loading:false,
            saveLoading:false,
            treeData:[],
            data:{},
            formRules:{
                orgCn:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                orgEn:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ]
            }
        }
    },
    created(){
        this.loadOrganization();
    },
    methods:{
        loadOrganization(){
            let vm=this;
            vm.loading=true;
            if(vm.$refs.formOrganization){
                vm.$refs.formOrganization.resetFields();
            }
            vm.$http({
                method:'post',
                url:'jfcloud/jf-cloud-platform/config/organization/tree',
                data:{}
            }).then(result=>{
                vm.loading=false;
                if(result && result.success){
                    vm.treeData=result.data;
                }
                
            }).catch(error=>{
                vm.loading=false;
            });
        },
        onSelectChange(nodes){
            let vm=this;
            if(nodes && nodes.length>0){
                vm.data=Object.assign({},nodes[0]);
                if(vm.data.permissionType=='resources'){
                    vm.$set(vm.formRules,'parentCode',[]);
                }else{
                    vm.$set(vm.formRules,'parentCode',[{required:true,message:vm.$t('validator.notEmpty')}]);
                }
                vm.$util.dispatchUser(vm,vm.data);
            }
        },
        onChange(value){
            let vm=this;
            if(value=='resources'){
                vm.data.parentCode='';
                vm.$set(vm.formRules,'parentCode',[]);
            }else{
                vm.$set(vm.formRules,'parentCode',[{required:true,message:vm.$t('validator.notEmpty')}]);
            }
        },
        addOrganization(){
            
        },
        saveOrganization(){
            let vm=this;
            this.$refs.formOrganization.validate((valid) => {
                if (valid) {
                    vm.saveLoading=true;
                    vm.$http({
                        method:'put',
                        url:'jfcloud/jf-cloud-platform/config/organization/save',
                        data:vm.data
                    }).then(result=>{
                        vm.saveLoading=false;
                        if(result && result.success){
                            vm.$Message.success(vm.$t('saveSuccessful'));
                            vm.loadOrganization();
                        }
                        
                    }).catch(error=>{
                        vm.saveLoading=false;
                    });
                }
            });
            
        },
        renderContent (h, { root, node, data }) {
            let icon='ios-paper-outline';
            if(data.children && data.children.length>0){
                icon='ios-folder-outline';
            }
            let invalid=false;
            if(data.enableFlag!='Y'){
                invalid=true;
            }
            let dot=false;
            if(data.createDate){
                if(new Date()-new Date(data.createDate)<1000*3600)
                dot=true;
            }
            return h('span', [
                h('Icon', {
                    props: {
                        type: icon,
                        size:16
                    },
                    style: {
                        marginRight: '8px'
                    }
                }),
                h('span',{class:'ivu-tree-title ' + (node.node.selected ?'ivu-tree-title-selected':'')}, data.title)
            ]);
        },
        /**
         * 删除组织架构
         */
        deleteOrganization(){
            let vm=this;
            let selected=this.$refs.permissionTree.getSelectedNodes();
            if(selected.length>0){
                //存在子级不能删除
                if(selected[0].children && selected[0].children.length>0){
                    vm.$Message.warning(vm.$t('deleteOrgWarning'));
                    return;
                }
                vm.$http({
                    method:'delete',
                    url:'jfcloud/jf-cloud-platform/security/organization/delete',
                    data:{id:selected[0].orgId},
                    headers:{op:'delete'}
                }).then(result=>{
                    vm.loadOrganization();
                });
            }
        }
    }
}
</script>