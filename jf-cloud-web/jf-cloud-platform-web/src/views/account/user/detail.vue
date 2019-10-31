<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('user$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <Edit :formId="formId" formKey="userId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
    </Tabs>
</template>
<script>
import Edit from './edit.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
export default {
    mixins:[editMixins,detailMixins],
    components:{
        Edit
    },
    data(){
        let vm=this;
        return {
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/user/single'
                },
                editPermission:'user$save',
                autoLoad:vm.autoLoad(vm.detailId),
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
    methods:{
        
    }
}
</script>