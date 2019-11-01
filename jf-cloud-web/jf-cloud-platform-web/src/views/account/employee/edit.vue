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
                <FormItem :label="$t('userId')"   prop="userId">
                    <Input v-model="data.userId" disabled/>
                </FormItem>
                <FormItem :label="$t('employeeNo')"   prop="employeeNo">
                    <Input v-model="data.employeeNo" disabled/>
                </FormItem>
                <FormItem :label="$t('employeeType')"   prop="employeeType">
                    <RadioGroup v-model="data.employeeType" class="full-width">
                        <Radio  :label="1">{{$t('type.employeeType.1')}}</Radio>
                        <Radio  :label="2">{{$t('type.employeeType.2')}}</Radio>
                        <Radio  :label="3">{{$t('type.employeeType.3')}}</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem :label="$t('employeeStatus')"   prop="employeeStatus">
                    <RadioGroup v-model="data.employeeStatus" class="full-width">
                        <Radio  :label="1">{{$t('status.employeeStatus.1')}}</Radio>
                        <Radio  :label="2">{{$t('status.employeeStatus.2')}}</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem :label="$t('employeeJob')"   prop="employeeJob">
                    <Input v-model="data.employeeJob"/>
                </FormItem>
                <FormItem :label="$t('employeeSuperior')"   prop="parentId">
                    <JFUserSelect v-model="data.parentId"/>
                </FormItem>
                <FormItem :label="$t('entryDate')" prop="entryDate">
                    <DatePicker v-model="data.entryDate" type="date" class="full-width" @on-change="data.entryDate=$event"></DatePicker>
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
                    url:'jfcloud/jf-cloud-platform/security/employee/update',
                    method:'put'
                },
                formRules:{
                    userId:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    employeeNo:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    employeeType:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    employeeStatus:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                    employeeJob:[
                        {required:true,message:vm.$t('validator.notEmpty')}
                    ],
                }
            }
        }
    }
</script>