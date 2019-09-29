<template>
    <div v-if="displayName">
        <Avatar icon="ios-person" v-if="showPhoto"/>
        <Tooltip  :content="displayName" transfer>
            <a v-if="viewDetail">
                {{displayName}}
            </a>
            <label v-else>
                {{displayName}}
            </label>
        </Tooltip>
    </div>
</template>
<script>
export default {
    name:'JFUser',
    props:{
        userInfo:{
            type:Object,
            default:{}
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
        }
    },
    computed:{
        displayName(){
            let vm=this,names=[];
            if(vm.userInfo){
                if(vm.userInfo.userCn){
                    names.push(vm.userInfo.userCn);
                }
                if(vm.userInfo.userEn){
                    names.push(vm.userInfo.userEn);
                }
            }
            if(names.length>0){
                return names.join(vm.join);
            }
            return '';
        }
    }
}
</script>