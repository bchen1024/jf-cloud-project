<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('employee$single',$store.state.permission.permissionList)">
            <JFDetail ref="employeeDetail" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <EditEmployee :formId="formId" formKey="userId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
    </Tabs>
</template>
<script>
import EditEmployee from './edit.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
export default {
    mixins:[editMixins,detailMixins],
    components:{
        EditEmployee
    },
    data(){
        let vm=this;
        return {
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/employee/single'
                },
                editPermission:'employee$save',
                autoLoad:vm.autoLoad(vm.detailId),
                userFields:['userId','parentId'],
                items:[
                    {cols:[
                        {key:'userId'},
                        {key:'userId',type:'user',label:vm.$t('userName')},
                    ]},
                    {cols:[
                        {key:'employeeNo'},
                        {key:'employeeType',type:'type'}
                    ]},
                    {cols:[
                        {key:'employeeStatus',type:'status'},
                        {key:'entryDate'}
                    ]},
                    {cols:[
                        {key:'parentId',label:vm.$t('employeeSuperior'),type:'user'},
                        {key:'employeeJob'}
                    ]},
                    {key:'employeeOrg',render:(item,data)=>{
                        let orgName=vm.$util.getOrgName(vm,data.employeeOrg);
                        return orgName;
                    }}
                ]
            }
        }
    }
}
</script>