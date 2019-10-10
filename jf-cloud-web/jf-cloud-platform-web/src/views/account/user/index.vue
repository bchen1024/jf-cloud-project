<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()" v-permission="'user$save'">
                {{$t('createUser')}}
            </Button>
        </template>
        <EditUser slot="grid-drawer" :formId="formId" :formKey="formKey"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="gridSearch"/>
    </JFGrid>
</template>
<script>
import EditUser from './edit.vue';
import curdGrid from '@/mixins/curdGrid';
export default {
    mixins:[curdGrid],
    components:{
       EditUser
    },
    data(){
        let vm=this;
        return {
            formKey:'userId',
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/user/page',
                    advancedQueryFields:[
                        {key:'mobileValidated',type:'radio',items:[
                            {value:'Y',label:vm.$t('validated')},
                            {value:'N',label:vm.$t('noValidated')}
                        ]},
                        {key:'emailValidated',type:'radio',items:[
                            {value:'Y',label:vm.$t('validated')},
                            {value:'N',label:vm.$t('noValidated')}
                        ]}
                    ]
                },
                table:{
                    pkId:'userId',
                    logSetting:{module:'User'},
                    detail:{name:'userDetail'},
                    buttons:[
                        {title:vm.$t('edit'),permissionCode:'user$save',click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),permissionCode:'user$updateStatus',show:(row)=>{
                            if(row.userStatus!='D'){
                                return true;
                            }
                            return false;
                        },click:(params)=>{
                            vm.updateUserStatus({
                                userId:params.row.userId,
                                userStatus:'D'
                            });
                        }},
                        {title:(row)=>{
                            if(row.userStatus=='L'){
                                return vm.$t('unlock');
                            }if(row.userStatus=='D'){
                                return vm.$t('enable');
                            }
                            return vm.$t('lock');
                        },click:(params)=>{
                            let userStatus='';
                            if(params.row.userStatus=='L' || params.row.userStatus=='D'){
                               userStatus='Y';
                            }if(params.row.userStatus=='Y'){
                                userStatus='L'
                            }
                            vm.updateUserStatus({
                                userId:params.row.userId,
                                userStatus:userStatus
                            });
                        },permissionCode:'user$updateStatus'},
                        {title:vm.$t('detail'),permissionCode:'user$single',gridDetail:true},
                    ],
                    columns:[
                        {key:'userNo',width:120,condition:true},
                        {key:'userCn',width:100,condition:true},
                        {key:'userEn',width:100,condition:true},
                        {key:'mobile',width:120,condition:true,render: (h, params) => {
                            if(params.row.mobile){
                                let props={};
                                if(params.row.mobileValidated=='Y'){
                                    props={type:'md-checkmark',color:'#19be6b',size:18}
                                }else{
                                    props={type:'md-close',color:'#ed4014',size:18};
                                }
                                return h('div',{},[
                                    h('Icon',{props:props}),
                                    h('label',params.row.mobile)
                                ]);
                            }
                            return '';
                        }},
                        {key:'email',condition:true,render: (h, params) => {
                            if(params.row.email){
                                let props={};
                                if(params.row.emailValidated=='Y'){
                                    props={type:'md-checkmark',color:'#19be6b',size:18}
                                }else{
                                    props={type:'md-close',color:'#ed4014',size:18};
                                }
                                return h('div',{},[
                                    h('Icon',{props:props}),
                                    h('label',params.row.email)
                                ]);
                            }
                            return '';
                        }},
                        {key:'userType',width:90,format:'type',condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('type.userType.1')},
                               {value:'2',label:vm.$t('type.userType.2')}
                            ]
                        }},
                        {key:'userStatus',width:90,format:'status',condition:{
                           type:'radio',items:[
                               {value:'Y',label:vm.$t('status.userStatus.Y')},
                               {value:'L',label:vm.$t('status.userStatus.L')},
                               {value:'D',label:vm.$t('status.userStatus.D')}
                            ]
                        }},
                        {key:'passwordUpdateTime',width:160},
                        {key:'lockTime',width:160}
                    ]
                }
            }
        }
    },
    methods:{
        /**
         * 更新用户状态
         */
        updateUserStatus(data){
            let vm=this;
            vm.$Message.loading({
                content: this.$t('updating'),
                duration: 0
            });
            vm.$http({
                method:'put',
                url:'jfcloud/jf-cloud-platform/security/user/updateStatus',
                data:data
            }).then(result=>{
                vm.$Message.destroy();
                //成功
                if(result && result.success){
                    vm.$Message.success(vm.$t('updateSuccessful'));
                    vm.gridSearch();
                }
            }).catch(error=>{
                vm.$Message.destroy();
                vm.$Message.error(vm.$util.handerError(error,vm));
            });
        }
    }
}
</script>