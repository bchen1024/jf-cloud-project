<template>
    <Layout class="jf-main">
        <Sider hide-trigger collapsible :class="['left-sider',theme=='light'?'left-sider-light':'']" v-model="collapsed" 
            :width="200" :collapsed-width="64">
            <SiderMenu>
                <!-- 需要放在菜单上面的内容，如Logo，写在sider-menu标签内部，如下 -->
                <div class="logo-con">
                    {{collapsed?$t('logoMinTitle'):$t('logoTitle')}}
                </div>
            </SiderMenu>
        </Sider>
        <Layout>
            <Header class="header-con">
                <HeaderBar> 
                    <Personal/>
                    <App/>
                    <Language/>
                    <Theme/>
                    <FullScreen/>
                </HeaderBar>
            </Header>
            <Content class="main-content-con">
                <router-view/>
            </Content>
        </Layout>
    </Layout>
</template>
<script>
import HeaderBar from './components/header-bar/index.vue';
import SiderMenu from './components/sider-menu/index.vue';
import App from './components/app/index.vue';
import Language from './components/language/index.vue';
import Theme from './components/theme/index.vue';
import FullScreen from './components/fullscreen/index.vue';
import Personal from './components/personal/index.vue';
export default {
    components:{
        HeaderBar,
        SiderMenu,
        Language,
        Theme,
        App,
        FullScreen,
        Personal
    },
    computed: {
        collapsed(){
            return this.$store.state.menu.collapsed;
        },
        theme(){
            return this.$store.state.menu.theme;
        }
    }
}
</script>
<style lang="less">
    .jf-main{
        height:100%;
        .logo-con{
            height: 64px;
            text-align: center;
            line-height: 64px;
            color: #efefef;
            font-size: 20px;
            font-weight: bold;
        }
        .left-sider-light .logo-con{
            color:#515a6e;
        }
        .header-con{
            background: #fff;
            padding: 0 12px;
            width: 100%;
        }
        .main-layout-con{
            height: 100%;
            overflow: hidden;
            overflow-y: auto;
        }
        .main-content-con{
            height: ~"calc(100% - 60px)";
            overflow: hidden;
            padding: 12px;
            overflow-y: auto;
        }
        .tag-nav-wrapper{
            padding: 0;
            height:40px;
            background:#F0F0F0;
        }
        .content-wrapper{
            padding: 18px;
            height: ~"calc(100% - 80px)";
            overflow: auto;
        }
        .left-sider{
            overflow: hidden;
            border-right: 1px solid #e8eaec;
            .ivu-layout-sider-children{
            overflow-y: scroll;
            margin-right: -18px;
            }
        }
        .left-sider-light{
            background-color: #ffffff;
        }
        }
        .ivu-menu-item > i{
        margin-right: 12px !important;
        }
        .ivu-menu-submenu > .ivu-menu > .ivu-menu-item > i {
        margin-right: 8px !important;
        }
        .collased-menu-dropdown{
        width: 100%;
        margin: 0;
        line-height: normal;
        padding: 7px 0 6px 16px;
        clear: both;
        font-size: 12px !important;
        white-space: nowrap;
        list-style: none;
        cursor: pointer;
        &:hover{
            background: rgba(100, 100, 100, 0.1);
        }
        & * {
            color: #515a6e;
        }
        .ivu-menu-item > i{
            margin-right: 12px !important;
        }
        .ivu-menu-submenu > .ivu-menu > .ivu-menu-item > i {
            margin-right: 8px !important;
        }
        }

        .ivu-select-dropdown.ivu-dropdown-transfer{
        max-height: 400px;
        }

</style>