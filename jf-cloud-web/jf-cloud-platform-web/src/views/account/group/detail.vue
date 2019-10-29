<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('group$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :id="id" :op="detailOp" @detailEdit="openEdit"/>
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
                    url:'jfcloud/jf-cloud-platform/security/group/single'
                },
                editPermission:'group$save',
                autoLoad:vm.autoLoad(),
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
    }
}
</script>