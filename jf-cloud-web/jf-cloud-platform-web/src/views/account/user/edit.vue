<template>
    <Drawer
            :title="title"
            v-model="drawerShow"
            :width="width"
            :visible.sync="drawerShow"
            :mask-closable="false"
        >
            <Form :ref='formId' :model="data" :rules="formRules">
                <FormItem :label="$t('userCn')" label-position="top" prop="userCn">
                    <Input v-model="data.userCn" />
                </FormItem>
                <FormItem :label="$t('userEn')" label-position="top" prop="userEn">
                    <Input v-model="data.userEn"/>
                </FormItem>
                 <FormItem :label="$t('userSex')" label-position="top" prop="userSex">
                    <RadioGroup v-model="data.userSex">
                        <Radio  label="1">{{$t('male')}}</Radio>
                        <Radio  label="2">{{$t('female')}}</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem :label="$t('userType')" label-position="top" prop="userType">
                    <RadioGroup v-model="data.userType">
                        <Radio  label="1">{{$t('employee')}}</Radio>
                        <Radio  label="2">{{$t('register')}}</Radio>
                    </RadioGroup>
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
                mobileValidated:false,
                defaultValue:{userType:'1'},
                saveOp:{
                    url:'jfcloud/jf-cloud-platform/security/user/create',
                    method:'post'
                },
                formRules:{
                    userCn:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    userEn:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    userType:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ]
                }
            }
        },
        methods:{
            
        }
    }
</script>