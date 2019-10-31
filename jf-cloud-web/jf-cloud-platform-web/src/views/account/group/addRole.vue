<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('roleName')" label-position="top" prop="roleIds">
                    <Select v-model="data.roleIds" clearable filterable multiple>
                        <Option v-for="item in roleList" :value="item.roleId" :key="item.roleId">{{ item.roleName }}</Option>
                    </Select>
                </FormItem>
            </Form>
            <div class="jf-drawer-footer">
                <Button @click="cancelForm()" icon="md-close">{{$t('cancel')}}</Button>
                <Button type="primary" @click="saveForm()" :loading="loading" icon="md-checkmark">{{$t('save')}}</Button>
            </div>
        </Drawer>  
</template>
<script>
    import editDrawer from '@/mixins/editDrawer';
    export default {
        mixins:[editDrawer],
        data () {
            let vm=this;
            return {
                saveOp:{
                    url:'jfcloud/jf-cloud-platform/security/group/role/create',
                    method:'post'
                },
                formRules:{
                    roleIds:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                },
                roleLoad:false,
                roleList:[]
            }
        },
        methods:{
            showCallback(){
                let vm=this;
                if(!vm.roleLoad){
                    vm.$http({
                        method:'post',
                        url:'jfcloud/jf-cloud-platform/security/role/select',
                        data:{appCode:vm.$store.state.app.appInfo.appCode}
                    }).then(result=>{
                        vm.roleLoad=true;
                        if(result && result.success){
                            vm.roleList=result.data;
                        }
                    });
                }
            }
        }
    }
</script>