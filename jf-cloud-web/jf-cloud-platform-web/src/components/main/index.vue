<template>
    <Layout style="height: 100%" class="jf-main">
        <Sider hide-trigger collapsible class="left-sider"  v-model="collapsed" 
            :width="200" :collapsed-width="64">
            <SiderMenu accordion ref="siderMenu" :collapsed="collapsed"  :menu-list="menuList" @on-select="turnToPage">
                <!-- 需要放在菜单上面的内容，如Logo，写在sider-menu标签内部，如下 -->
                <div class="logo-con">
                    <img v-show="!collapsed" :src="maxLogo" key="max-logo" />
                    <img v-show="collapsed" :src="minLogo" key="min-logo" />
                </div>
            </SiderMenu>
        </Sider>
        <Layout>
            <Header class="header-con">
                <HeaderBar :collapsed="collapsed" @on-collapsed-change="collapsedSider"> 
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
import { mapMutations, mapActions, mapGetters } from 'vuex'
import minLogo from '@/assets/images/logo-min.jpg'
import maxLogo from '@/assets/images/logo.png'
import routes from '@/router/routes'
export default {
    components:{
        HeaderBar,
        SiderMenu
    },
    data(){
        return {
            //菜单是否折叠
            collapsed:false,
            minLogo,
            maxLogo,
        }
    },
    computed: {
        menuList () {
            let router=routes.filter(item=>{
                return item.name=='main';
            });
            return router[0].children;
        }
    },
    methods:{
        ...mapMutations([
            'setBreadCrumb'
        ]),
        
        /**
         * 左侧菜单折叠
         */
        collapsedSider(){
            this.collapsed=!this.collapsed;
        },
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