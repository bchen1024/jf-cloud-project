export default{
    data(){
        let vm=this,query=vm.$route.query,id=query.id;
        return{
            roleUsersId:'roleUsers',
            roleUserGrid:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/user/page',
                    defaultParams:{roleId:id,appCode:vm.$store.state.app.appInfo.appCode},
                    autoLoad:vm.autoLoad('roleUsers'),
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/security/role/user/delete'
                },
                table:{
                    pkId:'id',
                    buttons:[
                        {title:vm.$t('delete'),gridDelete:true}
                    ],
                    permissionCode:'roleUsers$delete',
                    logSetting:{module:'Role User'},
                    showAuditUpdate:true,
                    columns:[
                        {key:'grantStatus',width:150,render:(h,params)=>{
                            return vm.$util.grantStatus(vm,h,params.row.beginDate,params.row.endDate);
                        },condition:{
                            type:'radio',items:[
                               {value:'1',label:vm.$t('status.grantStatus.1')},
                               {value:'2',label:vm.$t('status.grantStatus.2')},
                               {value:'3',label:vm.$t('status.grantStatus.3')},
                               {value:'4',label:vm.$t('status.grantStatus.4')}
                            ]
                        }},
                        {key:'userId',format:'user',title:'userName',condition:{type:'string',key:'userName'}},
                        {key:'beginDate',width:150,condition:{type:'datePicker',format:'yyyy-MM-dd',width:200}},
                        {key:'endDate',width:150,condition:{type:'datePicker',format:'yyyy-MM-dd',width:200}}
                    ]
                }
            },
            showAddUser:false,
            addUserFormData:{roleId:id,appCode:vm.$store.state.app.appInfo.appCode},
        }
    },
    methods:{
        loadRoleUsers(){
            let roleUserGrid=this.$refs.roleUserGrid;
            if(roleUserGrid){
                roleUserGrid.search();
            }
        },
    }
}