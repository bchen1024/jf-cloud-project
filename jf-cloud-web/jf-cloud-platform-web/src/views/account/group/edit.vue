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
                <FormItem :label="$t('groupCode')" label-position="top" prop="groupCode">
                    <Input v-model="data.groupCode" />
                </FormItem>
                <FormItem :label="$t('groupName')" label-position="top" prop="groupName">
                    <Input v-model="data.groupName"/>
                </FormItem>
                <FormItem :label="$t('groupOwner')" label-position="top" prop="groupOwner">
                    <Input v-model="data.groupOwner"/>
                </FormItem>
                <FormItem :label="$t('applyStatus')" label-position="top" prop="applyStatus">
                    <RadioGroup v-model="data.applyStatus">
                        <Radio  label="Y">{{$t('status.applyStatus.Y')}}</Radio>
                        <Radio  label="N">{{$t('status.applyStatus.N')}}</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem :label="$t('groupDesc')" label-position="top">
                    <Input type="textarea" v-model="data.groupDesc" :rows="4" :maxlength="200" />
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
                    url:'jfcloud/jf-cloud-platform/security/group/save'
                },
                formRules:{
                    groupCode:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    groupName:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    groupOwner:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        },
        methods:{
            
        }
    }
</script>