<template>
    <Layout class="jf-layout-full">
        <Sider class="jf-layout-sider" :width="280">
            <Spin size="large" fix v-if="loading"></Spin>
            <JFOrgTree ref="orgTree" @onSelectChange="onSelectChange" @loadCallback="loadCallback"/>
        </Sider>
        <Content class="jf-layout-content">
            <div class="margin-bottom-12">
                <Button icon="md-add" type="primary" @click="addOrganization()" v-permission="'organization$save'">
                    {{$t('addOrganization')}}
                </Button>
                <Button  icon="md-refresh" :loading="loading" @click="loadOrganization()" v-permission="'organization$tree'">
                    {{$t('refresh')}}
                </Button>
                <Button type="error" icon="md-trash" @click="deleteOrganization()" v-permission="'organization$delete'" :disabled="!data.orgId">
                    {{$t('delete')}}
                </Button>
                <!--保存组织-->
                <Button icon="md-checkmark" :loading="saveLoading" type="primary" @click="saveOrganization()" class="float-right" >
                    {{$t('save')}}
                </Button>
            </div>
            <Form ref='formOrganization' :model="data" :rules="formRules"  label-position="top">
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
                        <FormItem :label="$t('orgType')" prop="orgType">
                            <Select v-model="data.orgType">
                                <Option :value="1">{{$t('type.orgType.1')}}</Option>
                                <Option :value="2">{{$t('type.orgType.2')}}</Option>
                                <Option :value="3">{{$t('type.orgType.3')}}</Option>
                                <Option :value="4">{{$t('type.orgType.4')}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('orgParent')" prop="parentId">
                            <JFOrgCascader v-model="data.parentId"/>
                        </FormItem>
                    </Col>
                </Row>
                
                <FormItem :label="$t('orgDesc')" prop="orgDesc">
                    <Input v-model="data.orgDesc" type="textarea" :rows="4" :maxlength="200" show-word-limit/>
                </FormItem>
                <template v-if="data.orgId">
                    <Row :gutter="32">
                        <Col :span="12">
                            <FormItem :label="$t('createBy')" class="jf-detail-item">
                                <JFUser :userId="data.createBy"/>
                            </FormItem>
                        </Col>
                        <Col :span="12">
                            <FormItem :label="$t('createDate')" class="jf-detail-item">
                                {{data.createDate}}
                            </FormItem>
                        </Col>
                    </Row>
                    <Row :gutter="32">
                        <Col :span="12">
                            <FormItem :label="$t('lastUpdateBy')" class="jf-detail-item">
                                <JFUser :userId="data.lastUpdateBy"/>
                            </FormItem>
                        </Col>
                        <Col :span="12">
                            <FormItem :label="$t('lastUpdateDate')" class="jf-detail-item">
                                {{data.lastUpdateDate}}
                            </FormItem>
                        </Col>
                    </Row>
                </template>
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
    methods:{
        loadOrganization(){
            this.$refs.orgTree.reload(true);
        },
        loadCallback(treeDatas){
            let vm=this;
            vm.data={};
            vm.treeData=treeDatas;
            if(vm.$refs.formOrganization){
                vm.$refs.formOrganization.resetFields();
            }
        },
        onSelectChange(nodes){
            let vm=this;
            if(nodes && nodes.length>0){
                if(vm.$refs.formOrganization){
                    vm.$refs.formOrganization.resetFields();
                }
                vm.data=Object.assign({},nodes[0]);
                // let parentIds=vm.data.parentIds;
                // vm.$set(vm.data,'parentId',parentIds || []);
                vm.$util.dispatchUser(vm,vm.data);
            }
        },
        addOrganization(){
            let vm=this;
            var data=vm.data;
            if(data.orgId){
                vm.data={parentId:data.orgId};
            }else{
                vm.data={};
            }
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
        /**
         * 删除组织架构
         */
        deleteOrganization(){
            let vm=this;
            let selected=this.$refs.orgTree.getSelectedNodes();
            if(selected.length>0){
                //存在子级不能删除
                if(selected[0].children && selected[0].children.length>0){
                    vm.$Message.warning(vm.$t('deleteNodeWarning'));
                    return;
                }
                vm.$http.delete('jfcloud/jf-cloud-platform/config/organization/delete',{
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