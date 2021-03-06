export default{
    data(){
        return {
            gridId:'jfGrid',
            formId:'editForm',
            showEdit:false,
            formData:{}
        }
    },
    methods:{
        /**
         * 打开边框弹框
         * @param {} data 
         */
        openEdit(data){
            if(data){
                this.formData=data;
            }else{
                this.formData={};
            }
            this.showEdit=true;
        },
        /**
         * 保存回调刷新表格
         */
        gridSearch(formData){
            let grid=this.$refs[this.gridId];
            if(grid){
                grid.search();
            }
        }
    }
}