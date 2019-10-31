<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('app$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :id="id" :op="detailOp" @detailEdit="openEdit" @loadCallback="detailCallback"/>
            <EditApp v-permission="'app$save'" :formId="formId" formKey="appId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
        <TabPane :label="$t('appUsers')" name="appUsers" v-if="detailData.appType!='1' && $util.checkPermission('appUsers$page',$store.state.permission.permissionList)" >
            <JFGrid ref="appUserGrid" :gridOp="appUserGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddUser=true" v-permission="'appUsers$create'">
                        {{$t('addUser')}}
                    </Button>
                </template>
                <AddUser slot="grid-drawer" formId="addUserForm" formKey="id"
                    :visible.sync="showAddUser" 
                    :formData="addUserFormData"
                    @saveCallback="loadAppUsers"/>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('appToken')" name="appToken" v-if="detailData.appType!='1'  && $util.checkPermission('appToken$single',$store.state.permission.permissionList)">
            <Form  :model="appData" :rules="appFormRules">
                <Spin size="large" fix v-if="appTokenLoading"></Spin>
                <FormItem :label="$t('appToken')" prop="appToken">
                    <Input type="textarea" v-model="appData.appToken" :rows="5" :maxlength="500" show-word-limit/>
                </FormItem>
                <div style="text-align:right;">
                    <Button icon="md-refresh" @click="$util.viewLog('AppToken','save')">{{$t('refresh')}}</Button>
                    <Button type="primary" v-permission="'appToken$save'" @click="saveAppToken()" icon="md-checkmark">{{$t('save')}}</Button>
                </div>
            </Form>
        </TabPane>
    </Tabs>
</template>
<script>
import EditApp from './edit.vue';
import AddUser from './addUser.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
export default {
    mixins:[editMixins,detailMixins],
    components:{
        EditApp,
        AddUser
    },
    data(){
        let vm=this,query=vm.$route.query,tabId=query.tabId || 'detail',id=query.id;
        return {
            eventMap:{
                appUsers:vm.loadAppUsers,
                appToken:vm.loadAppToken
            },
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/app/single'
                },
                editPermission:'app$save',
                autoLoad:vm.autoLoad(vm.detailId),
                userFields:['appOwner'],
                items:[
                    {cols:[
                        {key:'appCode'},
                        {key:'appName'},
                    ]},
                    {cols:[
                        {key:'appOwner',type:'user'},
                        {key:'appType',type:'type'},
                    ]},
                    {key:'contextPath'},
                    {key:'appDesc',desc:true}
                ]
            },
            appUserGrid:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/app/user/page',
                    defaultParams:{appId:id},
                    autoLoad:vm.autoLoad('appUsers')
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/security/app/user/delete'
                },
                table:{
                    pkId:'id',
                    buttons:[
                        {title:vm.$t('delete'),gridDelete:true}
                    ],
                    permissionCode:'appUsers$delete',
                    logSetting:{module:'AppUser'},
                    showAuditUpdate:true,
                    columns:[
                        {key:'grantStatus',width:150,render:(h,params)=>{
                            return vm.$util.grantStatus(vm,h,params.row.beginDate,params.row.endDate);
                        },condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('status.grantStatus.1')},
                               {value:'2',label:vm.$t('status.grantStatus.2')},
                               {value:'3',label:vm.$t('status.grantStatus.3')},
                               {value:'4',label:vm.$t('status.grantStatus.4')}
                            ]
                        }},
                        {key:'userId',width:150,format:'user',title:'userName',condition:{type:'string',key:'userName'}},
                        {key:'roleCode',width:150,condition:true},
                        {key:'roleName',condition:true},
                        {key:'beginDate',width:120,condition:{type:'datePicker',format:'yyyy-MM-dd',width:200}},
                        {key:'endDate',width:120,condition:{type:'datePicker',format:'yyyy-MM-dd',width:200}}
                    ]
                }
            },
            showAddUser:false,
            addUserFormData:{appId:id},

            //应用Token
            appData:{},
            appTokenLoading:false,
            appFormRules:{
                appToken:[
                    {required:true,message:vm.$t('validator.notEmpty')}
                ]
            }
        }
    },
    methods:{
        loadAppUsers(){
            let appUserGrid=this.$refs.appUserGrid;
            if(appUserGrid){
                appUserGrid.search();
            }
        },
        detailCallback(formData){
            this.detailData=formData;
        },
        loadAppToken(){
            let vm=this;
            vm.appTokenLoading=true;
            vm.$http({
                method:'get',
                url:'jfcloud/jf-cloud-platform/security/app/token/single',
                params:{id:vm.id}
            }).then(result=>{
                vm.appTokenLoading=false;
                if(result && result.success){
                    vm.appData=result.data;
                }
            }).catch(error=>{
                vm.appTokenLoading=false;
            });
        },
        saveAppToken(){
            let vm=this;
            vm.$http({
                method:'post',
                url:'jfcloud/jf-cloud-platform/security/app/token/save',
                data:{appId:vm.id,appToken:vm.appData.appToken}
            }).then(result=>{
                vm.appTokenLoading=false;
                if(result && result.success){
                    vm.$Message.success(vm.$t('saveSuccessful'));
                }
            }).catch(error=>{
                vm.$Message.error(vm.$util.handerError(error,vm));
            });
        }
    }
}
</script>