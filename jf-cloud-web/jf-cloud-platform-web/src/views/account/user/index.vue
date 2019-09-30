<template>
    <JFGrid :ref="gridId" :gridOp="gridOp">
        <template slot="grid-search-toolbar">
            <Button icon="md-add" type="primary" @click="openEdit()">
                {{$t('createUser')}}
            </Button>
        </template>
        <EditUser slot="grid-drawer" :formId="formId" :formKey="formKey"
            :visible.sync="showEdit" 
            :formData="formData"
            @saveCallback="saveCallback"/>
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
                        {title:vm.$t('edit'),click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('delete'),show:(row)=>{
                            if(row.userStatus!='D'){
                                return true;
                            }
                            return false;
                        }},
                        {title:(row)=>{
                            if(row.userStatus=='L'){
                                return vm.$t('unlock');
                            }if(row.userStatus=='D'){
                                return vm.$t('enable');
                            }
                            return vm.$t('lock');
                        },click:(params)=>{
                            alert(params.row.userId);
                        }},
                        {title:vm.$t('detail'),gridDetail:true},
                    ],
                    columns:[
                        {key:'userNo',width:120,condition:true},
                        {key:'userCn',width:100,condition:true},
                        {key:'userEn',width:100,condition:true},
                        {key:'mobile',width:120,condition:true,render: (h, params) => {
                            if(params.row.mobileValidated=='Y'){
                                return h('div',{},[
                                    h('Icon',{props:{type:'md-checkmark',color:'#19be6b',size:18}}),
                                    h('label',params.row.mobile)
                                ]);
                            }
                            return h('label',params.row.mobile);
                        }},
                        {key:'email',condition:true,render: (h, params) => {
                            if(params.row.emailValidated=='Y'){
                                return h('div',{},[
                                    h('Icon',{props:{type:'md-checkmark',color:'#19be6b',size:18}}),
                                    h('label',params.row.email)
                                ]);
                            }
                            return h('label',params.row.email);
                        }},
                        {key:'userType',width:90,condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('employee')},
                               {value:'2',label:vm.$t('register')}
                            ]
                        },render: (h, params) => {
                            if(params.row.userType=='1'){
                                return h('div',vm.$t('employee'));
                            }else if(params.row.userType=='2'){
                                return h('div',vm.$t('register'));
                            }
                            return '';
                        }},
                        {key:'userStatus',width:90,format:'userStatus',condition:{
                           type:'radio',items:[
                               {value:'Y',label:vm.$t('normal')},
                               {value:'L',label:vm.$t('lock')},
                               {value:'D',label:vm.$t('delete')}
                            ]
                        }},
                        {key:'passwordUpdateTime',width:160},
                        {key:'lockTime',width:160}
                    ]
                }
            }
        }
    }
}
</script>