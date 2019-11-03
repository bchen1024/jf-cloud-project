<template>
    <div v-if="value">{{title}}</div>
</template>
<script>
export default {
    name:'JFType',
    props:{
        value:{
            type:[String,Number]
        },
        type:{
            type:String,
            required:true
        }
    },
    computed:{
        title(){
            let vm=this;
            if(!vm.value){
                return '';
            }
            let title=vm.$t('type.'+vm.type+'.'+vm.value);
            return title;
        }
    }
}
</script>