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
                <FormItem :label="$t('roleCode')" label-position="top" prop="roleCode">
                    <Input v-model="data.roleCode" />
                </FormItem>
                <FormItem :label="$t('roleName')" label-position="top" prop="roleName">
                    <Input v-model="data.roleName"/>
                </FormItem>
                <FormItem :label="$t('roleOwner')" label-position="top" prop="roleOwner">
                    <JFUserSelect v-model="data.roleOwner"/>
                </FormItem>
                <FormItem :label="$t('applyStatus')" label-position="top" prop="applyStatus">
                    <RadioGroup v-model="data.applyStatus">
                        <Radio  label="Y">{{$t('status.applyStatus.Y')}}</Radio>
                        <Radio  label="N">{{$t('status.applyStatus.N')}}</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem :label="$t('roleDesc')" label-position="top">
                    <Input type="textarea" v-model="data.roleDesc" :rows="4" :maxlength="200" show-word-limit/>
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
                defaultValue:{appCode:this.$store.state.app.appInfo.appCode,applyStatus:'N'},
                saveOp:{
                    url:'jfcloud/jf-cloud-platform/security/role/save'
                },
                formRules:{
                    roleCode:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    roleName:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    roleOwner:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    applyStatus:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        },
        methods:{
            
        }
    }
</script>