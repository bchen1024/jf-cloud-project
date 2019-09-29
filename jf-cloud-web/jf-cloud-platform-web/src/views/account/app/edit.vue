<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('appCode')" label-position="top" prop="appCode">
                    <Input v-model="data.appCode" />
                </FormItem>
                <FormItem :label="$t('appName')" label-position="top" prop="appName">
                    <Input v-model="data.appName"/>
                </FormItem>
                <FormItem :label="$t('appOwner')" label-position="top" prop="appOwner">
                    <Input v-model="data.appOwner"/>
                </FormItem>
                <FormItem :label="$t('contextPath')" label-position="top" prop="contextPath">
                    <Input v-model="data.contextPath"/>
                </FormItem>
                <FormItem :label="$t('appDesc')" label-position="top">
                    <Input type="textarea" v-model="data.appDesc" :rows="4" :maxlength="200" />
                </FormItem>
            </Form>
            <div class="jf-drawer-footer">
                <Button @click="cancelForm()" icon="md-close">{{$t('cancel')}}</Button>
                <Button type="primary" @click="saveForm" :loading="loading" icon="md-checkmark">{{$t('save')}}</Button>
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
                    url:'jfcloud/jf-cloud-platform/security/app/save'
                },
                formRules:{
                    appCode:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    appName:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    appOwner:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        },
        methods:{
            
        }
    }
</script>