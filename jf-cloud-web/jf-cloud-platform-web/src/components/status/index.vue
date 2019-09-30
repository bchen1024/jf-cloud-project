<template>
    <Tag :color="color" v-if="value">{{title}}</Tag>
</template>
<script>
export default {
    name:'JFStatus',
    props:{
        value:{
            type:String
        },
        type:{
            type:String,
            validator:(value)=>{
                return ['applyStatus', 'userStatus'].includes(value);
            }
        }
    },
    computed:{
        color(){
            let vm=this;
            let color='';
            if(vm.value=='Y'){
                color='success';
            }else if(vm.value=='N' || vm.value=='D'){
                color='error';
            }else if(vm.value=='L'){
                color='warning';
            }
            return color;
        },
        title(){
            let vm=this;
            let title='';
            if(vm.type=='applyStatus'){
                if(vm.value=='Y'){
                    title=vm.$t('canApply');
                }else if(vm.value=='N'){
                    title=vm.$t('noApply');
                }
            }else if(vm.type=='userStatus'){
                if(vm.value=='Y'){
                    title=vm.$t('normal');
                }else if(vm.value=='L'){
                    title=vm.$t('lock');
                }else if(vm.value=='D'){
                    title=vm.$t('delete');
                }
            }
            return title;
        }
    }
}
</script>