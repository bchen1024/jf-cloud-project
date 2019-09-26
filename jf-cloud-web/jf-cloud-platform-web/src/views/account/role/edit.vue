<template>
    <Drawer
            :title="title"
            v-model="show"
            width="480"
            :visible.sync="show"
            :mask-closable="false"
        >
            <Form ref='roleForm' :model="data" :rules="formRules">
                <FormItem :label="$t('roleCode')" label-position="top" prop="roleCode" required>
                    <Input v-model="data.roleCode" />
                </FormItem>
                <FormItem :label="$t('roleName')" label-position="top" required>
                    <Input v-model="data.roleName"/>
                </FormItem>
                <FormItem :label="$t('roleOwner')" label-position="top" required>
                    <Input v-model="data.roleOwner"/>
                </FormItem>
                <FormItem :label="$t('applyStatus')" label-position="top" required>
                    <RadioGroup v-model="data.applyStatus">
                        <Radio  label="Y">{{$t('canApply')}}</Radio>
                        <Radio  label="N">{{$t('noApply')}}</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem :label="$t('roleDesc')" label-position="top">
                    <Input type="textarea" v-model="data.roleDesc" :rows="4" :maxlength="200" />
                </FormItem>
            </Form>
            <div class="demo-drawer-footer">
                <Button style="margin-right: 8px" @click="show = false">Cancel</Button>
                <Button type="primary" @click="submit">Submit</Button>
            </div>
        </Drawer>  
</template>
<script>
    export default {
        props: {
            visible: Boolean,
            formData:{
                type:Object,
                default:{}
            }
        },
        data () {
            let vm=this;
            return {
                data:{},
                show: false,
                title:vm.$t('createRole'),
                formRules:{
                    roleCode:[
                        {required:true}
                    ]
                }
            }
        },
        watch: {
            visible: function (newval) {
                if (newval) {
                    this.$refs.roleForm.resetFields();
                    this.show = newval;
                    this.data=Object.assign({},this.formData);
                    if(this.data.roleId){
                        this.title=this.$t('editRole');
                    }
                }
            },
            show: function (newval) {
                this.$emit('update:visible', newval);
            }
        },
        methods:{
            submit(){
                this.$refs.roleForm.validate((valid) => {
                    if (valid) {
                        this.$emit('submitCallback',this.formData);
                    }else{
                        alert(valid);
                    }
                });
            }
        }
    }
</script>