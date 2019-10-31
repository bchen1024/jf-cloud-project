export default{
    data(){
        let vm=this;
        return {
            detailOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/group/single'
                },
                editPermission:'group$save',
                autoLoad:vm.autoLoad(vm.detailId),
                userFields:['groupOwner'],
                items:[
                    {cols:[
                        {key:'groupCode'},
                        {key:'groupName'},
                    ]},
                    {cols:[
                        {key:'groupOwner',type:'user'},
                        {key:'applyStatus',type:'status'},
                    ]},
                    {key:'groupDesc',desc:true}
                ]
            }
        }
    }
}