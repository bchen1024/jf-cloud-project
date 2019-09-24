<template>
    <div class="header-bar">
        <a @click="collapsedSider" type="text" :class="['sider-trigger-a', collapsed ? 'collapsed' : '']">
            <Icon type="md-menu" size="24"></Icon>
        </a>
        <Breadcrumb class="jf-bread-crumb">
            <BreadcrumbItem v-for="item in breadCrumbList" :to="item.path" :key="`bread-crumb-${item.name}`">
                <Icon :type="item.meta.icon" v-if="item.meta && item.meta.icon"></Icon>
                {{ $t($util.showTitle(item)) }}
            </BreadcrumbItem>
        </Breadcrumb>
        <div class="custom-content-con">
            <slot></slot>
        </div>
    </div>
</template>
<script>
import { mapMutations, mapActions, mapGetters } from 'vuex'
export default {
    name: 'HeaderBar',
    data(){
        return{
        }
    },
    computed: {
        breadCrumbList () {
            return this.$store.state.menu.breadCrumbList;
        },
        collapsed(){
            return this.$store.state.menu.collapsed;
        }
    },
    methods:{
        ...mapMutations([
            'collapsedSider'            
        ])
    }
}
</script>
<style lang="less">
    .header-bar{
        width: 100%;
        height: 100%;
        position: relative;
        .custom-content-con{
            float: right;
            height: auto;
            padding-right: 20px;
            line-height: 64px;
            & > *{
            float: right;
            }
        }
    }
    .trans{
        transition: transform .2s ease;
    }

    .sider-trigger-a{
        color: #5c6b77;
        i{
            .trans;
            vertical-align: middle;
        }
        &.collapsed i{
            transform: rotateZ(90deg);
            .trans;
        }
    }
    .jf-bread-crumb{
        display: inline-block;
        vertical-align: top;
        margin-left: 24px;
    }
</style>