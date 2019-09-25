<template>
    <JFGrid :gridOp="gridOp">
        <template slot="toolbar">
            <Button icon="md-add" type="primary">
                {{$t('createRole')}}
            </Button>
        </template>
    </JFGrid>
</template>
<script>
export default {
    data(){
        let vm=this;
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/page'
                },
                table:{
                    columns:[
                        {key:'roleCode',width:120,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150},
                        {key:'applyStatus',width:120,condition:{
                           type:'radio',items:[{value:'Y',label:vm.$t('canApply')},{value:'N',label:vm.$t('noApply')}]
                        }},
                        {key:'roleDesc',condition:true},
                        {key:'operation',width:100,align:'center',fixed:'right', render: (h, params) => {
                            return h('Dropdown',{props:{transfer:true}}, [
                                h('Icon',{props:{type:'ios-more'}}),
                                h('DropdownMenu',{slot:'list'},[
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            alert(params.row.roleCode);
                                        }
                                    }},vm.$t('edit')),
                                     h('DropdownItem',{},vm.$t('delete'))
                                ])
                            ]);
                        }}
                    ]
                }
            }
        }
    },
    methods:{
        show(row,index,column){
           console.info(index);
        }
    }
}
</script>