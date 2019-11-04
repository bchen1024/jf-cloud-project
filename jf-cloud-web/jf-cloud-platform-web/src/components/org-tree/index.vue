<template>
    <Tree ref="organizationTree" :empty-text="$t('noOrganizationData')" 
                :render="renderContent" :data="orgList" 
                @on-select-change="onSelectChange"></Tree>
</template>
<script>
export default {
    name:'JFOrgTree',
    computed:{
        orgList(){
            return Object.assign([],[],this.$store.state.cache.orgCacheList);
        }
    },
    created(){
        this.reload();
    },
    methods:{
        onSelectChange(nodes){
            let vm=this;
            vm.$emit('onSelectChange',nodes);
        },
        reload(reload=false){
            this.$store.dispatch('loadOrgList',reload);
        },
        getSelectedNodes(){
            return this.$refs.organizationTree.getSelectedNodes();
        },
        renderContent (h, { root, node, data }) {
            let icon='ios-paper-outline';
            if(data.children && data.children.length>0){
                icon='ios-folder-outline';
            }
            return h('span', [
                h('Icon', {
                    props: {type: icon,size:16},
                    style: {marginRight: '8px'}
                }),
                h('span',{class:'ivu-tree-title ' + (node.node.selected ?'ivu-tree-title-selected':''),
                    on: {
                        click: () => {
                            if (!node.node.selected){
                                this.$refs.organizationTree.handleSelect(node.nodeKey); //手动选择树节点
                            }
                        }
                    }
                }, data.title)
            ]);
        },
    }
}
</script>