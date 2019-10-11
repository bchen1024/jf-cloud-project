<template>
    <Submenu :name="parentItem.name">
        <template slot="title">
            <Icon v-if="parentItem.meta && parentItem.meta.icon" :type="parentItem.meta.icon"/><span>{{ $t($util.showTitle(parentItem)) }}</span>
        </template>
        <template v-for="item in children">
            <SiderMenuItem v-if="checkMenu(item.children)" :key="`menu-${item.name}`" :parent-item="item"></SiderMenuItem>
            <menu-item v-else-if="$util.checkPermission((item.meta && item.meta.permissionCode),$store.state.permission.permissionList)" :name="item.name" :key="`menu-${item.name}`">
                <Icon v-if="item.meta && item.meta.icon" :type="item.meta.icon"/><span>{{ $t($util.showTitle(item)) }}</span>
            </menu-item>
        </template>
    </Submenu>
</template>
<script>
export default {
    name:'SiderMenuItem',
    props: {
        parentItem: {
            type: Object,
            default: () => {}
        }
    },
    computed: {
        parentName () {
            return this.parentItem.name
        },
        children () {
            return this.parentItem.children
        }
    },
    methods:{
        checkMenu(children){
            if(!children || children.length==0){
                return false;
            }else{
                let filterMenu=children.filter(v=>util.checkPermission((v.meta && v.meta.permissionCode),this));
                if(filterMenu.length>0){
                return true;
                }
                return false;
            }
        }
    }
}
</script>