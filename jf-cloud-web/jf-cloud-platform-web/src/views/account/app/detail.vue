<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" name="detail">
            <JFDetail ref="appDetail" :id="id" :op="detailOp" @detailEdit="openEdit" @loadCallback="detailCallback"/>
            <EditApp :formId="formId" formKey="appId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
        <TabPane :label="$t('appUsers')" name="appUsers" v-if="detailData.appType!='1'">
            <JFGrid ref="appUserGrid" :gridOp="appUserGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary">
                        {{$t('addUser')}}
                    </Button>
                </template>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('appToken')" name="appToken" v-if="detailData.appType!='1'">标签二的内容</TabPane>
    </Tabs>
</template>
<script>
import EditApp from './edit.vue';
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    components:{
        EditApp
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
                    logSetting:{module:'AppUser'},
                    showAuditCreate:true,
                    showAuditUpdate:true,
                    columns:[
                        {key:'userId',format:'user',title:'userName',condition:{type:'string',key:'userName'}},
                        {key:'roleName',condition:true}
                    ]
                }
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
        }
    }
}
</script>