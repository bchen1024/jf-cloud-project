<template>
    <Tag :color="color" v-if="value && color">{{title}}</Tag>
    <div v-else>{{title}}</div>
</template>
<script>
export default {
    name:'JFStatus',
    props:{
        value:{
            type:String
        },
        type:{
            type:String
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
            if(vm.value){
                title=vm.$t('status.'+vm.type+'.'+vm.value);
            }
            return title;
        }
    }
}
</script>