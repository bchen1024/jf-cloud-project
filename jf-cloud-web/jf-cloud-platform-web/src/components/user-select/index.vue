<template>
    <div>
        <Select
            :value="innerValue"
            icon="ios-search"
            clearable filterable transfer :multiple="multiple"
            @on-change="onChange">
            <Avatar slot="prefix" icon="ios-person" size="small"/>
            <Option v-for="user in userCacheList" :value="user.userId" :key="user.userId" :label="$util.getUserName(user)">
                <Avatar icon="ios-person" size="small"/>
                <span>{{$util.getUserName(user)}}</span>
                <span class="float-right margin-right-12 margin-top-4">{{user.userId}}</span>
            </Option>
        </Select>
    </div>
</template>
<script>
export default {
    props:{
        value:{
            type:[Number,String,Array]
        },
        multiple:{
            type:Boolean,
            default:false
        }
    },
    data(){
        return {
           innerValue:this.value
        }
    },
    computed:{
        userCacheList(){
            return this.$store.state.user.userCache || {}
        }
    },
    created(){
        if(!this.$store.state.user.userSelectInit){
            this.loadUserSelect();
            this.$store.dispatch('setUserSelectInit',true);
        }
    },
    methods:{
        onChange(value){
            this.innerValue=value;
        },
        loadUserSelect(){
            let vm=this;
            let data={};
            vm.$http.post('jfcloud/jf-cloud-platform/security/user/select',data).then(result=>{
                vm.$store.dispatch('setUserCache',result.data);
            });
            
        }
    },
    watch: {
        value(newValue){
            this.innerValue=newValue;
            let user=this.userCacheList[newValue];
            if(!user){
                this.$store.dispatch('loadUser',newValue);
            }
        },
        innerValue(newVal) {
            this.$emit('input', newVal)
        }
    }
}
</script>