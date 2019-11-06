<template>
    <Tabs :value="tabId" v-if="id" @on-click="load">
        <TabPane :label="$t('detail')" :name="detailId" v-if="$util.checkPermission('role$single',$store.state.permission.permissionList)">
            <JFDetail :ref="detailRef" :op="detailOp" :id="id" @detailEdit="openEdit"/>
            <EditRole :formId="formId" formKey="roleId"
                :visible.sync="showEdit" 
                :formData="formData"
                @saveCallback="loadDetail()"/>
        </TabPane>  
        <TabPane :label="$t('rolePermission')" name="rolePermission" v-if="$util.checkPermission('rolePermission$tree',$store.state.permission.permissionList)">
            <Spin size="large" fix v-if="loading"></Spin>
            <div>
                <Button icon="md-checkmark" :loading="saveLoading" type="primary" @click="saveRolePermission()" v-permission="'rolePermission$save'">
                    {{$t('save')}}
                </Button>
                 <!--同步权限-->
                <Button icon="md-sync" :loading="syncLoading"  @click="syncPermission()" v-permission="'permission$sync'">
                    {{$t('syncPermission')}}
                </Button>
                <Button  icon="md-refresh" @click="loadPermission()">
                    {{$t('refresh')}}
                </Button>
            </div>
            <Tree ref="permissionTree" :empty-text="$t('noPermissionData')" :data="treeData" multiple show-checkbox></Tree>
        </TabPane>
        <TabPane :label="$t('roleUsers')" name="roleUsers" v-if="$util.checkPermission('roleUsers$page',$store.state.permission.permissionList)">
            <JFGrid ref="roleUserGrid" :gridOp="roleUserGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddUser=true" v-permission="'roleUsers$create'">
                        {{$t('addUser')}}
                    </Button>
                </template>
                <AddUser slot="grid-drawer" formId="addUserForm" formKey="id"
                    :visible.sync="showAddUser" 
                    :formData="addUserFormData"
                    @saveCallback="loadRoleUsers"/>
            </JFGrid>
        </TabPane>
        <TabPane :label="$t('roleGroups')" name="roleGroups" v-if="$util.checkPermission('roleGroups$page',$store.state.permission.permissionList)">
            <JFGrid ref="roleGroupGrid" :gridOp="roleGroupGrid">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="showAddGroup=true" v-permission="'roleGroups$create'">
                        {{$t('addGroup')}}
                    </Button>
                </template>
                <AddGroup slot="grid-drawer" formId="addGroupForm" formKey="id"
                    :visible.sync="showAddGroup" 
                    :formData="addGroupFormData"
                    @saveCallback="loadRoleGroups"/>
            </JFGrid>
        </TabPane>
    </Tabs>
</template>
<script>
import EditRole from './edit.vue';
import AddGroup from './addGroup.vue';
import AddUser from './addUser.vue';
import editMixins from '@/mixins/editMixins';
import detailMixins from '@/mixins/detailMixins';
import detail from './detail';
import rolePermission from './rolePermission';
import roleGroup from './roleGroup';
import roleUser from './roleUser';
export default {
    mixins:[editMixins,detailMixins,detail,rolePermission,roleGroup,roleUser],
    components:{
        EditRole,
        AddGroup,
        AddUser
    },
    data(){
        let vm=this;
        return {
             eventMap:{
                roleUsers:vm.loadRoleUsers,
                rolePermission:vm.loadPermission,
                roleGroups:vm.loadRoleGroups
            }
        }
    }
}
</script>