<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('group$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :id="id" :op="detailOp" @detailEdit="openEdit"/>
            <Edit v-permission="'group$save'" :formId="formId" formKey="groupId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
        <TabPane :label="$t('groupUsers')" :name="groupUsersId" v-if="$util.checkPermission('groupUsers$page',$store.state.permission.permissionList)" >
            <JFGrid ref="groupUserGrid" :gridOp="groupUserGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddUser=true" v-permission="'groupUsers$create'">
                        {{$t('addUser')}}
                    </Button>
                </template>
                <AddUser slot="grid-drawer" formId="addUserForm" formKey="id"
                    :visible.sync="showAddUser" 
                    :formData="addUserFormData"/>
            </JFGrid>
        </TabPane>
    </Tabs>
</template>
<script>
import Edit from './edit.vue';
import AddUser from './addUser.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
import detail from './detail';
import groupUser from './groupUser';
export default {
    mixins:[editMixins,detailMixins,detail,groupUser],
    components:{
        Edit,AddUser
    },
    data(){
        let vm=this;
        return {
            eventMap:{
                groupUsers:vm.loadGroupUsers
            },
        }
    }
}
</script>