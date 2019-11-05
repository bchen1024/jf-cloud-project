<template>
    <Tabs  @on-click="load" v-if="$store.state.user.userInfo && $store.state.user.userInfo.userId">
        <TabPane :label="$t('baseInfo')" name="baseInfo" >
            <BaseInfo/>
        </TabPane>
        <TabPane :label="$t('userPhoto')" name="userPhoto" >
            
        </TabPane>
        <TabPane :label="$t('updatePassword')" name="updatePassword" >
            <UpdatePassword/>
        </TabPane>
        <TabPane :label="$t('bindEmailMobile')" name="bindEmailMobile" >
           
        </TabPane>
        <TabPane :label="$t('myApp')" name="myApp" >
             <MyApps/>
        </TabPane>
        <TabPane :label="$t('myRole')" name="myRole" >
            
        </TabPane>
        <TabPane :label="$t('myPermission')" name="myPermission" >
            
        </TabPane>
        <TabPane :label="$t('mySetting')" name="mySetting" >
            
        </TabPane>
        <TabPane :label="$t('myLogs')" name="myLogs" >
            
        </TabPane>
    </Tabs>
</template>
<script>
import BaseInfo from './baseInfo.vue';
import UpdatePassword from './updatePassword.vue';
import MyApps from './myApps.vue';
export default {
    components:{
        BaseInfo,UpdatePassword,MyApps
    },
    data(){
        return {

        }
    },
    methods:{
        load(){

        }
    }
}
</script>