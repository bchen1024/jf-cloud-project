<template>
    <Layout class="jf-layout-full">
        <Sider class="jf-layout-sider" :width="200">
            <JFOrgTree ref="orgTree" @onSelectChange="onSelectChange"/>
        </Sider>
        <Content class="jf-layout-content">
            <JFGrid :ref="gridId" :gridOp="gridOp" @seachBefore="seachBefore">
                <template slot="grid-search-toolbar">
                    <Button icon="md-add" type="primary" @click="openEdit()" v-permission="'post$save'">
                        {{$t('createPost')}}
                    </Button>
                </template>
                <EditPost slot="grid-drawer" :formId="formId" formKey="postId"
                    :visible.sync="showEdit" 
                    :formData="formData"
                    @saveCallback="gridSearch"/>
            </JFGrid>
        </Content>
    </Layout>
</template>
<script>
import EditPost from './editPost.vue';
import editMixins from '@/mixins/editMixins';
export default {
    mixins:[editMixins],
    components:{
       EditPost
    },
    data(){
        let vm=this;
        return {
            gridOp:{
                search:{
                    url:'jfcloud/jf-cloud-platform/config/post/page'
                },
                delete:{
                    url:'jfcloud/jf-cloud-platform/config/post/delete'
                },
                table:{
                    pkId:'postId',
                    logSetting:{module:'Post'},
                    detail:{name:'postDetail'},
                    buttons:[
                        {title:vm.$t('edit'),permissionCode:'post$save',click:(params)=>{
                            vm.openEdit(params.row);
                        }},
                        {title:vm.$t('detail'),permissionCode:'post$single',gridDetail:true},
                        {title:vm.$t('delete'),permissionCode:'post$delete',gridDelete:true},
                    ],
                    columns:[
                        {key:'postCn',width:180,condition:true},
                        {key:'postEn',width:180,condition:true},
                        {key:'postLevel',width:120},
                        {key:'orgId',title:'postOrg',render: (h, params) => {
                            if(params.row.orgId){
                                let title=vm.$util.getOrgName(vm,params.row.orgId,false);
                                if(title){
                                    return h('Tooltip',{props:{content:title,transfer:true,'max-width':400}},title);
                                }
                            }
                            return '';
                        }},
                        {key:'postDesc'}
                    ]
                }
            }
        }
    },
    methods:{
        seachBefore(params){
            let selected=this.$refs.orgTree.getSelectedNodes();
            if(selected && selected.length>0){
                params.orgId=selected[0].orgId;
            }else{
                params.orgId=null;
            }
        },
        onSelectChange(){
            this.$refs[this.gridId].search();
        }
    }
}
</script>