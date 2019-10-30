<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('userName')" prop="userId">
                    <Input v-model="data.userId" />
                </FormItem>
                <FormItem :label="$t('dateRange')" prop="dateRange">
                    <DatePicker v-model="data.dateRange" format="yyyy-MM-dd" type="daterange"></DatePicker>
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
                width:600,
                saveOp:{
                    url:'jfcloud/jf-cloud-platform/security/group/user/create',
                    method:'post'
                },
                formRules:{
                    userId:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    beginDate:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    endDate:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        }
    }
</script>