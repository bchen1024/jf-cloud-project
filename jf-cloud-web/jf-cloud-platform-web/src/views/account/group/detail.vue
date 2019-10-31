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
                    :formData="addUserFormData"
                    @saveCallback="loadGroupUsers"/>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('groupRoles')" :name="groupRolesId" v-if="$util.checkPermission('groupRoles$page',$store.state.permission.permissionList)" >
            <JFGrid ref="groupRoleGrid" :gridOp="groupRoleGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddRole=true" v-permission="'groupRoles$create'">
                        {{$t('addRole')}}
                    </Button>
                </template>
                <AddRole slot="grid-drawer" formId="addRoleForm" formKey="id"
                    :visible.sync="showAddRole" 
                    :formData="addRoleFormData"
                    @saveCallback="loadGroupRoles"/>
            </JFGrid>
        </TabPane>
    </Tabs>
</template>
<script>
import Edit from './edit.vue';
import AddUser from './addUser.vue';
import AddRole from './addRole.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
import detail from './detail';
import groupUser from './groupUser';
import groupRole from './groupRole';
export default {
    mixins:[editMixins,detailMixins,detail,groupUser,groupRole],
    components:{
        Edit,AddUser,AddRole
    },
    data(){
        let vm=this;
        return {
            eventMap:{
                groupUsers:vm.loadGroupUsers,
                groupRoles:vm.loadGroupRoles
            },
        }
    }
}
</script>