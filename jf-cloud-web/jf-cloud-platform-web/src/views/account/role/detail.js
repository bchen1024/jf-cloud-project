export default{
    data(){
        let vm=this;
        return {
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/single'
                },
                editPermission:'role$save',
                autoLoad:vm.autoLoad(vm.detailId),
                userFields:['roleOwner'],
                items:[
                    {cols:[
                        {key:'roleCode'},
                        {key:'roleName'},
                    ]},
                    {cols:[
                        {key:'roleOwner',type:'user'},
                        {key:'applyStatus',type:'status'}
                    ]},
                    
                    {key:'roleDesc',desc:true}
                ]
            }
        }
    }
}