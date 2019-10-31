<template>
    <Select
        :value="innerValue"
        icon="ios-search"
        clearable filterable transfer :multiple="multiple" :loading="loading" remote
        @on-change="onChange" 
        :remote-method="loadUserSelect">
        <Avatar slot="prefix" icon="ios-person" size="small"/>
        <Option v-for="user in userCacheList" :value="user.userId" :key="user.userId" :label="$util.getUserName(user)">
            <Avatar icon="ios-person" size="small"/>
            <span>{{$util.getUserName(user)}}</span>
            <span class="float-right margin-right-12 margin-top-4">{{user.userId}}</span>
        </Option>
    </Select>
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
           innerValue:this.value,
           loading:false,
           userSelect: null
        }
    },
    computed:{
        userCacheList(){
            return this.userSelect || this.$store.state.user.userCache || {}
        }
    },
    created(){
        if(!this.$store.state.user.userSelectInit){
            this.loadUserSelect();
        }
    },
    methods:{
        onChange(value){
            this.innerValue=value;
        },
        loadUserSelect(keyword){
            let vm=this;
            let data={};
            vm.userSelect=null;
            if(keyword){
                data.keyword=keyword;
            }
            vm.loading=true;
            vm.$http.post('jfcloud/jf-cloud-platform/security/user/select',data).then(result=>{
                let userList=result.data;
                if(userList && userList.length){
                    let userCache={};
                    userList.forEach(user => {
                        userCache[user.userId]=user;
                    });
                    vm.userSelect=userCache;
                }
                vm.$store.dispatch('setUserCache',userList);
            }).catch(error=>{}).then(()=>{
                vm.loading=false;
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