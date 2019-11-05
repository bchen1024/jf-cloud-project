<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('user$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <Edit :formId="formId" formKey="userId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>
        <TabPane :label="$t('userGroups')" name="userGroups" v-if="$util.checkPermission('userGroups$page',$store.state.permission.permissionList)">
            <JFGrid ref="userGroupGrid" :gridOp="userGroupGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddGroup=true" v-permission="'userGroups$create'">
                        {{$t('addGroup')}}
                    </Button>
                </template>
                <AddGroup slot="grid-drawer" formId="addGroupForm" formKey="id"
                    :visible.sync="showAddGroup" 
                    :formData="addGroupFormData"
                    @saveCallback="loadUserGroups"/>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('userRoles')" name="userRoles" v-if="$util.checkPermission('userRoles$page',$store.state.permission.permissionList)">
            <JFGrid ref="userRoleGrid" :gridOp="userRoleGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddRole=true" v-permission="'userRoles$create'">
                        {{$t('addRole')}}
                    </Button>
                </template>
                <AddRole slot="grid-drawer" formId="addRoleForm" formKey="id"
                    :visible.sync="showAddRole" 
                    :formData="addRoleFormData"
                    @saveCallback="loadUserRoles"/>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('userApps')" name="userApps" v-if="$util.checkPermission('userApps$page',$store.state.permission.permissionList)">
            <JFGrid ref="userAppGrid" :gridOp="userAppGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddApp=true" v-permission="'userApps$create'">
                        {{$t('addApp')}}
                    </Button>
                </template>
            </JFGrid>
        </TabPane>
    </Tabs>
</template>
<script>
import Edit from './edit.vue';
import AddGroup from './addGroup.vue';
import AddRole from './addRole.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
import detail from './detail';
import userGroup from './userGroup';
import userRole from './userRole';
import userApp from './userApp';
export default {
    mixins:[editMixins,detailMixins,detail,userGroup,userRole,userApp],
    components:{
        Edit,AddGroup,AddRole
    },
    data(){
        let vm=this;
        return {
            eventMap:{
                userGroups:vm.loadUserGroups,
                userRoles:vm.loadUserRoles,
                userApps:vm.loadUserApps
            }
        }
    },
    methods:{
        
    }
}
</script>