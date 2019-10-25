<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" name="detail" v-if="$util.checkPermission('group$single',$store.state.permission.permissionList)">
            <JFDetail ref="groupDetail" :id="id" :op="detailOp" @detailEdit="openEdit" @loadCallback="detailCallback"/>
            <Edit v-permission="'group$save'" :formId="formId" formKey="groupId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
    </Tabs>
</template>
<script>
import Edit from './edit.vue';
import editMixins from '@/mixins/editMixins';
export default {
    mixins:[editMixins],
    components:{
        Edit
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
                    url:'jfcloud/jf-cloud-platform/security/group/single'
                },
                editPermission:'group$save',
                autoLoad:tabId=='detail',
                userFields:['groupOwner'],
                items:[
                    {cols:[
                        {key:'groupCode'},
                        {key:'groupName'},
                    ]},
                    {cols:[
                        {key:'groupOwner',type:'user'},
                        {key:'applyStatus',type:'status'},
                    ]},
                    {key:'groupDesc',desc:true}
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
                }
                vm.loadMap[vm.tabId]=true;
            }
            
        },
        loadDetail(){
            let groupDetail=this.$refs.groupDetail;
            if(groupDetail){
                groupDetail.load();
            }
        },
        detailCallback(formData){
            this.detailData=formData;
        }
    }
}
</script>