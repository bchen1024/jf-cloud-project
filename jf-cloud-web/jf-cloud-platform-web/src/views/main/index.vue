<template>
    <Layout style="height: 100%" class="jf-main">
        <Sider hide-trigger collapsible class="left-sider" v-model="collapsed" 
            :width="200" :collapsed-width="64">
            <SiderMenu accordion ref="siderMenu" :menu-list="menuList" @on-select="turnToPage">
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
import './index.less'
import HeaderBar from './components/header-bar/index.vue'
import SiderMenu from './components/sider-menu/index.vue'
import App from './components/app/index.vue'
import Language from './components/language/index.vue'
import FullScreen from './components/fullscreen/index.vue'
import Personal from './components/personal/index.vue'
import { mapMutations, mapActions, mapGetters } from 'vuex'
import routes from '@/router/routes'
export default {
    components:{
        HeaderBar,
        SiderMenu,
        Language,
        App,
        FullScreen,
        Personal
    },
    data(){
        return {
        }
    },
    computed: {
        menuList () {
            let router=routes.filter(item=>{
                return item.name=='main';
            });
            let menus=router[0].children.filter(item=>{
                return !item.meta || !item.meta.hideMenu;
            });
            return menus;
        },
        collapsed(){
            return this.$store.state.menu.collapsed;
        }
    },
    methods:{
        ...mapMutations([
            'setBreadCrumb'            
        ]),
        turnToPage (route) {
            if(route==this.$route.name || route.name==this.$route.name){
                return;
            }
            let { name, params, query } = {}
            if (typeof route === 'string') name = route
            else {
                name = route.name
                params = route.params
                query = route.query
            }
            if (name.indexOf('isTurnByHref_') > -1) {
                window.open(name.split('_')[1])
                return
            }
            this.$router.push({
                name,
                params,
                query
            });
        },
    },
    watch: {
        '$route' (newRoute) {
            this.setBreadCrumb(newRoute);
        }
    },
    mounted () {
        this.setBreadCrumb(this.$route);
    }
}
</script>