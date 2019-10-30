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
                <FormItem :label="$t('userCn')" label-position="top" prop="userCn">
                    <Input v-model="data.userCn" />
                </FormItem>
                <FormItem :label="$t('userEn')" label-position="top" prop="userEn">
                    <Input v-model="data.userEn"/>
                </FormItem>
                <FormItem :label="$t('mobile')" label-position="top" prop="mobile">
                    <Input v-model="data.mobile" :disabled="!isCreate && data.mobileValidated=='Y'"/>
                </FormItem>
                <FormItem :label="$t('email')" label-position="top" prop="email">
                    <Input v-model="data.email" :disabled="!isCreate && data.emailValidated=='Y'"/>
                </FormItem>
                <FormItem :label="$t('userSex')" label-position="top" prop="userSex">
                    <RadioGroup v-model="data.userSex">
                        <Radio  label="1">{{$t('type.userSex.1')}}</Radio>
                        <Radio  label="2">{{$t('type.userSex.2')}}</Radio>
                    </RadioGroup>
                </FormItem>
                 <template v-if="isCreate">
                    <FormItem  :label="$t('userType')" label-position="top" prop="userType">
                        <RadioGroup  v-model="data.userType">
                            <Radio :disabled="!isCreate"  label="1">{{$t('type.userType.1')}}</Radio>
                            <Radio :disabled="!isCreate"  label="2">{{$t('type.userType.2')}}</Radio>
                        </RadioGroup>
                    </FormItem>
                    <template v-if="data.userType=='1'" >
                        <FormItem :label="$t('employeeType')" label-position="top" prop="employeeType">
                            <RadioGroup v-model="data.employeeType">
                                <Radio  label="1">{{$t('type.employeeType.1')}}</Radio>
                                <Radio  label="2">{{$t('type.employeeType.2')}}</Radio>
                                <Radio  label="3">{{$t('type.employeeType.3')}}</Radio>
                            </RadioGroup>
                        </FormItem>
                        <FormItem :label="$t('employeeStatus')" label-position="top" prop="employeeStatus">
                            <RadioGroup v-model="data.employeeStatus">
                                <Radio  label="1">{{$t('status.employeeStatus.1')}}</Radio>
                                <Radio  label="2">{{$t('status.employeeStatus.2')}}</Radio>
                            </RadioGroup>
                        </FormItem>
                        <FormItem :label="$t('employeeJob')" label-position="top" prop="employeeJob">
                            <Input v-model="data.employeeJob" />
                        </FormItem>
                        <FormItem :label="$t('employeeSuperior')" label-position="top" prop="parentId">
                            <JFUserSelect v-model="data.parentId"/>
                        </FormItem>
                    </template>
                </template>
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
                defaultValue:{userType:'1',userSex:'1',employeeType:'1',employeeStatus:'1'},
                saveOp:{
                    url:'jfcloud/jf-cloud-platform/security/user/save',
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
                    ],
                    mobile:[
                        {type:'string',message:vm.$t('validator.tel'),pattern:/^1[3456789]\d{9}$/}
                    ],
                    email:[
                        {type:'email',message:vm.$t('validator.email')}
                    ]
                }
            }
        }
    }
</script>