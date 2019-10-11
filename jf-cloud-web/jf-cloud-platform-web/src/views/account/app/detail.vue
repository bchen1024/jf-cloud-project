<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" name="detail" v-if="$util.checkPermission('app$single',$store.state.permission.permissionList)">
            <JFDetail ref="appDetail" :id="id" :op="detailOp" @detailEdit="openEdit" @loadCallback="detailCallback"/>
            <EditApp v-permission="'app$save'" :formId="formId" formKey="appId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
        <TabPane :label="$t('appUsers')" name="appUsers" v-if="detailData.appType!='1' && $util.checkPermission('appUsers$page',$store.state.permission.permissionList)" >
            <JFGrid ref="appUserGrid" :gridOp="appUserGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="openAddUser()" v-permission="'appUsers$create'">
                        {{$t('addUser')}}
                    </Button>
                </template>
                <AddUser slot="grid-drawer" formId="addUserForm" formKey="id"
                    :visible.sync="showAddUser" 
                    :formData="addUserFormData"
                    @saveCallback="loadAppUsers"/>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('appToken')" name="appToken" v-if="detailData.appType!='1'  && $util.checkPermission('app$appToken',$store.state.permission.permissionList)">
            <Form  :model="appData" :rules="appFormRules">
                <Spin size="large" fix v-if="appTokenLoading"></Spin>
                <FormItem :label="$t('appToken')" prop="appToken">
                    <Input type="textarea" v-model="appData.appToken" :rows="5" :maxlength="500" />
                </FormItem>
                <div style="text-align:right;">
                    <Button icon="md-refresh" @click="loadAppToken()">{{$t('refresh')}}</Button>
                    <Button type="primary"  @click="saveAppToken()" icon="md-checkmark">{{$t('save')}}</Button>
                </div>
            </Form>
        </TabPane>
    </Tabs>
</template>
<script>
import EditApp from './edit.vue';
import AddUser from './addUser.vue';
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    components:{
        EditApp,
        AddUser
    },
    data(){
        let vm=this,query=vm.getQuery(),tabId=query.tabId || 'detail',id=query.id;
        return {
            tabId:'detail',
            id:null,
            loadMap:{},
            detailData:{},
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/app/single'
                },
                editPermission:'app$save',
                autoLoad:tabId=='detail',
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
                    {cols:[
                        {key:'appPort'},
                        {key:'contextPath'},
                    ]},
                    {key:'appDesc',desc:true}
                ]
            },
            appUserGrid:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/app/user/page',
                    defaultParams:{appId:id},
                    autoLoad:(tabId=='appUsers')
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
                    showAuditCreate:true,
                    showAuditUpdate:true,
                    columns:[
                        {key:'userId',format:'user',title:'userName',condition:{type:'string',key:'userName'}},
                        {key:'roleName',condition:true}
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
    created(){
        let vm=this,query=vm.getQuery();
        vm.id=query.id;
        if(query.tabId){
            vm.tabId=query.tabId;
        }
        vm.load();
    },
    methods:{
        getQuery(){
            let vm=this,query=vm.$route.query;
            return query;
        },
        load(name){
            let vm=this;
            if(name){
                vm.tabId=name;
            }
            if(!vm.loadMap[vm.tabId]){
                if(vm.tabId=='detail'){
                    vm.loadDetail();
                }else if(vm.tabId=='appUsers'){
                    vm.loadAppUsers();
                }else if(vm.tabId=='appToken'){
                    vm.loadAppToken();
                }
                vm.loadMap[vm.tabId]=true;
            }
            
        },
        loadDetail(){
            let appDetail=this.$refs.appDetail;
            if(appDetail){
                appDetail.load();
            }
        },
        loadAppUsers(){
            let appUserGrid=this.$refs.appUserGrid;
            if(appUserGrid){
                appUserGrid.search();
            }
        },
        detailCallback(formData){
            this.detailData=formData;
        },
        openAddUser(){
            this.showAddUser=true;
        },
        loadAppToken(){
            let vm=this;
            vm.appTokenLoading=true;
            vm.$http({
                method:'get',
                url:'jfcloud/jf-cloud-platform/security/app/token',
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