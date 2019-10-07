<template>
    <Layout :style="{height:'100%'}">
        <Sider width="240" style="background:#ffffff;padding:4px;border-right: 1px solid #e8eaec;overflow: auto;">
            <Spin size="large" fix v-if="loading"></Spin>
            <Tree :empty-text="$t('noPermissionData')" :data="treeData" @on-select-change="onSelectChange"></Tree>
        </Sider>
        <Content :style="{background: '#fff',padding:'12px'}">
            <div style="margin-bottom: 12px;">
                <Button icon="md-add" type="primary" @click="addPermission()">
                    {{$t('addPermission')}}
                </Button>
                <Tooltip :content="$t('syncPermissionTip')" transfer>
                    <Button icon="md-sync" @click="loadPermission()">
                        {{$t('syncPermission')}}
                    </Button>
                </Tooltip>
                <Tooltip :content="$t('forceSyncPermissionTip')" transfer :max-width="300">
                    <Button icon="md-sync" @click="loadPermission()">
                        {{$t('forceSyncPermission')}}
                    </Button>
                </Tooltip>
                <Tooltip :content="$t('checkInvalidPermissionTip')" transfer :max-width="300">
                    <Button icon="md-alert" @click="loadPermission()">
                        {{$t('checkInvalidPermission')}}
                    </Button>
                </Tooltip>
                <Button  icon="md-refresh" @click="loadPermission()">
                    {{$t('refresh')}}
                </Button>
                <Button type="error" icon="md-trash" :disabled="data.permissionSource=='annotation' || !data.permissionId">
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
                <div style="text-align:right;" v-if="data.permissionSource!='annotation'">
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
                url:'jfcloud/jf-cloud-platform/security/permission/list',
                data:{appCode:vm.$store.state.app.appInfo.appCode}
            }).then(result=>{
                vm.loading=false;
                if(result && result.success){
                    vm.treeData=result.data;
                    let parentCodes=[];
                    vm.treeData.forEach(item=>{
                        parentCodes.push({value:item.permissionCode,label:item.title});
                    });
                    vm.parentCodes=parentCodes;
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
            
        }
    }
}
</script>