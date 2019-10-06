<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" name="detail">
            <JFDetail ref="userDetail" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <EditUser :formId="formId" formKey="userId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
    </Tabs>
</template>
<script>
import EditUser from './edit.vue';
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    components:{
        EditUser
    },
    data(){
        return {
            tabId:'detail',
            id:null,
            loadMap:{},
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/user/single'
                },
                autoLoad:!this.$route.query.tabId,
                userFields:['roleOwner'],
                items:[
                    {cols:[
                        {key:'userId'},
                        {key:'userNo'},
                    ]},
                    {cols:[
                        {key:'userCn'},
                        {key:'userEn'}
                    ]},
                    {cols:[
                        {key:'mobile',valid:'mobileValidated'},
                        {key:'email',valid:'emailValidated'}
                    ]},
                    {cols:[
                        {key:'userType',type:'type'},
                        {key:'userStatus',type:'status'}
                    ]},
                     {cols:[
                        {key:'passwordUpdateTime'},
                        {key:'lockTime'}
                    ]},
                ]
            }
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
            if(!vm.loadMap[vm.tabId]){
                if(vm.tabId=='detail'){
                    vm.loadDetail();
                }
                vm.loadMap[vm.tabId]=true;
            }
        },
        loadDetail(){
            let vm=this;
            let userDetail=vm.$refs.userDetail;
            if(userDetail){
                userDetail.load();
            }
        }
    }
}
</script>