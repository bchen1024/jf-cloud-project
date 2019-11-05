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
import editMixins from '@/mixins/editMixins';
export default {
    mixins:[editMixins],
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
                        {key:'mobileValidated',labelWidth:150,type:'radio',items:[
                            {value:'Y',label:vm.$t('validated')},
                            {value:'N',label:vm.$t('noValidated')}
                        ]},
                        {key:'emailValidated',labelWidth:150,type:'radio',items:[
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
                        {title:vm.$t('userApps'),permissionCode:'userApps$page',gridDetail:{tabId:'userApps'}},
                        {title:vm.$t('userRoles'),permissionCode:'userRoles$page',gridDetail:{tabId:'userRoles'}},
                        {title:vm.$t('userGroups'),permissionCode:'userGroups$page',gridDetail:{tabId:'userGroups'}},
                        {title:vm.$t('employeeDetail'),permissionCode:'employee$single',router:{name:'employeeDetail',query:(row)=>{
                            return {id:row.userId};
                        }},show:(row)=>{
                            return row.userType==1;
                        }},
                        {title:vm.$t('clearUserEnvCache'),permissionCode:'user$clearUserEnvCache',show:(row)=>{
                            return row.userEnvCache;
                        },click:(params)=>{
                            vm.clearUserEnvCache(params.row.userId);
                        }}
                    ],
                    columns:[
                        {key:'userId',width:110},
                        {key:'userNo',width:120,condition:true},
                        {key:'userCn',width:120,condition:true},
                        {key:'userEn',width:120,condition:true},
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
                        {key:'userType',width:120,format:'type',condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('type.userType.1')},
                               {value:'2',label:vm.$t('type.userType.2')}
                            ]
                        }},
                        {key:'userStatus',width:120,format:'status',condition:{
                           type:'radio',items:[
                               {value:'Y',label:vm.$t('status.userStatus.Y')},
                               {value:'L',label:vm.$t('status.userStatus.L')},
                               {value:'D',label:vm.$t('status.userStatus.D')}
                            ]
                        }},
                        {key:'userSex',width:110,format:'type',condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('type.userSex.1')},
                               {value:'2',label:vm.$t('type.userSex.2')}
                            ]
                        }}
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
            vm.$http({
                method:'put',
                url:'jfcloud/jf-cloud-platform/security/user/updateStatus',
                data:data,
                headers:{op:'update'}
            }).then(result=>{
                vm.gridSearch();
            })
        },
        /**
         * 清除用户环境缓存
         */
        clearUserEnvCache(userId){
            let vm=this;
            vm.$http.post('jfcloud/jf-cloud-platform/security/user/clearUserEnvCache',{id:userId},{
                headers:{op:'delete'}
            }).then(result=>{
                vm.gridSearch();
            });
        }
    }
}
</script>