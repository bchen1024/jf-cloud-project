<template>
    <Layout class="jf-layout-full">
        <Sider class="jf-layout-sider" :width="280">
            <Spin size="large" fix v-if="loading"></Spin>
            <Tree ref="organizationTree" :empty-text="$t('noOrganizationData')" 
                :render="renderContent" :data="treeData" 
                @on-select-change="onSelectChange"></Tree>
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
                <FormItem :label="$t('orgParent')" prop="parentId">
                    <Cascader :data="treeData" v-model="data.parentId" change-on-select></Cascader>
                </FormItem>
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
    created(){
        this.loadOrganization();
    },
    methods:{
        loadOrganization(){
            let vm=this;
            vm.loading=true;
            vm.data={};
            if(vm.$refs.formOrganization){
                vm.$refs.formOrganization.resetFields();
            }
            vm.$http.post('jfcloud/jf-cloud-platform/config/organization/tree',{}).then(result=>{
                vm.treeData=result.data;
            }).catch(error=>{}).then(()=>{
                vm.loading=false;
            });
        },
        onSelectChange(nodes){
            let vm=this;
            if(nodes && nodes.length>0){
                if(vm.$refs.formOrganization){
                    vm.$refs.formOrganization.resetFields();
                }
                
                vm.data=Object.assign({},nodes[0]);
                let parentIds=nodes[0].__value;
                console.info(parentIds);
                let parentId=[];
                (parentIds?parentIds.split(','):[]).forEach(val => {
                    parentId.push(parseInt(val));
                });
                parentId.pop()
                vm.$set(vm.data,'parentId',parentId);
                vm.$util.dispatchUser(vm,vm.data);
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
            return h('span', [
                h('Icon', {
                    props: {type: icon,size:16},
                    style: {marginRight: '8px'}
                }),
                h('span',{class:'ivu-tree-title ' + (node.node.selected ?'ivu-tree-title-selected':''),
                    on: {
                        click: () => {
                            if (!node.node.selected){
                                this.$refs.organizationTree.handleSelect(node.nodeKey); //手动选择树节点
                            } 
                        }
                    }
                }, data.title)
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