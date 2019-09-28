<template>
    <div>
        <JFGrid ref="userGrid" :gridOp="gridOp">
            <template slot="grid-search-toolbar">
                <Button icon="md-add" type="primary" @click="userEdit()">
                    {{$t('createUser')}}
                </Button>
            </template>
            <EditUser slot="grid-drawer" formId="userForm"   formKey="userId"
                :visible.sync="showEditUser" 
                :formData="formData"
                @saveCallback="saveCallback"/>
        </JFGrid>
    </div>
</template>
<script>
import EditUser from './edit.vue';
export default {
    components:{
       EditUser
    },
    data(){
        let vm=this;
        return {
            showEditUser:false,
            formData:{},
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
                    logSetting:{module:'User'},
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
                        {key:'userStatus',width:90,condition:{
                           type:'radio',items:[
                               {value:'Y',label:vm.$t('normal')},
                               {value:'L',label:vm.$t('lock')},
                               {value:'D',label:vm.$t('delete')}
                            ]
                        },render: (h, params) => {
                            if(params.row.userStatus=='Y'){
                                return h('Tag',{props:{color:'success'}},vm.$t('normal'));
                            }else if(params.row.userStatus=='L'){
                                return h('Tag',{props:{color:'warning'}},vm.$t('lock'));
                            }else if(params.row.userStatus=='D'){
                                return h('Tag',{props:{color:'error'}},vm.$t('delete'));
                            }
                            return '';
                        }},
                        {key:'passwordUpdateTime',width:160},
                        {key:'lockTime',width:160},
                        {key:'operation',width:100,align:'center',fixed:'right', render: (h, params) => {
                            return h('Dropdown',{props:{transfer:true}}, [
                                h('Icon',{props:{type:'ios-more',size:20}}),
                                h('DropdownMenu',{slot:'list'},[
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.userEdit(params.row);
                                        }
                                    }},vm.$t('edit')),
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                           
                                        }
                                    }},vm.$t('lock'))                                    
                                ])
                            ]);
                        }}
                    ]
                }
            }
        }
    },
    methods:{
        //创建、编辑账号
        userEdit(data){
            if(data){
                this.formData=data;
            }else{
                this.formData={};
            }
            this.showEditUser=true;
        },
        /**
         * 保存回调刷新表格
         */
        saveCallback(formData){
            this.$refs.userGrid.search();
        }
    }
}
</script>