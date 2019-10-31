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
        }
    },
    data(){
        return {
            data:{},
            width:480,
            title:this.$t('create'),
            loading:false,
            drawerShow: false,
            saveOp:{},
            styles: {
                height: 'calc(100% - 55px)',
                overflow: 'auto',
                paddingBottom: '53px',
                position: 'static'
            },
            isCreate:true
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
            let id=this.data[this.formKey];
            if(id){
                this.isCreate=false;
                this.title=this.$t('edit');
            }else{
                this.isCreate=true;
                this.title=this.$t('create');
            }
            if(this.showCallback){
                this.showCallback();
            }
        },
        /**
         * 保存表单
         */
        saveForm(){
            let vm=this;
            this.$refs[vm.formId].validate((valid) => {
                if (valid) {
                    vm.loading=true;
                    let saveOp=Object.assign({},{
                        method:'put',
                        data:vm.data,
                        headers:{op:'save'}
                    },vm.saveOp);
                    vm.$http(saveOp).then(result=>{
                        vm.drawerShow=false;
                        vm.$emit('saveCallback',vm.data);
                    }).catch(error=>{
                    }).then(()=>{
                        vm.loading=false;
                    });
                    
                }
            });
        }
    }
}