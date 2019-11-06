<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('groupName')"   prop="groupIds">
                    <Select v-model="data.groupIds" clearable filterable multiple>
                        <Option v-for="item in groupList" :value="item.groupId" :key="item.groupId">{{ item.groupName }}</Option>
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
                    url:'jfcloud/jf-cloud-platform/security/role/group/create',
                    method:'post'
                },
                formRules:{
                    groupIds:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                },
                groupLoad:false,
                groupList:[]
            }
        },
        methods:{
            showCallback(){
                let vm=this;
                if(!vm.groupLoad){
                    vm.$http({
                        method:'post',
                        url:'jfcloud/jf-cloud-platform/security/group/select',
                        data:{appCode:vm.$store.state.app.appInfo.appCode}
                    }).then(result=>{
                        vm.groupLoad=true;
                        if(result && result.success){
                            vm.groupList=result.data;
                        }
                    });
                }
            }
        }
    }
</script>