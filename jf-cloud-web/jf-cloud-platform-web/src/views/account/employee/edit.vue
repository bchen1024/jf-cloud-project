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
                <FormItem :label="$t('employeeNo')" label-position="top" prop="employeeNo">
                    <Input v-model="data.employeeNo" disabled/>
                </FormItem>
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
                <FormItem :label="$t('employeeParent')" label-position="top" prop="parentId">
                    <Input v-model="data.parentId" />
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
                    
                }
            }
        }
    }
</script>