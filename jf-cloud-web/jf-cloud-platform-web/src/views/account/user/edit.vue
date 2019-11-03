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
                <template v-if="!isCreate">
                    <Row :gutter="32" >
                        <Col :span="12">
                            <FormItem :label="$t('userId')" prop="userId">
                                <Input v-model="data.userId" disabled/>
                            </FormItem>
                        </Col>
                        <Col :span="12">
                            <FormItem :label="$t('userNo')" prop="userNo">
                                <Input v-model="data.userNo" disabled/>
                            </FormItem>
                        </Col>
                    </Row>
                </template>
                <Row :gutter="32" >
                    <Col :span="12">
                        <FormItem :label="$t('userCn')" prop="userCn">
                            <Input v-model="data.userCn" />
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('userEn')" prop="userEn">
                            <Input v-model="data.userEn"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32" >
                    <Col :span="12">
                        <FormItem :label="$t('mobile')" prop="mobile">
                            <Input v-model="data.mobile" :disabled="!isCreate && data.mobileValidated=='Y'"/>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem :label="$t('email')" prop="email">
                            <Input v-model="data.email" :disabled="!isCreate && data.emailValidated=='Y'"/>
                        </FormItem>
                    </Col>
                </Row>
                <Row :gutter="32" >
                    <Col :span="12">
                         <FormItem :label="$t('userSex')" prop="userSex">
                            <RadioGroup v-model="data.userSex" class="full-width">
                                <Radio  label="1">{{$t('type.userSex.1')}}</Radio>
                                <Radio  label="2">{{$t('type.userSex.2')}}</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                    <Col :span="12">
                        <FormItem  :label="$t('userType')" prop="userType" :disabled="!isCreate">
                            <RadioGroup  v-model="data.userType" class="full-width">
                                <Radio :disabled="!isCreate"  label="1">{{$t('type.userType.1')}}</Radio>
                                <Radio :disabled="!isCreate"  label="2">{{$t('type.userType.2')}}</Radio>
                            </RadioGroup>
                        </FormItem>
                    </Col>
                </Row>
                 <template v-if="isCreate">
                    <template v-if="data.userType=='1'" >
                        <Row :gutter="32" >
                            <Col :span="12">
                                <FormItem :label="$t('employeeType')" prop="employeeType">
                                    <RadioGroup v-model="data.employeeType" class="full-width">
                                        <Radio  :label="1">{{$t('type.employeeType.1')}}</Radio>
                                        <Radio  :label="2">{{$t('type.employeeType.2')}}</Radio>
                                        <Radio  :label="3">{{$t('type.employeeType.3')}}</Radio>
                                    </RadioGroup>
                                </FormItem>
                            </Col>
                            <Col :span="12">
                                <FormItem :label="$t('employeeStatus')" prop="employeeStatus">
                                    <RadioGroup v-model="data.employeeStatus" class="full-width">
                                        <Radio  :label="1">{{$t('status.employeeStatus.1')}}</Radio>
                                        <Radio  :label="2">{{$t('status.employeeStatus.2')}}</Radio>
                                    </RadioGroup>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row :gutter="32" >
                            <Col :span="12">
                                <FormItem :label="$t('entryDate')" prop="entryDate">
                                    <DatePicker type="date" v-model="data.entryDate" class="full-width" @on-change="data.entryDate=$event"></DatePicker>
                                </FormItem>
                            </Col>
                            <Col :span="12">
                                <FormItem :label="$t('employeeSuperior')" prop="parentId">
                                    <JFUserSelect v-model="data.parentId"/>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row :gutter="32" >
                            <Col :span="12">
                                <FormItem :label="$t('employeeOrg')" prop="employeeOrg">
                                    <JFOrgCascader v-model="data.employeeOrg" />
                                </FormItem>
                            </Col>
                            <Col :span="12">
                                <FormItem :label="$t('employeeJob')" prop="employeeJob">
                                    <Input v-model="data.employeeJob" />
                                </FormItem>
                            </Col>
                        </Row>
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
                width:800,
                defaultValue:{userType:'1',userSex:'1',employeeType:1,employeeStatus:1},
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
                    userSex:[
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