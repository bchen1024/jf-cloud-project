<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('userName')" prop="userIds">
                    <JFUserSelect v-model="data.userIds" multiple/>
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
                    url:'jfcloud/jf-cloud-platform/security/role/user/create',
                    method:'post'
                },
                formRules:{
                    userIds:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    dateRange:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        }
    }
</script>