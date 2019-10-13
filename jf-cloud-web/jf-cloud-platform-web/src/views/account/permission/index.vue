<template>
    <Layout :style="{height:'100%'}">
        <Sider width="300" style="background:#ffffff;padding:4px;border-right: 1px solid #e8eaec;overflow: auto;">
            <Spin size="large" fix v-if="loading"></Spin>
            <Tree ref="permissionTree" :empty-text="$t('noPermissionData')" :render="renderContent" :data="treeData" @on-select-change="onSelectChange"></Tree>
        </Sider>
        <Content :style="{background: '#fff',padding:'12px'}">
            <div style="margin-bottom: 12px;">
                <Button icon="md-add" type="primary" @click="addPermission()" v-permission="'permission$save'">
                    {{$t('addPermission')}}
                </Button>
                <Tooltip :content="$t('syncPermissionTip')" transfer v-permission="'permission$sync'">
                    <Button icon="md-sync" @click="syncPermission()">
                        {{$t('syncPermission')}}
                    </Button>
                </Tooltip>
                <Button  icon="md-refresh" @click="loadPermission()">
                    {{$t('refresh')}}
                </Button>
                <Button type="error" icon="md-trash" @click="deletePermission()" :disabled="(data.permissionSource=='annotation' && data.enableFlag=='Y') || !data.permissionId">
                    {{$t('delete')}}
                </Button>
            </div>
            <Form ref='formPermission' :model="data" :rules="formRules" label-position="top">
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('permissionType')"  prop="permissionType">
                            <Select :disabled="data.permissionSource=='annotation'" v-model="data.permissionType" @on-change="onChange">
                                <Option value="resources">{{$t('resources')}}</Option>
                                <Option value="method">{{$t('method')}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                    <FormItem :label="$t('permissionCode')" prop="permissionCode">
                            <Input :disabled="data.permissionSource=='annotation'" v-model="data.permissionCode" />
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('permissionDescCn')" prop="permissionDescCn">
                            <Input :disabled="data.permissionSource=='annotation'" v-model="data.permissionDescCn" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                    <FormItem :label="$t('permissionDescEn')" prop="permissionDescEn">
                            <Input :disabled="data.permissionSource=='annotation'" v-model="data.permissionDescEn" />
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('parentCode')" prop="parentCode">
                            <Select placeholder="" :disabled="data.permissionSource=='annotation' || data.permissionType=='resources'" v-model="data.parentCode" >
                                <Option v-for="item in parentCodes" :value="item.value" :key="item.value">{{item.label}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('permissionSource')" prop="permissionSource">
                            <Select disabled v-model="data.permissionSource">
                                <Option value="annotation">{{$t('annotation')}}</Option>
                                <Option value="custom">{{$t('custom')}}</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32">
                    <Col :span="12">
                        <FormItem :label="$t('permissionSort')" prop="permissionSort">
                            <Input :disabled="data.permissionSource=='annotation'" v-model="data.permissionSort" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('permissionParentCode')" class="jf-detail-item">
                            {{(data.permissionId && data.permissionType=='method')?data.parentCode+'$'+data.permissionCode:''}}
                        </FormItem>
                    </Col>
                </Row>
                <template v-if="data.permissionId">
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
                <div style="text-align:right;" v-if="data.permissionSource!='annotation'" v-permission="'permission$save'">
                    <Button icon="md-checkmark" :loading="saveLoading" type="primary" @click="savePermission()">
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
            data:{permissionSource:'custom'},
            formRules:{
                permissionType:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionCode:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionDescCn:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionDescEn:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ],
                permissionSource:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ]
            },
            parentCodes:[]
        }
    },
    created(){
        this.loadPermission();
    },
    methods:{
        initForm(){
            this.data={permissionSource:'custom'};
        },
        loadPermission(){
            let vm=this;
            vm.loading=true;
            if(vm.$refs.formPermission){
                vm.$refs.formPermission.resetFields();
            }
            vm.initForm();
            vm.$http({
                method:'post',
                url:'jfcloud/jf-cloud-platform/security/permission/tree',
                data:{appCode:vm.$store.state.app.appInfo.appCode}
            }).then(result=>{
                vm.loading=false;
                if(result && result.success){
                    vm.treeData=result.data;
                    let parentCodes=[];
                    vm.loadParentCode(vm.treeData,parentCodes);
                    vm.parentCodes=parentCodes;
                }
                
            }).catch(error=>{
                vm.loading=false;
            });
        },
        loadParentCode(list,parentCodes){
            let vm=this;
            list.forEach(item=>{
                if(item.permissionType=='resources'){
                    parentCodes.push({value:item.permissionCode,label:item.title});
                }
                if(item.children && item.children.length>0){
                    vm.loadParentCode(item.children,parentCodes);
                }
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
                let userIds=[vm.data['createBy'],vm.data['lastUpdateBy']];
                vm.$store.dispatch('loadUser',userIds);
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
        addPermission(){
            let vm=this;
            let permissionType=vm.data.permissionType;
            let parentCode=vm.data.permissionCode;
            vm.$refs.formPermission.resetFields();
            let data={permissionSource:'custom'};
            if(vm.data){
                if(permissionType=='resources'){
                    data.parentCode=parentCode;
                    data.permissionType='method';
                }
            }
            vm.data=data;
        },
        savePermission(){
            let vm=this;
            this.$refs.formPermission.validate((valid) => {
                if (valid) {
                    vm.saveLoading=true;
                    vm.data.appCode=vm.$store.state.app.appInfo.appCode;
                    vm.$http({
                        method:'put',
                        url:'jfcloud/jf-cloud-platform/security/permission/save',
                        data:vm.data
                    }).then(result=>{
                        vm.saveLoading=false;
                        if(result && result.success){
                            vm.$Message.success(vm.$t('saveSuccessful'));
                            vm.loadPermission();
                        }
                        
                    }).catch(error=>{
                        vm.saveLoading=false;
                        vm.$Message.error(vm.$util.handerError(error,vm));
                    });
                }
            });
            
        },
        syncPermission(){
            let vm=this;
            vm.$Message.loading({
                content: this.$t('synching'),
                duration: 0
            });
            let appInfo=vm.$store.state.app.appInfo;
            vm.$http({
                method:'post',
                url:'jfcloud/jf-cloud-platform/security/permission/sync',
                data:{appCode:appInfo.appCode,contextPath:appInfo.contextPath}
            }).then(result=>{
                this.$Message.destroy();
                //成功
                if(result && result.success){
                    vm.$Message.success(vm.$t('syncSuccessful'));
                    vm.loadPermission();
                }
            }).catch(error=>{
                vm.$Message.destroy();
                vm.$Message.error(vm.$util.handerError(error,vm));
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
                h('Badge',{
                    props:{
                        dot:dot
                    }
                },[
                    h('span',{style:{
                        textDecoration:invalid?'line-through':'none',
                        cursor: 'pointer'
                    },class:'ivu-tree-title ' + (node.node.selected ?'ivu-tree-title-selected':''),
                        on: {
                            click: () => {
                                if (!node.node.selected){
                                    this.$refs.permissionTree.handleSelect(node.nodeKey); //手动选择树节点
                                } 
                            }
                        }
                    }, data.title)
                ])
            ]);
        },
        deletePermission(){
            let vm=this;
            let selected=this.$refs.permissionTree.getSelectedNodes();
            if(selected.length>0){
                vm.$Message.loading({
                    content: this.$t('deleteing'),
                    duration: 0
                });
                let appInfo=vm.$store.state.app.appInfo;
                vm.$http({
                    method:'delete',
                    url:'jfcloud/jf-cloud-platform/security/permission/delete',
                    data:{appCode:appInfo.appCode,id:selected[0].permissionId}
                }).then(result=>{
                    this.$Message.destroy();
                    //成功
                    if(result && result.success){
                        vm.$Message.success(vm.$t('deleteSuccessful'));
                        vm.loadPermission();
                    }
                }).catch(error=>{
                    vm.$Message.destroy();
                    vm.$Message.error(vm.$util.handerError(error,vm));
                });
            }
            console.info(JSON.stringify(selected));
        }
    }
}
</script>