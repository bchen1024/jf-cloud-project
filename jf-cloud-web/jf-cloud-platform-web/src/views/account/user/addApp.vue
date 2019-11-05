<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('appName')"   prop="appIds">
                    <Select v-model="data.appIds" clearable filterable multiple>
                        <Option v-for="item in appList" :value="item.appId" :key="item.appId">{{ item.appName }}</Option>
                    </Select>
                </FormItem>
                <FormItem :label="$t('roleName')"   prop="roleId">
                    <Select v-model="data.roleId" clearable filterable>
                        <Option v-for="item in roleList" :value="item.roleId" :key="item.roleId">{{ item.roleName }}</Option>
                    </Select>
                </FormItem>
                <FormItem :label="$t('dateRange')" prop="dateRange">
                    <DatePicker v-model="data.dateRange" format="yyyy-MM-dd" type="daterange" class="full-width" @on-change="data.dateRange=$event"></DatePicker>
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
                    url:'jfcloud/jf-cloud-platform/security/user/app/create',
                    method:'post'
                },
                formRules:{
                    appIds:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    roleId:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    dateRange:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                },
                roleLoad:false,
                roleList:[],
                appLoad:false,
                appList:[]
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

                if(!vm.appLoad){
                    vm.$http({
                        method:'post',
                        url:'jfcloud/jf-cloud-platform/security/app/select',
                        data:{appType:2}
                    }).then(result=>{
                        vm.appLoad=true;
                        if(result && result.success){
                            vm.appList=result.data;
                        }
                    });
                }
            }
        }
    }
</script>