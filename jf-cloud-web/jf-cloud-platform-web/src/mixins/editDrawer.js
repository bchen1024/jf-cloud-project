export default{
    props: {
        visible: Boolean,
        formData:{
            type:Object,
            default:{}
        },
        formId:{
            type:String,
            required:true
        },
        formKey:{
            type:[String,Array],
            required:true
        },
        width:{
            type:Number,
            default:480
        }
    },
    data(){
        return {
            data:{},
            title:this.$t('create'),
            loading:false,
            drawerShow: false,
            saveOp:{}
        }
    },
    watch: {
        visible (val) {
            if (val) {
                this.drawerShow = val;
                this.initForm();
            }
        },
        drawerShow(val) {
            this.$emit('update:visible', val);
        }
    },
    methods:{
        cancelForm(){
            this.drawerShow=false
        },
        initForm(){
            this.$refs[this.formId].resetFields();
            this.data=Object.assign({},this.defaultValue,this.formData);
            if(this.data[this.formKey]){
                this.title=this.$t('edit');
            }else{
                this.title=this.$t('create');
            }
        },
        saveForm(){
            let vm=this;
            this.$refs[vm.formId].validate((valid) => {
                if (valid) {
                    vm.loading=true;
                    let saveOp=Object.assign({},{
                        method:'put',
                        data:vm.data
                    },vm.saveOp);
                    vm.$http(saveOp).then(result=>{
                        vm.loading=false;
                        if(result && result.success){
                            vm.drawerShow=false;
                            vm.$Message.success(vm.$t('saveSuccessful'));
                            vm.$emit('saveCallback',vm.data);
                        }else{
                            vm.$Message.error(vm.$t(result.code) || result.msg);
                        }
                    }).catch(error=>{
                        vm.loading=false;
                        let errorMsg='';
                        if(typeof error=='string'){
                            errorMsg=error;
                        }else if(error.code){
                            errorMsg=vm.$t(error.code);
                        }else{
                            errorMsg=error.msg || error.message;
                        }
                        vm.$Message.error(errorMsg);
                    });
                    
                }
            });
        }
    }
}