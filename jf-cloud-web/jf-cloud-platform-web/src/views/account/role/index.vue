<template>
    <div>
        <JFGrid :gridOp="gridOp">
            <template slot="grid-search-toolbar">
                <Button icon="md-add" type="primary" @click="editRole">
                    {{$t('createRole')}}
                </Button>
                <Dropdown>
                    <Button type="info">
                        导入导出
                        <Icon type="ios-arrow-down"></Icon>
                    </Button>
                    <DropdownMenu slot="list">
                        <DropdownItem>导出角色</DropdownItem>
                        <DropdownItem>导入角色</DropdownItem>
                        <DropdownItem>下载模板</DropdownItem>
                    </DropdownMenu>
                </Dropdown>
                <Button>
                    {{$t('operationLog')}}
                </Button>
            </template>
        </JFGrid>
        <EditRole :visible.sync="showEditRole" :formData="formData" @submitCallback="submitCallback"/>
    </div>
    
</template>
<script>
import EditRole from './edit.vue';
export default {
    components:{
        EditRole
    },
    data(){
        let vm=this;
        return {
            showEditRole:false,
            formData:{},
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/security/role/page',
                    defaultParams:{appCode:'jf-cloud-platform'}
                },
                table:{
                    columns:[
                        {key:'roleCode',width:120,condition:true},
                        {key:'roleName',width:150,condition:true},
                        {key:'roleOwner',width:150,condition:{}},
                        {key:'applyStatus',width:120,condition:{
                           type:'radio',items:[{value:'Y',label:vm.$t('canApply')},{value:'N',label:vm.$t('noApply')}]
                        }},
                        {key:'roleDesc',condition:true},
                        {key:'operation',width:100,align:'center',fixed:'right', render: (h, params) => {
                            return h('Dropdown',{props:{transfer:true}}, [
                                h('Icon',{props:{type:'ios-more',size:20}}),
                                h('DropdownMenu',{slot:'list'},[
                                    h('DropdownItem',{nativeOn:{
                                        click:(name)=>{
                                            vm.editRole(params.row);
                                        }
                                    }},vm.$t('edit')),
                                    h('DropdownItem',{},vm.$t('delete')),
                                    h('DropdownItem',{},vm.$t('detail')),
                                    h('DropdownItem',{},vm.$t('roleUsers')),
                                    h('DropdownItem',{},vm.$t('rolePermission')),
                                    h('DropdownItem',{},vm.$t('roleGroups'))
                                    
                                ])
                            ]);
                        }}
                    ]
                }
            }
        }
    },
    methods:{
        editRole(data){
            this.showEditRole=true;
            if(data){
                this.formData=data;
            }else{
                this.formData={applyStatus:'N'};
            }
        },
        submitCallback(formData){
            alert(JSON.stringify(formData));
        }
    }
}
</script>