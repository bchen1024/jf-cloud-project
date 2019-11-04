<template>
    <Cascader :data="orgList" v-model="innerValue" change-on-select></Cascader>
</template>
<script>
export default {
    name:'JFOrgCascader',
    props:{
        value:{
            type:[Array,Number]
        }
    },
    computed:{
        orgList(){
            return Object.assign([],[],this.$store.state.cache.orgCacheList);
        }
    },
    data(){
        return {
            innerValue:[]
        }
    },
    created(){
        this.$store.dispatch('loadOrgList');
        this.valueChange(this.value);
    },
    methods:{
        valueChange(newValue){
            if(typeof newValue ==='number'){
                let org=this.$store.state.cache.orgMap[newValue];
                let parentIds=[];
                if(org && org.parentIds){
                    parentIds=parentIds.concat(org.parentIds);
                    parentIds.push(newValue);
                }else{
                    parentIds=[newValue];
                }
                this.innerValue=parentIds || [];
            }else if(newValue instanceof Array){
                this.innerValue=newValue;
            }else{
                this.innerValue=[];
            }
        }
    },
    watch: {
        value(newValue){
            this.valueChange(newValue);
        },
        innerValue(newVal) {
            let value=null;
            if(newVal && newVal.length>0){
                value=newVal[newVal.length-1];
            }
            this.$emit('input', value)
        }
    }
}
</script>