<template>
    <div v-if="displayName">
        <template v-if="userId==displayName">
            <Spin v-if="loading" style="text-align:left">
                <Icon type='ios-loading' size="18" class="jf-spin-icon-load"/>
                {{displayName}}
            </Spin>
        </template>
        <template v-else>
            <Tooltip  transfer>
                <Avatar :icon="userIcon" v-if="showPhoto" :style="'color:'+bgColor"/>
                <a v-if="viewDetail">
                    {{displayName}}
                </a>
                <label v-else>
                    {{displayName}}
                </label>
                <div slot="content">
                    <div v-if="userId">{{$t('userId')}}:{{userId}}</div>
                    <div v-if="displayName">{{$t('userName')}}:{{displayName}}</div>
                    <div v-if="userStatus">{{$t('userStatus')}}:{{userStatus}}</div>
                    <div v-if="userSex">{{$t('userSex')}}:{{userSex}}</div>
                </div>
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
            default:false
        },
        viewDetail:{
            type:Boolean,
            default:true
        },
        join:{
            type:String,
            default:'/'
        },
        autoLoad:{
            type:Boolean,
            default:false
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
        },
        bgColor(){
            let vm=this;
            let bgColor='';
            if(vm.userInfo.userStatus=='Y'){
                bgColor='#19be6b';
            }if(vm.userInfo.userStatus=='L'){
                bgColor='#ff9900';
            }if(vm.userInfo.userStatus=='D'){
                bgColor='#ed4014';
            }
            return bgColor;
        },
        userSex(){
            let vm=this;
            let userSex='';
            if(vm.userInfo.userSex=='1'){
                userSex=vm.$t('male')
            }if(vm.userInfo.userSex=='2'){
                userSex=vm.$t('female')
            }
            return userSex;
        },
        userStatus(){
            let vm=this;
            return vm.$util.getUserStatus(vm.userInfo.userStatus,vm);
        },
        userIcon(){
            let vm=this;
            let icon='ios-person';
            if(vm.userInfo.userSex=='1'){
                icon='ios-man';
            }else if(vm.userInfo.userSex=='2'){
                icon='ios-woman';
            }
            return icon;
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