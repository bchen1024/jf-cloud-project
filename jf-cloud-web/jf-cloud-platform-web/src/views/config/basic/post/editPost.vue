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
                <FormItem :label="$t('postCn')"   prop="postCn">
                    <Input v-model="data.postCn"/>
                </FormItem> 
                <FormItem :label="$t('postEn')"   prop="postEn">
                    <Input v-model="data.postEn"/>
                </FormItem>
                <FormItem :label="$t('postLevel')"   prop="postLevel">
                    <Input v-model="data.postLevel"/>
                </FormItem> 
                <FormItem :label="$t('postOrg')"   prop="orgId">
                    <JFOrgCascader v-model="data.orgId" />
                </FormItem>
                <FormItem :label="$t('orgDesc')"   prop="orgDesc">
                    <Input v-model="data.orgDesc" type="textarea" :rows="4" :maxlength="200" show-word-limit/>
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
                    url:'jfcloud/jf-cloud-platform/config/post/save'
                },
                formRules:{
                    postCn:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    postEn:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        }
    }
</script>