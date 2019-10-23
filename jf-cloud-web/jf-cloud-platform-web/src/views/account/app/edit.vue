<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
            :styles="styles"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('appCode')" label-position="top" prop="appCode">
                    <Input v-model="data.appCode" :disabled="!isCreate"/>
                </FormItem>
                <FormItem :label="$t('appName')" label-position="top" prop="appName">
                    <Input v-model="data.appName"/>
                </FormItem>
                <FormItem :label="$t('appOwner')" label-position="top" prop="appOwner">
                    <Input v-model="data.appOwner"/>
                </FormItem>
                <FormItem :label="$t('appType')" label-position="top" prop="appType">
                    <RadioGroup v-model="data.appType">
                        <Radio  label="1">{{$t('type.appType.1')}}</Radio>
                        <Radio  label="2">{{$t('type.appType.2')}}</Radio>
                    </RadioGroup>
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
                defaultValue:{appType:'1'},
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
                    ],
                    appType:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        }
    }
</script>