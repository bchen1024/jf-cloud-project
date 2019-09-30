<template>
    <div v-if="displayName">
        <template v-if="userId==displayName">
            <Spin v-if="loading">
                <Icon type='ios-loading' size="18" class="jf-spin-icon-load"/>
                {{displayName}}
            </Spin>
        </template>
        <template v-else>
            <Tooltip  :content="displayName" transfer>
                <Avatar icon="ios-person" v-if="showPhoto"/>
                <a v-if="viewDetail">
                    {{displayName}}
                </a>
                <label v-else>
                    {{displayName}}
                </label>
            </Tooltip>
        </template>
    </div>
</template>
<script>
export default {
    name:'JFUser',
    props:{
        userId:{
            type:Number
        },
        showPhoto:{
            type:Boolean,
            default:true
        },
        viewDetail:{
            type:Boolean,
            default:true
        },
        join:{
            type:String,
            default:'/'
        }
    },
    data(){
        return {
            loading:false
        }
    },
    computed:{
        userInfo(){
            return this.$store.state.user.userCache[this.userId];
        },
        displayName(){
            let vm=this,names=[];
            if(vm.userInfo){
                this.loading=false;
                if(vm.userInfo.userCn){
                    names.push(vm.userInfo.userCn);
                }
                if(vm.userInfo.userEn){
                    names.push(vm.userInfo.userEn);
                }
            }
            if(names.length>0){
                return names.join(vm.join);
            }else{
                this.loading=true;
                return this.userId;
            }
        }
    }
}
</script>
<style lang="less">
    .jf-spin-icon-load{
        animation: ani-demo-spin 1s linear infinite;
    }
    @keyframes ani-demo-spin {
        from { transform: rotate(0deg);}
        50%  { transform: rotate(180deg);}
        to   { transform: rotate(360deg);}
    }
</style>